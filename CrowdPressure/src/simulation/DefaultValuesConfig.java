package simulation;

public class DefaultValuesConfig {

    public static boolean SIMULATION_PAUSED = true;
    public static int VERSION = 6;
    public static int MARK_PATH_OF = 100;

    // Pedestrian
    public static final double DEFAULT_RADIUS = 0.35*25;           // meters       (basing on 2005, Lakoba, Kaup and Finkelstein's)
    public static final double DEFAULT_MASS = 60;               // kilograms    (basing on 2005, Lakoba, Kaup and Finkelstein's)
    public static final double DEFAULT_RELAXATION_TIME = 0.5;   // seconds      (basing on 2005, Lakoba, Kaup and Finkelstein's)
    public static final double DEFAULT_DESIRED_SPEED = 1.34; // meter/second (basing on 1995, Helbing and Molnar's)
    public static final double DEFAULT_MAX_SPEED = 1.7;      // meter/second (basing on 1995, Helbing and Molnar's)
    public static final double APPROXIMATION = 9;
    public static final double APPROXIMATION_WALL = 2;
    public static final double MAX_SOCIAL_FORCE = 500;
}
