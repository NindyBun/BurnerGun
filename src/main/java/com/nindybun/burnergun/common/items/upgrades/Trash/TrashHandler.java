package com.nindybun.burnergun.common.items.upgrades.Trash;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

public class TrashHandler extends ItemStackHandler {
    private boolean isDirty = true;
    public static final Logger LOGGER = LogManager.getLogger();

    public TrashHandler(int numberOfSlots){
        super(numberOfSlots);
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        this.setStackInSlot(slot, Items.AIR.getDefaultInstance());
        return ItemStack.EMPTY;
    }


    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        if (this.getStackInSlot(slot).getItem() == Items.AIR){
            this.setStackInSlot(slot, stack.getItem().getDefaultInstance());

        }else{
            this.setStackInSlot(slot, Items.AIR.getDefaultInstance());
        }
        return false;
    }

    public boolean isDirty() {
        boolean currentState = isDirty;
        isDirty = false;
        return currentState;
    }

    protected void onContentsChanged(int slot) {
        super.onContentsChanged(slot);
        isDirty = true;
    }


}
