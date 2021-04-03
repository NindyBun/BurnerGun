package com.nindybun.burnergun.common.items;

import com.nindybun.burnergun.common.BurnerGun;
import com.nindybun.burnergun.common.blocks.ModBlocks;
import com.nindybun.burnergun.common.items.upgrades.Auto_Fuel.AutoFuel;
import com.nindybun.burnergun.common.items.upgrades.Trash.Trash;
import com.nindybun.burnergun.common.items.upgrades.Upgrade;
import com.nindybun.burnergun.common.items.upgrades.Upgrade_Bag.UpgradeBag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final Item.Properties ITEM_GROUP = new Item.Properties().group(BurnerGun.itemGroup);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BurnerGun.MOD_ID);

    // We have a separate register just to contain all of the upgrades for quick reference
    public static final DeferredRegister<Item> UPGRADE_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BurnerGun.MOD_ID);

    public static final RegistryObject<Item> BURNER_GUN = ITEMS.register("burner_gun", () ->  new com.nindybun.burnergun.common.items.Burner_Gun.BurnerGun());
    public static final RegistryObject<Item> LIGHT_ITEM = ITEMS.register("light", () -> new BlockItem(ModBlocks.LIGHT.get(), ITEM_GROUP.maxStackSize(1)));

    public static final RegistryObject<Item> BASE_TIER_1 = UPGRADE_ITEMS.register("base_tier_1", Upgrade.TIER_1::getCard);
    public static final RegistryObject<Item> BASE_TIER_2 = UPGRADE_ITEMS.register("base_tier_2", Upgrade.TIER_2::getCard);
    public static final RegistryObject<Item> BASE_TIER_3 = UPGRADE_ITEMS.register("base_tier_3", Upgrade.TIER_3::getCard);
    public static final RegistryObject<Item> BASE_TIER_4 = UPGRADE_ITEMS.register("base_tier_4", Upgrade.TIER_4::getCard);
    public static final RegistryObject<Item> BASE_TIER_5 = UPGRADE_ITEMS.register("base_tier_5", Upgrade.TIER_5::getCard);

    public static final RegistryObject<Item> AUTO_FUEL = UPGRADE_ITEMS.register("auto_fuel_upgrade", () -> new AutoFuel(Upgrade.AUTO_FUEL));
    public static final RegistryObject<Item> MAGNET = UPGRADE_ITEMS.register("magnet_upgrade", Upgrade.MAGNET::getCard);
    public static final RegistryObject<Item> SILK_TOUCH = UPGRADE_ITEMS.register("silk_touch_upgrade", Upgrade.SILK_TOUCH::getCard);
    public static final RegistryObject<Item> LIGHT = UPGRADE_ITEMS.register("light_upgrade", Upgrade.LIGHT::getCard);
    public static final RegistryObject<Item> AUTO_SMELT = UPGRADE_ITEMS.register("auto_smelt_upgrade", Upgrade.AUTO_SMELT::getCard);
    public static final RegistryObject<Item> TRASH = UPGRADE_ITEMS.register("trash_upgrade", () -> new Trash(Upgrade.TRASH));
    public static final RegistryObject<Item> UPGRADE_BAG = UPGRADE_ITEMS.register("upgrade_bag", () -> new UpgradeBag(Upgrade.UPGRADE_BAG));

    public static final RegistryObject<Item> FORTUNE_1 = UPGRADE_ITEMS.register("fortune_1_upgrade", Upgrade.FORTUNE_1::getCard);
    public static final RegistryObject<Item> FORTUNE_2 = UPGRADE_ITEMS.register("fortune_2_upgrade", Upgrade.FORTUNE_2::getCard);
    public static final RegistryObject<Item> FORTUNE_3 = UPGRADE_ITEMS.register("fortune_3_upgrade", Upgrade.FORTUNE_3::getCard);

    public static final RegistryObject<Item> HORIZONTAL_EXPANSION_1 = UPGRADE_ITEMS.register("horizontal_expansion_1_upgrade", Upgrade.HORIZONTAL_EXPANSION_1::getCard);
    public static final RegistryObject<Item> HORIZONTAL_EXPANSION_2 = UPGRADE_ITEMS.register("horizontal_expansion_2_upgrade", Upgrade.HORIZONTAL_EXPANSION_2::getCard);
    public static final RegistryObject<Item> HORIZONTAL_EXPANSION_3 = UPGRADE_ITEMS.register("horizontal_expansion_3_upgrade", Upgrade.HORIZONTAL_EXPANSION_3::getCard);
    public static final RegistryObject<Item> HORIZONTAL_EXPANSION_4 = UPGRADE_ITEMS.register("horizontal_expansion_4_upgrade", Upgrade.HORIZONTAL_EXPANSION_4::getCard);

    public static final RegistryObject<Item> VERTICAL_EXPANSION_1 = UPGRADE_ITEMS.register("vertical_expansion_1_upgrade", Upgrade.VERTICAL_EXPANSION_1::getCard);
    public static final RegistryObject<Item> VERTICAL_EXPANSION_2 = UPGRADE_ITEMS.register("vertical_expansion_2_upgrade", Upgrade.VERTICAL_EXPANSION_2::getCard);
    public static final RegistryObject<Item> VERTICAL_EXPANSION_3 = UPGRADE_ITEMS.register("vertical_expansion_3_upgrade", Upgrade.VERTICAL_EXPANSION_3::getCard);
    public static final RegistryObject<Item> VERTICAL_EXPANSION_4 = UPGRADE_ITEMS.register("vertical_expansion_4_upgrade", Upgrade.VERTICAL_EXPANSION_4::getCard);

    public static final RegistryObject<Item> FOCAL_POINT_1 = UPGRADE_ITEMS.register("focal_point_1_upgrade", Upgrade.FOCAL_POINT_1::getCard);
    public static final RegistryObject<Item> FOCAL_POINT_2 = UPGRADE_ITEMS.register("focal_point_2_upgrade", Upgrade.FOCAL_POINT_2::getCard);
    public static final RegistryObject<Item> FOCAL_POINT_3 = UPGRADE_ITEMS.register("focal_point_3_upgrade", Upgrade.FOCAL_POINT_3::getCard);

    public static final RegistryObject<Item> FUEL_EFFICIENCY_1 = UPGRADE_ITEMS.register("fuel_efficiency_1_upgrade", Upgrade.FUEL_EFFICIENCY_1::getCard);
    public static final RegistryObject<Item> FUEL_EFFICIENCY_2 = UPGRADE_ITEMS.register("fuel_efficiency_2_upgrade", Upgrade.FUEL_EFFICIENCY_2::getCard);
    public static final RegistryObject<Item> FUEL_EFFICIENCY_3 = UPGRADE_ITEMS.register("fuel_efficiency_3_upgrade", Upgrade.FUEL_EFFICIENCY_3::getCard);
    public static final RegistryObject<Item> FUEL_EFFICIENCY_4 = UPGRADE_ITEMS.register("fuel_efficiency_4_upgrade", Upgrade.FUEL_EFFICIENCY_4::getCard);
    public static final RegistryObject<Item> FUEL_EFFICIENCY_5 = UPGRADE_ITEMS.register("fuel_efficiency_5_upgrade", Upgrade.FUEL_EFFICIENCY_5::getCard);

}
