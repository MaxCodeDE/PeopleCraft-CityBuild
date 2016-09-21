package me.minemax.CityBuild;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GS_Frei implements Listener {

	
	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] " + ChatColor.GREEN;
	
	
	
	public void openStadtMenuForKompassManager(Player p) {
		
		
		File file = new File("plugins/PeopleCraft-CityBuild/" + "Preise.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		Inventory inv = Bukkit.createInventory(p, 36, str + "Staedte");
		
		int SlotID = 0;
		
		for (String stadt : cfg.getConfigurationSection("Preise." + ".").getKeys(false)) {
		
			ItemStack item = new ItemStack(Material.BRICK);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(stadt);
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "Klicke hier um ein freies GS");
			lore.add(ChatColor.GRAY + "in der Stadt '" + stadt + "'");
			lore.add(ChatColor.GRAY + "zu finden.");
			meta.setLore(lore);
			item.setItemMeta(meta);
			
			inv.setItem(SlotID, item);
			
			SlotID++;
			
			
		}
			
		p.openInventory(inv);	
		
		}
		
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		
		if (e.getInventory().getName().equals(str + "Staedte")) {
			e.setCancelled(true);
		if (e.getSlotType() != SlotType.OUTSIDE) {
		if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()) {
			
			Player p = (Player) e.getWhoClicked();
			ItemStack item = e.getCurrentItem();
			String Stadt = item.getItemMeta().getDisplayName();
			
			KompassManager km = new KompassManager();
			km.setCompassTargetToEmptyGS(p, Stadt);
			
			
		}
		}
		}
		
		
	}
	
	
	
	
	
	
		
	}
	