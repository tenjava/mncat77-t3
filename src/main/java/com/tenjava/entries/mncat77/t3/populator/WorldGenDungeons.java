package com.tenjava.entries.mncat77.t3.populator;

import java.util.Random;
import net.minecraft.server.v1_7_R3.Blocks;
import net.minecraft.server.v1_7_R3.IInventory;
import net.minecraft.server.v1_7_R3.Items;
import net.minecraft.server.v1_7_R3.Material;
import net.minecraft.server.v1_7_R3.StructurePieceTreasure;
import net.minecraft.server.v1_7_R3.TileEntityChest;
import net.minecraft.server.v1_7_R3.TileEntityMobSpawner;
import net.minecraft.server.v1_7_R3.World;
import net.minecraft.server.v1_7_R3.WorldGenerator;

public class WorldGenDungeons extends WorldGenerator {

    //Goodies that can be in the chests
    private static final StructurePieceTreasure[] a = new StructurePieceTreasure[]{new StructurePieceTreasure(Items.PUMPKIN_PIE, 0, 1, 1, 10), new StructurePieceTreasure(Items.DIAMOND, 0, 1, 5, 8), new StructurePieceTreasure(Items.IRON_INGOT, 0, 1, 4, 10), new StructurePieceTreasure(Items.BREAD, 0, 1, 1, 10), new StructurePieceTreasure(Items.GOLDEN_APPLE, 0, 1, 2, 1), new StructurePieceTreasure(Items.NAME_TAG, 0, 1, 1, 10), new StructurePieceTreasure(Items.HORSE_ARMOR_GOLD, 0, 1, 1, 2), new StructurePieceTreasure(Items.HORSE_ARMOR_IRON, 0, 1, 1, 5), new StructurePieceTreasure(Items.HORSE_ARMOR_DIAMOND, 0, 1, 1, 1)};

    public WorldGenDungeons() {
    }

    @Override
    public boolean a(World world, Random random, int i, int j, int k) {
        byte b0 = 3;
        int l = random.nextInt(2) + 2;
        int i1 = random.nextInt(2) + 2;
        int j1 = 0;

        int k1;
        int l1;
        int i2;

        for(k1 = i - l - 1; k1 <= i + l + 1; ++k1) {
            for(l1 = j - 1; l1 <= j + b0 + 1; ++l1) {
                for(i2 = k - i1 - 1; i2 <= k + i1 + 1; ++i2) {
                    Material material = world.getType(k1, l1, i2).getMaterial();

                    if(l1 == j - 1 && !material.isBuildable()) {
                        return false;
                    }

                    if(l1 == j + b0 + 1 && !material.isBuildable()) {
                        return false;
                    }

                    if((k1 == i - l - 1 || k1 == i + l + 1 || i2 == k - i1 - 1 || i2 == k + i1 + 1) && l1 == j && world.isEmpty(k1, l1, i2) && world.isEmpty(k1, l1 + 1, i2)) {
                        ++j1;
                    }
                }
            }
        }

        if(j1 >= 1 && j1 <= 5) {
            for(k1 = i - l - 1; k1 <= i + l + 1; ++k1) {
                for(l1 = j + b0; l1 >= j - 1; --l1) {
                    for(i2 = k - i1 - 1; i2 <= k + i1 + 1; ++i2) {
                        if(k1 != i - l - 1 && l1 != j - 1 && i2 != k - i1 - 1 && k1 != i + l + 1 && l1 != j + b0 + 1 && i2 != k + i1 + 1) {
                            world.setAir(k1, l1, i2);
                        }
                        else if(l1 >= 0 && !world.getType(k1, l1 - 1, i2).getMaterial().isBuildable()) {
                            world.setAir(k1, l1, i2);
                        }
                        else if(world.getType(k1, l1, i2).getMaterial().isBuildable()) {
                            if(l1 == j - 1 && random.nextInt(4) != 0) {
                                world.setTypeAndData(k1, l1, i2, Blocks.MOSSY_COBBLESTONE, 0, 2);
                            }
                            else {
                                world.setTypeAndData(k1, l1, i2, Blocks.COBBLESTONE, 0, 2);
                            }
                        }
                    }
                }
            }

            k1 = 0;

            while(k1 < 2) {
                l1 = 0;

                while(true) {
                    if(l1 < 5) {
                        label101:
                        {
                            i2 = i + random.nextInt(l * 2 + 1) - l;
                            int j2 = k + random.nextInt(i1 * 2 + 1) - i1;

                            if(world.isEmpty(i2, j, j2)) {
                                int k2 = 0;

                                if(world.getType(i2 - 1, j, j2).getMaterial().isBuildable()) {
                                    ++k2;
                                }

                                if(world.getType(i2 + 1, j, j2).getMaterial().isBuildable()) {
                                    ++k2;
                                }

                                if(world.getType(i2, j, j2 - 1).getMaterial().isBuildable()) {
                                    ++k2;
                                }

                                if(world.getType(i2, j, j2 + 1).getMaterial().isBuildable()) {
                                    ++k2;
                                }

                                if(k2 == 1) {
                                    world.setTypeAndData(i2, j, j2, Blocks.TRAPPED_CHEST, 0, 2);
                                    StructurePieceTreasure[] astructurepiecetreasure = StructurePieceTreasure.a(a, new StructurePieceTreasure[]{Items.ENCHANTED_BOOK.b(random)});
                                    TileEntityChest tileentitychest = (TileEntityChest)world.getTileEntity(i2, j, j2);

                                    if(tileentitychest != null) {
                                        StructurePieceTreasure.a(random, astructurepiecetreasure, (IInventory)tileentitychest, 8);
                                    }
                                    break label101;
                                }
                            }

                            ++l1;
                            continue;
                        }
                    }

                    ++k1;
                    break;
                }
            }
            String m = this.a(random);
            for(int x = -1; x < 2; x++) {
                for(int z = -1; z < 2; z++) {
                    if(Math.abs(x) + Math.abs(z) < 2) {
                        world.setTypeAndData(i + x, j - 1, k + z, Blocks.MOB_SPAWNER, 0, 2);
                        TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)world.getTileEntity(i + x, j - 1, k + z);

                        if(tileentitymobspawner != null) {
                            tileentitymobspawner.a().a(m);
                        }
                        else {
                            System.err.println("Failed to fetch mob spawner entity at (" + i + ", " + j + ", " + k + ")");
                        }
                    }
                }
            }

            return true;
        }
        else {
            return false;
        }
    }

    //Randomize Spawner Mob Type
    private String a(Random random) {
        int i = random.nextInt(5);
        switch(i) {
            case 0:
                return "Skeleton";
            case 1:
                return "Zombie";
            case 2:
                return "Spider";
            case 3:
                return "CaveSpider";
            case 4:
                return "Enderman";
            default:
                return "";
        }
    }
}
