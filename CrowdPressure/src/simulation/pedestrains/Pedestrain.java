package simulation.pedestrains;
import simulation.DefaultValuesConfig;
import simulation.Position;

import static java.lang.Math.sqrt;


public class Pedestrain {

    private Position start_;
    private Position target_;
    private Position current_;
    private int id_;
    private double radius_;
    private double mass_;
    private double relaxation_time_;

    private double desired_velocity_;
    private double max_velocity_;


    private boolean reached_target;

    public Pedestrain(Position start, Position target){
        radius_ = DefaultValuesConfig.DEFAULT_RADIUS;
        mass_ = DefaultValuesConfig.DEFAULT_MASS;
        relaxation_time_ = DefaultValuesConfig.DEFAULT_RELAXATION_TIME;
        desired_velocity_ = DefaultValuesConfig.DEFAULT_DESIRED_VELOCITY;
        max_velocity_ = DefaultValuesConfig.DEFAULT_MAX_VELOCITY;
        start_ = start;
        current_ = start;
        target_ = target;
        reached_target = false;
    }

    public void GenerateNextStep(){
        if(reached_target){
            return;
        }


        float differenceX = current_.getX_()-target_.getX_();
        float differenceY = current_.getY_()-target_.getY_();
        float vectorLength = (float) sqrt(differenceX*differenceX + differenceY*differenceY);

        // TO-DO pozbyć się STEP na rzecz prędkości
        current_.setX_(current_.getX_()-(differenceX*DefaultValuesConfig.STEP)/vectorLength);
        current_.setY_(current_.getY_()-(differenceY*DefaultValuesConfig.STEP)/vectorLength);

        if(current_.getX_()-DefaultValuesConfig.APPROXIMATION < target_.getX_() && current_.getX_()+DefaultValuesConfig.APPROXIMATION > target_.getX_()){
            if(current_.getY_()-DefaultValuesConfig.APPROXIMATION < target_.getY_() && current_.getY_()+DefaultValuesConfig.APPROXIMATION > target_.getY_()){
                reached_target = true;
                System.out.println("REACHED");
            }

        }
    }

    public Position getCurrent_() {
        return current_;
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

}
