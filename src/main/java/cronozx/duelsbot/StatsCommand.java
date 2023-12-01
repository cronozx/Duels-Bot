package cronozx.duelsbot;

import me.realized.duels.api.Duels;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class StatsCommand extends ListenerAdapter {

    private DuelsBot plugin;
    private JDA jda;
    private Duels duels;

    public StatsCommand(DuelsBot plugin) {
        this.plugin = plugin;
        jda = plugin.getJDA();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("stats")) {
            this.duels = plugin.getDuels();
            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setThumbnail("https://mc-heads.net/head/" + event.getOption("name").getAsString() + "/25" )
                    .setTitle("Stats for " + event.getOption("name").getAsString())
                    .addField("Rating: ", String.valueOf(duels.getUserManager().get(event.getOption("name").getAsString()).getRating()), true)
                    .addField("Wins: ", String.valueOf(duels.getUserManager().get(event.getOption("name").getAsString()).getWins()), true)
                    .addField("Losses: ", String.valueOf(duels.getUserManager().get(event.getOption("name").getAsString()).getLosses()), true);

            event.replyEmbeds(embedBuilder.build()).queue();
        }
    }
}
