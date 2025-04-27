package pepse.util.src.pepse.world.daynight;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.components.Transition;
import danogl.gui.rendering.OvalRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.util.src.pepse.util.Constants;

import java.awt.*;

public class Sun extends GameObject{
    private static final float SUN_HEIGHT_FACTOR = (float)(1.0/3);



    /**
     * Construct a new Sun instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     *                      the GameObject will not be rendered.
     */
    public Sun(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable) {
        super(topLeftCorner, dimensions, renderable);
    }

    public static GameObject create(Vector2 windowDimensions,
                                    float cycleLength){

        float sunCycleCenterX = windowDimensions.x()/2;
        float sunCycleCenterY = windowDimensions.y() * (Constants.GROUND_HEIGHT_FACTOR);
        Vector2 initialSunCenter = new Vector2(sunCycleCenterX, windowDimensions.y()*(SUN_HEIGHT_FACTOR));
        Vector2 cycleCenter = new Vector2(sunCycleCenterX, sunCycleCenterY);

        Renderable sunImage = new OvalRenderable(Color.YELLOW);
        GameObject sun = new GameObject(initialSunCenter, Vector2.ONES.mult(Constants.SUN_RADIUS), sunImage);
        setSun(cycleLength, sun, initialSunCenter, cycleCenter);

        return sun;
    }

    private static void setSun(float cycleLength, GameObject sun, Vector2 initialSunCenter, Vector2 cycleCenter) {
        sun.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        sun.setTag("sun");
        new Transition<>(sun,
                (Float angle) -> sun.setCenter(initialSunCenter.subtract(cycleCenter).rotated(angle).add(cycleCenter)),
                Constants.INITIAL_SUN_ANGLE, Constants.FINAL_SUN_ANGLE, Transition.LINEAR_INTERPOLATOR_FLOAT,
                cycleLength, Transition.TransitionType.TRANSITION_LOOP, null);
    }
}
