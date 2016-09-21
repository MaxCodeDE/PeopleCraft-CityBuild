package me.minemax.CityBuild;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Befehle implements CommandExecutor {
	
	public String str = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "PeopleCraft-GS" + ChatColor.DARK_GRAY+ "] " + ChatColor.GREEN;
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) { 
		
		Player p = (Player) sender;
		
		//Info -> /gs
		if (cmd.getName().equalsIgnoreCase("gs")) {
		if (args.length == 0) {	
			p.sendMessage(str + "Ein CityBuildSystem vom PeopleCraft.");			
			p.sendMessage(str + "GS Menu -> /gs menu");
			p.sendMessage(str + "GS Rechte -> /gs rechte");
		}
			
		
		
		//Info Admin -> /gs Admin
		if (args.length == 1) {
		if (args[0].equalsIgnoreCase("admin")) {
		if (p.isOp()) {
			p.sendMessage(str + "GS loeschen -> /gs loeschen <gsname>");
			p.sendMessage(str + "GS sperren -> /gs sperren <gsname>");
			p.sendMessage(str + "GS freigeben -> /gs freigeben <gsname>");
			p.sendMessage(str + "GS Nutzen -> /gs nutzen");
			p.sendMessage(str + "GS System -> /gs system");
			p.sendMessage(str + "GS Besitzer loeschen -> /gs spieler loeschen <gsname>");
			p.sendMessage(str + "GS Check -> /gs check");
			p.sendMessage(str + "GS Schild regestrieren -> /gs schildtool");
		}
		}
		}
		
		
		
		//Info -> /gs nutzen
		if (args.length == 1) {
		if (args[0].equalsIgnoreCase("nutzen")) {
		if (p.isOp()) {
			p.sendMessage(str + "1. Stadt ertellen -> /stadt");
			p.sendMessage(str + "2. GS erstellen -> /gs erstellen <name>");
			p.sendMessage(str + "3. Schild erstellen:");
			p.sendMessage(str + "      [GS]");
			p.sendMessage(str + "     <name>");
			p.sendMessage(str + "    <stadt>");
			p.sendMessage(str + "3. Aufladen Schild erstellen:");
			p.sendMessage(str + "      [GS-A]");
			p.sendMessage(str + "     aufladen");
			p.sendMessage(str + "WICHTIG: Economy muss eingereichtet sein!");
		}
		}
		}
		
		
		
		
		
		//Item bekommen + Info -> /gs erstellen
		if (args.length == 1) {
		if (args[0].equalsIgnoreCase("erstellen")) {
		if (p.isOp()) {
			GS_Erstellen gse = new GS_Erstellen();
			gse.infounditems(p);
		}
		}
		}
		
		
		
		//Region erstellen -> /gs erstellen <name>
		if (args.length == 2) {
		if (args[0].equalsIgnoreCase("erstellen")) {
		if (p.isOp()) {
		String name = args[1].toLowerCase();
		if (args[1].equalsIgnoreCase(name)) {
			GS_Erstellen gse = new GS_Erstellen();
			gse.erstellen(p,name);
		}
		}
		} 
		} 
		
		
		
		//Region wird geloescht -> /gs loeschen <name>
		if (args.length == 2) {
		if (args[0].equalsIgnoreCase("loeschen")) {
		if (p.isOp()) {
		String name2 = args[1].toLowerCase();
		if (args[1].equalsIgnoreCase(name2)) {
			GS_Loeschen gsl = new GS_Loeschen();
			gsl.loeschen(p, name2);
		}
		}
		}
		}
		
		//GS Menu(GUI) -> /gs menu
		if (args.length == 1) {
		if (args[0].equalsIgnoreCase("menu")) {
			GS_GUI gsg = new GS_GUI();
			gsg.GSGUI(p);
		}
		}
		
		//Sperren info -> /gs sperren
		if (args.length == 1) {
		if (args[0].equalsIgnoreCase("sperren")) {
		if (p.isOp()) {
			p.sendMessage(str + "Benutze: -> /gs sperren <name>");
		}
		}
		}
		
		
		//Sperren Befehl -> /gs sperren <name>
		if (args.length == 2) {
		if (p.isOp()) {
		if (args[0].equalsIgnoreCase("sperren")) {
			String sperrenname = args[1].toLowerCase();
		if (args[1].equalsIgnoreCase(sperrenname)) {
			GS_Sperren gss = new GS_Sperren();
			gss.sperren(sperrenname, p);
		}
		}
		}
		}
		
		//Sperren info -> /gs freigeben
		if (args.length == 1) {
		if (p.isOp()) {
		if (args[0].equalsIgnoreCase("freigeben")) {
			p.sendMessage(str + "Benutze: -> /gs freigeben <name>");
		}
		}
		}
		
		
		//Sperren Befehl -> /gs freigeben <name>
		if (args.length == 2) {
		if (p.isOp()) {
		if (args[0].equalsIgnoreCase("freigeben")) {
			String freigebenname = args[1].toLowerCase();
		if (args[1].equalsIgnoreCase(freigebenname)) {
			GS_Freigeben gsf = new GS_Freigeben();
			gsf.freigeben(freigebenname, p);
		}
		}
		}
		}
		
		
		
		//GS Rechte Info -> /gs rechte
		if (args.length == 1) {
		if (args[0].equalsIgnoreCase("rechte")) {
			p.sendMessage(str + "Fuege z.b. Freunde zu deinem GS hinzu damit du mit ihnen Bauen kannst:");
			p.sendMessage(str + "Freund hinzufuegen -> /gs rechte hinzu <name>");
			p.sendMessage(str + "Freund hinzufuegen -> /gs rechte entfernen <name>");
			
			
		}
		}
		
	
		//GS Rechte 
		if (args.length == 3) {
		if (args[0].equalsIgnoreCase("rechte")) {
		
		//GS einen Member hinzufuegen -> /gs rechte hinzu <name>
		if (args[1].equalsIgnoreCase("hinzu")) {
			String rechtename = args[2].toLowerCase();
		if (args[2].equalsIgnoreCase(rechtename)) {
			GS_Rechte gse = new GS_Rechte();
			gse.Hinzu(p, rechtename);
		}
		}
		
		//Gs einen Member entfernen -> /gs rechte entfernen <name>
		if (args[1].equalsIgnoreCase("entfernen")) {
			String rechtenameent = args[2].toLowerCase();
		if (args[2].equalsIgnoreCase(rechtenameent)) {
			GS_Rechte gse = new GS_Rechte();
			gse.Entfernen(p, rechtenameent);
		}
		}
		}
		}
		
		
		
		
		
		if (args[0].equalsIgnoreCase("system")) {
		if (p.isOp()) {
		// GS System Info
		if (args.length == 1) {
			p.sendMessage(str + "GS System sperren -> /gs system sperren");
			p.sendMessage(str + "GS System freigeben -> /gs system freigeben");
			p.sendMessage(str + "GS System freigeben -> /gs system check");
		}
		if (args.length == 2) {
		// GS System Sperren Befehl -> /gs system sperren
		if (args[1].equalsIgnoreCase("sperren")) {
			GS_System_Sperren_Freigeben gssf = new GS_System_Sperren_Freigeben();
			gssf.Sperren(p);
		}
		// GS System freigeben Befehl -> /gs system freigeben
		if (args[1].equalsIgnoreCase("freigeben")) {
			GS_System_Sperren_Freigeben gssf = new GS_System_Sperren_Freigeben();
			gssf.Freigeben(p);
		}
		if (args[1].equalsIgnoreCase("check")) {
			GS_System_Sperren_Freigeben gssf = new GS_System_Sperren_Freigeben();
			gssf.Check(p);
		}
		}
		}
		}
		
		
		
		//Befehl Spieler von GS loeschen
		if (args[0].equalsIgnoreCase("spieler")) {
		if (args[1].equalsIgnoreCase("loeschen")) {
		String gsname = args[2].toLowerCase();
		if (args[2].equalsIgnoreCase(gsname)) {	
			
			GS_Spieler gss = new GS_Spieler();
			gss.spielerLoeschen(p, gsname);
			
		}
		}
		}
		
		
		
		//Befehl GS Check
		if (args[0].equalsIgnoreCase("check")) {
		if (p.isOp()) {
		
			GS_Check gsc = new GS_Check();
			gsc.check(p);
			
			
		}	
		}
		
		
		
		//Befehl Schildtool zum setzen der Schilder in Schilder.yml
		if (args[0].equalsIgnoreCase("schildtool")) {
		if (p.isOp()) {
			GS_Schildtool gsst = new GS_Schildtool();
			gsst.addSchildtool(p);
		}
		}
		
		
		//Befehl TPtoGS
		if (args[0].equalsIgnoreCase("tp")) {
		String gsname = args[1].toLowerCase();
		if (args[1].equalsIgnoreCase(gsname)) {
			GS_TP gstp = new GS_TP();
			gstp.TPtoGS(p, gsname);
		}
		}
		
		
		
		
		
		
		}
		
		return false;
	}
	
	
	
}
