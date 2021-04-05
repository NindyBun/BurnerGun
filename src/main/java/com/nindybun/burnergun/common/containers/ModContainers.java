package com.nindybun.burnergun.common.containers;

import com.nindybun.burnergun.common.BurnerGun;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, BurnerGun.MOD_ID);

    public static final RegistryObject<ContainerType<BurnerGunContainer>> BURNERGUN_CONTAINER = CONTAINERS.register("burnergun_container", () -> IForgeContainerType.create(BurnerGunContainer::new));
    public static final RegistryObject<ContainerType<AutoFuelContainer>> AUTOFUEL_CONTAINER = CONTAINERS.register("auto_fuel_container", () -> IForgeContainerType.create(AutoFuelContainer::new));
    public static final RegistryObject<ContainerType<TrashContainer>> TRASH_CONTAINER = CONTAINERS.register("trash_container", () -> IForgeContainerType.create(TrashContainer::new));
    public static final RegistryObject<ContainerType<UpgradeBagContainer>> UPGRADE_BAG_CONTAINER = CONTAINERS.register("upgrade_bag_container", () -> IForgeContainerType.create(UpgradeBagContainer::new));
}
