package net.hazen.hazentouvelib.Data;

import net.hazen.hazentouvelib.Blocks.SoulFire.SoulFireData;
import net.hazen.hazentouvelib.HazentouveLib;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class HLDataAttachments {

    private static final DeferredRegister<AttachmentType<?>> REGISTER = DeferredRegister.create(
        NeoForgeRegistries.Keys.ATTACHMENT_TYPES, HazentouveLib.MOD_ID);

    public static void register(IEventBus bus) {
        REGISTER.register("soul_fire", () -> SoulFireData.ATTACHMENT);
        REGISTER.register(bus);
    }

}