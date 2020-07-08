package com.github.PheonixVX;

import com.mojang.serialization.Dynamic;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.world.biome.Biome;
import net.minecraft.block.Blocks;
import net.minecraft.block.Block;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.decorator.Decorator;

import java.util.function.Function;

public class AmethystMod implements ModInitializer {

    ItemGroup group;
    public static Item amethyst;

    @Override
    public void onInitialize() {
        group = FabricItemGroupBuilder.create(new Identifier("amethystmod", "amethystmod"))
                .icon(() -> new ItemStack(amethyst)).build();

        amethyst = new Item(new Item.Settings().group(group));
        Registry.register(Registry.ITEM, new Identifier("amethystmod", "amethyst"), amethyst);

        AmethystToolMaterial amethystToolMaterial = new AmethystToolMaterial();
        AmethystArmorMaterial amethystArmorMaterial = new AmethystArmorMaterial();

        Item amethystMultitool = new ItemMultitool(5f, -1.0f, amethystToolMaterial,
                new Item.Settings().group(group));
        Registry.register(Registry.ITEM, new Identifier("amethystmod", "amethyst_multitool"), amethystMultitool);

        Item amethystHammer = new ItemHammer(amethystToolMaterial, 1, -2.8f, new Item.Settings().group(group));
        Registry.register(Registry.ITEM, new Identifier("amethystmod", "amethyst_hammer"), amethystHammer);

        Item amethystSword = new SwordItem(amethystToolMaterial, 3, -2.4F, new Item.Settings().group(group));
        Registry.register(Registry.ITEM, new Identifier("amethystmod", "amethyst_sword"), amethystSword);

        Item amethystPickaxe = new ItemPickaxe(amethystToolMaterial, 1, -2.8f,
                new Item.Settings().group(group));
        Registry.register(Registry.ITEM, new Identifier("amethystmod", "amethyst_pickaxe"), amethystPickaxe);

        Item amethystShovel = new ShovelItem(amethystToolMaterial, 2F, -3.0F, new Item.Settings().group(group));
        Registry.register(Registry.ITEM, new Identifier("amethystmod", "amethyst_shovel"), amethystShovel);

        Item amethystAxe = new ItemAxe(amethystToolMaterial, 5.0F, -1.0F, new Item.Settings().group(group));
        Registry.register(Registry.ITEM, new Identifier("amethystmod", "amethyst_axe"), amethystAxe);

        Item amethystHoe = new AmethystHoeItem(amethystToolMaterial, 0, 4.0f, new Item.Settings().group(group));
        Registry.register(Registry.ITEM, new Identifier("amethystmod", "amethyst_hoe"), amethystHoe);

        Item amethystHelmet = new ArmorItem(amethystArmorMaterial, EquipmentSlot.HEAD,
                new Item.Settings().group(group));
        Registry.register(Registry.ITEM, new Identifier("amethystmod", "amethyst_helmet"), amethystHelmet);

        Item amethystChestplate = new ArmorItem(amethystArmorMaterial, EquipmentSlot.CHEST,
                new Item.Settings().group(group));
        Registry.register(Registry.ITEM, new Identifier("amethystmod", "amethyst_chestplate"), amethystChestplate);

        Item amethystLeggings = new ArmorItem(amethystArmorMaterial, EquipmentSlot.LEGS,
                new Item.Settings().group(group));
        Registry.register(Registry.ITEM, new Identifier("amethystmod", "amethyst_leggings"), amethystLeggings);

        Item amethystBoots = new ArmorItem(amethystArmorMaterial, EquipmentSlot.FEET,
                new Item.Settings().group(group));
        Registry.register(Registry.ITEM, new Identifier("amethystmod", "amethyst_boots"), amethystBoots);

        Block amethystOre = new BlockAmethystOre(
                FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE).breakByTool(FabricToolTags.PICKAXES, 3));
        Registry.register(Registry.BLOCK, new Identifier("amethystmod", "amethyst_ore"), amethystOre);
        Registry.register(Registry.ITEM, new Identifier("amethystmod", "amethyst_ore"),
                new BlockItem(amethystOre, new Item.Settings().group(group)));

        Block amethystBlock = new Block(
                FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK).breakByTool(FabricToolTags.PICKAXES, 2));
        Registry.register(Registry.BLOCK, new Identifier("amethystmod", "amethyst_block"), amethystBlock);
        Registry.register(Registry.ITEM, new Identifier("amethystmod", "amethyst_block"),
                new BlockItem(amethystBlock, new Item.Settings().group(group)));

        AmethystOreFeature oreSpawn = Registry.register(Registry.FEATURE,
                new Identifier("amethystmod", "amethystorespawn"),
                new AmethystOreFeature(OreFeatureConfig.CODEC)); // Target.getName?

        for (Biome biome : Registry.BIOME) {
            biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES,
                    oreSpawn.configure(
                            new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE,
                                    amethystOre.getDefaultState(), 8)).createDecoratedFeature(
                            Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(1, 0, 0, 16))));
        }
    }
}
