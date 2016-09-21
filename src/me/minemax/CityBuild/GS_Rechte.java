package me.minemax.CityBuild;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class GS_Rechte {

	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] " + ChatColor.GREEN;
	
	
	
	
	
	public void Hinzu(Player p, String rechtename) {
		
		
		File file = new File("plugins/PeopleCraft-CityBuild/" + "Grundstuecke.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		for (String gsname : cfg.getConfigurationSection("GS.").getKeys(false)) {
			
			if (cfg.getString("GS." + gsname + ".Besitzer").equals(p.getName())) {
				
				
				
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rg addmember " + gsname + " -w gs " + rechtename);
				
				
				
				
				p.sendMessage(str + "Du hast " + rechtename + " zu deinem GS hinzugefügt!");
				
				
			} 
			
			
		}
		
		
		
	}
	
	
	public void Entfernen(Player p, String rechtenameent) {
		
		
		File file = new File("plugins/PeopleCraft-CityBuild/" + "Grundstuecke.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		for (String gsname : cfg.getConfigurationSection("GS.").getKeys(false)) {
			
			if (cfg.getString("GS." + gsname + ".Besitzer").equals(p.getName())) {
				
				
				
				
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rg removemember " + gsname + " -w gs " + rechtenameent);
				
				
				
				p.sendMessage(str + "Du hast " + rechtenameent + " von deinem GS entfernt!");
				
				
			} 
			
			
		}
		
		
		
	}
	
	
	
	
	
	
}
