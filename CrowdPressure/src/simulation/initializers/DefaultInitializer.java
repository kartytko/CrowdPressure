package simulation.initializers;

import simulation.DefaultValuesConfig;
import simulation.Position;
import simulation.pedestrains.Pedestrain;
import simulation.space.Space;
import simulation.space.Wall;

import java.util.LinkedList;

public class DefaultInitializer {

    private LinkedList<Pedestrain> pedestrains_;

    public LinkedList<Pedestrain> getPedestrains_() {
        return pedestrains_;
    }

    public Space getSpace_() {
        return space_;
    }

    private Space space_;

    public DefaultInitializer(LinkedList<Pedestrain> pedestrains, Space space){
        pedestrains_ = pedestrains;
        space_ = space;
    }

    public DefaultInitializer(){

        // Pedestrians
        pedestrains_ = new LinkedList<>();

        // TO-DO zmienić sztywno wpisane dane na modele
        Pedestrain pedestrain = new Pedestrain(/* start point */ new Position(300, 300), /* end point */ new Position(350, 150));
        Pedestrain pedestrain2 = new Pedestrain(/* start point */ new Position(450, 120), /* end point */ new Position(350, 150));
        Pedestrain pedestrain3 = new Pedestrain(/* start point */ new Position(425, 160), /* end point */ new Position(350, 150));
        Pedestrain pedestrain4 = new Pedestrain(/* start point */ new Position(700, 190), /* end point */ new Position(350, 150));

        pedestrains_.add(pedestrain);
        pedestrains_.add(pedestrain2);
        pedestrains_.add(pedestrain3);
        pedestrains_.add(pedestrain4);

        // Space
        space_ = new Space();
        Position left_top = new Position(DefaultValuesConfig.DEFAULT_WALL_LEFT, DefaultValuesConfig.DEFAULT_WALL_TOP);
        Position left_bottom = new Position(DefaultValuesConfig.DEFAULT_WALL_LEFT, DefaultValuesConfig.DEFAULT_WALL_BOTTOM);
        Position right_top = new Position(DefaultValuesConfig.DEFAULT_WALL_RIGHT, DefaultValuesConfig.DEFAULT_WALL_TOP);
        Position right_bottom = new Position(DefaultValuesConfig.DEFAULT_WALL_RIGHT, DefaultValuesConfig.DEFAULT_WALL_BOTTOM);
        space_.addWall(new Wall(left_top, right_top));
        space_.addWall(new Wall(right_top, right_bottom));
        space_.addWall(new Wall(right_bottom, left_bottom));
        space_.addWall(new Wall(left_bottom, left_top));

    }
}
