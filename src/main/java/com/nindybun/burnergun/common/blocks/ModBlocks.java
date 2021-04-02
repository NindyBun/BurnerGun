package com.nindybun.burnergun.common.blocks;

import com.nindybun.burnergun.common.BurnerGun;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BurnerGun.MOD_ID);

    public static final RegistryObject<Block> LIGHT = BLOCKS.register("light", Light::new);
}
