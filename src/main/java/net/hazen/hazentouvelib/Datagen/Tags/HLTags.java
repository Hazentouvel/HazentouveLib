package net.hazen.hazentouvelib.Datagen.Tags;

import net.hazen.hazentouvelib.HazentouveLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class HLTags {

    public static class Blocks {
        public static final TagKey<Block> SOUL_FIRE_BASE_BLOCK = createTag("soul_fire_base_block");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(Identifier.fromNamespaceAndPath(HazentouveLib.MOD_ID, name));
        }
    }

    public static class Entities {
        public static final TagKey<EntityType<?>> SOUL_FIRE_IMMUNE = TagKey
                .create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(HazentouveLib.MOD_ID, "soul_fire_immune"));
    }


    public static class Items {

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(Identifier.fromNamespaceAndPath(HazentouveLib.MOD_ID, name));
        }


    }
}