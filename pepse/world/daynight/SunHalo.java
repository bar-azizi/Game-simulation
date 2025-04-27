package pepse.util.src.pepse.world.daynight;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.gui.rendering.OvalRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.util.src.pepse.util.Constants;

public class SunHalo extends GameObject{
    /**
     * Construct a new SunHalo instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     *                      the GameObject will not be rendered.
     */
    public SunHalo(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable) {
        super(topLeftCorner, dimensions, renderable);
    }

    public static GameObject create(GameObject sun){
        Renderable sunHaloImage = new OvalRenderable(Constants.SUN_HALO_COLOR);
        GameObject sunHalo =  new GameObject(sun.getTopLeftCorner(), sun.getDimensions(), sunHaloImage);
        sunHalo.setDimensions(sunHalo.getDimensions().mult(2));
        sunHalo.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        sunHalo.addComponent(deltaTime -> sunHalo.setCenter(sun.getCenter()));
        sunHalo.setTag("sun halo");
        return sunHalo;
    }
}
