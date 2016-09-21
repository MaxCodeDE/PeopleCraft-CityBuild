package me.minemax.CityBuild;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ZeitChecker {

	
	public CityBuildMain instanz;
	public ZeitChecker(CityBuildMain plugin) {
		
		instanz = plugin;
		
	}
	
	
	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] " + ChatColor.GREEN;
	
	
	public void ZeitCheckerVoid() {
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(instanz, new Runnable() {
			
			
			public void run() {
			
				
				File file = new File("plugins/PeopleCraft-CityBuild/" + "Grundstuecke.yml");
				FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				
				if (cfg.contains("GS.")) {
				for (String gsname : cfg.getConfigurationSection("GS.").getKeys(false)) {
					
					if (cfg.getInt("GS." + gsname + ".Zeit") > 0) {
					int zeit = cfg.getInt("GS." + gsname + ".Zeit") -1;
					cfg.set("GS." + gsname +".Zeit", zeit);
										
					if (!cfg.get("GS." + gsname + ".Besitzer").equals(0)) {
					String playername = cfg.getString("GS." + gsname + ".Besitzer");
					
					@SuppressWarnings("deprecation")
					Player p = Bukkit.getServer().getPlayer(playername);
					if (p != null) {
					if (p.isOnline()) {
					p.sendMessage(str + "Dein GS ist noch " + zeit + " Stunden bezahlt.");
					}
					}
					}
					
					try {
						cfg.save(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					
					
					if(zeit <= 0) {
						
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rg flag " + gsname + " -w gs build deny");
						System.out.println("Das GS " + gsname + " ist abgelaufen. Es wurde wieder zum kauf frei gegeben!");
						
					}
					
					
				}
				}
				}
				System.out.println("ZeitCheckerVoid wurde ausgefuert.");
				
				GS_Reset gsr = new GS_Reset();
				gsr.resetCheck();
				
				ZeitCheckerVoid();
				
			}
			
		}, 72000);
		//1 Stunde = 72000 ticks
		
		
	}
	
	
	
	
}
