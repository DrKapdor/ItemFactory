package me.drkapdor.itemfactory.api;

import lombok.Getter;

@Getter
public class ItemFactory {

    private final CustomItemRegistry registry;

    public ItemFactory(CustomItemRegistry registry) {
        this.registry = registry;
    }
}
