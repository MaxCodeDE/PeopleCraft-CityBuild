package me.minemax.CityBuild;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GS_Schildtool {

	
	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] " + ChatColor.GREEN;
	
	
	
	public void addSchildtool(Player p) {
		
		
		ItemStack schildtool = new ItemStack(Material.STICK);
		ItemMeta schildtoolmeta = schildtool.getItemMeta();
		schildtoolmeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Schildtool");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Klicke mit dem Stock auf ein");
		lore.add("GS-Schild um es zu regestrieren.");
		schildtoolmeta.setLore(lore);
		schildtool.setItemMeta(schildtoolmeta);
		p.getInventory().addItem(schildtool);
		
		p.sendMessage(str + "Nutze den Stock zum regestrieren eines Schildes.");
		
	}
	
	
	
	
	
	
}
