package me.minemax.CityBuild;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
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

public class GS_GUI implements Listener {


	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] " + ChatColor.GREEN;
	
	
	public void GSGUI(Player p) {
		
		Inventory inv = Bukkit.createInventory(null, 36, str + "GS Menu");
		
		ItemStack sethome = new ItemStack(Material.BED);
		ItemMeta sethomemeta = sethome.getItemMeta();
		sethomemeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Homepunkt setzen");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "Hiermit kannst du dein Zuhause setzen.");
		sethomemeta.setLore(lore);
		lore.clear();
		sethome.setItemMeta(sethomemeta);
		inv.setItem(0, sethome);
		
		ItemStack gohome = new ItemStack(Material.ENDER_PEARL);
		ItemMeta gohomemeta = gohome.getItemMeta();
		gohomemeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Zum Homepunkt teleportieren");
		ArrayList<String> gohomelore = new ArrayList<String>();
		gohomelore.add(ChatColor.GRAY + "Teleportiert dich zu deinem");
		gohomelore.add(ChatColor.GRAY + "Homepunkt.");
		gohomemeta.setLore(gohomelore);
		gohomelore.clear();
		gohome.setItemMeta(gohomemeta);
		inv.setItem(1, gohome);
		
		ItemStack gskaufen = new ItemStack(Material.SIGN);
		ItemMeta gskaufenmeta = gskaufen.getItemMeta();
		gskaufenmeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Grundstueck kaufen");
		ArrayList<String> gskaufenlore = new ArrayList<String>();
		gskaufenlore.add(ChatColor.GRAY + "Zeigt dir wie du ein GS");
		gskaufenlore.add(ChatColor.GRAY + "kaufen kannst.");
		gskaufenmeta.setLore(gskaufenlore);
		gskaufenlore.clear();
		gskaufen.setItemMeta(gskaufenmeta);
		inv.setItem(2, gskaufen);
		
		ItemStack freiesgs = new ItemStack(Material.COMPASS);
		ItemMeta freiesgsmeta = freiesgs.getItemMeta();
		freiesgsmeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Freies Grundstueck finden");
		ArrayList<String> freiesgslore = new ArrayList<String>();
		freiesgslore.add(ChatColor.GRAY + "Fuehrt dich zu einem freien GS");
		freiesgsmeta.setLore(freiesgslore);
		freiesgslore.clear();
		freiesgs.setItemMeta(freiesgsmeta);
		inv.setItem(3, freiesgs);
		
		
		//TO-DO zu GS finden
		
		
		p.openInventory(inv);
		
	}
	
	
	
	@EventHandler
	public void InvClick(InventoryClickEvent e) {
		
		
		
		if (e.getInventory().getName().equals(str + "GS Menu")) {
			e.setCancelled(true);
		if (e.getSlotType() != SlotType.OUTSIDE) {
		if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()) {
			
			//Homepunkt setzen
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "" + ChatColor.BOLD + "Homepunkt setzen")) {
				
				Player p = (Player) e.getWhoClicked();
			if (p.getLocation().getWorld().getName().equals("gs")) {
				
				File file = new File("plugins/PeopleCraft-CityBuild/" + "Homepunkte.yml");
				FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				cfg.set("Homepunkte." + p.getName() + ".Y", p.getLocation().getBlockY());
				cfg.set("Homepunkte." + p.getName() + ".X", p.getLocation().getBlockX());
				cfg.set("Homepunkte." + p.getName() + ".Z", p.getLocation().getBlockZ());
				cfg.set("Homepunkte." + p.getName() + ".Yaw", p.getLocation().getYaw());
				cfg.set("Homepunkte." + p.getName() + ".Pitch", p.getLocation().getPitch());
				
				try {
					cfg.save(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				 
				p.sendMessage(str + "Du hast deinen Homepunkt gesetzt.");
				p.closeInventory();
			} else {
				p.sendMessage(str + "Nur in der Citybuild Welt moeglich!");
			}
			}
			//Homepunkt setzen
			
			
			//Homepunkt TP
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "" + ChatColor.BOLD + "Zum Homepunkt teleportieren")) {
				
				Player p = (Player) e.getWhoClicked();
			if (p.getLocation().getWorld().getName().equals("gs")) {
				
				File file = new File("plugins/PeopleCraft-CityBuild/" + "Homepunkte.yml");
				FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

			if (!(cfg.getString("Homepunkte." + p.getName()) == null)) {
				
				World world = Bukkit.getWorld(p.getLocation().getWorld().getName());
				double locX = cfg.getInt("Homepunkte." + p.getName() + ".X") +0.5D;
				double locY = cfg.getInt("Homepunkte." + p.getName() + ".Y") +0.5D;
				double locZ = cfg.getInt("Homepunkte." + p.getName() + ".Z") +0.5D;
				float yaw = cfg.getInt("Homepunkte." + p.getName() + ".Yaw");
				float pitch = cfg.getInt("Homepunkte." + p.getName() + ".Pitch");
				p.teleport(new Location(world, locX, locY, locZ, yaw, pitch));
				
				p.getLocation().getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 5);
				p.getLocation().getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 5);
				p.getLocation().getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 5);
				p.getLocation().getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 5);
				p.getLocation().getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 5);
				p.getLocation().getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 5);
				p.getLocation().getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 5);
				
				p.closeInventory();
				
			p.sendMessage(str + "Du hast dich zu deinem Homepunkt teleportiert.");
			} else {
				p.sendMessage(str + "Du musst erst einen Homepunkt setzen!");
			}
			} else {
				p.sendMessage(str + "Nur in der Citybuild Welt moeglich!");
			}
			}
			//Homepunkt tp
			
			
			//GS kaufen Info
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "" + ChatColor.BOLD + "Grundstueck kaufen")) {
				
				Player p = (Player) e.getWhoClicked();
				
				p.sendMessage(str + "Suche ein freies GS mit folgenden Inhalt auf dem Schild:");
				p.sendMessage("");
				p.sendMessage(str + "      [GS]");
				p.sendMessage(str + "     GS-ID");
				p.sendMessage(str + "    Stadt-ID");
				p.sendMessage("");
				p.sendMessage(str + "Nutze im Menu den Punkt 'Freies Grundstueck finden' um zu einem gefuehrt zu werden.");
				
				p.closeInventory();
				
			}
			//GS kaufen Info
			
			
			
			//Freies GS finden
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "" + ChatColor.BOLD + "Freies Grundstueck finden")) {
				
				Player p = (Player) e.getWhoClicked();
				
				GS_Frei gsf = new GS_Frei();
				gsf.openStadtMenuForKompassManager(p);
				
			}
			//Freies GS finden
			
			
			
			
			
		}
		
		}
		}
	}
	
	
	
	
	
}
