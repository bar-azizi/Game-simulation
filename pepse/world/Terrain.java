package pepse.util.src.pepse.world;

import danogl.util.Vector2;
import pepse.util.src.pepse.util.Constants;
import pepse.util.src.pepse.util.NoiseGenerator;

import java.util.*;
import java.util.List;

public class Terrain {
    private static final int MAX_NUMBER_OF_INNER_BLOCKS = 20;
    private final int seed;
    private final Vector2 windowDimensions;
    public float groundHeightAtX0;
    private final NoiseGenerator noiseGenerator;

    public Terrain(Vector2 windowDimensions, int seed){
        this.seed = seed;
        this.windowDimensions = windowDimensions;
        this.groundHeightAtX0 = windowDimensions.y() * (Constants.GROUND_HEIGHT_FACTOR);
        this.noiseGenerator = new NoiseGenerator(seed, (int) groundHeightAtX0);
    }

    public float groundHeightAt(float x) {
        float noise = (float) noiseGenerator.noise(x, Block.SIZE *7);
        return groundHeightAtX0 + noise;
    }

    public List<Block> createInRange(int minX, int maxX) {

        List<Block> blocks = new ArrayList<>();
        float currentX = getLeftmostX(minX);

        createBlocks(maxX, currentX, blocks);

        return blocks;
    }

    private void createBlocks(int maxX, float currentX, List<Block> blocks) {
        while (currentX <= maxX){
            float currentGroundHeight = groundHeightAt(currentX);
            addBlock(currentX, currentGroundHeight, Constants.BLOCK, blocks);
            for(int i=0; i<MAX_NUMBER_OF_INNER_BLOCKS; i++){
                currentGroundHeight += Block.SIZE;
                addBlock(currentX, currentGroundHeight, Constants.GROUND, blocks);
            }
            currentX += Block.SIZE;
        }
    }

    private static void addBlock(float currentX, float currentGroundHeight, String blockName, List<Block> blocks) {
        Block block = Block.create(currentX, currentGroundHeight, blockName);
        blocks.add(block);
    }

    private int getLeftmostX(int minX) {
        return ((minX/Block.SIZE)*Block.SIZE)-Block.SIZE;
    }
}
