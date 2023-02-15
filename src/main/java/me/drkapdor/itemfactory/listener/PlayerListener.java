package me.drkapdor.itemfactory.listener;

import com.google.common.base.Supplier;
import me.drkapdor.itemfactory.ItemFactoryPlugin;
import me.drkapdor.itemfactory.api.CustomItem;
import me.drkapdor.itemfactory.api.ItemCategory;
import me.drkapdor.itemfactory.api.ItemFactory;
import me.drkapdor.itemfactory.api.event.CustomItemUseEvent;
import me.drkapdor.itemfactory.entity.decoration.ItemDecoration;
import me.drkapdor.itemfactory.entity.decoration.ItemPlaceholder;
import me.drkapdor.itemfactory.entity.nbt.NbtWatcher;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.List;

public class PlayerListener implements Listener {

    private final ItemFactory itemFactory;

    public PlayerListener(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        CustomItem customItem = CustomItem.builder()
                .id("test_item")
                .material(Material.EMERALD)
                .displayName(new ItemDecoration("&eТестовый предмет!"))
                .category(new ItemCategory("Тестовая категория", "#55FF55"))
                .loreLine(new ItemDecoration("&7Ваше здоровье: &e{health}"))
                .loreLine(new ItemDecoration("&fСтрока 2: &6{1}"))
                .clickAction(clickEvent -> {
                    if (clickEvent.getHand() == EquipmentSlot.HAND) {
                        Player clicker = event.getPlayer();
                        clicker.sendMessage("Каклиш)");
                    }
                })
                .build();
        itemFactory.getRegistry().registerItem(customItem);
        ItemStack itemStack = customItem.toItemStack(
                new ItemPlaceholder("health", String.valueOf(Math.round(player.getHealth())))
        );
        player.getInventory().addItem(itemStack);
    }

    @EventHandler
    public void onItemUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getEquipment() != null && (event.getHand() == EquipmentSlot.HAND || event.getHand() == EquipmentSlot.OFF_HAND)) {
            ItemStack itemStack = player.getEquipment().getItemInMainHand();
            if (itemStack.getItemMeta() != null) {
                NbtWatcher<ItemMeta> watcher = new NbtWatcher<>(itemStack.getItemMeta());
                watcher.getValue("itemfactory.id", PersistentDataType.STRING).ifPresent(value -> {
                    itemFactory.getRegistry().get(value).ifPresent(customItem -> {
                        CustomItemUseEvent customItemUseEvent = new CustomItemUseEvent(player, event.getAction(), event.getItem(),
                                event.getClickedBlock(), event.getBlockFace(), event.getHand(), customItem);
                        Bukkit.getServer().getPluginManager().callEvent(customItemUseEvent);
                        if (!customItemUseEvent.isCancelled()) customItemUseEvent.getCustomItem().getClickAction().accept(customItemUseEvent);
                        else event.setCancelled(true);
                    });
                });
            }
        }
    }

}
