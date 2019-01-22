package simulation.pedestrains;
import simulation.DefaultValuesConfig;
import simulation.Position;
import simulation.space.Wall;

import java.util.LinkedList;

import static java.lang.Math.*;


// Klasa opisująca ruch pojedynczego pieszego.
public class Pedestrain {

    private Position target_;
    private Position current_position_;
    private Position actual_velocity_;
    private Position direction_;
    private int id_;
    private double radius_;
    private double mass_;
    private double relaxation_time_;
    private double desired_speed_;
    private Position desired_velocity_;
    private double body_factor_;
    //interactionRange, p, bodyFactor, slideFricFactor, A, B

    private boolean reached_target;
    private double crowd_pressure_;

    private LinkedList<Position> recent_positions_ = new LinkedList<>();

    private LinkedList<Double> actual_speeds_ = new LinkedList<>();

    public Pedestrain(Position start, Position target, int id){
        radius_ = DefaultValuesConfig.DEFAULT_RADIUS;
        mass_ = DefaultValuesConfig.DEFAULT_MASS;
        relaxation_time_ = DefaultValuesConfig.DEFAULT_RELAXATION_TIME;
        desired_speed_ = DefaultValuesConfig.DEFAULT_DESIRED_SPEED;
        current_position_ = start;
        target_ = target;
        reached_target = false;
        id_ = id;

        actual_velocity_ = new Position(1,1);
        direction_ = target_.normalize(current_position_);
        desired_velocity_=direction_.multiply((float)desired_speed_);
        body_factor_ = 24000;
        crowd_pressure_=0;
    }



    // Sprawdzenie, czy agent dotarł do celu.
    public void checkIfFinished (){
        if(current_position_.getX_()-DefaultValuesConfig.APPROXIMATION < target_.getX_() && current_position_.getX_()+DefaultValuesConfig.APPROXIMATION > target_.getX_()){
            if(current_position_.getY_()-DefaultValuesConfig.APPROXIMATION < target_.getY_() && current_position_.getY_()+DefaultValuesConfig.APPROXIMATION > target_.getY_()){
                reached_target = true;
                System.out.println(this.id_+": REACHED TARGET");
            }
        }
    }


    public Position calculateInteractionWithNeighbour(Pedestrain neighbour, double A, double B){
        A = this.mass_*this.desired_speed_/this.relaxation_time_;
        B = 1;

        // Nie obliczamy interakcji, jeśli sąsiad dotarł do celu.
        if(neighbour.reachedTarget()){
            return new Position(0,0);
        }

        double r  = this.radius_ + neighbour.radius_;
        double d = this.current_position_.calculateDistance(neighbour.current_position_);


        // SOCIAL FORCE
        // social_force = A*exp((r-d)/B)*n*((1+cos(phi))/2)
        // cos(phi) = -n*e       , gdzie phi to kąt pomiędzy e=v/|v|, a -n
        Position n = this.current_position_.normalize(neighbour.current_position_); // Wektor od agenta do sąsiada (n).
        Position minus_n = n.multiply(-1);
        Position actual_velocity =   this.getActual_velocity_();
        Position e = actual_velocity.multiply(1/actual_velocity.calculateLenght()); // e = v(t)/||v(t)||

        // Iloczyn skalarny wektorów podzielony przez iloczyn długości wektorów daje cosinus kąta.
        // Iloczyn skalarny wektorów podzielony przez iloczyn długości wektorów daje cosinus kąta.
        double scalar = minus_n.multiply(e).getX_()+minus_n.multiply(e).getY_();
        double n_lenght = minus_n.calculateLenght();
        double e_lenght = e.calculateLenght();
        double cosinus = scalar/(e_lenght*n_lenght);

        Position social_force = n.multiply((A*exp((r-d)/B))).multiply((1+cosinus)/2);


        // BODY FORCE
        // body_force = k*g(r-d)*n
        Position body_force = n.multiply((this.body_factor_*max(0,(r-d))));



        // SLIDING FRICTION FORCE
        // sliding_friction_force = kappa*g(r-d)*deltaVt*tij
        // deltaVt = (vj - vi)*tij  <-- tangential velocity difference
        // tij=(-nij2, nij1)     <-- tangential direction     n=(nij1, nij2)
        Position t = new Position(n.getY_()*(-1), n.getX_());
        Position deltaVt = (neighbour.getActual_velocity_().subtract(this.getActual_velocity_())).multiply(t);

        Position sliding_friction_force = deltaVt.multiply(max(0, (r-d))).multiply(t);

        return social_force.add(body_force).add(sliding_friction_force);
    }


    public Position calculateInteractionWithWall(Wall wall){
        // {A*exp((r-d)/B)+ kq(r-d)}*n - g(r-d)(v*t)*t
        if(this.reachedTarget()){return new Position(0,0);}


        double A = (-1)*this.mass_*this.desired_speed_/(25*this.relaxation_time_);
        double B = 5;

        double r = this.radius_;
        double d = wall.calculateDistanceToWall(this.current_position_);

        // niepotrzbne
        if(d==7777.7){
            return new Position(0,0);
        }

        // BODY FORCE
        // body_force = {A*exp((r-d)/B)+ kq(r-d)}*n;
        Position cross_point = wall.calculateCrossPoint(this.current_position_);
        Position n = current_position_.normalize(cross_point);
        Position t = new Position(n.getY_()*(-1), n.getX_()); // t=(-n2, n1) <-- tangential direction   n=(n1, n2)

        Position body_force = n.multiply(A*exp((r-d)/B) + this.body_factor_*max(0, (r-d)));


        // SLIDING FRICTION
        // sliding_friction_force = g(r-d)(v*t)*t  <-- artykuł: Vicsek, Farkas
        // v*t=cos(phi) (wyliczanie analogicznie do sliding friction dla interakcji z sąsiadem)
        //double scalar = this.getActual_velocity_().multiply(t).getX_()+this.getActual_velocity_().multiply(t).getY_();
        double scalar = this.getActual_velocity_().getX_()*t.getX_()+this.getActual_velocity_().getY_()*t.getY_();
        double v_lenght = this.getActual_velocity_().calculateLenght();
        double t_lenght = t.calculateLenght();
        double cosinus = scalar/(t_lenght*v_lenght);
        // ALTERNATYWA: Position sliding_friction_force = t.multiply(cosinus*this.body_factor_*max(0,(r-d))); //Vicsek&Farkas

        // sliding_friction_force = g(r-d)*k*t  <-- artykuł: Lakoba, Kaup, Finkelstein (rozszerzenie Vicseka i Farkasa)
        Position sliding_friction_force = t.multiply(max(0,(r-d))*this.body_factor_);

        return body_force.add(sliding_friction_force);
        // ALTERNATYWA: return body_force.subtract(sliding_friction_force);  //Vicsek&Farkas
    }


    public Double calculateMeanVelocity(){
        Double sum = 0.0;
        for(Double d : actual_speeds_){
            sum += d;
        }
        return sum/actual_speeds_.size();
    }

    // Wyznacza siłę, która bierze pod uwagę aktualną i preferowaną prędkość.
    // siła = (masa * (prędkość_preferowana - prędkość_aktualna))/czas_relaksacji
    public Position updateVelocity(){
        Position delta = this.desired_velocity_.subtract(this.actual_velocity_);
        return delta.multiply((mass_/relaxation_time_));
    }

    public Position getCurrent_position_() {
        return current_position_;
    }

    public double getRadius_() {
        return radius_;
    }

    public Position getTarget_() {
        return target_;
    }

    public boolean reachedTarget() {
        return reached_target;
    }

    public void setDirection_(Position direction_) {
        this.direction_ = direction_;
    }

    public double getMass_() {
        return mass_;
    }

    public Position getActual_velocity_() {
        return actual_velocity_;
    }

    public void setActual_velocity_(Position actual_velocity_) {
        this.actual_velocity_ = actual_velocity_;
    }

    public void setCurrent_position_(Position current_position_) {
        this.current_position_ = current_position_;
    }
    public int getId_() {
        return id_;
    }

    public double getCrowd_pressure_() {
        return crowd_pressure_;
    }

    public void setCrowd_pressure_(double crowd_pressure_) {
        this.crowd_pressure_ = crowd_pressure_;
    }

    public void setDesired_velocity_(Position desired_velocity_) {
        this.desired_velocity_ = desired_velocity_;
    }

    public double getDesired_speed_() {
        return this.desired_speed_;
    }

    public LinkedList<Position> getRecent_positions_() {
        return recent_positions_;
    }

    public void setRecent_positions_(LinkedList<Position> recent_positions_) {
        this.recent_positions_ = recent_positions_;
    }

    public LinkedList<Double> getActual_speeds_() {
        return actual_speeds_;
    }


}
