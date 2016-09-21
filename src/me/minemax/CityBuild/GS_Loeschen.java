package me.minemax.CityBuild;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class GS_Loeschen {
	
	
	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] " + ChatColor.GREEN;


	//Region wird geloescht
	public void loeschen(Player p, String name2) {
	
		File file = new File("plugins/PeopleCraft-CityBuild/" + "Grundstuecke.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		
		cfg.set("GS." + name2, null);
		
		int anzahl = cfg.getInt("Anzahl." + ".Anzahl") -1;
		cfg.set("Anzahl." + ".Anzahl", anzahl);
		
		
		p.performCommand("rg remove " + name2);
		
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		p.sendMessage(str + name2 + " wurde geloescht!");
}
}
