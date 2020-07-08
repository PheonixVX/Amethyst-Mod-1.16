package com.github.PheonixVX;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.MathHelper;

public class BlockAmethystOre extends Block {

    public BlockAmethystOre(FabricBlockSettings block$Settings_1) {
        super(block$Settings_1);
    }

    public void onStacksDropped(BlockState blockState_1, World world_1, BlockPos blockPos_1, ItemStack itemStack_1) {
        super.onStacksDropped(blockState_1, world_1, blockPos_1, itemStack_1);
        if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, itemStack_1) == 0) {
            int int_1 = MathHelper.nextInt(world_1.random, 3, 7);
            if (int_1 > 0) {
                this.dropExperience(world_1, blockPos_1, int_1);
            }
        }
    }

}
