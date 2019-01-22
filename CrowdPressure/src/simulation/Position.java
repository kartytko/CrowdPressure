package simulation;

import static java.lang.Math.sqrt;

// Klasa opisująca pozycję (punkt) na płaszczyźnie.
// Używana różnież do opisu wektorów (traktowanie wektorów jako wektory wodzące - wówczas punkt klasy Position pokazuje,
// gdzie znajduje się grot strzałki wektora.
public class Position {
    private double x_;
    private double y_;

    public Position(double x, double y){
        x_ = x;
        y_ = y;
    }

    public double calculateDistance (Position other){
        double differenceX = this.getX_()-other.getX_();
        double differenceY = this.getY_()-other.getY_();
        return sqrt(differenceX*differenceX + differenceY*differenceY);
    }

    public double calculateLenght(){
        return sqrt((x_*x_+y_*y_));
    }

    public Position normalize (Position other){
        double distance = calculateDistance(other);
        double x = this.x_ - other.getX_();
        double y = this.y_ - other.getY_();
        if(distance!=0){
            x = x/distance;
            y = y/distance;
        }
        return new Position(x, y);
    }

    public Position multiply (Position p){
        double x = this.x_*p.getX_();
        double y = this.y_*p.getY_();
        return new Position(x, y);
    }


    public Position multiply (double number){
        double x = this.getX_()*number;
        double y = this.getY_()*number;
        return new Position(x, y);
    }

    public Position add(Position p){
        return new Position(this.x_+p.getX_(), this.y_+p.getY_());
    }

    public Position subtract(Position p){
        return new Position(this.x_-p.getX_(), this.y_-p.getY_());
    }

    public double getX_() {
        return x_;
    }

    public void setX_(double x_) {
        this.x_ = x_;
    }

    public double getY_() {
        return y_;
    }

    public void setY_(double y_) {
        this.y_ = y_;
    }
}
