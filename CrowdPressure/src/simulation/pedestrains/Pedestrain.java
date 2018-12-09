package simulation.pedestrains;

import simulation.DefaultValuesConfig;
import simulation.Position;


public class Pedestrain {

    private Position start_;
    private Position target_;

    private double radius_;
    private double mass_;
    private double relaxation_time_;

    private double desired_velocity_;
    private double max_velocity_;

    public Pedestrain(){
        radius_ = DefaultValuesConfig.DEFAULT_RADIUS;
        mass_ = DefaultValuesConfig.DEFAULT_MASS;
        relaxation_time_ = DefaultValuesConfig.DEFAULT_RELAXATION_TIME;
        desired_velocity_ = DefaultValuesConfig.DEFAULT_DESIRED_VELOCITY;
        max_velocity_ = DefaultValuesConfig.DEFAULT_MAX_VELOCITY;
    }
}
