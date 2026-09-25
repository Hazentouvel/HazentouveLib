package net.hazen.hazentouvelib.Datagen;

import io.redspace.ironsspellbooks.registries.ItemRegistry;
import io.redspace.ironsspellbooks.util.ModTags;
import net.hazen.hazentouvelib.HazentouveLib;
import net.hazen.hazentouvelib.Registries.HLItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class HLItemTagProvider extends ItemTagsProvider  {
    public HLItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, HazentouveLib.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(HLTags.ENDER_MASK)
                .add(Items.CARVED_PUMPKIN)
        ;

        tag(HLTags.FIRE_SPELLBOOK)
                .add(
                        ItemRegistry.BLAZE_SPELL_BOOK.get()
                )
        ;

        tag(HLTags.ICE_SPELLBOOK)
                .add(
                        ItemRegistry.ICE_SPELL_BOOK.get()
                )
        ;

        tag(HLTags.NATURE_SPELLBOOK)
                .add(
                        ItemRegistry.DRUIDIC_SPELL_BOOK.get()
                )
        ;

        tag(HLTags.ENDER_SPELLBOOK)
                .add(
                        ItemRegistry.DRAGONSKIN_SPELL_BOOK.get()
                )
        ;

        tag(HLTags.BLOOD_SPELLBOOK)
                .add(
                        ItemRegistry.CURSED_DOLL_SPELLBOOK.get()
                )
        ;

        tag(HLTags.EVOCATION_SPELLBOOK)
                .add(
                        ItemRegistry.EVOKER_SPELL_BOOK.get()
                )
        ;

        tag(HLTags.HOLY_SPELLBOOK)
                .add(
                        ItemRegistry.VILLAGER_SPELL_BOOK.get()
                )
        ;

        tag(HLTags.SCHOOL_HELMET)
                .add(ItemRegistry.PYROMANCER_HELMET.get())
                .add(ItemRegistry.PLAGUED_HELMET.get())
                .add(ItemRegistry.ARCHEVOKER_HELMET.get())
                .add(ItemRegistry.PRIEST_HELMET.get())
                .add(ItemRegistry.CRYOMANCER_HELMET.get())
                .add(ItemRegistry.ELECTROMANCER_HELMET.get())
                .add(ItemRegistry.CULTIST_HELMET.get())
                .add(ItemRegistry.SHADOWWALKER_HELMET.get())
        ;

        tag(HLTags.SCHOOL_CHESTPLATE)
                .add(ItemRegistry.PYROMANCER_CHESTPLATE.get())
                .add(ItemRegistry.PLAGUED_CHESTPLATE.get())
                .add(ItemRegistry.ARCHEVOKER_CHESTPLATE.get())
                .add(ItemRegistry.PRIEST_CHESTPLATE.get())
                .add(ItemRegistry.CRYOMANCER_CHESTPLATE.get())
                .add(ItemRegistry.ELECTROMANCER_CHESTPLATE.get())
                .add(ItemRegistry.CULTIST_CHESTPLATE.get())
                .add(ItemRegistry.SHADOWWALKER_CHESTPLATE.get())
        ;

        tag(HLTags.SCHOOL_LEGGINGS)
                .add(ItemRegistry.PYROMANCER_LEGGINGS.get())
                .add(ItemRegistry.PLAGUED_LEGGINGS.get())
                .add(ItemRegistry.ARCHEVOKER_LEGGINGS.get())
                .add(ItemRegistry.PRIEST_LEGGINGS.get())
                .add(ItemRegistry.CRYOMANCER_LEGGINGS.get())
                .add(ItemRegistry.ELECTROMANCER_LEGGINGS.get())
                .add(ItemRegistry.CULTIST_LEGGINGS.get())
                .add(ItemRegistry.SHADOWWALKER_LEGGINGS.get())
        ;

        tag(HLTags.SCHOOL_BOOTS)
                .add(ItemRegistry.PYROMANCER_BOOTS.get())
                .add(ItemRegistry.PLAGUED_BOOTS.get())
                .add(ItemRegistry.ARCHEVOKER_BOOTS.get())
                .add(ItemRegistry.PRIEST_BOOTS.get())
                .add(ItemRegistry.CRYOMANCER_BOOTS.get())
                .add(ItemRegistry.ELECTROMANCER_BOOTS.get())
                .add(ItemRegistry.CULTIST_BOOTS.get())
                .add(ItemRegistry.SHADOWWALKER_BOOTS.get())
        ;



        tag(HLTags.NETHERITE_BATTLEMAGE_HELMET)
                .add(ItemRegistry.NETHERITE_MAGE_HELMET.get())
        ;

        tag(HLTags.NETHERITE_BATTLEMAGE_CHESTPLATE)
                .add(ItemRegistry.NETHERITE_MAGE_CHESTPLATE.get())
        ;

        tag(HLTags.NETHERITE_BATTLEMAGE_LEGGINGS)
                .add(ItemRegistry.NETHERITE_MAGE_LEGGINGS.get())
        ;

        tag(HLTags.NETHERITE_BATTLEMAGE_BOOTS)
                .add(ItemRegistry.NETHERITE_MAGE_BOOTS.get())
        ;

        tag(HLTags.PARAGON_HELMET)
        ;

        tag(HLTags.PARAGON_CHESTPLATE)
        ;

        tag(HLTags.PARAGON_LEGGINGS)
        ;

        tag(HLTags.PARAGON_BOOTS)
        ;


        tag(HLTags.PURE_HELMET)
        ;

        tag(HLTags.PURE_CHESTPLATE)
        ;

        tag(HLTags.PURE_LEGGINGS)
        ;

        tag(HLTags.PURE_BOOTS)
        ;

        tag(HLTags.HELMETS)
                .addTag(HLTags.SCHOOL_HELMET)
                .addTag(HLTags.NETHERITE_BATTLEMAGE_HELMET)
                .addTag(HLTags.PURE_HELMET)
                .addTag(HLTags.PARAGON_HELMET)
        ;

        tag(HLTags.CHESTPLATES)
                .addTag(HLTags.SCHOOL_CHESTPLATE)
                .addTag(HLTags.NETHERITE_BATTLEMAGE_CHESTPLATE)
                .addTag(HLTags.PURE_CHESTPLATE)
                .addTag(HLTags.PARAGON_CHESTPLATE)
        ;

        tag(HLTags.LEGGINGS)
                .addTag(HLTags.SCHOOL_LEGGINGS)
                .addTag(HLTags.NETHERITE_BATTLEMAGE_LEGGINGS)
                .addTag(HLTags.PURE_LEGGINGS)
                .addTag(HLTags.PARAGON_LEGGINGS)
        ;

        tag(HLTags.BOOTS)
                .addTag(HLTags.SCHOOL_BOOTS)
                .addTag(HLTags.NETHERITE_BATTLEMAGE_BOOTS)
                .addTag(HLTags.PURE_BOOTS)
                .addTag(HLTags.PARAGON_BOOTS)
        ;

        tag(ItemTags.HEAD_ARMOR)
                .addTag(HLTags.HELMETS)
        ;

        tag(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .addTag(HLTags.HELMETS)
        ;

        tag(ItemTags.CHEST_ARMOR)
                .addTag(HLTags.CHESTPLATES)
        ;

        tag(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .addTag(HLTags.CHESTPLATES)
        ;

        tag(ItemTags.LEG_ARMOR)
                .addTag(HLTags.LEGGINGS)
        ;

        tag(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .addTag(HLTags.LEGGINGS)
        ;

        tag(ItemTags.FOOT_ARMOR)
                .addTag(HLTags.BOOTS)
        ;

        tag(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .addTag(HLTags.BOOTS)
        ;

        tag(ModTags.INSCRIBED_RUNES)
                .add(HLItemRegistry.SHADOW_RUNE.get())
                .add(HLItemRegistry.RADIANCE_RUNE.get())
                .add(HLItemRegistry.COSMIC_RUNE.get())
        ;

    }
}