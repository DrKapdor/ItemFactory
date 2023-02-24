package me.drkapdor.itemfactory.api.event;

import lombok.Getter;
import me.drkapdor.itemfactory.api.CustomItem;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class CustomItemUseEvent extends PlayerInteractEvent implements Cancellable {

    @Getter
    private final CustomItem customItem;
    private boolean isCancelled;

    private static final HandlerList HANDLER_LIST = new HandlerList();

    public CustomItemUseEvent(Player player,
                              Action action,
                              ItemStack item,
                              Block block,
                              BlockFace blockFace,
                              EquipmentSlot hand,
                              CustomItem customItem) {
        super(player, action, item, block, blockFace, hand);
        this.customItem = customItem;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public String getEventName() {
        return "CustomItemUseEvent";
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

}
