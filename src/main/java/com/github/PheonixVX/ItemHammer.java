package com.github.PheonixVX;

import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.loot.context.LootContext;

public class ItemHammer extends PickaxeItem {

    public ItemHammer(ToolMaterial toolMaterial_1, int int_1, float float_1, Item.Settings item$Settings_1) {
        super(toolMaterial_1, int_1, float_1, item$Settings_1);
    }

    public boolean postMine(ItemStack itemStack_1, World world_1, BlockState blockState_1, BlockPos blockPos_1,
                               LivingEntity livingEntity_1) {
        if (!world_1.isClient) {
            if (blockState_1.getHardness(world_1, blockPos_1) != 0.0F) {
                itemStack_1.damage(1, livingEntity_1, null);
            }

            PlayerEntity player = (PlayerEntity) livingEntity_1;
            if (player.pitch < -45 || player.pitch > 45) {
                for (int x = -1; x < 2; x++) {
                    for (int z = -1; z < 2; z++) {
                        BlockState blockState = world_1.getBlockState(blockPos_1.add(x, 0, z));
                        if (blockState.isAir() == false
                                && blockState.getHardness(world_1, blockPos_1.add(x, 0, z)) != -1.0F) {
                            itemStack_1.damage(1, livingEntity_1, null);

                            LootContext.Builder lootContextBuilder = (new LootContext.Builder((ServerWorld) world_1))
                                    .parameter(LootContextParameters.POSITION, blockPos_1.add(x, 0, z))
                                    .parameter(LootContextParameters.BLOCK_STATE, blockState)
                                    .parameter(LootContextParameters.TOOL, itemStack_1);
                            List<ItemStack> droppedItems = blockState.getDroppedStacks(lootContextBuilder);

                            for (int i = 0; i < droppedItems.size(); i++) {
                                Block.dropStack(world_1, blockPos_1.add(x, 0, z), droppedItems.get(i));
                            }

                            world_1.breakBlock(blockPos_1.add(x, 0, z), false);
                        }
                    }
                }
            } else if (player.getHorizontalFacing() == Direction.EAST
                    || player.getHorizontalFacing() == Direction.WEST) {
                for (int y = -1; y < 2; y++) {
                    for (int z = -1; z < 2; z++) {
                        BlockState blockState = world_1.getBlockState(blockPos_1.add(0, y, z));
                        if (blockState.isAir() == false
                                && blockState.getHardness(world_1, blockPos_1.add(0, y, z)) != -1.0F) {
                            itemStack_1.damage(1, livingEntity_1, null);

                            LootContext.Builder lootContextBuilder = (new LootContext.Builder((ServerWorld) world_1))
                                    .parameter(LootContextParameters.POSITION, blockPos_1.add(0, y, z))
                                    .parameter(LootContextParameters.BLOCK_STATE, blockState)
                                    .parameter(LootContextParameters.TOOL, itemStack_1);
                            List<ItemStack> droppedItems = blockState.getDroppedStacks(lootContextBuilder);

                            for (int i = 0; i < droppedItems.size(); i++) {
                                Block.dropStack(world_1, blockPos_1.add(0, y, z), droppedItems.get(i));
                            }

                            world_1.breakBlock(blockPos_1.add(0, y, z), false);
                        }
                    }
                }
            } else if (player.getHorizontalFacing() == Direction.SOUTH
                    || player.getHorizontalFacing() == Direction.NORTH) {
                for (int x = -1; x < 2; x++) {
                    for (int y = -1; y < 2; y++) {
                        BlockState blockState = world_1.getBlockState(blockPos_1.add(x, y, 0));
                        if (blockState.isAir() == false
                                && blockState.getHardness(world_1, blockPos_1.add(x, y, 0)) != -1.0F) {
                            itemStack_1.damage(1, livingEntity_1, null);

                            LootContext.Builder lootContextBuilder = (new LootContext.Builder((ServerWorld) world_1))
                                    .parameter(LootContextParameters.POSITION, blockPos_1.add(x, y, 0))
                                    .parameter(LootContextParameters.BLOCK_STATE, blockState)
                                    .parameter(LootContextParameters.TOOL, itemStack_1);
                            List<ItemStack> droppedItems = blockState.getDroppedStacks(lootContextBuilder);

                            for (int i = 0; i < droppedItems.size(); i++) {
                                Block.dropStack(world_1, blockPos_1.add(x, y, 0), droppedItems.get(i));
                            }

                            world_1.breakBlock(blockPos_1.add(x, y, 0), false);
                        }
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }
}
