package me.minemax.CityBuild;

import java.io.File;
import java.io.IOException;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class GS_Kaufen implements Listener {

	
	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] " + ChatColor.GREEN;
	
	
	public Economy econ;
	public CityBuildMain cbMain;
	@SuppressWarnings("static-access")
	public GS_Kaufen(CityBuildMain cbm) {
		econ = cbm.economy;
		cbMain = cbm;
	}
	
	
	
	@EventHandler
	public void signCreate(final SignChangeEvent e) {
		
		Player p = e.getPlayer();
		if (e.getLine(0).equals("[GS]")) {
		if (e.getPlayer().isOp()) {
		if (!e.getLine(1).isEmpty() || !e.getLine(2).isEmpty()) {
			String gsname = e.getLine(1);
			String stadt = e.getLine(2);
		File file = new File("plugins/PeopleCraft-CityBuild/" + "Grundstuecke.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		File file2 = new File("plugins/PeopleCraft-CityBuild/" + "Preise.yml");
		FileConfiguration preise = YamlConfiguration.loadConfiguration(file2);
		if (cfg.isSet("GS." + gsname)) {
		if (preise.isSet("Preise." + "." + e.getLine(2))) {
			
			cfg.set("GS." + gsname + ".Stadt", stadt);
			try {
				cfg.save(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			e.setLine(0, ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "GS" + ChatColor.DARK_GRAY+ "]");
			p.sendMessage(str + "Du hast das GS-Kaufen Schild erfolgreich erstellt!");
			
			//1 sec delay weil Schild brauch Zeit um inhalt zu aendern
			Bukkit.getScheduler().scheduleSyncDelayedTask(cbMain, new Runnable() {
				
				public void run() {	
			
				GS_SchildRegestrieren gsr = new GS_SchildRegestrieren();
				Block b = e.getBlock();
				gsr.registerSchild(b);
			
				}
			}, 20);
			
			
		} else {
			e.getBlock().breakNaturally();
			p.sendMessage(str + "Gebe eine gueltige Stadt an!");
		}
		} else {
			e.getBlock().breakNaturally();
			p.sendMessage(str + "Dieses GS gibt es nicht!");
		}
		} else {
			e.getBlock().breakNaturally();
			p.sendMessage(str + "Eine Zeile fehlt!");
		}
		} else {
			e.getBlock().breakNaturally();
		}
		}
		}
	
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void kaufen(PlayerInteractEvent e) {
		
		Player p = e.getPlayer();
		
		if (p.getLocation().getWorld().getName().equalsIgnoreCase("gs")) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		BlockState bs = e.getClickedBlock().getState();
		
		if (bs instanceof Sign) {
		
		Sign s = (Sign) bs;
		String gsname = s.getLine(1);
		String stadt = s.getLine(2);
		
		if (s.getLine(0).equals(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "GS" + ChatColor.DARK_GRAY+ "]")) {
			File file = new File("plugins/PeopleCraft-CityBuild/" + "Grundstuecke.yml");
			FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		if (cfg.isSet("GS." + s.getLine(1))) {
			
			
		if (this.spielerGS(p, stadt) == false) {
			
			File file3 = new File("plugins/PeopleCraft-CityBuild/" + "Preise.yml");
			FileConfiguration preise = YamlConfiguration.loadConfiguration(file3);
			
			
			int preis = preise.getInt("Preise." + "." + stadt);
		if (econ.has(p.getName(), preis)) {
				
			econ.withdrawPlayer(p.getName(), preis);
			
			
			cfg.set("GS." + s.getLine(1) + ".Besitzer", p.getName());
			cfg.set("GS." + s.getLine(1) + ".Zeit", 720);
			try {
				cfg.save(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rg addmember " + s.getLine(1) + " -w gs " + p.getName());
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rg flag " + s.getLine(1) + " -w gs build");
			
			
			s.setLine(0, ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "GS von:" + ChatColor.DARK_GRAY+ "]");
			s.setLine(1, p.getName());
			s.setLine(2, gsname);
			s.update();
			p.sendMessage(str + "Du hast das GS erfolgreich fuer " + preis + " Bux gekauft!");
			p.sendMessage(str + "Das GS ist Jetzt fuer 720 Stunden bezahlt.");
			
		} else {
			p.sendMessage(str + "Du hast nicht genug Bux!");
		}
		} else {
			p.sendMessage(str + "Du hast bereits ein GS in dieser Stadt!");
		}
		}
		}
		}
		}
		}	
		}
		
	
	
	public boolean spielerGS(Player p, String stadt) {
		
		File file = new File("plugins/PeopleCraft-CityBuild/" + "Grundstuecke.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		boolean spielerGSBO = false;
		
		
		for (String s : cfg.getConfigurationSection("GS.").getKeys(false)) {
			
			if (cfg.get("GS." + s + ".Besitzer").equals(p.getName())) {
			if (cfg.get("GS." + s + ".Stadt").equals(stadt)) {
				
				spielerGSBO = true;
				
			}
			}
			
			
			
		}
		
		return spielerGSBO;
		
	}
	
	
	
	
		
	}
	

	
	
	

