package com.nindybun.burnergun.common.items.upgrades.Auto_Fuel;

import com.nindybun.burnergun.common.containers.AutoFuelContainer;
import com.nindybun.burnergun.common.items.upgrades.Upgrade;
import com.nindybun.burnergun.common.items.upgrades.UpgradeCard;
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

public class AutoFuel extends UpgradeCard {
    Upgrade upgrade;
    public static final Logger LOGGER = LogManager.getLogger();

    public AutoFuel(Upgrade upgrade) {
        super(upgrade);
        this.upgrade = upgrade;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (!world.isRemote) {
            player.openContainer(new SimpleNamedContainerProvider(
                    (windowId, playerInv, playerEntity) -> new AutoFuelContainer(windowId, playerInv, getHandler(stack)),
                    new StringTextComponent("")
            ));
        }
        return ActionResult.resultSuccess(stack);
    }

    public Upgrade getUpgrade(){
        return upgrade;
    }

    @Nonnull
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT oldCapNbt) {
        if (this.getClass() == AutoFuel.class){
            return new AutoFuelProvider();
        }else{
            return super.initCapabilities(stack, oldCapNbt);
        }

    }

    public static AutoFuelHandler getHandler(ItemStack itemStack) {
        IItemHandler handler = itemStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
        if (handler == null || !(handler instanceof AutoFuelHandler)) {
            LOGGER.error("AutoFuelStack did not have the expected ITEM_HANDLER_CAPABILITY");
            return new AutoFuelHandler(AutoFuelContainer.MAX_EXPECTED_HANDLER_SLOT_COUNT);
        }
        return (AutoFuelHandler) handler;
    }

    private final String BASE_NBT_TAG = "base";
    private final String CAPABILITY_NBT_TAG = "cap";


    @Nullable
    @Override
    public CompoundNBT getShareTag(ItemStack stack) {
        CompoundNBT baseTag = stack.getTag();
        AutoFuelHandler handler = getHandler(stack);
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
        AutoFuelHandler handler = getHandler(stack);
        handler.deserializeNBT(capabilityTag);
    }
}
