package com.nindybun.burnergun.client.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.nindybun.burnergun.common.BurnerGun;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.awt.*;

@Mod.EventBusSubscriber(modid = BurnerGun.MOD_ID, value = Dist.CLIENT)
public class FuelValueRenderer {
    private static final Logger LOGGER = LogManager.getLogger();
    @SubscribeEvent
    public static void renderOverlay(@Nonnull RenderGameOverlayEvent.Post event){
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL){
            ItemStack stack = ItemStack.EMPTY;
            PlayerEntity player = Minecraft.getInstance().player;
            if (player.getHeldItemMainhand().getItem() instanceof com.nindybun.burnergun.common.items.Burner_Gun.BurnerGun)
                stack = player.getHeldItemMainhand();
            else if (player.getHeldItemOffhand().getItem() instanceof com.nindybun.burnergun.common.items.Burner_Gun.BurnerGun)
                stack = player.getHeldItemOffhand();

            if (stack.getItem() instanceof com.nindybun.burnergun.common.items.Burner_Gun.BurnerGun)
                renderFuel(event, stack);

        }

    }

    public static void renderFuel(RenderGameOverlayEvent event, ItemStack stack){
        FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;
        fontRenderer.drawString(event.getMatrixStack(), "Fuel level: " + stack.getTag().getInt("FuelValue"), 6, event.getWindow().getScaledHeight()-12, Color.ORANGE.getRGB());
    }
}
