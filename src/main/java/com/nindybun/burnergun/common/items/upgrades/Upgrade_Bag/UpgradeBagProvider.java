package com.nindybun.burnergun.common.items.upgrades.Upgrade_Bag;

import com.nindybun.burnergun.common.containers.UpgradeBagContainer;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class UpgradeBagProvider implements ICapabilitySerializable<INBT> {
    private final Direction NO_SPECIFIC_SIDE = null;

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY == cap) return (LazyOptional<T>)(lazyInitialisionSupplier);
        return LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.writeNBT(getCachedInventory(), NO_SPECIFIC_SIDE);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(getCachedInventory(), NO_SPECIFIC_SIDE, nbt);
    }

    /**
     * Return a lazily-initialised inventory
     * i.e. After the class instance has been created, but before the first call to this function, the inventory hasn't been created yet.
     * At the time of the first call, we create the inventory
     * For all subsequent calls, we return the previously-created instance.
     * To be honest, unless your initialisation is very expensive in memory or time, it's probably not worth the effort, i.e. you
     *   could just allocate the itemStackHandlerFlowerBag in your constructor and your lazyInitialisationSupplier could just
     *   return that without a dedicated method to perform a cache check.
     * @return the ItemStackHandlerFlowerBag which stores the flowers.
     */
    private UpgradeBagHandler getCachedInventory() {
        if (handler == null) {
            handler = new UpgradeBagHandler(UpgradeBagContainer.MAX_EXPECTED_HANDLER_SLOT_COUNT);
        }
        return handler;
    }

    private UpgradeBagHandler handler;  // initially null until our first call to getCachedInventory


    //  a supplier: when called, returns the result of getCachedInventory()
    private final LazyOptional<IItemHandler> lazyInitialisionSupplier = LazyOptional.of(this::getCachedInventory);
}
