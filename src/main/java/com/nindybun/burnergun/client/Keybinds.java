package com.nindybun.burnergun.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class Keybinds {

    public static KeyBinding burnergun_gui_key = new KeyBinding("key.burnergun_gui_key", GLFW.GLFW_KEY_C, "key.categories.burnergun");


    public static void register()
    {
        ClientRegistry.registerKeyBinding(burnergun_gui_key);
    }


}
