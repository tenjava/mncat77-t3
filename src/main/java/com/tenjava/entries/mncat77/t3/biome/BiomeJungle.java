package com.tenjava.entries.mncat77.t3.biome;

import java.util.Random;
import net.minecraft.server.v1_7_R3.BiomeMeta;
import net.minecraft.server.v1_7_R3.Blocks;
import net.minecraft.server.v1_7_R3.EntityChicken;
import net.minecraft.server.v1_7_R3.EntityOcelot;
import net.minecraft.server.v1_7_R3.World;
import net.minecraft.server.v1_7_R3.WorldGenGrass;
import net.minecraft.server.v1_7_R3.WorldGenGroundBush;
import net.minecraft.server.v1_7_R3.WorldGenJungleTree;
import net.minecraft.server.v1_7_R3.WorldGenMelon;
import net.minecraft.server.v1_7_R3.WorldGenTreeAbstract;
import net.minecraft.server.v1_7_R3.WorldGenTrees;
import net.minecraft.server.v1_7_R3.WorldGenVines;
import net.minecraft.server.v1_7_R3.WorldGenerator;

public class BiomeJungle extends BiomeBase {

    private boolean aC;

    public BiomeJungle(int i, boolean flag) {
        super(i);
        this.aC = flag;
        if(flag) {
            this.decorator.x = 2;
        }
        else {
            this.decorator.x = 50;
        }

        this.decorator.grassC = 25;
        this.decorator.flowersC = 4;
        if(!flag) {
            this.monsters.add(new BiomeMeta(EntityOcelot.class, 2, 1, 1));
        }

        this.creatures.add(new BiomeMeta(EntityChicken.class, 10, 4, 4));
    }

    public WorldGenTreeAbstract a(Random random) {
        return (WorldGenTreeAbstract)(random.nextInt(10) == 0 ? this.aA : (random.nextInt(2) == 0 ? new WorldGenGroundBush(3, 0) : (!this.aC && random.nextInt(3) == 0 ? new WorldGenJungleTree(false, 10, 20, 3, 3) : new WorldGenTrees(false, 4 + random.nextInt(7), 3, 3, true))));
    }

    public WorldGenerator b(Random random) {
        return random.nextInt(4) == 0 ? new WorldGenGrass(Blocks.LONG_GRASS, 2) : new WorldGenGrass(Blocks.LONG_GRASS, 1);
    }

    @Override
    public void a(World world, Random random, int i, int j) {
        super.a(world, random, i, j);
        int k = i + random.nextInt(16) + 8;
        int l = j + random.nextInt(16) + 8;
        int i1 = random.nextInt(world.getHighestBlockYAt(k, l) * 2);

        (new WorldGenMelon()).a(world, random, k, i1, l);
        WorldGenVines worldgenvines = new WorldGenVines();

        for(l = 0; l < 50; ++l) {
            i1 = i + random.nextInt(16) + 8;
            short short1 = 128;
            int j1 = j + random.nextInt(16) + 8;

            worldgenvines.a(world, random, i1, short1, j1);
        }
    }
}
