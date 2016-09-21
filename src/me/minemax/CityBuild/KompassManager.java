package me.minemax.CityBuild;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KompassManager {

	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] " + ChatColor.GREEN;
	
	
	
	public void setCompassTargetToEmptyGS(Player p, String stadt) {
		
		
		File file = new File("plugins/PeopleCraft-CityBuild/" + "Grundstuecke.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		Location loc = null;
		
		for (String gsname : cfg.getConfigurationSection("GS.").getKeys(false)) {
			
			if (cfg.getInt("GS." + gsname + ".Zeit") <= 0) {
			if (cfg.get("GS." + gsname + ".Besitzer").equals(0) || cfg.get("GS." + gsname + ".Besitzer").equals("0")) {
			if (cfg.get("GS." + gsname + ".Mitglieder").equals(0) || cfg.get("GS." + gsname + ".Mitglieder").equals("0")) {
			if (cfg.get("GS." + gsname + ".Stadt").equals(stadt)) {

				
				File schilder = new File("plugins/PeopleCraft-CityBuild/" + "Schilder.yml");
				FileConfiguration cfgschilder = YamlConfiguration.loadConfiguration(schilder);
				
				
				World world = Bukkit.getWorld(cfgschilder.getString("Schilder." + gsname + ".World"));
				int locX = cfgschilder.getInt("Schilder." + gsname + ".locX");
				int locY = cfgschilder.getInt("Schilder." + gsname + ".locY");
				int locZ = cfgschilder.getInt("Schilder." + gsname + ".locZ");
				float yaw = cfgschilder.getInt("Schilder." + gsname + ".Yaw");
				float pitch = cfgschilder.getInt("Schilder." + gsname + ".Pitch");
				
				loc = new Location(world, locX, locY, locZ, yaw, pitch);

			}
			}
			}
			} 
		}
		
		if (loc != null) {
		
		ItemStack compass = new ItemStack(Material.COMPASS);
		ItemMeta compassmeta = compass.getItemMeta();
		compassmeta.setDisplayName(ChatColor.GREEN + "" +  ChatColor.BOLD + "Freies GS finden");
		ArrayList<String> compasslore = new ArrayList<String>();
		compasslore.add(ChatColor.GRAY + "Der Kompass zeigt dir den weg");
		compasslore.add(ChatColor.GRAY + "zu einem freien Grundstueck.");
		compassmeta.setLore(compasslore);
		compasslore.clear();
		compass.setItemMeta(compassmeta);
		
		p.getInventory().addItem(compass);
		p.setCompassTarget(loc);
		
		p.sendMessage(str + "Der Kompass zeigt dir ein freies GS!");
		p.closeInventory();
	
		} else {
			p.sendMessage(str + "Es wurde leider kein freies GS in dieser Stadt gefunden!");
			p.closeInventory();
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
