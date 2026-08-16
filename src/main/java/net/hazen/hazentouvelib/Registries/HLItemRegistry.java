package net.hazen.hazentouvelib.Registries;

import io.redspace.ironsspellbooks.item.UpgradeOrbItem;
import io.redspace.ironsspellbooks.registries.ComponentRegistry;
import net.hazen.hazentouvelib.HazentouveLib;
import net.hazen.hazentouvelib.Items.Misc.SoulIgniterItem;
import net.hazen.hazentouvelib.Rarities.HLRarities;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.Unbreakable;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;

public class HLItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(HazentouveLib.MOD_ID);


    /*
     *** [Upgrade Orbs] **************************************************************************
     */

    // Radiance Upgrade Orb
    public static final DeferredHolder<Item, Item> RADIANCE_UPGRADE_ORB = ITEMS.register("radiance_upgrade_orb",
            () -> new UpgradeOrbItem(new Item.Properties()
                    .rarity(Rarity.UNCOMMON)
                    .component(ComponentRegistry.UPGRADE_ORB_TYPE, HLUpgradeOrbTypeRegistry.RADIANCE_SPELL_POWER))
    );

    // Shadow Upgrade Orb
    public static final DeferredHolder<Item, Item> SHADOW_UPGRADE_ORB = ITEMS.register("shadow_upgrade_orb",
            () -> new UpgradeOrbItem(new Item.Properties()
                    .rarity(Rarity.UNCOMMON)
                    .component(ComponentRegistry.UPGRADE_ORB_TYPE, HLUpgradeOrbTypeRegistry.SHADOW_SPELL_POWER))
    );

    // Cosmic Upgrade Orb
    public static final DeferredHolder<Item, Item> COSMIC_UPGRADE_ORB = ITEMS.register("cosmic_upgrade_orb",
            () -> new UpgradeOrbItem(new Item.Properties()
                    .rarity(Rarity.UNCOMMON)
                    .component(ComponentRegistry.UPGRADE_ORB_TYPE, HLUpgradeOrbTypeRegistry.COSMIC_SPELL_POWER))
    );

    /*
     *** [Runes] **************************************************************************
     */

    //Shadow Rune
    public static final DeferredHolder<Item, Item> SHADOW_RUNE = ITEMS.register("shadow_rune",
            () -> new Item(new Item.Properties())
    );

    //Radiance Rune
    public static final DeferredHolder<Item, Item> RADIANCE_RUNE = ITEMS.register("radiance_rune",
             () -> new Item(new Item.Properties())
    );

    //Cosmic Rune
    public static final DeferredHolder<Item, Item> COSMIC_RUNE = ITEMS.register("cosmic_rune",
             () -> new Item(new Item.Properties())
    );

    /*
    *** Misc
     */

    //Soul Igniter
    public static final DeferredHolder<Item, Item> SOUL_IGNITER = ITEMS.register("soul_igniter",
            () -> new SoulIgniterItem(new Item.Properties()
                    .stacksTo(1)
                    .fireResistant()
                    .rarity(HLRarities.FIRE_RARITY.getValue())
                    .component(DataComponents.UNBREAKABLE, new Unbreakable(false))
            )
    );


    public static Collection<DeferredHolder<Item, ? extends Item>> getHLItems()
    {
        return ITEMS.getEntries();
    }

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
