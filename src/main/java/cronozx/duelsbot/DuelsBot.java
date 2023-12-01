package cronozx.duelsbot;

import cronozx.duelsbot.botcommands.StatsCommand;
import cronozx.duelsbot.ingamecommands.ReloadCommand;
import me.realized.duels.api.Duels;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class DuelsBot extends JavaPlugin {
    private static DuelsBot instance;
    public static JDA jda;
    private Duels api = null;
    private ConfigManager configManager;
    private FileConfiguration config;

    @Override
    public void onEnable() {
        instance = this;

        configManager = new ConfigManager(this);

        config = getConfig();

        configManager.setup();

        if (configManager.getToken() == null) {
            getLogger().warning("Please set your bot token in the config.yml file.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        } else {
            jda = JDABuilder.createDefault(configManager.getToken()).build();


            if (configManager.getPresence() == null) {
                getLogger().warning("Please set your bot presence in the config.yml file.");
                Bukkit.getPluginManager().disablePlugin(this);
                return;
            } else {
                jda.getPresence().setPresence(Activity.playing(configManager.getPresence()), false);
            }

            jda.updateCommands().addCommands(
                    Commands.slash("stats", "Get a player's stats")
                            .addOption(OptionType.STRING, "name", "The player's name")).queue();

            jda.addEventListener(new StatsCommand(this));
        }


        api = (Duels) Bukkit.getServer().getPluginManager().getPlugin("Duels");

        getCommand("reload").setExecutor(new ReloadCommand(this, configManager));
    }

    public JDA getJDA() {
        return jda;
    }

    public Duels getDuels() {
        return api;
    }
}
