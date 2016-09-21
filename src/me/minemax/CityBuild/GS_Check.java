package me.minemax.CityBuild;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class GS_Check {

	
	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] " + ChatColor.GREEN;
	
	
	
	public void check(Player p) {
		
		
		File file = new File("plugins/PeopleCraft-CityBuild/" + "Grundstuecke.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		
		p.sendMessage(str + "Folgende GS sind abgelaufen:");
		
		for (String gsname : cfg.getConfigurationSection("GS.").getKeys(false)) {
		
			if (cfg.getInt("GS." + gsname + ".Zeit") <= 0) {
			if (!cfg.get("GS." + gsname + ".Besitzer").equals(1)) {
			if (!cfg.get("GS." + gsname + ".Besitzer").equals(0)) {
		
				String gsbesitzer = cfg.getString("GS." + gsname + ".Besitzer");
				
		
			p.sendMessage(str + "-> " + gsname + " | " + gsbesitzer);
			
			
			
			}
			} 
			} 
		}
		
	}
	
	
	
	
}
