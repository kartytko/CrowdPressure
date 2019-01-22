package simulation.space;

import simulation.DefaultValuesConfig;
import simulation.Position;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class Wall {
    private Position begin_;
    private Position end_;

    public Wall(Position begin, Position end){
        begin_ = begin;
        end_ = end;
    }
    public Position getBegin() { return begin_; }

    public Position getEnd() { return end_; }

    public Position calculateCrossPoint(Position current_position){
        Wall wall = this;
        // a_wall = A = (y2 - y1)/(x2 - x1); <-- współczynnik prostej zawierającej w sobie ścianę wall
        double a_wall = (wall.getEnd().getY_()-wall.getBegin().getY_())/(wall.getEnd().getX_()-wall.getBegin().getX_());
        double b_wall = wall.getBegin().getY_() - a_wall*wall.getBegin().getX_();
        double x=0;
        double y=0;
        if(a_wall!=0) {
            // a = -1/a_wall <-- współczynnik prostej prostopadłej
            double a = (-1) / a_wall;
            // b = y - a*x <-- wspólczynnik prostej prostopadłej przechodzącej przez current_position
            double b = current_position.getY_() - a * current_position.getX_();
            if (a_wall != a) {
                x = (b - b_wall) / (a_wall - a);
                y = a_wall * x + b_wall;
            }
        }

        // TO-DO: Implementacja dla ścian pionowych i poziomych (tam, gdzie a=0).
        return new Position(x, y);
    }



    // Odległość punktu od prostej.
    // dist = abs(Ax0+By0+C)/sqrt(A*A+B*B)
    public double calculateDistanceToWall(Position current_position){
        Wall wall = this;
        // a = A = (y2 - y1)/(x2 - x1);
        double A = (wall.getEnd().getY_()-wall.getBegin().getY_())/(wall.getEnd().getX_()-wall.getBegin().getX_());
        // B = -1
        double B = -1;
        // y1 = a*x1 + b
        // b = C = y1 - a * x1;
        // b = C = y2 - a * x2
        double C = wall.getEnd().getY_() - A*wall.getEnd().getX_();
        double x0 = current_position.getX_();
        double y0 = current_position.getY_();
        if(checkIfPointIsInBetween(current_position, wall.getBegin(), wall.getEnd())){
            return (abs(A*x0+B*y0+C))/(sqrt(A*A+B*B));
        }
        return 7777.7;
    }



    public static boolean checkIfPointIsInBetween(Position point, Position start, Position end) {
        double x1 = start.getX_();
        double y1 = start.getY_();
        double x2 = end.getX_();
        double y2 = end.getY_();

        return (((x1 - DefaultValuesConfig.APPROXIMATION_WALL <= point.getX_()
                && point.getX_() <= x2 + DefaultValuesConfig.APPROXIMATION_WALL)
                || (x2 - DefaultValuesConfig.APPROXIMATION_WALL <= point.getX_()
                && point.getX_() <= x1 + DefaultValuesConfig.APPROXIMATION_WALL))
                && ((y1 - DefaultValuesConfig.APPROXIMATION_WALL <= point.getY_()
                && point.getY_() <= y2 + DefaultValuesConfig.APPROXIMATION_WALL)
                || (y2 - DefaultValuesConfig.APPROXIMATION_WALL <= point.getY_()
                && point.getY_() <= y1 + DefaultValuesConfig.APPROXIMATION_WALL)));
    }

}
