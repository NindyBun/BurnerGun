package com.nindybun.burnergun.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class WorldUtil {
    public WorldUtil() {
    }
    private static final Logger LOGGER = LogManager.getLogger();

    public static BlockRayTraceResult getLookingAt(PlayerEntity player, RayTraceContext.FluidMode rayTraceFluid, double range) {
        World world = player.world;

        Vector3d look = player.getLookVec();
        Vector3d start = new Vector3d(player.getPosX(), player.getPosY() + player.getEyeHeight(), player.getPosZ());

        Vector3d end = new Vector3d(player.getPosX() + look.x * (double) range, player.getPosY() + player.getEyeHeight() + look.y * (double) range, player.getPosZ() + look.z * (double) range);
        RayTraceContext context = new RayTraceContext(start, end, RayTraceContext.BlockMode.COLLIDER, rayTraceFluid, player);
        return world.rayTraceBlocks(context);
    }
}
