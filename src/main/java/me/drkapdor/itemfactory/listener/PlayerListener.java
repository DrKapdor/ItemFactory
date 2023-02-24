package me.drkapdor.itemfactory.listener;

import me.drkapdor.itemfactory.api.ItemFactory;
import me.drkapdor.itemfactory.api.event.CustomItemUseEvent;
import me.drkapdor.itemfactory.entity.nbt.NbtWatcher;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class PlayerListener implements Listener {

    private final ItemFactory itemFactory;

    public PlayerListener(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    @EventHandler
    public void onItemUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getEquipment() != null && (event.getHand() == EquipmentSlot.HAND || event.getHand() == EquipmentSlot.OFF_HAND)) {
            ItemStack itemStack = player.getEquipment().getItemInMainHand();
            if (itemStack.getItemMeta() != null) {
                NbtWatcher<ItemMeta> watcher = new NbtWatcher<>(itemStack.getItemMeta());
                watcher.getValueOptional("itemfactory.id", PersistentDataType.STRING).ifPresent(value -> {
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
