package net.hazen.hazentouvelib.Data.Tags;

import net.hazen.hazentouvelib.HazentouveLib;
import net.hazen.hazentouvelib.Registries.HLItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class HLItemTagsProvider extends ItemTagsProvider {
    public HLItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, HazentouveLib.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ItemTags.METAL_NUGGETS)
                .add(HLItemRegistry.STEEL_NUGGET.get())
        ;

    }
}