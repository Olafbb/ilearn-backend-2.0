package com.ilearn.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class InfoConfig {
    //links
    @Value("${link.chat}")
    private String chatLink;

    //tokens
    @Value("${discord.bot.token}")
    private String discordBotToken;

    @Value("${dropbox.token}")
    private String dropboxToken;

    //paths
    @Value("${dropbox.save.path}")
    private String dropboxSavePath;
}
