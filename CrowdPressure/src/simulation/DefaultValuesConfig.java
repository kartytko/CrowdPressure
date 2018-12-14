package simulation;

public class DefaultValuesConfig {

    // Pedestrian
    public static final double DEFAULT_RADIUS = 0.35;           // meters       (basing on 2005, Lakoba, Kaup and Finkelstein's)
    public static final double DEFAULT_MASS = 80;               // kilograms    (basing on 2005, Lakoba, Kaup and Finkelstein's)
    public static final double DEFAULT_RELAXATION_TIME = 0.5;   // seconds      (basing on 2005, Lakoba, Kaup and Finkelstein's)
    public static final double DEFAULT_DESIRED_VELOCITY = 1.34; // meter/second (basing on 1995, Helbing and Molnar's)
    public static final double DEFAULT_MAX_VELOCITY = 1.7;      // meter/second (basing on 1995, Helbing and Molnar's)
    public static final int STEP = 3;                           // TO-DO zrezygnować z tego pola (patrz: klasa Pedestrain)
    public static final double APPROXIMATION = 2;

    // Space
    public static final int DEFAULT_WALL_LEFT = 100;
    public static final int DEFAULT_WALL_RIGHT = 800;
    public static final int DEFAULT_WALL_TOP = 100;
    public static final int DEFAULT_WALL_BOTTOM = 400;
}
