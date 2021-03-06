package com.tenjava.entries.mncat77.t3.biome;

import com.tenjava.entries.mncat77.t3.generator.BiomeDecorator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.server.v1_7_R3.BiomeMeta;
import net.minecraft.server.v1_7_R3.BiomeTemperature;
import net.minecraft.server.v1_7_R3.Block;
import net.minecraft.server.v1_7_R3.BlockFlowers;
import net.minecraft.server.v1_7_R3.Blocks;
import net.minecraft.server.v1_7_R3.EntityBat;
import net.minecraft.server.v1_7_R3.EntityChicken;
import net.minecraft.server.v1_7_R3.EntityCow;
import net.minecraft.server.v1_7_R3.EntityCreeper;
import net.minecraft.server.v1_7_R3.EntityEnderman;
import net.minecraft.server.v1_7_R3.EntityGiantZombie;
import net.minecraft.server.v1_7_R3.EntityPig;
import net.minecraft.server.v1_7_R3.EntitySheep;
import net.minecraft.server.v1_7_R3.EntitySkeleton;
import net.minecraft.server.v1_7_R3.EntitySlime;
import net.minecraft.server.v1_7_R3.EntitySpider;
import net.minecraft.server.v1_7_R3.EntitySquid;
import net.minecraft.server.v1_7_R3.EntityWitch;
import net.minecraft.server.v1_7_R3.EntityWither;
import net.minecraft.server.v1_7_R3.EntityZombie;
import net.minecraft.server.v1_7_R3.EnumCreatureType;
import net.minecraft.server.v1_7_R3.EnumTemperature;
import net.minecraft.server.v1_7_R3.Material;
import net.minecraft.server.v1_7_R3.NoiseGenerator3;
import net.minecraft.server.v1_7_R3.World;
import net.minecraft.server.v1_7_R3.WorldGenBigTree;
import net.minecraft.server.v1_7_R3.WorldGenGrass;
import net.minecraft.server.v1_7_R3.WorldGenSwampTree;
import net.minecraft.server.v1_7_R3.WorldGenTallPlant;
import net.minecraft.server.v1_7_R3.WorldGenTreeAbstract;
import net.minecraft.server.v1_7_R3.WorldGenTrees;
import net.minecraft.server.v1_7_R3.WorldGenerator;
import net.minecraft.util.com.google.common.collect.Sets;

public abstract class BiomeBase {

    //Who named this class BiomeTemperature? ._. It wraps around Humidity aswell
    protected static final BiomeTemperature a = new BiomeTemperature(0.1F, 0.2F);
    protected static final BiomeTemperature b = new BiomeTemperature(-0.5F, 0.0F);
    protected static final BiomeTemperature c = new BiomeTemperature(-1.0F, 0.1F);
    protected static final BiomeTemperature d = new BiomeTemperature(-1.8F, 0.1F);
    protected static final BiomeTemperature e = new BiomeTemperature(0.125F, 0.05F);
    protected static final BiomeTemperature f = new BiomeTemperature(0.2F, 0.2F);
    protected static final BiomeTemperature g = new BiomeTemperature(0.45F, 0.3F);
    protected static final BiomeTemperature h = new BiomeTemperature(1.5F, 0.025F);
    protected static final BiomeTemperature i = new BiomeTemperature(1.0F, 0.5F);
    protected static final BiomeTemperature j = new BiomeTemperature(0.0F, 0.025F);
    protected static final BiomeTemperature k = new BiomeTemperature(0.1F, 0.8F);
    protected static final BiomeTemperature l = new BiomeTemperature(0.2F, 0.3F);
    protected static final BiomeTemperature m = new BiomeTemperature(-0.2F, 0.1F);
    public static final BiomeBase[] byId = new BiomeBase[256];
    public static final Set n = Sets.newHashSet();
    //All the biomes as constants
    public static final BiomeBase OCEAN = (new BiomeOcean(0)).b(112).a("Ocean").setTemperatureHumidity(c);
    public static final BiomeBase PLAINS = (new BiomePlains(1)).b(9286496).a("Plains");
    public static final BiomeBase DESERT = (new BiomeDesert(2)).b(16421912).a("Desert").b().setTemperatureHumidity(2.0F, 0.0F).setTemperatureHumidity(e);
    public static final BiomeBase EXTREME_HILLS = (new BiomeBigHills(3, false)).b(6316128).a("Extreme Hills").setTemperatureHumidity(i).setTemperatureHumidity(0.2F, 0.3F);
    public static final BiomeBase FOREST = (new BiomeForest(4, 0)).b(353825).a("Forest");
    public static final BiomeBase TAIGA = (new BiomeTaiga(5, 0)).b(747097).a("Taiga").a(5159473).setTemperatureHumidity(0.25F, 0.8F).setTemperatureHumidity(f);
    public static final BiomeBase SWAMPLAND = (new BiomeSwamp(6)).b(522674).a("Swampland").a(9154376).setTemperatureHumidity(m).setTemperatureHumidity(0.8F, 0.9F);
    public static final BiomeBase RIVER = (new BiomeRiver(7)).b(255).a("River").setTemperatureHumidity(b);
    //public static final BiomeBase HELL = (new BiomeHell(8)).b(16711680).a("Hell").b().a(2.0F, 0.0F);
    //public static final BiomeBase SKY = (new BiomeTheEnd(9)).b(8421631).a("Sky").b();
    public static final BiomeBase FROZEN_OCEAN = (new BiomeOcean(10)).b(9474208).a("FrozenOcean").c().setTemperatureHumidity(c).setTemperatureHumidity(0.0F, 0.5F);
    public static final BiomeBase FROZEN_RIVER = (new BiomeRiver(11)).b(10526975).a("FrozenRiver").c().setTemperatureHumidity(b).setTemperatureHumidity(0.0F, 0.5F);
    public static final BiomeBase ICE_PLAINS = (new BiomeIcePlains(12, false)).b(16777215).a("Ice Plains").c().setTemperatureHumidity(0.0F, 0.5F).setTemperatureHumidity(e);
    public static final BiomeBase ICE_MOUNTAINS = (new BiomeIcePlains(13, false)).b(10526880).a("Ice Mountains").c().setTemperatureHumidity(g).setTemperatureHumidity(0.0F, 0.5F);
    //public static final BiomeBase MUSHROOM_ISLAND = (new BiomeMushrooms(14)).b(16711935).a("MushroomIsland").a(0.9F, 1.0F).a(l);
    //public static final BiomeBase MUSHROOM_SHORE = (new BiomeMushrooms(15)).b(10486015).a("MushroomIslandShore").a(0.9F, 1.0F).a(j);
    public static final BiomeBase BEACH = (new BiomeBeach(16)).b(16440917).a("Beach").setTemperatureHumidity(0.8F, 0.4F).setTemperatureHumidity(j);
    public static final BiomeBase DESERT_HILLS = (new BiomeDesert(17)).b(13786898).a("DesertHills").b().setTemperatureHumidity(2.0F, 0.0F).setTemperatureHumidity(g);
    public static final BiomeBase FOREST_HILLS = (new BiomeForest(18, 0)).b(2250012).a("ForestHills").setTemperatureHumidity(g);
    public static final BiomeBase TAIGA_HILLS = (new BiomeTaiga(19, 0)).b(1456435).a("TaigaHills").a(5159473).setTemperatureHumidity(0.25F, 0.8F).setTemperatureHumidity(g);
    public static final BiomeBase SMALL_MOUNTAINS = (new BiomeBigHills(20, true)).b(7501978).a("Extreme Hills Edge").setTemperatureHumidity(i.a()).setTemperatureHumidity(0.2F, 0.3F);
    public static final BiomeBase JUNGLE = (new BiomeJungle(21, false)).b(5470985).a("Jungle").a(5470985).setTemperatureHumidity(0.95F, 0.9F);
    public static final BiomeBase JUNGLE_HILLS = (new BiomeJungle(22, false)).b(2900485).a("JungleHills").a(5470985).setTemperatureHumidity(0.95F, 0.9F).setTemperatureHumidity(g);
    public static final BiomeBase JUNGLE_EDGE = (new BiomeJungle(23, true)).b(6458135).a("JungleEdge").a(5470985).setTemperatureHumidity(0.95F, 0.8F);
    public static final BiomeBase DEEP_OCEAN = (new BiomeOcean(24)).b(48).a("Deep Ocean").setTemperatureHumidity(d);
    public static final BiomeBase STONE_BEACH = (new BiomeStoneBeach(25)).b(10658436).a("Stone Beach").setTemperatureHumidity(0.2F, 0.3F).setTemperatureHumidity(k);
    public static final BiomeBase COLD_BEACH = (new BiomeBeach(26)).b(16445632).a("Cold Beach").setTemperatureHumidity(0.05F, 0.3F).setTemperatureHumidity(j).c();
    public static final BiomeBase BIRCH_FOREST = (new BiomeForest(27, 2)).a("Birch Forest").b(3175492);
    public static final BiomeBase BIRCH_FOREST_HILLS = (new BiomeForest(28, 2)).a("Birch Forest Hills").b(2055986).setTemperatureHumidity(g);
    public static final BiomeBase ROOFED_FOREST = (new BiomeForest(29, 3)).b(4215066).a("Roofed Forest");
    public static final BiomeBase COLD_TAIGA = (new BiomeTaiga(30, 0)).b(3233098).a("Cold Taiga").a(5159473).c().setTemperatureHumidity(-0.5F, 0.4F).setTemperatureHumidity(f).c(16777215);
    public static final BiomeBase COLD_TAIGA_HILLS = (new BiomeTaiga(31, 0)).b(2375478).a("Cold Taiga Hills").a(5159473).c().setTemperatureHumidity(-0.5F, 0.4F).setTemperatureHumidity(g).c(16777215);
    public static final BiomeBase MEGA_TAIGA = (new BiomeTaiga(32, 1)).b(5858897).a("Mega Taiga").a(5159473).setTemperatureHumidity(0.3F, 0.8F).setTemperatureHumidity(f);
    public static final BiomeBase MEGA_TAIGA_HILLS = (new BiomeTaiga(33, 1)).b(4542270).a("Mega Taiga Hills").a(5159473).setTemperatureHumidity(0.3F, 0.8F).setTemperatureHumidity(g);
    public static final BiomeBase EXTREME_HILLS_PLUS = (new BiomeBigHills(34, true)).b(5271632).a("Extreme Hills+").setTemperatureHumidity(i).setTemperatureHumidity(0.2F, 0.3F);
    public static final BiomeBase SAVANNA = (new BiomeSavanna(35)).b(12431967).a("Savanna").setTemperatureHumidity(1.2F, 0.0F).b().setTemperatureHumidity(e);
    public static final BiomeBase SAVANNA_PLATEAU = (new BiomeSavanna(36)).b(10984804).a("Savanna Plateau").setTemperatureHumidity(1.0F, 0.0F).b().setTemperatureHumidity(h);
    public static final BiomeBase MESA = (new BiomeMesa(37, false, false)).b(14238997).a("Mesa");
    public static final BiomeBase MESA_PLATEAU_F = (new BiomeMesa(38, false, true)).b(11573093).a("Mesa Plateau F").setTemperatureHumidity(h);
    public static final BiomeBase MESA_PLATEAU = (new BiomeMesa(39, false, false)).b(13274213).a("Mesa Plateau").setTemperatureHumidity(h);

    protected static final NoiseGenerator3 ac;
    protected static final NoiseGenerator3 ad;
    protected static final WorldGenTallPlant ae;
    public String af;
    public int ag;
    public int ah;
    public Block topBlock;
    public int aj;
    public Block fillerBlock;
    public int al;
    public float am;
    public float an;
    public float temperature;
    public float humidity;
    public int aq;
    public BiomeDecorator decorator;
    protected List monsters;
    protected List creatures;
    protected List waterCreatures;
    protected List ambient;
    protected boolean aw;
    protected boolean ax;
    public final int id;
    protected WorldGenTrees az;
    protected WorldGenBigTree aA;
    protected WorldGenSwampTree aB;

    //Default Biome Value (unchanged for Plains)
    protected BiomeBase(int id) {
        this.topBlock = Blocks.GRASS;
        this.aj = 0;
        this.fillerBlock = Blocks.DIRT;
        this.al = 5169201;
        this.am = a.a;
        this.an = a.b;
        this.temperature = 0.5F;
        this.humidity = 0.5F;
        this.aq = 16777215;
        this.monsters = new ArrayList();
        this.creatures = new ArrayList();
        this.waterCreatures = new ArrayList();
        this.ambient = new ArrayList();
        this.ax = true;
        this.az = new WorldGenTrees(false);
        this.aA = new WorldGenBigTree(false);
        this.aB = new WorldGenSwampTree();
        this.id = id;
        byId[id] = this;
        this.decorator = this.createBiomeDecorator();

        //Mobs that can spawn in the biome in 4 categories: Neutral(creature), Hostile(monster), Water Creatures and Ambient
        this.creatures.add(new BiomeMeta(EntitySheep.class, 12, 1, 1));
        this.creatures.add(new BiomeMeta(EntityPig.class, 10, 1, 1));
        this.creatures.add(new BiomeMeta(EntityChicken.class, 10, 1, 1));
        this.creatures.add(new BiomeMeta(EntityCow.class, 8, 1, 1));
        this.monsters.add(new BiomeMeta(EntitySpider.class, 100, 6, 6));
        this.monsters.add(new BiomeMeta(EntityZombie.class, 100, 9, 9));
        this.monsters.add(new BiomeMeta(EntitySkeleton.class, 100, 7, 7));
        this.monsters.add(new BiomeMeta(EntityCreeper.class, 100, 8, 8));
        this.monsters.add(new BiomeMeta(EntitySlime.class, 100, 4, 4));
        this.monsters.add(new BiomeMeta(EntityEnderman.class, 80, 3, 7));
        this.monsters.add(new BiomeMeta(EntityWitch.class, 60, 4, 6));
        this.monsters.add(new BiomeMeta(EntityGiantZombie.class, 13, 1, 1));
        this.monsters.add(new BiomeMeta(EntityWither.class, 1, 1, 1));
        this.waterCreatures.add(new BiomeMeta(EntitySquid.class, 10, 4, 4));
        this.ambient.add(new BiomeMeta(EntityBat.class, 10, 8, 8));
    }

    protected BiomeDecorator createBiomeDecorator() {
        return new BiomeDecorator();
    }

    protected BiomeBase setTemperatureHumidity(float f, float f1) {
        if(f > 0.1F && f < 0.2F) {
            throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
        }
        else {
            this.temperature = f;
            this.humidity = f1;
            return this;
        }
    }

    protected final BiomeBase setTemperatureHumidity(BiomeTemperature biometemperature) {
        this.am = biometemperature.a;
        this.an = biometemperature.b;
        return this;
    }

    protected BiomeBase b() {
        this.ax = false;
        return this;
    }

    public WorldGenTreeAbstract getTreeGen(Random random) {
        return (WorldGenTreeAbstract)(random.nextInt(10) == 0 ? this.aA : this.az);
    }

    public WorldGenerator createGrassGen(Random random) {
        return new WorldGenGrass(Blocks.LONG_GRASS, 1);
    }

    public String getFlowerName(Random random, int i, int j, int k) {
        return random.nextInt(3) > 0 ? BlockFlowers.b[0] : BlockFlowers.a[0];
    }

    protected BiomeBase c() {
        this.aw = true;
        return this;
    }

    protected BiomeBase a(String s) {
        this.af = s;
        return this;
    }

    protected BiomeBase a(int i) {
        this.al = i;
        return this;
    }

    protected BiomeBase b(int i) {
        this.a(i, false);
        return this;
    }

    protected BiomeBase c(int i) {
        this.ah = i;
        return this;
    }

    protected BiomeBase a(int i, boolean flag) {
        this.ag = i;
        if(flag) {
            this.ah = (i & 16711422) >> 1;
        }
        else {
            this.ah = i;
        }

        return this;
    }

    public List getMobs(EnumCreatureType enumcreaturetype) {
        return enumcreaturetype == EnumCreatureType.MONSTER ? this.monsters : (enumcreaturetype == EnumCreatureType.CREATURE ? this.creatures : (enumcreaturetype == EnumCreatureType.WATER_CREATURE ? this.waterCreatures : (enumcreaturetype == EnumCreatureType.AMBIENT ? this.ambient : null)));
    }

    public boolean d() {
        return this.j();
    }

    public boolean e() {
        return this.j() ? false : this.ax;
    }

    public boolean f() {
        return this.humidity > 0.85F;
    }

    public float g() {
        return 0.1F;
    }

    public final int h() {
        return (int)(this.humidity * 65536.0F);
    }

    public final float a(int i, int j, int k) {
        if(j > 64) {
            float f = (float)ac.a((double)i * 1.0D / 8.0D, (double)k * 1.0D / 8.0D) * 4.0F;

            return this.temperature - (f + (float)j - 64.0F) * 0.05F / 30.0F;
        }
        else {
            return this.temperature;
        }
    }

    public void a(World world, Random random, int i, int j) {
        this.decorator.a(world, random, this, i, j);
    }

    public boolean j() {
        return this.aw;
    }

    public void a(World world, Random random, Block[] ablock, byte[] abyte, int i, int j, double d0) {
        this.b(world, random, ablock, abyte, i, j, d0);
    }

    public final void b(World world, Random random, Block[] ablock, byte[] abyte, int i, int j, double d0) {
        boolean flag = true;
        Block block = this.topBlock;
        byte b0 = (byte)(this.aj & 255);
        Block block1 = this.fillerBlock;
        int k = -1;
        int l = (int)(d0 / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        int i1 = i & 15;
        int j1 = j & 15;
        int k1 = ablock.length / 256;

        for(int l1 = 255; l1 >= 0; --l1) {
            int i2 = (j1 * 16 + i1) * k1 + l1;

            if(l1 <= 0 + random.nextInt(5)) {
                ablock[i2] = Blocks.BEDROCK;
            }
            else {
                Block block2 = ablock[i2];

                if(block2 != null && block2.getMaterial() != Material.AIR) {
                    if(block2 == Blocks.STONE) {
                        if(k == -1) {
                            if(l <= 0) {
                                block = null;
                                b0 = 0;
                                block1 = Blocks.STONE;
                            }
                            else if(l1 >= 59 && l1 <= 64) {
                                block = this.topBlock;
                                b0 = (byte)(this.aj & 255);
                                block1 = this.fillerBlock;
                            }

                            if(l1 < 63 && (block == null || block.getMaterial() == Material.AIR)) {
                                if(this.a(i, l1, j) < 0.15F) {
                                    block = Blocks.ICE;
                                    b0 = 0;
                                }
                                else {
                                    block = Blocks.STATIONARY_WATER;
                                    b0 = 0;
                                }
                            }

                            k = l;
                            if(l1 >= 62) {
                                ablock[i2] = block;
                                abyte[i2] = b0;
                            }
                            else if(l1 < 56 - l) {
                                block = null;
                                block1 = Blocks.STONE;
                                ablock[i2] = Blocks.GRAVEL;
                            }
                            else {
                                ablock[i2] = block1;
                            }
                        }
                        else if(k > 0) {
                            --k;
                            ablock[i2] = block1;
                            if(k == 0 && block1 == Blocks.SAND) {
                                k = random.nextInt(4) + Math.max(0, l1 - 63);
                                block1 = Blocks.SANDSTONE;
                            }
                        }
                    }
                }
                else {
                    k = -1;
                }
            }
        }
    }

    protected BiomeBase k() {
        return new BiomeBaseSub(this.id + 128, this);
    }

    public Class l() {
        return this.getClass();
    }

    public boolean a(BiomeBase biomebase) {
        return biomebase == this ? true : (biomebase == null ? false : this.l() == biomebase.l());
    }

    public EnumTemperature m() {
        return (double)this.temperature < 0.2D ? EnumTemperature.COLD : ((double)this.temperature < 1.0D ? EnumTemperature.MEDIUM : EnumTemperature.WARM);
    }

    public static BiomeBase[] getBiomes() {
        return byId;
    }

    public static BiomeBase getBiome(int i) {
        if(i >= 0 && i <= byId.length) {
            return byId[i];
        }
        else {
            return OCEAN;
        }
    }

    static {
        PLAINS.k();
        DESERT.k();
        FOREST.k();
        TAIGA.k();
        SWAMPLAND.k();
        ICE_PLAINS.k();
        JUNGLE.k();
        JUNGLE_EDGE.k();
        COLD_TAIGA.k();
        SAVANNA.k();
        SAVANNA_PLATEAU.k();
        MESA.k();
        MESA_PLATEAU_F.k();
        MESA_PLATEAU.k();
        BIRCH_FOREST.k();
        BIRCH_FOREST_HILLS.k();
        ROOFED_FOREST.k();
        MEGA_TAIGA.k();
        EXTREME_HILLS.k();
        EXTREME_HILLS_PLUS.k();
        byId[MEGA_TAIGA_HILLS.id + 128] = byId[MEGA_TAIGA.id + 128];
        BiomeBase[] abiomebase = byId;
        int i = abiomebase.length;

        for(int j = 0; j < i; ++j) {
            BiomeBase biomebase = abiomebase[j];

            if(biomebase != null && biomebase.id < 128) {
                n.add(biomebase);
                byId[biomebase.id] = biomebase;
            }
        }

        n.remove(FROZEN_OCEAN);
        n.remove(SMALL_MOUNTAINS);
        ac = new NoiseGenerator3(new Random(1234L), 1);
        ad = new NoiseGenerator3(new Random(2345L), 1);
        ae = new WorldGenTallPlant();
    }

    /*The id of the material the top block will have, e.g. for plains grass.
     */
    public int topBlockId = 2;
    /*The id of the material that will be filling in the space between the top block and stone, e.g. for plains dirt
     */
    public int fillerBlockId = 3;

    public boolean equals(BiomeBase other) {
        return other == this ? true : (other == null ? false : this.getClass() == other.getClass());
    }

}
