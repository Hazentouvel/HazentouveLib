package net.hazen.hazentouvelib.Setup;

import net.hazen.hazentouvelib.HazentouveLib;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

public final class HLAttachmentUtil {

    public static void syncToPlayer(CustomPacketPayload payload, Player player) {
        if (player instanceof ServerPlayer sp)
            PacketDistributor.sendToPlayer(sp, payload);
    }

    public static void syncToTracking(CustomPacketPayload payload, Level level, BlockPos pos) {
        if (level instanceof ServerLevel sl)
            PacketDistributor.sendToPlayersTrackingChunk(sl, new ChunkPos(pos), payload);
    }

    public static <L extends CustomPacketPayload> CustomPacketPayload.Type<L> create(String name) {
        return new CustomPacketPayload.Type<>(HazentouveLib.id(name));
    }
}