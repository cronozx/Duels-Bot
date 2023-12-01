package cronozx.duelsbot;

import me.realized.duels.api.Duels;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DuelsBot extends JavaPlugin {
    private static DuelsBot instance;
    public static JDA jda = null;
    private Duels api = null;

    @Override
    public void onEnable() {
        instance = this;

        jda = JDABuilder.createDefault("TOKEN").build();

        jda.updateCommands().addCommands(
                Commands.slash("stats", "Get a player's stats")
                .addOption(OptionType.STRING, "name", "The player's name")).queue();

        jda.addEventListener(new StatsCommand(this));

        jda.getPresence().setPresence(Activity.playing("YOUR PRESENCE"), false);

        api = (Duels) Bukkit.getServer().getPluginManager().getPlugin("Duels");
    }

    public JDA getJDA() {
        return jda;
    }

    public Duels getDuels() {
        return api;
    }
}
