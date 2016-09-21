package me.minemax.CityBuild;

import java.io.File;
import java.io.IOException;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class GS_System_Sperren_Freigeben implements Listener {

	
	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] " + ChatColor.GREEN;
	
	
	
	public void Sperren(Player p) {
		
		
		File sperrenfile = new File("plugins/PeopleCraft-CityBuild/" + "GS-System-Sperren.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(sperrenfile);
		
		cfg.set("Sperren", "true");
		try {
			cfg.save(sperrenfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		p.sendMessage(str + "GS-System wurde gesperrt!"); 
		
		
	}
	
	public void Freigeben(Player p) {
		
		File sperrenfile = new File("plugins/PeopleCraft-CityBuild/" + "GS-System-Sperren.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(sperrenfile);
		
		cfg.set("Sperren", "false");
		try {
			cfg.save(sperrenfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		p.sendMessage(str + "GS-System wurde freigegeben!"); 
		
	}
	
	
	public void Check(Player p) {
		
		File sperrenfile = new File("plugins/PeopleCraft-CityBuild/" + "GS-System-Sperren.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(sperrenfile);
		
		if (cfg.getString("Sperren").equals("false")) {
			p.sendMessage(str + "GS-System ist freigegeben.");
		} 
		if (cfg.getString("Sperren").equals("true")) {
			p.sendMessage(str + "GS-System ist gesperrt.");
		}
		
		
		
		
	}
	
	
	@EventHandler
	public void PlayerInteract(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		
		if (p.getLocation().getWorld().getName().equalsIgnoreCase("gs")) {
			File sperrenfile = new File("plugins/PeopleCraft-CityBuild/" + "GS-System-Sperren.yml");
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(sperrenfile);
		if (cfg.getString("Sperren").equals("true")) {
			
			e.setCancelled(true);
			
			p.sendMessage(str + "Das Citybuild-System ist zurzeit deaktiviert.");
			
		}
		}
		
		
	}
	
	
}
