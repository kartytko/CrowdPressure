package simulation.space;

import simulation.Position;

public class Wall {
    private Position begin_;
    private Position end_;

    public Wall(Position begin, Position end){
        begin_ = begin;
        end_ = end;
    }
    public Position getBegin() { return begin_; }

    public Position getEnd() { return end_; }


}
