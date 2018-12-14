package simulation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import simulation.initializers.DefaultInitializer;
import simulation.pedestrains.Pedestrain;
import simulation.space.Space;
import simulation.space.Wall;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Canvas canvas;
    private GraphicsContext graphicsContext;
    private Timeline timelineLoop;
    private DefaultInitializer defaultInitializer;

    private void drawCanvasSimulation() {
        canvasInit();
        loopInit();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        defaultInitializer = new DefaultInitializer();
        drawCanvasSimulation();
    }

    public void stopLoop(){     //TO-DO przycisk do pauzowania, a nie do zatrzymywania
        timelineLoop.stop();
    }

    private KeyFrame getNextKeyFrame(Duration duration) {
        KeyFrame keyFrame = new KeyFrame(duration, event -> {
            try {
                removeObjectsFromPreviousFrame();
                drawSpace(defaultInitializer.getSpace_());
                drawPedestrains(defaultInitializer.getPedestrains_());

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        return keyFrame;
    }

    private void loopInit() {
        Duration duration = Duration.millis(50);    // TO-DO Dostosowywać szybkość animacji do preferencji użytkownika
        KeyFrame frame = getNextKeyFrame(duration);

        timelineLoop = new Timeline();
        timelineLoop.setCycleCount(Animation.INDEFINITE);
        timelineLoop.getKeyFrames().add(frame);
        timelineLoop.play();
    }

    private void canvasInit() {
        canvas.setHeight(700);
        canvas.setWidth(1000);
        graphicsContext = canvas.getGraphicsContext2D();
        canvas.setScaleY(-1);   // By zachować kartezjański układ współrzędnych
    }

    private void removeObjectsFromPreviousFrame(){
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawPedestrains (LinkedList <Pedestrain> pedestrains){
        for(Pedestrain pedestrain : pedestrains){
            if(pedestrain.reachedTarget()){
                continue;
            }
            double x = pedestrain.getCurrent_().getX_();
            double y = pedestrain.getCurrent_().getY_();
            System.out.println("x="+x);
            System.out.println("y="+y);
            double radius = pedestrain.getRadius_()*25;     // Stała wartość w iloczynie jest dobrana empirycznie,
                                                            // żeby aktor był dobrze widoczny na symulacji.
            Color crowdPressureColor = new Color(0, 1, 0.0, 1.0);  // TO-DO zaimplementować
                                        // funkcję, która kontroluje kolor kółka w zależności od działających sił.
            graphicsContext.setFill(crowdPressureColor);
            graphicsContext.fillOval(x - radius, y - radius, radius * 2, radius * 2);

            // Zaznaczenie targetu
            graphicsContext.setFill(new Color(0,0,0,1));
            graphicsContext.fillOval(pedestrain.getTarget_().getX_(), pedestrain.getTarget_().getY_(), 5, 5);
            pedestrain.GenerateNextStep();
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