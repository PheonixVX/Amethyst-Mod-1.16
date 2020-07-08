package com.github.PheonixVX;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class AmethystArmorMaterial implements ArmorMaterial {

    private static final int[] BASE_DURABILITY = new int[] { 13, 15, 16, 11 };
    private static final int[] PROTECTION_VALUES = new int[] {4, 7, 9, 4};

    @Override
    public int getDurability(EquipmentSlot equipmentSlot_1) {
        return BASE_DURABILITY[equipmentSlot_1.getEntitySlotId()] * 45;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot equipmentSlot_1) {
        return PROTECTION_VALUES[equipmentSlot_1.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return 10;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(AmethystMod.amethyst);
    }

    @Override
    public String getName() {
        return "amethyst";
    }

    @Override
    public float getToughness() {
        return 2;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }

}
