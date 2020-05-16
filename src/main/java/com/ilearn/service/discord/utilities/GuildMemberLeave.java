package com.ilearn.service.discord.utilities;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class GuildMemberLeave extends ListenerAdapter {
    String leaveMessage = "[member] left.";

    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        EmbedBuilder join = new EmbedBuilder();
        join.setColor(Color.DARK_GRAY);
        join.setDescription(leaveMessage.replace("[member]", event.getMember().getAsMention()));
        event.getGuild().getDefaultChannel().sendMessage(join.build()).queue();

        event.getGuild().modifyMemberRoles(
                event.getMember(), event.getGuild().getRolesByName(
                        "Member", true))
                .complete();
    }
}
