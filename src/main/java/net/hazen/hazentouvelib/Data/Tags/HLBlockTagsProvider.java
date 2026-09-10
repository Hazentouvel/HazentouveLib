package net.hazen.hazentouvelib.Data.Tags;

import net.hazen.hazentouvelib.HazentouveLib;
import net.hazen.hazentouvelib.Registries.HLBlockRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class HLBlockTagsProvider extends BlockTagsProvider {
    public HLBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, HazentouveLib.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(HLBlockRegistry.STEEL_BLOCK.get())
                .add(HLBlockRegistry.CRUDE_METAL_BLOCK.get())
             ;

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(HLBlockRegistry.STEEL_BLOCK.get())
                .add(HLBlockRegistry.CRUDE_METAL_BLOCK.get())
        ;
    }
}