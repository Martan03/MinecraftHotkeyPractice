package me.Martan03.Practice;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		this.getCommand("keybinds").setExecutor(new Keybind());
		this.getServer().getPluginManager().registerEvents(new Keybind(), this);
	}
}
