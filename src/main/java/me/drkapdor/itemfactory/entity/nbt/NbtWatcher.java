package me.drkapdor.itemfactory.entity.nbt;

import me.drkapdor.itemfactory.ItemFactoryPlugin;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class NbtWatcher <T extends PersistentDataHolder> {
    private final T object;

    public NbtWatcher(T object) {
        this.object = object;
    }

    public boolean hasTag(String key) {
        for (NamespacedKey namespacedKey : object.getPersistentDataContainer().getKeys()) {
            if (namespacedKey.getKey().equals(key))
                return true;
        }
        return false;
    }

    public boolean hasTags(String... keys) {
        PersistentDataContainer container = object.getPersistentDataContainer();
        int found = 0;
        Set<NamespacedKey> namespacedKeys = container.getKeys();
        for (String key : new HashSet<>(Arrays.asList(keys))) {
            NamespacedKey currentKey = new NamespacedKey(ItemFactoryPlugin.getInstance(), key);
            if (namespacedKeys.contains(currentKey)) found++;
        }
        return found == keys.length;
    }

    public <K, M> Optional<M> getValue(String tag, PersistentDataType<K, M> dataType) {
        return Optional.ofNullable(object.getPersistentDataContainer()
                .get(new NamespacedKey(ItemFactoryPlugin.getInstance(), tag), dataType));
    }
}
