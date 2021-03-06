package com.tenjava.entries.mncat77.t3.biome;

import java.util.Random;
import net.minecraft.server.v1_7_R3.BiomeMeta;
import net.minecraft.server.v1_7_R3.BlockFlowers;
import net.minecraft.server.v1_7_R3.EntityHorse;
import net.minecraft.server.v1_7_R3.World;

public class BiomePlains extends BiomeBase {

    protected boolean aC;

    protected BiomePlains(int i) {
        super(i);
        this.setTemperatureHumidity(0.8F, 0.4F);
        this.setTemperatureHumidity(e);
        this.creatures.add(new BiomeMeta(EntityHorse.class, 5, 2, 6));
        this.decorator.x = -999;
        this.decorator.flowersC = 4;
        this.decorator.grassC = 10;
    }

    public String a(Random random, int i, int j, int k) {
        double d0 = ad.a((double)i / 200.0D, (double)k / 200.0D);
        int l;

        if(d0 < -0.8D) {
            l = random.nextInt(4);
            return BlockFlowers.a[4 + l];
        }
        else if(random.nextInt(3) > 0) {
            l = random.nextInt(3);
            return l == 0 ? BlockFlowers.a[0] : (l == 1 ? BlockFlowers.a[3] : BlockFlowers.a[8]);
        }
        else {
            return BlockFlowers.b[0];
        }
    }

    @Override
    public void a(World world, Random random, int i, int j) {
        double d0 = ad.a((double)(i + 8) / 200.0D, (double)(j + 8) / 200.0D);
        int k;
        int l;
        int i1;
        int j1;

        if(d0 < -0.8D) {
            this.decorator.flowersC = 15;
            this.decorator.grassC = 5;
        }
        else {
            this.decorator.flowersC = 4;
            this.decorator.grassC = 10;
            ae.a(2);

            for(k = 0; k < 7; ++k) {
                l = i + random.nextInt(16) + 8;
                i1 = j + random.nextInt(16) + 8;
                j1 = random.nextInt(world.getHighestBlockYAt(l, i1) + 32);
                ae.a(world, random, l, j1, i1);
            }
        }

        if(this.aC) {
            ae.a(0);

            for(k = 0; k < 10; ++k) {
                l = i + random.nextInt(16) + 8;
                i1 = j + random.nextInt(16) + 8;
                j1 = random.nextInt(world.getHighestBlockYAt(l, i1) + 32);
                ae.a(world, random, l, j1, i1);
            }
        }

        super.a(world, random, i, j);
    }

    @Override
    protected BiomeBase k() {
        BiomePlains biomeplains = new BiomePlains(this.id + 128);

        biomeplains.a("Sunflower Plains");
        biomeplains.aC = true;
        biomeplains.b(9286496);
        biomeplains.ah = 14273354;
        return biomeplains;
    }
}
