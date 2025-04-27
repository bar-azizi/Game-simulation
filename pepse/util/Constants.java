package pepse.util.src.pepse.util;

import java.awt.*;

abstract public class Constants {
    public static final String SKY = "sky";
    public static final String GROUND = "ground";
    public static final String BLOCK = "block";
    public static final float MIDNIGHT_OPACITY = 0.5f;
    public static final float MIDDAY_OPACITY = 0f;
    public static final float INITIAL_SUN_ANGLE = 0f;
    public static final float FINAL_SUN_ANGLE = 360f;
    public static final float TIME_TO_MIDNIGHT = 15;
    public static final float FULL_DAY_CYCLE_LENGTH = 30;
    public static final float SUN_RADIUS = 70;
    public static final float GROUND_HEIGHT_FACTOR = (float) (2.0/3.0);
    public static final Color SUN_HALO_COLOR = new Color(255, 255, 0, 50);
}

