package com.nindybun.burnergun.common.items.Burner_Gun;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.nindybun.burnergun.common.BurnerGun;
import com.nindybun.burnergun.common.containers.BurnerGunContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;

public class BurnerGunScreen extends ContainerScreen<BurnerGunContainer> {
    public BurnerGunScreen(BurnerGunContainer container, PlayerInventory playerInv, ITextComponent title) {
        super(container, playerInv, title);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.blendColor(1.0F, 1.0F, 1.0F, 1.0F);
        //this.minecraft
        this.minecraft.getTextureManager().bindTexture(DEFAULT_TEXTURE);

        // width and height are the size provided to the window when initialised after creation.
        // xSize, ySize are the expected size of the texture-? usually seems to be left as a default.
        // The code below is typical for vanilla containers, so I've just copied that- it appears to centre the texture within
        //  the available window
        int edgeSpacingX = (this.width - this.xSize) / 2;
        int edgeSpacingY = (this.height - this.ySize) / 2;
        this.blit(matrixStack, edgeSpacingX, edgeSpacingY, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        String name = "Burner Gun";
        this.font.drawString(matrixStack, name, (this.width - this.xSize)/2-name.length()/2, -8, Color.WHITE.getRGB());
    }

    private static final Logger LOGGER = LogManager.getLogger();
    // This is the resource location for the background image
    private static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation(BurnerGun.MOD_ID, "textures/gui/burnergun_gui.png");
}
