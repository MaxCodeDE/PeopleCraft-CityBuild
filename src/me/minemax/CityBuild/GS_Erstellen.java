package me.minemax.CityBuild;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GS_Erstellen {
	
	
	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] " + ChatColor.GREEN;
	
	
	//Item wird gegeben + Info
	public void infounditems(Player p) {
		
		
		p.getInventory().addItem(new ItemStack(Material.WOOD_AXE));
		p.sendMessage(str + "Makiere mit der Axt eine Region");
		p.sendMessage(str + "GS erstellen -> /gs erstellen <name>");
		
		File file = new File("plugins/PeopleCraft-CityBuild/" + "Grundstuecke.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		if (!file.exists()) {
			try {
				cfg.set("Anzahl." + ".Anzahl", 0);
				cfg.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	
	
	
	
	
	//Region wird ertellt
	public void erstellen(Player p, String name) {
		
		
		
		File file = new File("plugins/PeopleCraft-CityBuild/" + "Grundstuecke.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		
		//if (!cfg.getConfigurationSection("GS.").contains(name)) {
		if (!cfg.isSet("GS." + name)) {
		
		p.performCommand("/expand vert");
		p.performCommand("rg define " + name);
		
		int anzahl = cfg.getInt("Anzahl." + ".Anzahl") +1;
		
		cfg.set("Anzahl." + ".Anzahl", anzahl);
		cfg.set("GS." + name + ".Zeit", 0);
		cfg.set("GS." + name + ".Besitzer", 0);
		cfg.set("GS." + name + ".Mitglieder", 0);
		cfg.set("GS." + name + ".Stadt", 0);
		
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		p.sendMessage(str + "Du hast das GS " + name + " erstellt");
			
		} else {
			p.sendMessage(str + "Fehler: Den GS-Namen " + name + " gibt es schon");
		}
		
		
	}
	
	
	
	
	
	
	
	
	

}
