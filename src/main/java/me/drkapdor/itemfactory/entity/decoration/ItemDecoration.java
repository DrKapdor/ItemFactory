package me.drkapdor.itemfactory.entity.decoration;

import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.ChatColor;

@Getter
@Setter
public class ItemDecoration {

    private String content;

    public ItemDecoration(String content) {
        this.content = content;

    }

    @Override
    public String toString() {
        return ChatColor.translateAlternateColorCodes('&', content);
    }

}
