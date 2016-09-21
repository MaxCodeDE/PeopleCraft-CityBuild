package me.minemax.CityBuild;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class GS_TP {

	
	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] " + ChatColor.GREEN;
	
	
	public void TPtoGS(Player p, String gsname) {
		
		
		File file = new File("plugins/PeopleCraft-CityBuild/" + "Schilder.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		
		World world = Bukkit.getWorld(cfg.getString("Schilder." + gsname + ".World"));
		int locX = cfg.getInt("Schilder." + gsname + ".locX");
		int locY = cfg.getInt("Schilder." + gsname + ".locY");
		int locZ = cfg.getInt("Schilder." + gsname + ".locZ");
		float yaw = cfg.getInt("Schilder." + gsname + ".Yaw");
		float pitch = cfg.getInt("Schilder." + gsname + ".Pitch");
		
		Location loc = new Location(world, locX, locY, locZ, yaw, pitch);
		
		p.teleport(loc);
		
		
		p.sendMessage(str + "Du wurdest zum GS " + gsname + " teleportiert!");
		
	}
	
	
	
	
	
	
}
