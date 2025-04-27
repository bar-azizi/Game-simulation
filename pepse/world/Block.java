package pepse.util.src.pepse.world;

import danogl.GameObject;
import danogl.components.GameObjectPhysics;
import danogl.gui.rendering.RectangleRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.util.src.pepse.util.ColorSupplier;

import java.awt.*;

public class Block extends GameObject {

    private static final Color BASE_GROUND_COLOR = new Color(212, 123, 74);
    public static final int SIZE = 30;

    public Block(Vector2 topLeftCorner, Renderable renderable) {
        super(topLeftCorner, Vector2.ONES.mult(SIZE), renderable);
        physics().preventIntersectionsFromDirection(Vector2.ZERO);
        physics().setMass(GameObjectPhysics.IMMOVABLE_MASS);
    }

    public static Block create(float x, float y, String blockName){
        Renderable blockImage = new RectangleRenderable(ColorSupplier.approximateColor(BASE_GROUND_COLOR));
        Block block = new Block(new Vector2(x, y), blockImage);
        block.setTag(blockName);
        return block;
    }
}
