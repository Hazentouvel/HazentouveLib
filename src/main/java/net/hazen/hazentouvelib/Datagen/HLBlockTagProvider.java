package net.hazen.hazentouvelib.Datagen;

import net.hazen.hazentouvelib.HazentouveLib;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class HLBlockTagProvider extends BlockTagsProvider {
    public HLBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, HazentouveLib.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(HLTags.Blocks.NEEDS_MITRHIL_TOOL)
                .addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
        ;

        tag(HLTags.Blocks.SOUL_FIRE_BASE_BLOCK)
                .add(Block.byItem(Items.SCULK_CATALYST))
                .add(Block.byItem(Items.SCULK))
        ;

    }
}