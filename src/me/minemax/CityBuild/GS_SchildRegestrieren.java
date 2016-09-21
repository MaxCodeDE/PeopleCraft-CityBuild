package me.minemax.CityBuild;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class GS_SchildRegestrieren implements Listener {

	
	@EventHandler
	public void schildKlick(PlayerInteractEvent e) {
		
		if (e.getPlayer().isOp()) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		if (e.getPlayer().getItemInHand().getType() == Material.STICK) {
			
		Block b = e.getClickedBlock();
		
		
		registerSchild(b);
		
		
		}
		}
		}
		
	}
	
	
	
	
	public void registerSchild(Block b) {
		
		BlockState bs = b.getState();
		
		if (bs instanceof Sign) {
			
			Sign s = (Sign) bs;
			
			File file = new File("plugins/PeopleCraft-CityBuild/" + "Schilder.yml");
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
			
			World world = s.getLocation().getWorld();
			double locX = s.getLocation().getX();
			double locY = s.getLocation().getY();
			double locZ = s.getLocation().getZ();
			float yaw = s.getLocation().getYaw();
			float pitch = s.getLocation().getPitch();
			
			
			if (s.getLine(0).equals(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "GS" + ChatColor.DARK_GRAY+ "]")) {
				
				String gsname = s.getLine(1);
				cfg.set("Schilder." + gsname + ".World", world.getName());
				cfg.set("Schilder." + gsname + ".locX",  locX);
				cfg.set("Schilder." + gsname + ".locY",  locY);
				cfg.set("Schilder." + gsname + ".locZ",  locZ);
				cfg.set("Schilder." + gsname + ".Yaw",  yaw);
				cfg.set("Schilder." + gsname + ".Pitch",  pitch);
				
				try {
					cfg.save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else if (s.getLine(0).equals(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "GS von:" + ChatColor.DARK_GRAY+ "]")) {
				
				String gsname = s.getLine(2);
				cfg.set("Schilder." + gsname + ".World", world.getName());
				cfg.set("Schilder." + gsname + ".locX",  locX);
				cfg.set("Schilder." + gsname + ".locY",  locY);
				cfg.set("Schilder." + gsname + ".locZ",  locZ);
				cfg.set("Schilder." + gsname + ".Yaw",  yaw);
				cfg.set("Schilder." + gsname + ".Pitch",  pitch);
				
				try {
					cfg.save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else {
				System.out.println("Zeile nicht gefunden!");
			}
			
			
			
		}
		
	}
	
	
	
	
	
	
}
