package com.nindybun.burnergun.common.items.upgrades.Upgrade_Bag;

import com.nindybun.burnergun.common.items.upgrades.UpgradeCard;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

public class UpgradeBagHandler extends ItemStackHandler {
    private boolean isDirty = true;
    public static final Logger LOGGER = LogManager.getLogger();

    public UpgradeBagHandler(int numberOfSlots){
        super(numberOfSlots);
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        if (stack.getItem() instanceof UpgradeCard || net.minecraftforge.common.ForgeHooks.getBurnTime(stack) > 0)
            return true;
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
