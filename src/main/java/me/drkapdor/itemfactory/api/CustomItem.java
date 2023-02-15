package me.drkapdor.itemfactory.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import me.drkapdor.itemfactory.api.event.CustomItemUseEvent;
import me.drkapdor.itemfactory.entity.decoration.ItemDecoration;
import me.drkapdor.itemfactory.entity.decoration.ItemPlaceholder;
import me.drkapdor.itemfactory.entity.nbt.NbtApplier;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.function.Consumer;

@Builder
@Getter
public class CustomItem {

    private final String id;
    private final Material material;
    private final int modelData;
    private ItemDecoration displayName;
    private ItemCategory category;
    @Singular private Set<ItemDecoration> loreLines;
    private Consumer<CustomItemUseEvent> clickAction;
    public ItemStack toItemStack(ItemPlaceholder... placeholders) {
        ItemStack itemStack = new ItemStack(material);
        applyMetadata(itemStack, placeholders);
        return new NbtApplier(itemStack).append("itemfactory.id", id).complete();
    }

    public void applyMetadata(ItemStack itemStack, ItemPlaceholder... placeholders) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            itemMeta.setCustomModelData(modelData);
            if (displayName != null)  {
                String string = displayName.toString();
                for (ItemPlaceholder placeholder : placeholders)
                    string = placeholder.replace(displayName.toString());
                itemMeta.setDisplayName(string);
            }
            if (loreLines != null && !loreLines.isEmpty()) {
                List<String> lore = new ArrayList<>();
                if (category != null) {
                    lore.add(new ItemDecoration(category.toString()).toString());
                }
                loreLines.forEach(line -> {
                    String string = line.toString();
                    for (ItemPlaceholder placeholder : placeholders)
                        string = placeholder.replace(line.toString());
                    lore.add(string);
                });
                itemMeta.setLore(lore);
            }
            itemStack.setItemMeta(itemMeta);
        }
    }
}
