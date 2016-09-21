package me.minemax.CityBuild;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class GS_Spieler {

	
	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] " + ChatColor.GREEN;
	
	
	
	public void spielerLoeschen(Player p, String gsname) {
		
		File file = new File("plugins/PeopleCraft-CityBuild/" + "Grundstuecke.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rg removemember " + gsname + " -w gs -a");
				
				
				cfg.set("GS." + gsname + ".Besitzer", "0");
				cfg.set("GS." + gsname + ".Mitglieder", "0");
				try {
					cfg.save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				p.sendMessage(str + "Besitzer wurde von " + gsname + " geloescht!");
			
				
		
	}
	
	
	
	
}
