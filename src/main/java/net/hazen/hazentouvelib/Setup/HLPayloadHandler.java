package net.hazen.hazentouvelib.Setup;

import net.hazen.hazentouvelib.Blocks.SoulFire.SoulFireData;
import net.hazen.hazentouvelib.HazentouveLib;
import net.hazen.hazentouvelib.Items.Armor.HLMessageArmorKey;
import net.hazen.hazentouvelib.Items.Curios.HLMessageCurioKey;
import net.hazen.hazentouvelib.Items.Shields.HLMessageShieldKey;
import net.hazen.hazentouvelib.Items.HLMessageItemKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber

public class HLPayloadHandler {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar payloadRegistrar = event.registrar(HazentouveLib.MOD_ID).versioned("1.0.0").optional();

        payloadRegistrar.playToServer(HLMessageArmorKey.TYPE, HLMessageArmorKey.STREAM_CODEC, HLMessageArmorKey::handle);
        payloadRegistrar.playToServer(HLMessageShieldKey.TYPE, HLMessageShieldKey.STREAM_CODEC, HLMessageShieldKey::handle);
        payloadRegistrar.playToServer(HLMessageItemKey.TYPE, HLMessageItemKey.STREAM_CODEC, HLMessageItemKey::handle);
        payloadRegistrar.playToServer(HLMessageCurioKey.TYPE, HLMessageCurioKey.STREAM_CODEC, HLMessageCurioKey::handle);

        payloadRegistrar.playToClient(SoulFireData.Payload.TYPE, SoulFireData.Payload.CODEC, SoulFireData.Payload::execute);
    }
}
