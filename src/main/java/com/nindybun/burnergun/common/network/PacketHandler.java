package com.nindybun.burnergun.common.network;

import com.nindybun.burnergun.common.BurnerGun;
import com.nindybun.burnergun.common.network.packets.PacketOpenBurnerGunGui;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "2";
    private static int index = 0;
    public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(BurnerGun.MOD_ID, "main"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    public static void register(){
        int id = 0;
        INSTANCE.registerMessage(id++, PacketOpenBurnerGunGui.class, PacketOpenBurnerGunGui::encode, PacketOpenBurnerGunGui::decode, PacketOpenBurnerGunGui.Handler::handle);
    }

    public static void sendTo(Object msg, ServerPlayerEntity player) {
        if (!(player instanceof FakePlayer))
            INSTANCE.sendTo(msg, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void sendToServer(Object msg){
        INSTANCE.sendToServer(msg);
    }



}
