package me.tehbeard.BeardAch.achievement.rewards;


import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Configurable(tag="comm")
public class CommandReward implements IReward{

	String command = "";
	public void configure(String config) {
		command = config;
		
	}

	public void giveReward(Player player) {
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command.replace("<PLAYER>", player.getName()));
		BeardAch.printCon(command.replace("<PLAYER>", player.getName()));
	}

}
