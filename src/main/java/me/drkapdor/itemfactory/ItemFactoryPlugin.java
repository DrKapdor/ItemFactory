package me.drkapdor.itemfactory;

import lombok.Getter;
import me.drkapdor.itemfactory.api.CustomItemRegistry;
import me.drkapdor.itemfactory.api.ItemFactory;
import me.drkapdor.itemfactory.listener.PlayerListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemFactoryPlugin extends JavaPlugin {


    @Getter
    private static ItemFactoryPlugin instance;

    @Getter
    private static ItemFactory itemFactory;

    @Override
    public void onEnable() {
        init();
    }

    private void init() {
        instance = this;
        itemFactory = new ItemFactory(new CustomItemRegistry());
        registerListeners(new PlayerListener(itemFactory));
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) getServer().getPluginManager().registerEvents(listener, this);
    }
}
