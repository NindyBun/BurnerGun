package com.nindybun.burnergun.common.items.upgrades;

import com.nindybun.burnergun.common.BurnerGun;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class UpgradeCard extends Item {
    Upgrade upgrade;

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (stack.getItem() instanceof UpgradeCard){
            Upgrade upgrade = ((UpgradeCard)stack.getItem()).upgrade;
            tooltip.add(new StringTextComponent("Cost: " +  (!(this.getUpgrade().equals(Upgrade.AUTO_SMELT)) ? upgrade.getCost()+"" : "[0, 75]")).mergeStyle(TextFormatting.AQUA));
            tooltip.add(new TranslationTextComponent(this.upgrade.getToolTip())
                    .appendString(this.getUpgrade().getBaseName().equals(Upgrade.FOCAL_POINT_1.getBaseName()) ? this.upgrade.getExtraValue() + " blocks." : "")
                    .appendString(this.getUpgrade().getBaseName().equals(Upgrade.COOLDOWN_MULTIPLIER_1.getBaseName()) ? this.upgrade.getExtraValue() + "" : "")
                    .appendString(this.getUpgrade().getBaseName().equals(Upgrade.FUEL_EFFICIENCY_1.getBaseName()) || this.getUpgrade().getBaseName().equals(Upgrade.HEAT_EFFICIENCY_1.getBaseName()) ? this.upgrade.getExtraValue()*100 + "%" : "")
                    .appendString(this.getUpgrade().getBaseName().equals(Upgrade.HORIZONTAL_EXPANSION_1.getBaseName()) ||
                            this.getUpgrade().getBaseName().equals(Upgrade.VERTICAL_EXPANSION_1.getBaseName()) ? this.upgrade.getTier()*2+1 + "" : "")
                    .mergeStyle(TextFormatting.GRAY));
        }
    }

    public UpgradeCard(Upgrade upgrade) {
        super(new Properties().group(BurnerGun.itemGroup).maxStackSize(upgrade.getStackSize()));
        this.upgrade = upgrade;
    }

    public Upgrade getUpgrade(){
        return upgrade;
    }
}
