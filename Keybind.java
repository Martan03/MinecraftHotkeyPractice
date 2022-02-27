package me.Martan03.Practice;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;


public class Keybind implements CommandExecutor, Listener {
	
	private static boolean Started;
	private static Random rnd;
	
	public Keybind() {
		Started = false;
		rnd = new Random();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player))
			return false;
		
		if (label.equalsIgnoreCase("keybinds")) {
			Player plr = (Player)sender;
			if (Started) {
				Started = false;
				plr.sendMessage(ChatColor.GOLD + "Keybinds Practice has ended!");				
			}
			else {
				Started = true;
				plr.sendMessage(ChatColor.GOLD + "Keybinds Practice has started!");				
			}
		}
		
		return false;
	}
	
	@EventHandler
	public void onItemHeld(PlayerItemHeldEvent event) {
		if (!Started)
			return;
		
		if (event.isCancelled())
			return;
		
		Player plr = event.getPlayer();
		if (plr == null)
			return;
		
		ItemStack stack = plr.getInventory().getItem(event.getNewSlot());
		
		if (stack == null)
			plr.sendMessage(ChatColor.RED + "Bad :(");
		else if (stack.getType().equals(Material.TARGET))
			plr.sendMessage(ChatColor.GREEN + "Nice!");

		newTarget(plr, event.getNewSlot());
		plr.sendMessage(ChatColor.GOLD + "Switch to TARGET");
			
	}
	
	private void newTarget(Player plr, int curIndex) {
		plr.getInventory().clear();
		
		ItemStack item = new ItemStack(Material.TARGET, 1);
		int slot = rnd.nextInt(9);
		while (slot == curIndex)
			slot = rnd.nextInt(9);
		
		plr.getInventory().setItem(slot, item);
	}
}
