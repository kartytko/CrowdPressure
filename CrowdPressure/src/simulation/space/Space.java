package simulation.space;

import simulation.Position;

import java.util.LinkedList;

public class Space {

    private Position start_;    // Upper left
    private Position end_;      // Bottom right
    private LinkedList<Wall> walls_;

    public Space(){
        walls_ = new LinkedList<>();
    }

    public void addWall(Wall new_wall){
        walls_.add(new_wall);
    }

}
