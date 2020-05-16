package com.ilearn.controller;

import com.ilearn.domain.dto.discord.ChannelDto;
import com.ilearn.service.discord.DiscordBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/v1/i-learn")
public class DiscordController {
    @Autowired
    private DiscordBotService discordBotService;

    @RequestMapping(method = RequestMethod.GET, value = "/channel/all")
    public List<ChannelDto> getChannels() {
        List<ChannelDto> channelsList = discordBotService.getChannels();
        channelsList.sort(Comparator.comparingInt(ChannelDto::getNumberOfPeople));
        Collections.reverse(channelsList);
        return channelsList;
    }
}
