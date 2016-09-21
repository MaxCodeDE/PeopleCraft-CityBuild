package me.minemax.CityBuild;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Stadt_Erstellen {

	
	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] " + ChatColor.GREEN;
	
	
	
	public void erstellen(Player p, String erstellenname){
		
		
		File file = new File("plugins/PeopleCraft-CityBuild/" + "Preise.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		if (!cfg.isSet("Preise." + "." + erstellenname)) {
			
			cfg.set("Preise." + "." + erstellenname, 0);
			
			p.sendMessage(str + "Du hast die Stadt " + erstellenname + " erstellt.");
			
			try {
				cfg.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			p.sendMessage(str + "Diese Stadt gibt es schon!");
		}
	}
	
}
