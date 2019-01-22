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
    public Space (LinkedList<Wall> walls) {
        walls_ = walls;
    }

    public void addWall(Wall new_wall){
        walls_.add(new_wall);
    }

    public LinkedList<Wall> getWalls() {
        return walls_;
    }

    public Position getStart_() {
        return start_;
    }

    public Position getEnd_() {
        return end_;
    }
}
