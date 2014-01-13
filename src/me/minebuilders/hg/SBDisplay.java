package me.minebuilders.hg;

import java.util.HashMap;
import me.minebuilders.hg.mobhandler.Spawner;
import me.minebuilders.hg.tasks.ChestDropTask;
import me.minebuilders.hg.tasks.FreeRoamTask;
import me.minebuilders.hg.tasks.StartingTask;
import me.minebuilders.hg.tasks.TimerTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class SBDisplay {

	private ScoreboardManager manager;
	private Scoreboard board;
	private Objective ob;
	private HashMap<String, Scoreboard> score = new HashMap<String, Scoreboard>();
	private Game g;
	private Objective timeob;
	private TimerTaks tt;

	public SBDisplay(Game g) {
		this.manager = Bukkit.getScoreboardManager();
		this.board = manager.getNewScoreboard();
		this.ob = board.registerNewObjective(ChatColor.GREEN + "Players-Alive:", "dummy");
                this.timeob = board.registerNewObjective(ChatColor.GREEN + "Time:", "time");
		this.ob.setDisplaySlot(DisplaySlot.SIDEBAR);
		this.ob.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "HungerGames");
		this.g = g;
		this.tt = ttt;
	}

	public void setAlive() {
                Score score = ob.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Players-Alive:")); 
                Score timescore = timeob.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Time:"));
                score.setScore(g.getPlayers().size());
                timescore.setScore(ttt.roamtime);
	}

	public void resetAlive() {
		board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Players-Alive:"));
		board.resetScores(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Time:"));
		score.clear();
	}

	public void setSB(Player p) {
		score.put(p.getName(), p.getScoreboard());
		p.setScoreboard(board);
	}

	public void restoreSB(Player p) {
		if (score.get(p.getName()) == null) {
			p.setScoreboard(manager.getNewScoreboard());
		} else {
			p.setScoreboard(score.get(p.getName()));
			score.remove(p.getName());
		}
	}
}
