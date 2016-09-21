package me.minemax.CityBuild;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class GS_Sperren {

	
	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] " + ChatColor.GREEN;
	
	
	public void sperren(String sperrenname, Player p) {
		

		File file = new File("plugins/PeopleCraft-CityBuild/" + "Grundstuecke.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		if (cfg.getConfigurationSection("GS.").contains(sperrenname)) {
		
			
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rg flag " + sperrenname + " -w gs build deny");
			
			
		
		p.sendMessage(str + "Das GS " + sperrenname + " wurde gesperrt!");
		
		
	} else {
		p.sendMessage(str + "Dieses GS gibt es nicht!");
	}
	}
	
	
	
	
}
