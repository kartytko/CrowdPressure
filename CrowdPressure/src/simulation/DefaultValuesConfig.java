package simulation;

public class DefaultValuesConfig {

    public static boolean SIMULATION_PAUSED = true;
    public static int VERSION = 5; // 1 - dwie grupy, 2 -jeden chłopiec i ściana, 3 - grupa idąca w jedną stronę

    // Pedestrian

    public static final double DEFAULT_RADIUS = 0.35*25;           // meters       (basing on 2005, Lakoba, Kaup and Finkelstein's)
    public static final double DEFAULT_MASS = 60;               // kilograms    (basing on 2005, Lakoba, Kaup and Finkelstein's)
    public static final double DEFAULT_RELAXATION_TIME = 0.5;   // seconds      (basing on 2005, Lakoba, Kaup and Finkelstein's)
    public static final double DEFAULT_DESIRED_SPEED = 1.34; // meter/second (basing on 1995, Helbing and Molnar's)
    public static final double DEFAULT_MAX_SPEED = 1.7;      // meter/second (basing on 1995, Helbing and Molnar's)
    public static final int STEP = 3;                           // TO-DO zrezygnować z tego pola (patrz: klasa Pedestrain)
    public static final double APPROXIMATION = 9;
    public static final double APPROXIMATION2 = 3;
    public static final double MAX_SOCIAL_FORCE = 355;

    // Space
    public static final int DEFAULT_WALL_LEFT = 100;
    public static final int DEFAULT_WALL_RIGHT = 800;
    public static final int DEFAULT_WALL_TOP = 100;
    public static final int DEFAULT_WALL_BOTTOM = 400;
}
