package me.tehbeard.BeardAch;

import java.io.File;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.AchievementManager;
import me.tehbeard.BeardAch.achievement.rewards.CommandReward;
import me.tehbeard.BeardAch.achievement.triggers.CuboidCheckTrigger;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.achievement.triggers.PermCheckTrigger;
import me.tehbeard.BeardAch.achievement.triggers.StatCheckTrigger;
import me.tehbeard.BeardAch.listener.BeardAchPlayerListener;
import me.tehbeard.BeardStat.BeardStat;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class BeardAch extends JavaPlugin {

	public static Configuration config;
	

	private static final String PERM_PREFIX = "ach";

	public static boolean hasPermission(Player player,String node){

		return (player.hasPermission(PERM_PREFIX + "." + node) || player.isOp());


	}
	public static void printCon(String line){
		System.out.println("[BeardAch] " + line);
	}

	public static void printDebugCon(String line){
		//if(config!=null){
			//if(config.getBoolean("general.debug", false)){
				System.out.println("[BeardAch][DEBUG] " + line);

		//	}
		//}
	}

	public void onDisable() {
		// TODO Auto-generated method stub

	}
	
	public static boolean checkBeardStat(){
		BeardStat stats = (BeardStat) Bukkit.getServer().getPluginManager().getPlugin("BeardStat");
		return (stats!=null && stats.isEnabled());
		
	}

	public void onEnable() {
		
		// TODO Auto-generated method stub
		BeardStat stats = (BeardStat)getServer().getPluginManager().getPlugin("BeardStat");
		if(!checkBeardStat()){
			printCon("BeardStat NOT FOUND, DISABLING PLUGIN!");
			onDisable();
			return;
		}

		
		
		
		
		
		//setup events
		getServer().getPluginManager().registerEvent(Type.PLAYER_MOVE, new BeardAchPlayerListener(), Priority.Highest, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_LOGIN, new BeardAchPlayerListener(), Priority.Highest, this);
		

		
		//TEST ACHIEVEMENTS, DELETE ONCE DATASOURCE'S COMPLETE
		

		
		
		
		//Load config
		printCon("Starting BeardAch");
		if(!(new File(getDataFolder(),"BeardAch.yml")).exists()){
			initalConfig();
		}
		config = new Configuration(new File(getDataFolder(),"BeardAch.yml"));
		config.load();
		
		AchievementManager.database.getAchievements();
		
		
		}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {

			AchievementManager.checkPlayers();
			AchievementManager.unloadOfflinePlayers();
		
		return true;
	}
	
	
	/**
	 * Creates the inital config
	 */
	private void initalConfig() {
		printCon("Generating Inital config");
		config = new Configuration(new File(getDataFolder(),"BeardAch.yml"));
		config.load();
		config.setProperty("ach.database.type", "mysql");
		config.setProperty("ach.database.host", "localhost");
		config.setProperty("ach.database.username", "Beardstats");
		config.setProperty("ach.database.password", "changeme");
		config.setProperty("ach.database.database", "stats");
		config.setProperty("achievements", null);
		config.save();
	}
}
