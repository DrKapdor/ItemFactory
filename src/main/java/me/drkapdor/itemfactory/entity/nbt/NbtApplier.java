package me.drkapdor.itemfactory.entity.nbt;

import me.drkapdor.itemfactory.ItemFactoryPlugin;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class NbtApplier {

    private final ItemStack itemStack;

    private Map<String, String> strings;
    private Map<String, Integer> integers;
    private Map<String, Byte> bytes;
    private Map<String, Short> shorts;
    private Map<String, Float> floats;
    private Map<String, Double> doubles;
    private Map<String, byte[]> byteArrays;
    private Map<Attribute, AttributeModifier> modifiers;

    public NbtApplier(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public NbtApplier append(String key, String value) {
        if (strings == null)
            strings = new HashMap<>();
        strings.put(key, value);
        return this;
    }

    public NbtApplier append(String key, int value) {
        if (integers == null)
            integers = new HashMap<>();
        integers.put(key, value);
        return this;
    }

    public NbtApplier append(String key, byte value) {
        if (byteArrays == null)
            bytes = new HashMap<>();
        bytes.put(key, value);
        return this;
    }

    public NbtApplier append(String key, short value) {
        if (shorts == null)
            shorts = new HashMap<>();
        shorts.put(key, value);
        return this;
    }

    public NbtApplier append(String key, float value) {
        if (floats == null)
            floats = new HashMap<>();
        floats.put(key, value);
        return this;
    }

    public NbtApplier append(String key, double value) {
        if (doubles == null)
            doubles = new HashMap<>();
        doubles.put(key, value);
        return this;
    }

    public NbtApplier append(String key, byte[] value) {
        if (byteArrays == null)
            byteArrays = new HashMap<>();
        byteArrays.put(key, value);
        return this;
    }

    public NbtApplier modify(Attribute attribute, AttributeModifier modifier) {
        if (modifiers == null)
            modifiers = new HashMap<>();
        modifiers.put(attribute, modifier);
        return this;
    }

    public ItemStack complete() {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            if (strings != null)
                strings.forEach((key, value) ->
                        meta.getPersistentDataContainer().set(
                                new NamespacedKey(ItemFactoryPlugin.getInstance(), key),
                                PersistentDataType.STRING, value));
            if (integers != null)
                integers.forEach((key, value) ->
                        meta.getPersistentDataContainer().set(
                                new NamespacedKey(ItemFactoryPlugin.getInstance(), key),
                                PersistentDataType.INTEGER, value));
            if (bytes != null)
                bytes.forEach((key, value) ->
                        meta.getPersistentDataContainer().set(
                                new NamespacedKey(ItemFactoryPlugin.getInstance(), key),
                                PersistentDataType.BYTE, value));
            if (shorts != null)
                shorts.forEach((key, value) ->
                        meta.getPersistentDataContainer().set(
                                new NamespacedKey(ItemFactoryPlugin.getInstance(),key),
                                PersistentDataType.SHORT, value));
            if (floats != null)
                floats.forEach((key, value) ->
                        meta.getPersistentDataContainer().set(
                                new NamespacedKey(ItemFactoryPlugin.getInstance(),key),
                                PersistentDataType.FLOAT, value));
            if (doubles != null)
                doubles.forEach((key, value) ->
                        meta.getPersistentDataContainer().set(
                                new NamespacedKey(ItemFactoryPlugin.getInstance(),key),
                                PersistentDataType.DOUBLE, value));
            if (byteArrays != null)
                byteArrays.forEach((key, value) ->
                        meta.getPersistentDataContainer().set(
                                new NamespacedKey(ItemFactoryPlugin.getInstance(),key),
                                PersistentDataType.BYTE_ARRAY, value));
            if (modifiers != null)
                modifiers.forEach((meta::addAttributeModifier));
            itemStack.setItemMeta(meta);
        }
        return itemStack;
    }

}
