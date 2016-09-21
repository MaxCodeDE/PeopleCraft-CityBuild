package me.minemax.CityBuild;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GS_Aufladen implements Listener {

	
	public Economy econ;
	@SuppressWarnings("static-access")
	public GS_Aufladen(CityBuildMain cbm) {
		econ = cbm.economy;
	}
	
	
	
	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] " + ChatColor.GREEN;
	
	
	
	@EventHandler
	public void SignChange(SignChangeEvent e) {
		
		
	Player p = e.getPlayer();
	if (p.isOp()) {
	if (e.getLine(0).equals("[GS-A]"));
	if (e.getLine(1).equals("aufladen")) {
		
		e.setLine(0, ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "GS" + ChatColor.DARK_GRAY+ "]");
		e.setLine(1, ChatColor.BOLD + "Aufladen");
		
		
		p.sendMessage(str + "Das GS aufladen Schild wurde erstellt!");
	}
	}
	}
	
	
	
	
	
	
	
	@EventHandler
	public void OnSignClick(PlayerInteractEvent e) {
		
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK) {
			
			BlockState bs = e.getClickedBlock().getState();
			
		if (bs instanceof Sign) {
			
			Sign sign = (Sign) bs;
			
		if (sign.getLine(0).equals(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "GS" + ChatColor.DARK_GRAY+ "]")) {
		if (sign.getLine(1).equals(ChatColor.BOLD + "Aufladen")) {
			
			Player p = e.getPlayer();
			
			Inventory inv = Bukkit.createInventory(p, InventoryType.CHEST, ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] ");
			
			
			File file = new File("plugins/PeopleCraft-CityBuild/" + "Grundstuecke.yml");
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
			
			for (String gsname : cfg.getConfigurationSection("GS.").getKeys(false)) {
			
			if (cfg.getString("GS." + gsname + ".Besitzer").equals(p.getName())) {
			
				ItemStack item = new ItemStack(Material.BED);
				ItemMeta itemm = item.getItemMeta();
				itemm.setDisplayName(gsname); //ChatColor.DARK_GREEN + "" + ChatColor.BOLD + 
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(ChatColor.GRAY + "Klicke auf das Bed um");
				lore.add(ChatColor.GRAY + "die Laufzeit von deinem Gs");
				lore.add(ChatColor.GRAY + "zu erweitern");
				lore.add(ChatColor.GRAY + "Preis: 1000");
				itemm.setLore(lore);
				item.setItemMeta(itemm);
				
				inv.addItem(item);
				lore.clear();
				
				
			}
				
			}
			
			
			
			p.openInventory(inv);
		}
		}
		}
	}
	}
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void InvClick(InventoryClickEvent e) {
		
		
		if (e.getSlotType() != SlotType.OUTSIDE) {
		if (e.getInventory().getTitle().equals(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] ")) {
		Player p = (Player) e.getWhoClicked();
		if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.BED) {
			
		String gsname = e.getCurrentItem().getItemMeta().getDisplayName();
		
		
		File file = new File("plugins/PeopleCraft-CityBuild/" + "Grundstuecke.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
			
			if (cfg.isSet("GS." + gsname + ".Besitzer")) {
			if (cfg.getString("GS." + gsname + ".Besitzer").contains(p.getName())) {
				
				int preis = 1000;
				if (econ.has(p.getName(), preis)) {
					
				econ.withdrawPlayer(p.getName(), preis);
				
				
				int gszeit = cfg.getInt("GS." + gsname + ".Zeit") +720;
				cfg.set("GS." + gsname + ".Zeit", gszeit);
				try {
					cfg.save(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				
			p.sendMessage(str + "Du hast dein GS fuer 30 Tage(720 Stunden) aufgeladen!");
		
		
			
		p.closeInventory();
		} else {
			p.sendMessage(str + "Du hast nicht genug Bux!");
			p.closeInventory();
		}
		} else {
			p.sendMessage(str + "Dir gehoert dieses GS nicht!");
			p.closeInventory();
		}
		} else {
			p.sendMessage(str + "Dieses GS gibt es nicht!");
			p.closeInventory();
		}
		} 
		}
		}
		
		
		
	}
	
	
}
