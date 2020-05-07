package com.datdeveloper.jai.listeners;

import com.datdeveloper.jai.voice.VoiceManager;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.net.URISyntaxException;
import java.util.Random;

public class MessageListener extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String content = message.getContentRaw();
        String filename = "";
        boolean exists = true;
        boolean audioExists = true;
        boolean special = false;

        switch (content.toLowerCase()) {
            case "dewit":
            case "dowit":
            case "do it":
            case "doit":
            case "dew it":
                filename = "doit";
                break;
            case "hello there":
            case "hello there!":
                filename = "hellothere";
                break;
            case "general kenobi":
            case "general kenobi!":
                filename = "generalkenobi";
                break;
            case "unlimited power":
            case "unlimited powah":
            case "unlimited powah!":
            case "unlimited power!":
                filename = "unlimitedpower";
                break;
            case "heavy breathing":
                filename = "heavybreathing";
                exists = false;
                break;
            case "wilhelm":
                filename = "wilhelm";
                exists = false;
                break;
            case "high ground":
            case "it's over anakan, i have the high ground":
            case "its over anakan, i have the high ground":
            case "its over anakan i have the high ground":
                filename = "highground";
                exists = false;
                break;
            case "i don't like sand":
            case "i dont like sand":
                filename = "sand";
                exists = false;
                break;
            case "senate":
            case "i am the senate":
                filename = "senate";
                exists = false;
                break;
            case "spinning":
            case "i'll try spinning":
            case "ill try spinning":
                filename = "spinning";
                exists = false;
                break;
            case "these are not the droids":
            case "these are not the droids you're looking for":
            case "not droids":
                filename = "notdroids";
                exists = false;
                break;
            case "wookie":
                filename = "chewy" + new Random().nextInt(6);
                exists = false;
                break;
            case "destroyers":
            case "master, destroyers":
            case "master destroyers":
                filename = "destroyers";
                break;
            case "bang":
            case "blaster":
            case "blasters":
                filename = "blaster/blaster" + new Random().nextInt(5);
                exists = false;
                break;
            case "bigbang":
            case "bigblaster":
            case "big blaster":
            case "big blasters":
            case "bigblasters":
                filename = "blaster/bigblaster" + new Random().nextInt(12);
                exists = false;
                break;
            case "swoosh":
                filename = "lightsaber/Lightsaber" + new Random().nextInt(61);
                exists = false;
                break;
            case "swoosh clash":
                filename = "lightsaber/Lightsaber" + new Random().nextInt(26);
                exists = false;
                break;
            case "swoosh hum":
                filename = "lightsaber/Lightsaber" + (26 + new Random().nextInt(6));
                exists = false;
                break;
            case "swoosh start":
                filename = "lightsaber/Lightsaber" + (32 + new Random().nextInt(3));
                exists = false;
                break;
            case "swoosh stop":
                filename = "lightsaber/Lightsaber" + (35 + new Random().nextInt(2));
                exists = false;
                break;
            case "swoosh twirl":
                filename = "lightsaber/Lightsaber" + (37 + new Random().nextInt(24));
                exists = false;
                break;
            case "alarm":
                filename = "alarm";
                exists = false;
                break;
            case "flyby":
            case "fly by":
                filename = "flyby/flyby" + new Random().nextInt(9);
                exists = false;
                break;
            case "tieflyby":
            case "tie flyby":
                filename = "flyby/tieflyby" + new Random().nextInt(5);
                exists = false;
                break;

            // Special
            case "doot":
            case "max doot":
                filename = "/videos/Maxdoot.mp4";
                exists = true;
                audioExists = false;
                special = true;
                break;
            case "stop":
                VoiceManager.getInstance().stop();
        }
        if (!filename.isEmpty()) {
            System.out.println("Received message: " + content + ", from " + event.getAuthor().getAsTag());
            if (VoiceManager.getInstance().isConnected() && audioExists)
                VoiceManager.getInstance().playTrack("audio/" + filename + ".mp3");
            else if (exists) {
                if (special) {
                    event.getChannel().sendMessage(" ").addFile(getClass().getResourceAsStream( filename), filename).queue();
                } else {
                    event.getChannel().sendMessage(" ").addFile(getClass().getResourceAsStream("/images/" + filename + ".gif"), "Test.gif").queue();
                }
            }
        }
    }
}
