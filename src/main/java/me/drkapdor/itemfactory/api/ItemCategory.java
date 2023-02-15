package me.drkapdor.itemfactory.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;

@AllArgsConstructor
@Getter
public class ItemCategory {

    private final String display;
    private final String colorScheme;

    @Override
    public String toString() {
        return ChatColor.of(colorScheme) + display;
    }

}
