package com.github.PheonixVX;

import java.util.Map;
import java.util.Set;

import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.block.Blocks;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.BlockState;
import net.minecraft.sound.SoundEvents;
import net.minecraft.sound.SoundCategory;
import net.minecraft.block.Material;
import com.google.common.collect.Sets;
import com.google.common.collect.ImmutableMap.Builder;

public class ItemMultitool extends MiningToolItem {

    private static final Set<Block> EFFECTIVE_BLOCKS = Sets.newHashSet(new Block[] {});
    protected static final Map<Block, Block> BLOCK_TRANSFORMATIONS_MAP = (new Builder<Block, Block>())
            .put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD).put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG)
            .put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD)
            .put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG).put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD)
            .put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG).put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD)
            .put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG).put(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD)
            .put(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG).put(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD)
            .put(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG).put(Blocks.GRASS_BLOCK, Blocks.GRASS_PATH).build();

    protected ItemMultitool(float attackSpeed, float attackDamage, ToolMaterial toolMaterial_1, Item.Settings itemSettings) {
        super(attackSpeed, attackDamage, toolMaterial_1, EFFECTIVE_BLOCKS, itemSettings);
    }

    public float getMiningSpeedMultiplier(ItemStack itemStack_1, BlockState blockState_1) {
        //return this.getMiningSpeedMultiplier(itemStack_1, blockState_1) ? this.miningSpeed : 1.0F;
        return 1.0f;
    }

    public ActionResult useOnBlock(ItemUsageContext itemUsageContext_1) {
        World world_1 = itemUsageContext_1.getWorld();
        BlockPos blockPos_1 = itemUsageContext_1.getBlockPos();
        BlockState blockState_1 = world_1.getBlockState(blockPos_1);
        Block block_1 = BLOCK_TRANSFORMATIONS_MAP.get(blockState_1.getBlock());
        if (block_1 != null) {
            PlayerEntity playerEntity_1 = itemUsageContext_1.getPlayer();
            world_1.playSound(playerEntity_1, blockPos_1, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!world_1.isClient) {
                world_1.setBlockState(blockPos_1, (BlockState) block_1.getDefaultState().with(PillarBlock.AXIS,
                        blockState_1.get(PillarBlock.AXIS)), 11);
                if (playerEntity_1 != null) {
                    itemUsageContext_1.getStack().damage(1, playerEntity_1, null);
                }
            }

            return ActionResult.SUCCESS;
        } else {
            return ActionResult.PASS;
        }
    }

    public boolean isEffectiveOn(BlockState blockState_1) {
        if (blockState_1.getMaterial() == Material.GLASS || blockState_1.getMaterial() == Material.COBWEB || blockState_1.getMaterial() == Material.WOOL) {
            return false;
        } else {
            return true;
        }
    }

    public float getBlockBreakingSpeed(ItemStack itemStack_1, BlockState blockState_1) {
        Material material_1 = blockState_1.getMaterial();
        return material_1 != Material.PLANT && material_1 != Material.REPLACEABLE_PLANT
                ? super.getMiningSpeedMultiplier(itemStack_1, blockState_1)
                : this.miningSpeed;
    }

}
