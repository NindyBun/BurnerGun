package com.nindybun.burnergun.common.items.upgrades.Upgrade_Bag;

import com.nindybun.burnergun.common.containers.UpgradeBagContainer;
import com.nindybun.burnergun.common.items.upgrades.Upgrade;
import com.nindybun.burnergun.common.items.upgrades.UpgradeCard;
import com.nindybun.burnergun.common.network.PacketHandler;
import com.nindybun.burnergun.common.network.packets.PacketOpenUpgradeBagGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class UpgradeBag extends UpgradeCard {
    Upgrade upgrade;
    public static final Logger LOGGER = LogManager.getLogger();

    public UpgradeBag(Upgrade upgrade) {
        super(upgrade);
        this.upgrade = upgrade;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (!world.isRemote) {
            PacketHandler.sendToServer(new PacketOpenUpgradeBagGui());
        }
        return ActionResult.resultSuccess(stack);
    }

    public Upgrade getUpgrade(){
        return upgrade;
    }

    @Nonnull
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT oldCapNbt) {
        if (this.getClass() == UpgradeBag.class){
            return new com.nindybun.burnergun.common.items.upgrades.Upgrade_Bag.UpgradeBagProvider();
        }else{
            return super.initCapabilities(stack, oldCapNbt);
        }

    }

    public static com.nindybun.burnergun.common.items.upgrades.Upgrade_Bag.UpgradeBagHandler getHandler(ItemStack itemStack) {
        IItemHandler handler = itemStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
        if (handler == null || !(handler instanceof com.nindybun.burnergun.common.items.upgrades.Upgrade_Bag.UpgradeBagHandler)) {
            LOGGER.error("UpgraadeBagStack did not have the expected ITEM_HANDLER_CAPABILITY");
            return new com.nindybun.burnergun.common.items.upgrades.Upgrade_Bag.UpgradeBagHandler(UpgradeBagContainer.MAX_EXPECTED_HANDLER_SLOT_COUNT);
        }
        return (com.nindybun.burnergun.common.items.upgrades.Upgrade_Bag.UpgradeBagHandler) handler;
    }

    private final String BASE_NBT_TAG = "base";
    private final String CAPABILITY_NBT_TAG = "cap";


    @Nullable
    @Override
    public CompoundNBT getShareTag(ItemStack stack) {
        CompoundNBT baseTag = stack.getTag();
        com.nindybun.burnergun.common.items.upgrades.Upgrade_Bag.UpgradeBagHandler handler = getHandler(stack);
        CompoundNBT capabilityTag = handler.serializeNBT();
        CompoundNBT combinedTag = new CompoundNBT();
        if (baseTag != null) {
            combinedTag.put(BASE_NBT_TAG, baseTag);
        }
        if (capabilityTag != null) {
            combinedTag.put(CAPABILITY_NBT_TAG, capabilityTag);
        }
        return combinedTag;
    }

    @Override
    public void readShareTag(ItemStack stack, @Nullable CompoundNBT nbt) {
        if (nbt == null) {
            stack.setTag(null);
            return;
        }
        CompoundNBT baseTag = nbt.getCompound(BASE_NBT_TAG);
        CompoundNBT capabilityTag = nbt.getCompound(CAPABILITY_NBT_TAG);
        stack.setTag(baseTag);
        com.nindybun.burnergun.common.items.upgrades.Upgrade_Bag.UpgradeBagHandler handler = getHandler(stack);
        handler.deserializeNBT(capabilityTag);
    }
}
