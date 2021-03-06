package com.tenjava.entries.mncat77.t3.layer;

import com.tenjava.entries.mncat77.t3.biome.BiomeBase;
import net.minecraft.server.v1_7_R3.EnumGenLayerSpecial;

public abstract class BiomeLayer {

    /**
     * Compares 2 Biomes and check whether or not they are equal
     *
     * @param biome1Id The first biome id
     * @param biome2Id The second biome id
     * @return Whether or not the biomes are equal
     */
    protected static boolean biomeEquals(int biome1Id, int biome2Id) {
        if(biome1Id == biome2Id) {
            return true;
        }
        else if(biome1Id != BiomeBase.MESA_PLATEAU_F.id && biome1Id != BiomeBase.MESA_PLATEAU.id) {
            return BiomeBase.getBiome(biome1Id) != null && BiomeBase.getBiome(biome2Id) != null ? BiomeBase.getBiome(biome1Id).equals(BiomeBase.getBiome(biome2Id)) : false;
        }
        else {
            return biome2Id == BiomeBase.MESA_PLATEAU_F.id || biome2Id == BiomeBase.MESA_PLATEAU.id;
        }
    }

    /**
     * Init all the layers to create the final biome providing layers
     *
     * @param seed The base seed for the layers
     * @return The layers providing the final biomes
     */
    public static BiomeLayer[] initBiomeLayers(long seed) {
        BiomeLayer layerIsland = new BiomeLayerIslands(seed);
        BiomeLayer layerZoomFuzzy = new BiomeLayerZoomFuzzy(seed + 1, layerIsland);
        BiomeLayer layerIslands = new BiomeLayerAddToIslands(seed - 1, layerZoomFuzzy);
        BiomeLayer layerZoom = new BiomeLayerZoom(seed + 2, layerIslands);

        layerIslands = new BiomeLayerAddToIslands(seed - 2, layerZoom);
        layerIslands = new BiomeLayerAddToIslands(seed + 3, layerIslands);
        layerIslands = new BiomeLayerAddToIslands(seed - 3, layerIslands);
        BiomeLayer layerIcePlains = new BiomeLayerIcePlains(seed + 4, layerIslands);
        BiomeLayer layerTopSoil = new BiomeLayerTopSoil(seed - 4, layerIcePlains);
        layerIslands = new BiomeLayerAddToIslands(seed + 5, layerTopSoil);

        BiomeLayer layerSpecial = new BiomeLayerSpecial(seed - 5, layerIslands, EnumGenLayerSpecial.COOL_WARM);

        layerSpecial = new BiomeLayerSpecial(seed + 6, layerSpecial, EnumGenLayerSpecial.HEAT_ICE);
        layerSpecial = new BiomeLayerSpecial(seed - 6, layerSpecial, EnumGenLayerSpecial.PUFFERFISH);
        layerZoom = new BiomeLayerZoom(seed + 7, layerSpecial);
        layerZoom = new BiomeLayerZoom(seed - 7, layerZoom);
        layerIslands = new BiomeLayerAddToIslands(seed + 8, layerZoom);
        //No mushroom islands, cause who will find 'em anyways, don't wanna change rarity either ; prolly waste of time?
        BiomeLayer genlayerdeepocean = new BiomeLayerDeepOcean(seed - 8, layerIslands);
        BiomeLayer layer = BiomeLayerZoom.zoom(seed + 9, genlayerdeepocean, 0);
        byte biomeSize = 6;//large = 6; lemme fiddle with this later

        BiomeLayer layer1 = BiomeLayerZoom.zoom(seed - 9, layer, 0);
        BiomeLayer layerCleaner = new BiomeLayerCleaner(seed + 10, layer1);
        BiomeLayer layer3 = new BiomeLayerBiome(seed - 10, layer);

        BiomeLayer layer2 = BiomeLayerZoom.zoom(seed + 11, layer3, 2);

        layer3 = new BiomeLayerDesert(seed - 11, layer2);

        BiomeLayer layer4 = BiomeLayerZoom.zoom(seed + 12, layerCleaner, 2);
        BiomeLayer layerRegionHills = new BiomeLayerRegionHills(seed - 12, layer3, layer4);

        layer1 = BiomeLayerZoom.zoom(seed + 13, layerCleaner, 2);
        layer1 = BiomeLayerZoom.zoom(seed - 13, layer1, biomeSize);
        BiomeLayer layerRiver = new BiomeLayerRiver(seed + 14, layer1);
        BiomeLayerSmooth layerSmooth = new BiomeLayerSmooth(seed - 14, layerRiver);

        layer3 = new BiomeLayerPlains(seed + 15, layerRegionHills);

        for(int i = 0; i < biomeSize; ++i) {
            layer3 = new BiomeLayerZoom((long)(seed + i), layer3);
            if(i == 0) {
                layer3 = new BiomeLayerAddToIslands(3L, layer3);
            }
        }

        BiomeLayerSmooth layerSmooth1 = new BiomeLayerSmooth(seed - 15, layer3);
        BiomeLayerRiverMix layerRiverMix = new BiomeLayerRiverMix(seed + 16, layerSmooth1, layerSmooth);
        BiomeLayer layerZoomVoronoi = BiomeLayerZoom.zoom(seed, layerRiverMix, 2);//new BiomeLayerZoomVoronoi(seed - 16, new BiomeLayerZoom(seed, layerRiverMix));
        return new BiomeLayer[]{layerZoomVoronoi, layerRiverMix};
    }

    /**
     * Checks if the biome is ocean
     *
     * @param id The id of the biome
     * @return Whether or not the biome is ocean
     */
    protected static boolean isBiomeOcean(int id) {
        return id == BiomeBase.OCEAN.id || id == BiomeBase.DEEP_OCEAN.id || id == BiomeBase.FROZEN_OCEAN.id;
    }

    //The base seed
    protected long baseSeed;

    //A seed initialized for each area (size differs, usually one tile in layer)
    private long chunkSeed;

    //An optional parent Biome Layer (only the first one doesn't have a parent)
    protected BiomeLayer parent;

    /**
     * Create a fresh BiomeLayer(only used by first layer)
     *
     * @param seed Seed for the BiomeLayer
     */
    protected BiomeLayer(long seed) {
        this.baseSeed = seed;
    }

    /**
     * Creates a BiomeLayer with a parent
     *
     * @param seed Seed for the BiomeLayer
     * @param parent Parent for the BiomeLayer
     */
    protected BiomeLayer(long seed, BiomeLayer parent) {
        this.baseSeed = seed;
        this.parent = parent;
    }

    /**
     * Randomly choose one element from an array
     *
     * @param choices The choices as array
     * @return A random element of the array
     */
    protected int choose(int... choices) {
        return choices[this.nextInt(choices.length)];
    }

    protected int chooseZoom(int choice1, int choice2, int choice3, int choice4) {
        return choice2 == choice3 && choice3 == choice4 ? choice2 : (choice1 == choice2 && choice1 == choice3 ? choice1 : (choice1 == choice2 && choice1 == choice4 ? choice1 : (choice1 == choice3 && choice1 == choice4 ? choice1 : (choice1 == choice2 && choice3 != choice4 ? choice1 : (choice1 == choice3 && choice2 != choice4 ? choice1 : (choice1 == choice4 && choice2 != choice3 ? choice1 : (choice2 == choice3 && choice1 != choice4 ? choice2 : (choice2 == choice4 && choice1 != choice3 ? choice2 : (choice3 == choice4 && choice1 != choice2 ? choice3 : this.choose(new int[]{choice1, choice2, choice3, choice4}))))))))));
    }

    /**
     * Generates values (usually biome ids) for a specified rectangle in the
     * world
     *
     * @param realX The X-Offset of the rectangle in Blocks
     * @param realZ The Z-Offset of the rectangle in Blocks
     * @param width The width of the rectangle
     * @param length The length of the rectangle
     * @return int[] with the values for the rectangle (usually biome ids)
     */
    public abstract int[] getValues(int realX, int realZ, int width, int length);

    /**
     * Inits Chunk seed for specified position
     *
     * @param realX The X-Offset
     * @param realZ The Z-Offset
     */
    protected void initChunkSeed(int realX, int realZ) {
        chunkSeed = this.baseSeed;
        chunkSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        chunkSeed += realX;
        chunkSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        chunkSeed += realZ;
        chunkSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        chunkSeed += realX;
        chunkSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        chunkSeed += realZ;
    }

    /**
     * Generates a pseudo-random number for the layers
     *
     * @param max The maximum value the number should be (not inclusive)
     * @return A pseudo-random number generated with the baseSeed and the
     * parameters
     */
    protected int nextInt(int max) {
        int rand = (int)((this.chunkSeed >> 24) % (long)max);

        if(rand < 0) {
            rand += max;
        }
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += this.baseSeed;
        return rand;
    }

}
