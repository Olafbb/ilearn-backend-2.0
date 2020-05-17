package com.ilearn.service.discord;

import com.ilearn.config.InfoConfig;
import com.ilearn.domain.dto.ChannelDto;
import com.ilearn.service.discord.utilities.ClearCommand;
import com.ilearn.service.discord.utilities.GuildMemberJoin;
import com.ilearn.service.discord.utilities.GuildMemberLeave;
import com.ilearn.service.discord.utilities.InfoCommand;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiscordBotService {
    public static JDA jda;
    public static String prefix = "~";

    //    @Spring Beans
    private InfoCommand infoCommand;
    private ClearCommand clearCommand;
    private GuildMemberJoin join;
    private GuildMemberLeave leave;
    private InfoConfig info;

    public DiscordBotService(InfoCommand infoCommand, ClearCommand clearCommand, GuildMemberJoin join, GuildMemberLeave leave, InfoConfig info) {
        this.infoCommand = infoCommand;
        this.clearCommand = clearCommand;
        this.join = join;
        this.leave = leave;
        this.info = info;

        createAndConfigureBot();
    }

    public List<ChannelDto> getChannels() {
        List<ChannelDto> channels = new ArrayList<>();
        jda.getCategories().get(1).getChannels().forEach(
                guildChannel -> {
                    List<String> members = new ArrayList<>();
                    guildChannel.getMembers().forEach(member -> members.add(member.getEffectiveName()));
                    channels.add(
                            new ChannelDto(
                                    guildChannel.getName(),
                                    guildChannel.getMembers().size(),
                                    members));
                });
        return channels;
    }

    public void createAndConfigureBot() {
        try {
            jda = new JDABuilder(AccountType.BOT).setToken(info.getDiscordBotToken()).build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
        jda.getPresence().setActivity(Activity.watching("you"));
        jda.addEventListener(infoCommand, clearCommand, join, leave);
    }


}
