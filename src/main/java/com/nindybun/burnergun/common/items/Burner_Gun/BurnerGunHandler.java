package com.nindybun.burnergun.common.items.Burner_Gun;

import com.nindybun.burnergun.common.containers.BurnerGunContainer;
import com.nindybun.burnergun.common.items.upgrades.Upgrade;
import com.nindybun.burnergun.common.items.upgrades.UpgradeCard;
import com.nindybun.burnergun.common.items.upgrades.Upgrade_Bag.UpgradeBag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class BurnerGunHandler extends ItemStackHandler {
    public static final int MAX_SLOTS = BurnerGunContainer.MAX_EXPECTED_TEST_SLOT_COUNT;

    public BurnerGunHandler(int numberOfSlots){
        super(numberOfSlots);
    }

    public static boolean isFuel(ItemStack stack) {
        return net.minecraftforge.common.ForgeHooks.getBurnTime(stack) > 0;
    }

    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        if (slot < 0 || slot >= MAX_SLOTS) {
            throw new IllegalArgumentException("Invalid slot number: " + slot);
        }
        if ((isFuel(stack) || stack.getItem() == Items.BUCKET) && slot == 0) {
            return true;
        }
        if (slot != 0 && slot != 11 && stack.getItem() instanceof UpgradeCard && !(stack.getItem() instanceof UpgradeBag)){
            if (getUpgradeByUpgrade(((UpgradeCard) stack.getItem()).getUpgrade()) != null){
                return getUpgradeByUpgrade(((UpgradeCard) stack.getItem()).getUpgrade()).getUpgradeStack().getItem() == this.getStackInSlot(slot).getItem();
            }
            return canInsert(stack);
        }
        if (slot == 11 && stack.getItem() instanceof UpgradeBag){
            return true;
        }

        return false;
    }

    public List<UpgradeCard> getUpgrades(){
        List<UpgradeCard> upgrades = new ArrayList<>();
        for (int index  = 1; index < MAX_SLOTS; index++){
            if (this.getStackInSlot(index).getItem() != Items.AIR){
                upgrades.add((UpgradeCard)this.getStackInSlot(index).getItem());
            }
        }
        return upgrades;
    }

    public Upgrade getUpgradeByUpgrade(Upgrade upgrade){
        List<UpgradeCard> upgrades = getUpgrades();
        for (UpgradeCard upgradeCard : upgrades) {
            if (upgradeCard.getUpgrade().getBaseName().equals(upgrade.getBaseName())){
                return upgradeCard.getUpgrade();
            }
        }
        return null;
    }


    public boolean canInsert(ItemStack item){
        List<UpgradeCard> upgradeCards = getUpgrades();

        if (!upgradeCards.isEmpty()){
            for (UpgradeCard upgrade : upgradeCards) {
                UpgradeCard upgradeItem = (UpgradeCard) item.getItem();
                //Checks if the holding upgrade is Silk and if there is a fortune upgrade
                //Checks if the holding upgrade is fortune and if there is a silk upgrade
                if ((upgradeItem.getUpgrade().equals(Upgrade.SILK_TOUCH) && upgrade.getUpgrade().getBaseName().equals(Upgrade.FORTUNE_1.getBaseName())) ||
                        (upgradeItem.getUpgrade().getBaseName().equals(Upgrade.FORTUNE_1.getBaseName()) && upgrade.getUpgrade().equals(Upgrade.SILK_TOUCH)) ){
                    return false;
                }
            }
        }
        return true;
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

    private boolean isDirty = true;
    private final Logger LOGGER = LogManager.getLogger();



}
