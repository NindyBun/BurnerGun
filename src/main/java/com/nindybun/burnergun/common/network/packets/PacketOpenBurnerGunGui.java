package com.nindybun.burnergun.common.network.packets;

import com.nindybun.burnergun.common.items.Burner_Gun.BurnerGun;
import com.nindybun.burnergun.common.containers.BurnerGunContainer;
import com.nindybun.burnergun.common.items.Burner_Gun.BurnerGunHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.function.Supplier;

public class PacketOpenBurnerGunGui {
    public PacketOpenBurnerGunGui(){

    }

    public static void encode(PacketOpenBurnerGunGui msg, PacketBuffer buffer) {
    }

    public static PacketOpenBurnerGunGui decode(PacketBuffer buffer) {
        return new PacketOpenBurnerGunGui();
    }

    public static class Handler {
        public static void handle(PacketOpenBurnerGunGui msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                ServerPlayerEntity player = ctx.get().getSender();

                if (player == null)
                    return;

                ItemStack stack = ItemStack.EMPTY;
                if (player.getHeldItemMainhand().getItem() instanceof BurnerGun)
                    stack = player.getHeldItemMainhand();
                else if (player.getHeldItemOffhand().getItem() instanceof BurnerGun)
                    stack = player.getHeldItemOffhand();

                if( stack.isEmpty() )
                    return;

                IItemHandler handler = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);

                player.openContainer(new SimpleNamedContainerProvider(
                        (windowId, playerInv, playerEntity) -> new BurnerGunContainer(windowId, playerInv, (BurnerGunHandler) handler),
                        new StringTextComponent("")
                ));

            });

            ctx.get().setPacketHandled(true);
        }
    }




}
