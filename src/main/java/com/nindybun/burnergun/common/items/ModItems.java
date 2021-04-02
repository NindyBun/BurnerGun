package com.nindybun.burnergun.common.items;

import com.nindybun.burnergun.common.BurnerGun;
import com.nindybun.burnergun.common.blocks.ModBlocks;
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

    public static final RegistryObject<Item> LIGHT_ITEM = ITEMS.register("light", () -> new BlockItem(ModBlocks.LIGHT.get(), ITEM_GROUP.maxStackSize(1)));

}
