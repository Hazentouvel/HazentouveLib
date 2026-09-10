package net.hazen.hazentouvelib.Utils;

import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import java.util.List;

public class AdditionalArmorAttributes {
    public record CustomAttribute(Holder<Attribute> attribute, Identifier id, double value, AttributeModifier.Operation operation) {}

    public static ItemAttributeModifiers createAttributes(ArmorMaterial material, ArmorType type,  List<CustomAttribute> additionalAttributes) {
        int defense = material.defense().getOrDefault(type, 0);

        ItemAttributeModifiers.Builder modifiers = ItemAttributeModifiers.builder();
        EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(type.getSlot());
        Identifier modifierId = Identifier.withDefaultNamespace("armor." + type.getName());

        modifiers.add(Attributes.ARMOR, new AttributeModifier(modifierId, (double)defense, AttributeModifier.Operation.ADD_VALUE), slotGroup);
        modifiers.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(modifierId, (double)material.toughness(), AttributeModifier.Operation.ADD_VALUE), slotGroup);
        if (material.knockbackResistance() > 0.0F) {
            modifiers.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(modifierId, (double)material.knockbackResistance(), AttributeModifier.Operation.ADD_VALUE), slotGroup);
        }

        for (CustomAttribute customAttribute : additionalAttributes) {
            modifiers.add(customAttribute.attribute(),
                    new AttributeModifier(Identifier.withDefaultNamespace(customAttribute.id().getPath() + "_" + type.getName()),
                            customAttribute.value(), customAttribute.operation()),
                    slotGroup
            );
        }

        return modifiers.build();
    }
}