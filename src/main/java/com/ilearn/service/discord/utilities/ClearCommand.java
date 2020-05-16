package com.ilearn.service.discord.utilities;

import com.ilearn.service.discord.DiscordBotService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@Component
public class ClearCommand extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(DiscordBotService.prefix + "clear"))
            if (args.length < 2) {

            } else {
                try {
                    List<Message> messages = event
                            .getChannel()
                            .getHistory()
                            .retrievePast(Integer.parseInt(args[1]))
                            .complete();
                    event.getChannel().deleteMessages(messages).queue();
                    EmbedBuilder succes = new EmbedBuilder();
                    succes.setColor(Color.GREEN);
                    succes.setTitle("Succesfully deleted: " + args[1] + " messages.");

                    event.getChannel().sendMessage(succes.build()).queue();

                } catch (IllegalArgumentException e) {
                    if (e.toString().startsWith("java.langIllegalArgumentException: Message retrieval")) {
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(Color.RED);
                        error.setTitle("Too many messages selected");
                        error.setDescription("1-100 messages");
                        event.getChannel().sendMessage(error.build()).queue();
                    } else {
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(Color.RED);
                        error.setTitle("Too old messages");
                        error.setDescription("Messages older than two weeks");
                    }
                }
            }
    }
}
