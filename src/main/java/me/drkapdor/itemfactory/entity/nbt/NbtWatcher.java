package me.drkapdor.itemfactory.entity.nbt;

import lombok.Getter;
import me.drkapdor.itemfactory.ItemFactoryPlugin;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

@Getter
public class NbtWatcher<T extends PersistentDataHolder> {

    private final T dataHolder;
/*
    private Map<String, String> strings;
    private Map<String, Integer> integers;
    private Map<String, Byte> bytes;
    private Map<String, Short> shorts;
    private Map<String, Float> floats;
    private Map<String, Double> doubles;
    private Map<String, byte[]> byteArrays;
    private Map<Attribute, AttributeModifier> modifiers;
*/

    public NbtWatcher(T dataHolder) {
        this.dataHolder = dataHolder;
    }

    public NbtWatcher<T> set(String key, String value) {
        dataHolder.getPersistentDataContainer().set(
                new NamespacedKey(
                        ItemFactoryPlugin.getInstance(),
                        key.toLowerCase()),
                PersistentDataType.STRING,
                value);
        return this;
    }

    public NbtWatcher<T> set(String key, int value) {
        dataHolder.getPersistentDataContainer().set(
                new NamespacedKey(
                        ItemFactoryPlugin.getInstance(),
                        key.toLowerCase()),
                PersistentDataType.INTEGER,
                value);
        return this;
    }

    public NbtWatcher<T> set(String key, long value) {
        dataHolder.getPersistentDataContainer().set(
                new NamespacedKey(
                        ItemFactoryPlugin.getInstance(),
                        key.toLowerCase()),
                PersistentDataType.LONG,
                value);
        return this;
    }

    public NbtWatcher<T> set(String key, byte value) {
        dataHolder.getPersistentDataContainer().set(
                new NamespacedKey(
                        ItemFactoryPlugin.getInstance(),
                        key.toLowerCase()),
                PersistentDataType.BYTE,
                value);
        return this;
    }

    public NbtWatcher<T> set(String key, short value) {
        dataHolder.getPersistentDataContainer().set(
                new NamespacedKey(
                        ItemFactoryPlugin.getInstance(),
                        key.toLowerCase()),
                PersistentDataType.SHORT,
                value);
        return this;
    }

    public NbtWatcher<T> set(String key, float value) {
        dataHolder.getPersistentDataContainer().set(
                new NamespacedKey(
                        ItemFactoryPlugin.getInstance(),
                        key.toLowerCase()),
                PersistentDataType.FLOAT,
                value);
        return this;
    }

    public NbtWatcher<T> set(String key, double value) {
        dataHolder.getPersistentDataContainer().set(
                new NamespacedKey(
                        ItemFactoryPlugin.getInstance(),
                        key.toLowerCase()),
                PersistentDataType.DOUBLE,
                value);
        return this;
    }

    public NbtWatcher<T> set(String key, int[] value) {
        dataHolder.getPersistentDataContainer().set(
                new NamespacedKey(
                        ItemFactoryPlugin.getInstance(),
                        key.toLowerCase()),
                PersistentDataType.INTEGER_ARRAY,
                value);
        return this;
    }

    public NbtWatcher<T> set(String key, byte[] value) {
        dataHolder.getPersistentDataContainer().set(
                new NamespacedKey(
                        ItemFactoryPlugin.getInstance(),
                        key.toLowerCase()),
                PersistentDataType.BYTE_ARRAY,
                value);
        return this;
    }

    public NbtWatcher<T> remove(String key) {
        dataHolder.getPersistentDataContainer().getKeys().forEach(namespacedKey -> {
            if (namespacedKey.getKey().equalsIgnoreCase(key))
                dataHolder.getPersistentDataContainer().remove(namespacedKey);
        });
        return this;
    }

    public boolean has(String key) {
        for (NamespacedKey namespacedKey : dataHolder.getPersistentDataContainer().getKeys()) {
            if (namespacedKey.getKey().equalsIgnoreCase(key.toLowerCase()))
                return true;
        }
        return false;
    }

    public boolean has(String... keys) {
        PersistentDataContainer container = dataHolder.getPersistentDataContainer();
        int found = 0;
        Set<NamespacedKey> namespacedKeys = container.getKeys();
        for (String key : new HashSet<>(Arrays.asList(keys))) {
            NamespacedKey currentKey = new NamespacedKey(ItemFactoryPlugin.getInstance(), key.toLowerCase());
            if (namespacedKeys.contains(currentKey)) found++;
        }
        return found == keys.length;
    }

    public <K, M> Optional<M> getValueOptional(String tag, PersistentDataType<K, M> dataType) {
        return Optional.ofNullable(dataHolder.getPersistentDataContainer()
                .get(new NamespacedKey(ItemFactoryPlugin.getInstance(), tag.toLowerCase()), dataType));

    }

    public <K, M> M getValue(String tag, PersistentDataType<K, M> dataType) {
        return dataHolder.getPersistentDataContainer().get(new NamespacedKey(ItemFactoryPlugin.getInstance(), tag.toLowerCase()), dataType);
    }

    public void applyTo(ItemStack itemStack) {
        if (dataHolder instanceof ItemMeta itemMeta) itemStack.setItemMeta(itemMeta);
    }

}
