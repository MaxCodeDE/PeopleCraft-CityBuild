package me.minemax.CityBuild;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Stadt_Preis {
	
	
	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] " + ChatColor.GREEN;

	
	
	public void preis(Player p, String stadtname, String preisint) {
		
		
		
		File file = new File("plugins/PeopleCraft-CityBuild/" + "Preise.yml");
		FileConfiguration preise = YamlConfiguration.loadConfiguration(file);
		
		
		if (preise.isSet("Preise." + "." + stadtname)) {
			
			int preis = Integer.parseInt(preisint);
			
			preise.set("Preise." + "." + stadtname, preis);
			
			p.sendMessage(str + "Die Stadt " + stadtname + " hat nun den GS-Preis " + preis);
			
			
			try {
				preise.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		} else {
			p.sendMessage(str + "Diese Stadt gibt es nicht");
		}
		
		
		
		
		
	}
	
	
	
	
	
}
