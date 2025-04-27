package pepse.util.src.pepse;

import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.util.Vector2;
import pepse.util.src.pepse.util.Constants;
import pepse.util.src.pepse.world.Block;
import pepse.util.src.pepse.world.Sky;
import pepse.util.src.pepse.world.Terrain;
import pepse.util.src.pepse.world.daynight.Night;
import pepse.util.src.pepse.world.daynight.Sun;
import pepse.util.src.pepse.world.daynight.SunHalo;

import java.util.List;

public class PepseGameManager extends GameManager {

    private static final int SKY_LAYER = Layer.BACKGROUND;
    private static final int NO_COLLISION_TERRAIN_LAYER = Layer.STATIC_OBJECTS;
    private static final int COLLISION_TERRAIN_LAYER = Layer.DEFAULT;


    public PepseGameManager(String windowTitle, Vector2 windowDimensions) {
        super(windowTitle, windowDimensions);
    }

    /**
     * initializes the game start objects and states.
     *
     * @param imageReader Contains a single method: readImage, which reads an image from disk.
     *                 See its documentation for help.
     * @param soundReader Contains a single method: readSound, which reads a wav file from
     *                    disk. See its documentation for help.
     * @param inputListener Contains a single method: isKeyPressed, which returns whether
     *                      a given key is currently pressed by the user or not. See its
     *                      documentation.
     * @param windowController A two-dimensional vector represents the size of the game window
     */
    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader, UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        createSky(windowController);
        createTerrain(windowController);
        createDayNightLapse(windowController);
        createSun(windowController);
    }

    private void createSun(WindowController windowController) {
        GameObject sun = Sun.create(windowController.getWindowDimensions(), Constants.FULL_DAY_CYCLE_LENGTH);
        gameObjects().addGameObject(sun, Layer.BACKGROUND);
        GameObject sunHalo = SunHalo.create(sun);
        gameObjects().addGameObject(sunHalo, Layer.BACKGROUND);
    }

    private void createDayNightLapse(WindowController windowController) {
        GameObject night = Night.create(windowController.getWindowDimensions(), Constants.FULL_DAY_CYCLE_LENGTH);
        gameObjects().addGameObject(night, Layer.FOREGROUND);
    }

    private void createTerrain(WindowController windowController) {
        int rightMostPixel =(int) Math.ceil(windowController.getWindowDimensions().x());

        Terrain terrain = new Terrain(windowController.getWindowDimensions(), 1);
        List<Block> blocks = terrain.createInRange(0, rightMostPixel);

        addBlocks(blocks);
    }

    private void addBlocks(List<Block> blocks) {
        for(Block block : blocks){
            if(block.getTag().equals(Constants.GROUND)){
                gameObjects().addGameObject(block, NO_COLLISION_TERRAIN_LAYER);
            } else {
                gameObjects().addGameObject(block, COLLISION_TERRAIN_LAYER);
            }
        }
    }

    /*
        Creates the sky background for the game.
     */
    private void createSky(WindowController windowController) {
        GameObject sky = Sky.create(windowController.getWindowDimensions());
        gameObjects().addGameObject(sky, SKY_LAYER);
    }

    /**
     * The main method to start the program.
     *
     * @param args command-line arguments for the game.
     */
    public static void main(String[] args){
        new PepseGameManager("pepse", new Vector2(700, 500)).run();
    }
}
