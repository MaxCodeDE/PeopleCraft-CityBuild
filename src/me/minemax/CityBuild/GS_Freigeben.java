package me.minemax.CityBuild;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class GS_Freigeben {

	
	
	
	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] " + ChatColor.GREEN;
	
	
	public void freigeben(String freigebenname, Player p) {
		

		File file = new File("plugins/PeopleCraft-CityBuild/" + "Grundstuecke.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		if (cfg.getConfigurationSection("GS.").contains(freigebenname)) {
		
			
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rg flag " + freigebenname + " -w gs build");
			
			
		
		p.sendMessage(str + "Das GS " + freigebenname + " wurde freigegeben!");
		
		
	} else {
		p.sendMessage(str + "Dieses GS gibt es nicht!");
	}
	}
	
	
	
	
	
}
