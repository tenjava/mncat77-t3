package com.tenjava.entries.mncat77.t3.generator;

import com.tenjava.entries.mncat77.t3.biome.BiomeBase;
import java.util.Random;
import net.minecraft.server.v1_7_R3.BlockFlowers;
import net.minecraft.server.v1_7_R3.Blocks;
import net.minecraft.server.v1_7_R3.Material;
import net.minecraft.server.v1_7_R3.World;
import net.minecraft.server.v1_7_R3.WorldGenCactus;
import net.minecraft.server.v1_7_R3.WorldGenClay;
import net.minecraft.server.v1_7_R3.WorldGenDeadBush;
import net.minecraft.server.v1_7_R3.WorldGenFlowers;
import net.minecraft.server.v1_7_R3.WorldGenHugeMushroom;
import net.minecraft.server.v1_7_R3.WorldGenLiquids;
import net.minecraft.server.v1_7_R3.WorldGenMinable;
import net.minecraft.server.v1_7_R3.WorldGenPumpkin;
import net.minecraft.server.v1_7_R3.WorldGenReed;
import net.minecraft.server.v1_7_R3.WorldGenSand;
import net.minecraft.server.v1_7_R3.WorldGenTreeAbstract;
import net.minecraft.server.v1_7_R3.WorldGenWaterLily;
import net.minecraft.server.v1_7_R3.WorldGenerator;

public class BiomeDecorator {

    public World a;
    public Random b;
    public int c;
    public int d;
    public WorldGenerator e = new WorldGenClay(4);
    public WorldGenerator f;
    public WorldGenerator g;
    public WorldGenerator h;
    public WorldGenerator i;
    public WorldGenerator j;
    public WorldGenerator k;
    public WorldGenerator l;
    public WorldGenerator m;
    public WorldGenerator n;
    public WorldGenerator o;
    public WorldGenFlowers p;
    public WorldGenerator q;
    public WorldGenerator r;
    public WorldGenerator s;
    public WorldGenerator t;
    public WorldGenerator u;
    public WorldGenerator v;
    public int w;
    public int x;
    public int y;
    public int z;
    public int A;
    public int B;
    public int C;
    public int D;
    public int E;
    public int F;
    public int G;
    public int H;
    public boolean I;

    public BiomeDecorator() {
        this.f = new WorldGenSand(Blocks.SAND, 7);
        this.g = new WorldGenSand(Blocks.GRAVEL, 6);
        this.h = new WorldGenMinable(Blocks.DIRT, 32);
        this.i = new WorldGenMinable(Blocks.GRAVEL, 32);
        this.j = new WorldGenMinable(Blocks.COAL_ORE, 16);
        this.k = new WorldGenMinable(Blocks.IRON_ORE, 8);
        this.l = new WorldGenMinable(Blocks.GOLD_ORE, 8);
        this.m = new WorldGenMinable(Blocks.REDSTONE_ORE, 7);
        this.n = new WorldGenMinable(Blocks.DIAMOND_ORE, 7);
        this.o = new WorldGenMinable(Blocks.LAPIS_ORE, 6);
        this.p = new WorldGenFlowers(Blocks.YELLOW_FLOWER);
        this.q = new WorldGenFlowers(Blocks.BROWN_MUSHROOM);
        this.r = new WorldGenFlowers(Blocks.RED_MUSHROOM);
        this.s = new WorldGenHugeMushroom();
        this.t = new WorldGenReed();
        this.u = new WorldGenCactus();
        this.v = new WorldGenWaterLily();
        this.y = 2;
        this.z = 1;
        this.E = 1;
        this.F = 3;
        this.G = 1;
        this.I = true;
    }

    public void a(World world, Random random, BiomeBase biomebase, int i, int j) {
        if(this.a != null) {
            throw new RuntimeException("Already decorating!!");
        }
        else {
            this.a = world;
            this.b = random;
            this.c = i;
            this.d = j;
            this.a(biomebase);
            this.a = null;
            this.b = null;
        }
    }

    public void a(BiomeBase biomebase) {
        this.a();

        int i;
        int j;
        int k;

        for(i = 0; i < this.F; ++i) {
            j = this.c + this.b.nextInt(16) + 8;
            k = this.d + this.b.nextInt(16) + 8;
            this.f.a(this.a, this.b, j, this.a.i(j, k), k);
        }

        for(i = 0; i < this.G; ++i) {
            j = this.c + this.b.nextInt(16) + 8;
            k = this.d + this.b.nextInt(16) + 8;
            this.e.a(this.a, this.b, j, this.a.i(j, k), k);
        }

        for(i = 0; i < this.E; ++i) {
            j = this.c + this.b.nextInt(16) + 8;
            k = this.d + this.b.nextInt(16) + 8;
            this.g.a(this.a, this.b, j, this.a.i(j, k), k);
        }

        i = this.x;
        if(this.b.nextInt(10) == 0) {
            ++i;
        }

        int l;
        int i1;

        for(j = 0; j < i; ++j) {
            k = this.c + this.b.nextInt(16) + 8;
            l = this.d + this.b.nextInt(16) + 8;
            i1 = this.a.getHighestBlockYAt(k, l);
            WorldGenTreeAbstract worldgentreeabstract = biomebase.getTreeGen(this.b);

            worldgentreeabstract.a(1.0D, 1.0D, 1.0D);
            if(worldgentreeabstract.a(this.a, this.b, k, i1, l)) {
                worldgentreeabstract.b(this.a, this.b, k, i1, l);
            }
        }

        for(j = 0; j < this.H; ++j) {
            k = this.c + this.b.nextInt(16) + 8;
            l = this.d + this.b.nextInt(16) + 8;
            this.s.a(this.a, this.b, k, this.a.getHighestBlockYAt(k, l), l);
        }

        for(j = 0; j < this.y; ++j) {
            k = this.c + this.b.nextInt(16) + 8;
            l = this.d + this.b.nextInt(16) + 8;
            i1 = this.b.nextInt(this.a.getHighestBlockYAt(k, l) + 32);
            String s = biomebase.getFlowerName(this.b, k, i1, l);
            BlockFlowers blockflowers = BlockFlowers.e(s);

            if(blockflowers.getMaterial() != Material.AIR) {
                this.p.a(blockflowers, BlockFlowers.f(s));
                this.p.a(this.a, this.b, k, i1, l);
            }
        }

        for(j = 0; j < this.z; ++j) {
            k = this.c + this.b.nextInt(16) + 8;
            l = this.d + this.b.nextInt(16) + 8;
            i1 = this.b.nextInt(this.a.getHighestBlockYAt(k, l) * 2);
            WorldGenerator worldgenerator = biomebase.createGrassGen(this.b);

            worldgenerator.a(this.a, this.b, k, i1, l);
        }

        for(j = 0; j < this.A; ++j) {
            k = this.c + this.b.nextInt(16) + 8;
            l = this.d + this.b.nextInt(16) + 8;
            i1 = this.b.nextInt(this.a.getHighestBlockYAt(k, l) * 2);
            (new WorldGenDeadBush(Blocks.DEAD_BUSH)).a(this.a, this.b, k, i1, l);
        }

        for(j = 0; j < this.w; ++j) {
            k = this.c + this.b.nextInt(16) + 8;
            l = this.d + this.b.nextInt(16) + 8;

            for(i1 = this.b.nextInt(this.a.getHighestBlockYAt(k, l) * 2); i1 > 0 && this.a.isEmpty(k, i1 - 1, l); --i1) {
                ;
            }

            this.v.a(this.a, this.b, k, i1, l);
        }

        for(j = 0; j < this.B; ++j) {
            if(this.b.nextInt(4) == 0) {
                k = this.c + this.b.nextInt(16) + 8;
                l = this.d + this.b.nextInt(16) + 8;
                i1 = this.a.getHighestBlockYAt(k, l);
                this.q.a(this.a, this.b, k, i1, l);
            }

            if(this.b.nextInt(8) == 0) {
                k = this.c + this.b.nextInt(16) + 8;
                l = this.d + this.b.nextInt(16) + 8;
                i1 = this.b.nextInt(this.a.getHighestBlockYAt(k, l) * 2);
                this.r.a(this.a, this.b, k, i1, l);
            }
        }

        if(this.b.nextInt(4) == 0) {
            j = this.c + this.b.nextInt(16) + 8;
            k = this.d + this.b.nextInt(16) + 8;
            l = this.b.nextInt(this.a.getHighestBlockYAt(j, k) * 2);
            this.q.a(this.a, this.b, j, l, k);
        }

        if(this.b.nextInt(8) == 0) {
            j = this.c + this.b.nextInt(16) + 8;
            k = this.d + this.b.nextInt(16) + 8;
            l = this.b.nextInt(this.a.getHighestBlockYAt(j, k) * 2);
            this.r.a(this.a, this.b, j, l, k);
        }

        for(j = 0; j < this.C; ++j) {
            k = this.c + this.b.nextInt(16) + 8;
            l = this.d + this.b.nextInt(16) + 8;
            i1 = this.b.nextInt(this.a.getHighestBlockYAt(k, l) * 2);
            this.t.a(this.a, this.b, k, i1, l);
        }

        for(j = 0; j < 10; ++j) {
            k = this.c + this.b.nextInt(16) + 8;
            l = this.d + this.b.nextInt(16) + 8;
            i1 = this.b.nextInt(this.a.getHighestBlockYAt(k, l) * 2);
            this.t.a(this.a, this.b, k, i1, l);
        }

        if(this.b.nextInt(32) == 0) {
            j = this.c + this.b.nextInt(16) + 8;
            k = this.d + this.b.nextInt(16) + 8;
            l = this.b.nextInt(this.a.getHighestBlockYAt(j, k) * 2);
            (new WorldGenPumpkin()).a(this.a, this.b, j, l, k);
        }

        for(j = 0; j < this.D; ++j) {
            k = this.c + this.b.nextInt(16) + 8;
            l = this.d + this.b.nextInt(16) + 8;
            i1 = this.b.nextInt(this.a.getHighestBlockYAt(k, l) * 2);
            this.u.a(this.a, this.b, k, i1, l);
        }

        if(this.I) {
            for(j = 0; j < 50; ++j) {
                k = this.c + this.b.nextInt(16) + 8;
                l = this.b.nextInt(this.b.nextInt(248) + 8);
                i1 = this.d + this.b.nextInt(16) + 8;
                (new WorldGenLiquids(Blocks.WATER)).a(this.a, this.b, k, l, i1);
            }

            for(j = 0; j < 20; ++j) {
                k = this.c + this.b.nextInt(16) + 8;
                l = this.b.nextInt(this.b.nextInt(this.b.nextInt(240) + 8) + 8);
                i1 = this.d + this.b.nextInt(16) + 8;
                (new WorldGenLiquids(Blocks.LAVA)).a(this.a, this.b, k, l, i1);
            }
        }
    }

    public void a(int i, WorldGenerator worldgenerator, int j, int k) {
        for(int l = 0; l < i; ++l) {
            int i1 = this.c + this.b.nextInt(16);
            int j1 = this.b.nextInt(k - j) + j;
            int k1 = this.d + this.b.nextInt(16);

            worldgenerator.a(this.a, this.b, i1, j1, k1);
        }
    }

    public void b(int i, WorldGenerator worldgenerator, int j, int k) {
        for(int l = 0; l < i; ++l) {
            int i1 = this.c + this.b.nextInt(16);
            int j1 = this.b.nextInt(k) + this.b.nextInt(k) + (j - k);
            int k1 = this.d + this.b.nextInt(16);

            worldgenerator.a(this.a, this.b, i1, j1, k1);
        }
    }

    public void a() {
        this.a(20, this.h, 0, 256);
        this.a(10, this.i, 0, 256);
        this.a(20, this.j, 0, 128);
        this.a(20, this.k, 0, 64);
        this.a(2, this.l, 0, 32);
        this.a(8, this.m, 0, 16);
        this.a(1, this.n, 0, 16);
        this.b(1, this.o, 16, 16);
    }
}