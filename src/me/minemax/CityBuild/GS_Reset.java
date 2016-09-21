package me.minemax.CityBuild;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class GS_Reset {

	
	
	
	public void resetCheck() {
		
		
		File file = new File("plugins/PeopleCraft-CityBuild/" + "Grundstuecke.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		for (String gsname : cfg.getConfigurationSection("GS.").getKeys(false)) {
			
			if (cfg.getInt("GS." + gsname + ".Zeit") <= 0) {
			if (cfg.getInt("GS." + gsname + ".Besitzer") != 0 || !cfg.get("GS." + gsname + ".Besitzer").equals("0")) {
			
				System.out.println(gsname);
				resetGS(gsname);
				
				
			}
			}
			
		}
		
		
		
		
	}
	
	
	
	
	
	
	public void resetGS(String gsname) {
		
		
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rg flag " + gsname + " -w gs build deny");
		
		
		File file = new File("plugins/PeopleCraft-CityBuild/" + "Schilder.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		File file2 = new File("plugins/PeopleCraft-CityBuild/" + "Grundstuecke.yml");
		FileConfiguration cfg2 = YamlConfiguration.loadConfiguration(file2);
		
		
		World world = Bukkit.getWorld(cfg.getString("Schilder." + gsname + ".World"));
		int locX = cfg.getInt("Schilder." + gsname + ".locX");
		int locY = cfg.getInt("Schilder." + gsname + ".locY");
		int locZ = cfg.getInt("Schilder." + gsname + ".locZ");
		float yaw = cfg.getInt("Schilder." + gsname + ".Yaw");
		float pitch = cfg.getInt("Schilder." + gsname + ".Pitch");
		
		Location loc = new Location(world, locX, locY, locZ, yaw, pitch);
		
		Block b = loc.getBlock();
		BlockState bs = b.getState();
		
		
		if (bs instanceof Sign) {
			
			
			Sign s = (Sign) bs;
			
			s.getChunk().load();
			s.setLine(0, ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "GS" + ChatColor.DARK_GRAY+ "]");
			s.setLine(1, gsname);
			s.setLine(2, cfg2.getString("GS." + gsname + ".Stadt"));
			s.setLine(3, "");
			s.update();
			s.update(true);
			
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rg removemember " + gsname + " -w gs -a");
			
			
			cfg2.set("GS." + gsname + ".Zeit", 0);
			cfg2.set("GS." + gsname + ".Besitzer", "0");
			cfg2.set("GS." + gsname + ".Mitglieder", "0");
			
			try {
				cfg2.save(file2);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	
	
	
}
