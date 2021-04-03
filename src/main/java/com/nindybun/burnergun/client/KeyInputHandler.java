package com.nindybun.burnergun.client;

import com.nindybun.burnergun.common.items.Burner_Gun.BurnerGun;
import com.nindybun.burnergun.common.network.PacketHandler;
import com.nindybun.burnergun.common.network.packets.PacketOpenBurnerGunGui;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KeyInputHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
        PlayerEntity player = Minecraft.getInstance().player;
        if (Keybinds.burnergun_gui_key.isPressed() && Minecraft.getInstance().currentScreen == null && (player.getHeldItemMainhand().getItem() instanceof BurnerGun || player.getHeldItemOffhand().getItem() instanceof BurnerGun)){
            PacketHandler.sendToServer(new PacketOpenBurnerGunGui());
        }
    }
}
