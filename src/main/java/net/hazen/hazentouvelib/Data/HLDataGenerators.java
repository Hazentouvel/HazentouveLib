package net.hazen.hazentouvelib.Data;

import net.hazen.hazentouvelib.Data.Tags.HLBlockTagsProvider;
import net.hazen.hazentouvelib.Data.Tags.HLItemTagsProvider;
import net.hazen.hazentouvelib.HazentouveLib;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = HazentouveLib.MOD_ID)
public class HLDataGenerators {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();


        generator.addProvider(true, new HLRecipeProvider.Runner(packOutput, lookupProvider));
        generator.addProvider(true, new HLItemTagsProvider(packOutput, lookupProvider));
        generator.addProvider(true, new HLBlockTagsProvider(packOutput, lookupProvider));
    }
}