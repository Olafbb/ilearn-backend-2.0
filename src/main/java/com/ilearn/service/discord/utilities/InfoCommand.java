package com.ilearn.service.discord.utilities;

import com.ilearn.service.discord.DiscordBotService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class InfoCommand extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        if (args[0].equalsIgnoreCase(DiscordBotService.prefix + "info")) {
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("Information");
            info.setDescription("Description");
            info.addField("Creator", "ilearn", false);
            info.setColor(Color.DARK_GRAY);
            info.setFooter("Created by ", event.getMember().getUser().getAvatarUrl());

            event.getChannel().sendTyping();
            event.getChannel().sendMessage(info.build()).queue();
            info.clear();
        }
    }
}
