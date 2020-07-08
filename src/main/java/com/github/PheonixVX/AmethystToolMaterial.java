package com.github.PheonixVX;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class AmethystToolMaterial implements ToolMaterial {

    @Override
    public int getDurability() {
        return 2000;
    }

    @Override
    public float getAttackDamage() {
        return 4.0F;
    }

    @Override
    public int getMiningLevel() {
        return 3;
    }

    @Override
    public int getEnchantability() {
        return 10;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(AmethystMod.amethyst);
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 10.0F;
    }

}
