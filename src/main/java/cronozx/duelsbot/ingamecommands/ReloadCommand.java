package cronozx.duelsbot.ingamecommands;

import cronozx.duelsbot.ConfigManager;
import cronozx.duelsbot.DuelsBot;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {
    private DuelsBot plugin;
    private ConfigManager configManager;

    public ReloadCommand(DuelsBot plugin, ConfigManager configManager) {
        this.plugin = plugin;
        this.configManager = configManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("duelsbot.reload")) {
            configManager.reloadConfig();
            sender.sendMessage("DuelsBot config reloaded.");
            return true;
        } else {
            sender.sendMessage("You do not have permission to use this command.");
            return false;
        }
    }
}
