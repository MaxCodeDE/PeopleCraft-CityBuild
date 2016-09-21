package me.minemax.CityBuild;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Stadt_Befehle implements CommandExecutor {

	
	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] " + ChatColor.GREEN;

	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		
		Player p = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("stadt")) {
		if (args.length == 0) {
		if (p.isOp()) {
			p.sendMessage(str + "Stadt-System Befehle:");
			p.sendMessage(str + "Stadt erstellen -> /stadt erstellen <name>");
			p.sendMessage(str + "Stadt Preis -> /stadt preis <name> <preis>");
		}
		}
			
		
		
		if (args.length == 1) {
		if (args[0].equalsIgnoreCase("erstellen")) {
		if (p.isOp()) {
			p.sendMessage(str + "Benutze /stadt erstellen <name>");
		}
		}
		}
		
		
		
		
		
		if (args.length == 2) {
		if (p.isOp()) {
		if (args[0].equalsIgnoreCase("erstellen")) {
		String erstellenname = args[1].toLowerCase();
		if (args[1].equalsIgnoreCase(erstellenname)) {
			Stadt_Erstellen se = new Stadt_Erstellen();
			se.erstellen(p, erstellenname);
		}
		}
		}
		}	
			
			
		
		
		if (args.length == 3) {
		if (p.isOp()) {
		if (args[0].equalsIgnoreCase("preis")) {
		String stadtname = args[1].toLowerCase();
		if (args[1].equalsIgnoreCase(stadtname)) {
		String preisint = args[2].toLowerCase();
		if (args[2].equalsIgnoreCase(preisint)) {
			Stadt_Preis sp = new Stadt_Preis();
			sp.preis(p, stadtname, preisint);
		}
		}
		}
		}
		}
			
			
			
		}
		
		
		
		return false;
	}

	
	
	
	
	
}
