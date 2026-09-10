package net.hazen.hazentouvelib.Data;

import net.hazen.hazentouvelib.HazentouveLib;
import net.hazen.hazentouvelib.Registries.HLItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class HLRecipeProvider extends RecipeProvider {
    public HLRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new HLRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "HazentouveLib Recipes";
        }
    }

    @Override
    protected void buildRecipes() {

        /*
        *** Blocks
         */

        // Steel Block
        shaped(RecipeCategory.BUILDING_BLOCKS, HLItemRegistry.STEEL_BLOCK_ITEM.get())
                .pattern("ZZZ")
                .pattern("ZZZ")
                .pattern("ZZZ")
                .define('Z', HLItemRegistry.STEEL_INGOT.get())
                .unlockedBy(getHasName(HLItemRegistry.STEEL_INGOT.get()), has(HLItemRegistry.STEEL_INGOT.get()))
                .save(output);

        // Crude Metal Block
        shaped(RecipeCategory.BUILDING_BLOCKS, HLItemRegistry.CRUDE_METAL_BLOCK_ITEM.get())
                .pattern("ZZZ")
                .pattern("ZZZ")
                .pattern("ZZZ")
                .define('Z', HLItemRegistry.CRUDE_METAL.get())
                .unlockedBy(getHasName(HLItemRegistry.CRUDE_METAL.get()), has(HLItemRegistry.CRUDE_METAL.get()))
                .save(output);

        /*
        *** Materials
         */

        // Steel Ingot
        shaped(RecipeCategory.MISC, HLItemRegistry.STEEL_INGOT.get())
                .pattern("ZZZ")
                .pattern("ZZZ")
                .pattern("ZZZ")
                .define('Z', HLItemRegistry.STEEL_NUGGET.get())
                .unlockedBy(getHasName(HLItemRegistry.STEEL_INGOT.get()), has(HLItemRegistry.STEEL_INGOT.get()))
                .save(output, HazentouveLib.MOD_ID + ":" + "steel_ingot_from_steel_nuggets");

        shapeless(RecipeCategory.MISC, HLItemRegistry.STEEL_INGOT.get(), 9)
                .requires(HLItemRegistry.STEEL_BLOCK_ITEM)
                .unlockedBy(getHasName(HLItemRegistry.STEEL_BLOCK_ITEM.get()), has(HLItemRegistry.STEEL_BLOCK_ITEM.get()))
                .save(output, HazentouveLib.MOD_ID + ":" + "steel_ingot_from_steel_block");

        shapeless(RecipeCategory.MISC, HLItemRegistry.STEEL_NUGGET.get(), 9)
                .requires(HLItemRegistry.STEEL_INGOT)
                .unlockedBy(getHasName(HLItemRegistry.CRUDE_METAL_BLOCK_ITEM.get()), has(HLItemRegistry.CRUDE_METAL_BLOCK_ITEM.get()))
                .save(output, HazentouveLib.MOD_ID + ":" + "steel_nuggets_from_steel_ingot");

        shapeless(RecipeCategory.MISC, HLItemRegistry.CRUDE_METAL.get(), 9)
                .requires(HLItemRegistry.CRUDE_METAL)
                .unlockedBy(getHasName(HLItemRegistry.CRUDE_METAL_BLOCK_ITEM.get()), has(HLItemRegistry.CRUDE_METAL_BLOCK_ITEM.get()))
                .save(output, HazentouveLib.MOD_ID + ":" + "crude_metal_from_crude_metal_block");

        shapeless(RecipeCategory.MISC, HLItemRegistry.CRUDE_METAL.get(), 1)
                .requires(Items.IRON_INGOT)
                .requires(Items.IRON_INGOT)
                .requires(Items.RAW_IRON)
                .requires(Items.COAL)
                .requires(Items.COAL)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(output);


    }
}