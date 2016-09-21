package me.minemax.CityBuild;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileChecker {

	
	public void FileCheck() {
		
		
		File file = new File("plugins/PeopleCraft-CityBuild/" + "Grundstuecke.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		if (!file.exists()) {
			try {
				cfg.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		File file2 = new File("plugins/PeopleCraft-CityBuild/" + "Homepunkte.yml");
		FileConfiguration cfg2 = YamlConfiguration.loadConfiguration(file2);
		if (!file2.exists()) {
			try {
				cfg2.save(file2);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		File file4 = new File("plugins/PeopleCraft-CityBuild/" + "Preise.yml");
		FileConfiguration cfg4 = YamlConfiguration.loadConfiguration(file4);
		if (!file4.exists()) {
			try {
				cfg4.save(file4);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		File file5 = new File("plugins/PeopleCraft-CityBuild/" + "GS-System-Sperren.yml");
		FileConfiguration cfg5 = YamlConfiguration.loadConfiguration(file5);
		if (!file5.exists()) {
			try {
				
				cfg5.set("Sperren", "false");
				
				cfg5.save(file5);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		File file6 = new File("plugins/PeopleCraft-CityBuild/" + "Schilder.yml");
		FileConfiguration cfg6 = YamlConfiguration.loadConfiguration(file6);
		if (!file6.exists()) {
			try {				
				cfg6.save(file6);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	
	
	
}
