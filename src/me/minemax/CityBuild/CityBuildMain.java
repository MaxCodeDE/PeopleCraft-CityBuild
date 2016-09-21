package me.minemax.CityBuild;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class CityBuildMain extends JavaPlugin {

	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] " + ChatColor.GREEN;
	public static Economy economy = null;
	
	
	@Override
	public void onEnable() {
		
		System.out.println(str + "System wurde gestartet!");
		ZeitChecker zc = new ZeitChecker(this);
		zc.ZeitCheckerVoid();
		
		FileChecker fc = new FileChecker();
		fc.FileCheck();
		
		setupEconomy();
		
		getCommand("gs").setExecutor(new Befehle());
		getCommand("stadt").setExecutor(new Stadt_Befehle());
		
		
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new GS_Kaufen(this), this);
		pm.registerEvents(new GS_GUI(), this);
		pm.registerEvents(new GS_System_Sperren_Freigeben(), this);
		pm.registerEvents(new GS_Aufladen(this), this);
		pm.registerEvents(new GS_SchildRegestrieren(), this);
		pm.registerEvents(new GS_Frei(), this);
		
	}
	
	@Override
	public void onDisable() {
		
		
		
	}
	
	
	
    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
	
	
	
	
}
