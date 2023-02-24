package me.drkapdor.itemfactory.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CustomItemRegistry {

    private final Map<String, CustomItem> customItems;

    public CustomItemRegistry() {
        customItems = new HashMap<>();
    }

    public void registerItem(CustomItem customItem) {
        customItems.put(customItem.getId(), customItem);
    }

    public void unregisterItem(String id) {
        customItems.remove(id);
    }

    public Optional<CustomItem> get(String id) {
        return Optional.ofNullable(customItems.get(id));
    }

    public CustomItem unchecked(String id) {
        return customItems.get(id);
    }

}
