package simulation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import simulation.initializers.DefaultInitializer;
import simulation.pedestrains.Pedestrain;
import simulation.space.Space;
import simulation.space.Wall;
import sun.management.snmp.AdaptorBootstrap;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    public Canvas canvas;
    private GraphicsContext graphicsContext;
    private Timeline timelineLoop;
    private DefaultInitializer defaultInitializer;
    @FXML
    public ComboBox<String> symulationType;
    @FXML
    public ComboBox<String> markTrajectory;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        symulationType.getItems().addAll("One group, one direction, no wall", "Two groups, opposite direction, no wall", "Two groups, opposite directions, wall", "Column barrier", "Tunnel barrier");
        symulationType.getSelectionModel().select("Two groups, opposite direction, no wall");

        markTrajectory.getItems().add("None");
        markTrajectory.getSelectionModel().select("None");
        for(int i=1; i<11; i++) {
            markTrajectory.getItems().add(""+i);
        }


        defaultInitializer = new DefaultInitializer();
        drawCanvasSimulation();

    }

    private void drawCanvasSimulation() {
        canvasInit();
        loopInit();
    }

    public void stopLoop(){
        if(DefaultValuesConfig.SIMULATION_PAUSED){
            timelineLoop.play();
            DefaultValuesConfig.SIMULATION_PAUSED = false;

            switch (symulationType.getSelectionModel().getSelectedItem()){
                case "One group, one direction, no wall":
                    DefaultValuesConfig.VERSION = 3;
                    break;
                case "Two groups, opposite direction, no wall":
                    DefaultValuesConfig.VERSION = 1;
                    break;
                case "Two groups, opposite directions, wall":
                    DefaultValuesConfig.VERSION=8;
                    break;
                case "Column barrier":
                    DefaultValuesConfig.VERSION = 6;
                    break;
                case "Tunnel barrier":
                    DefaultValuesConfig.VERSION = 7;
                    break;
                default:
                    break;
            }


            defaultInitializer = new DefaultInitializer();
        }
        else{

            if(!markTrajectory.getSelectionModel().getSelectedItem().equals("Mark Trajectory")) {
                if(markTrajectory.getSelectionModel().getSelectedItem().equals("None")){
                    DefaultValuesConfig.MARK_PATH_OF = 100;
                }else{
                    DefaultValuesConfig.MARK_PATH_OF = Integer.parseInt(markTrajectory.getSelectionModel().getSelectedItem());
                }
            }

            timelineLoop.pause();
            DefaultValuesConfig.SIMULATION_PAUSED = true;
        }
    }


    // Funkcja odpowiedzialna za wyświetlanie statystyk.
    public void printSpeed(){
        for (Pedestrain p : defaultInitializer.getPedestrains_()){
            System.out.println("Pedestarian "+p.getId_()+": mean velocity="
                    +p.calculateMeanVelocity()+"; \t\tmax velocity="
                    +Collections.max(p.getActual_speeds_())+"; \t\tmin velocity="
                    +Collections.min(p.getActual_speeds_()));
        }
    }

    private KeyFrame getNextKeyFrame(Duration duration) {
        KeyFrame keyFrame = new KeyFrame(duration, event -> {
            try {
                removeObjectsFromPreviousFrame();
                drawSpace(defaultInitializer.getSpace_());
                updatePedestrainsPositions(defaultInitializer.getPedestrains_(), defaultInitializer.getSpace_().getWalls());
                drawPedestrains(defaultInitializer.getPedestrains_());

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        return keyFrame;
    }

    private void loopInit() {
        Duration duration = Duration.millis(30);
        KeyFrame frame = getNextKeyFrame(duration);

        timelineLoop = new Timeline();
        timelineLoop.setCycleCount(Animation.INDEFINITE);
        timelineLoop.getKeyFrames().add(frame);
        timelineLoop.play();
        timelineLoop.pause();
    }

    private void canvasInit() {
        canvas.setHeight(700);
        canvas.setWidth(1000);
        graphicsContext = canvas.getGraphicsContext2D();

        // Zachowanie kartezjańskiego układu współrzędnych.
        canvas.setScaleY(-1);
        canvas.setScaleX(1);
    }

    private void removeObjectsFromPreviousFrame(){
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }


    private void updatePedestrainsPositions(LinkedList<Pedestrain>pedestrains, LinkedList<Wall> walls){
        for(Pedestrain pedestrain : pedestrains){
            if(pedestrain.reachedTarget()){ continue; }

            Position target = pedestrain.getTarget_();
            Position current_position = pedestrain.getCurrent_position_();

            // Wyznaczenie znormalizowanego wektora o zwrocie w stronę celu.
            Position direction = target.normalize(current_position);
            pedestrain.setDirection_(direction);

            // Wyznaczenie preferowanego wektora prędkości.
            pedestrain.setDesired_velocity_(direction.multiply(pedestrain.getDesired_speed_()));

            // Wyznaczenie wektora na podstawie preferowanej i aktualnej prędkości.
            Position velocity_change_force = pedestrain.updateVelocity();


            Position people_interaction = new Position(0,0);
            Position wall_interaction = new Position(0,0);

            // Sumowanie sił, wynikających z interakcji z innymi agentami.
            for(Pedestrain pedestrain_j : pedestrains){
                if(pedestrain_j.reachedTarget()){
                    continue;
                }
                if(pedestrain != pedestrain_j){
                    people_interaction = people_interaction.add(pedestrain.calculateInteractionWithNeighbour(pedestrain_j, 1, 1));
                }
            }

            // Sumowanie sił, wynikających z interakcji ze ścianami.
            for(Wall wall : walls){
                if(pedestrain.calculateInteractionWithWall(wall) != null){
                    wall_interaction = wall_interaction.add(pedestrain.calculateInteractionWithWall(wall));
                    //System.out.println("ID: "+pedestrain.getId_() + "; Certain wall: " + wall_interaction.calculateLenght() +" x="+wall_interaction.getX_()+" y="+ wall_interaction.getY_());
                }
            }


            Position sum_of_forces = velocity_change_force.add(people_interaction);
            //System.out.println("ID: "+pedestrain.getId_() + "; Sum of forces without wall: " + sum_of_forces.calculateLenght());
            sum_of_forces = sum_of_forces.add(wall_interaction);
            //System.out.println("ID: "+pedestrain.getId_() + "; Sum of forces with wall: " + sum_of_forces.calculateLenght());

            // Wyznaczenie przyspieszenia.
            // przyspieszenie = siła/masa
            Position acceleration = sum_of_forces.multiply(1/pedestrain.getMass_());
            Position new_velocity = pedestrain.getActual_velocity_().add(acceleration);

            if(new_velocity.calculateLenght()>=DefaultValuesConfig.DEFAULT_MAX_SPEED){
                new_velocity = new_velocity.normalize(new Position(0,0));
                new_velocity = new_velocity.multiply(DefaultValuesConfig.DEFAULT_MAX_SPEED);
            }

            pedestrain.setActual_velocity_(new_velocity);
            current_position = current_position.add(pedestrain.getActual_velocity_());
            pedestrain.setCurrent_position_(current_position);
            if(pedestrain.getId_() == DefaultValuesConfig.MARK_PATH_OF) {
                pedestrain.getRecent_positions_().add(current_position);
            }
            pedestrain.checkIfFinished();
            pedestrain.getActual_speeds_().add(pedestrain.getActual_velocity_().calculateLenght());
            pedestrain.setCrowd_pressure_(sum_of_forces.calculateLenght());
        }
    }

    private void drawPedestrains (LinkedList <Pedestrain> pedestrains){
        for(Pedestrain pedestrain : pedestrains){

            // Omijanie rysowania, gdy agent dotarł już do celu.
            if(pedestrain.reachedTarget()){
                continue;
            }

            double x = pedestrain.getCurrent_position_().getX_();
            double y = pedestrain.getCurrent_position_().getY_();
            double radius = pedestrain.getRadius_();

            double crowdPressure = pedestrain.getCrowd_pressure_();
            //System.out.println("ID: "+pedestrain.getId_() +"; Crowd pressure: "+ crowdPressure);

            if(crowdPressure > DefaultValuesConfig.MAX_SOCIAL_FORCE) {
                crowdPressure = DefaultValuesConfig.MAX_SOCIAL_FORCE;
            }

            // Zaznaczenie położenia agenta.
            Color crowdPressureColor = new Color(0+(crowdPressure/DefaultValuesConfig.MAX_SOCIAL_FORCE), 1-(crowdPressure/DefaultValuesConfig.MAX_SOCIAL_FORCE), 0, 1);
            graphicsContext.setFill(crowdPressureColor);
            graphicsContext.fillOval(x - radius, y - radius, radius * 2, radius * 2);

            // Zaznaczenie targetu, do którego dąży agent.
            graphicsContext.setFill(new Color(0,0,0,1));
            graphicsContext.fillOval(pedestrain.getTarget_().getX_(), pedestrain.getTarget_().getY_(), 5, 5);

            if(pedestrain.getId_() == DefaultValuesConfig.MARK_PATH_OF){
                for(Position position : pedestrain.getRecent_positions_()){
                    graphicsContext.fillOval(position.getX_(), position.getY_(), 2, 2);

                }
            }
            // Zaznaczenie wektora prędkości agenta.
            // graphicsContext.strokeLine(pedestrain.getCurrent_position_().getX_(),pedestrain.getCurrent_position_().getY_(), pedestrain.getCurrent_position_().getX_()+pedestrain.getActual_velocity_().getX_()*10, pedestrain.getCurrent_position_().getY_()+pedestrain.getActual_velocity_().getY_()*10);
        }
    }

    private void drawSpace (Space space){
        drawWalls(space.getWalls());
    }

    private void drawWalls (LinkedList<Wall> walls){
        for(Wall wall : walls){
            Position begin = wall.getBegin();
            Position end = wall.getEnd();
            graphicsContext.strokeLine(begin.getX_(), begin.getY_(), end.getX_(), end.getY_());
        }
    }

}