package pepse.util.src.pepse.world.daynight;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.components.Transition;
import danogl.gui.rendering.RectangleRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.util.src.pepse.util.Constants;

import java.awt.*;

public class Night extends GameObject{
    private static final String NIGHT = "night";

    /**
     * Construct a new Night instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     *                      the GameObject will not be rendered.
     */
    public Night(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable) {
        super(topLeftCorner, dimensions, renderable);
    }

    public static GameObject create(Vector2 windowDimensions, float cycleLength){
        float halfDayCycleLength = cycleLength/2;

        Renderable nightImage = new RectangleRenderable(Color.BLACK);
        GameObject night  = new GameObject(Vector2.ZERO, windowDimensions, nightImage);
        setNight(night, halfDayCycleLength);
        return night;
    }

    private static void setNight(GameObject night, float halfDayCycleLength) {
        night.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        night.setTag(NIGHT);
        new Transition<>(night, night.renderer()::setOpaqueness, Constants.MIDDAY_OPACITY,
                Constants.MIDNIGHT_OPACITY, Transition.CUBIC_INTERPOLATOR_FLOAT, halfDayCycleLength,
                Transition.TransitionType.TRANSITION_BACK_AND_FORTH, null);
    }
}
