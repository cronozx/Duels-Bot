package cronozx.duelsbot;

import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigManager {
    private DuelsBot plugin;
    private FileConfiguration config;
    private File configFile;
    private String token;
    private String presence;

    public ConfigManager(DuelsBot plugin) {
        this.plugin = plugin;
        configFile = new File(plugin.getDataFolder(), "config.yml");
        config = plugin.getConfig();
    }

    public void setup() {
    plugin.saveDefaultConfig();
    config = plugin.getConfig();
    config.options().copyDefaults(true);
    plugin.saveConfig();
    configFile = new File(plugin.getDataFolder(), "config.yml");

    if (!configFile.exists()) {
        config = new YamlConfiguration();

        //reset values
        config.set("discord-bot-token", "YOUR_TOKEN_HERE");
        config.set("discord-bot-presence", "YOUR_PRESENCE_HERE");

        try {
            config.save(configFile);
        } catch (Exception e) {
            e.printStackTrace();
            }
        }
    token = config.getString("discord-bot-token");
    presence = config.getString("discord-bot-presence");
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
        token = config.getString("discord-bot-token");
        presence = config.getString("discord-bot-presence");
        plugin.getJDA().getPresence().setPresence(Activity.playing(presence), true);
    }

    public String getToken() {
        return token;
    }

    public String getPresence() {
        return presence;
    }
}
