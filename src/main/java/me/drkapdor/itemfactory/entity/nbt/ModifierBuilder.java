package me.drkapdor.itemfactory.entity.nbt;

import lombok.Getter;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;

import java.util.UUID;

@Getter
public class ModifierBuilder {

    private UUID uuid;
    private String name;
    private double amount;
    private AttributeModifier.Operation operation;
    private EquipmentSlot slot;

    public ModifierBuilder uuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public ModifierBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ModifierBuilder amount(double amount) {
        this.amount = amount;
        return this;
    }

    public ModifierBuilder operation(AttributeModifier.Operation operation) {
        this.operation = operation;
        return this;
    }

    public ModifierBuilder slot(EquipmentSlot slot) {
        this.slot = slot;
        return this;
    }

    public AttributeModifier build() {
        if (uuid != null && slot != null) return new AttributeModifier(uuid, name, amount, operation, slot);
        if (uuid != null) return new AttributeModifier(uuid, name, amount, operation);
        return new AttributeModifier(name, amount, operation);
    }
}
