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
        try {
            String filename = "";
            boolean exists = true;
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
                case "stop":
                    VoiceManager.getInstance().stop();
            }
            if (!filename.isEmpty()){
                System.out.println("Received message: " + content + ", from " + event.getAuthor().getAsTag());
                if (VoiceManager.getInstance().isConnected()) VoiceManager.getInstance().playTrack(getClass().getResource("/audio/" + filename + ".mp3").toURI().getPath());
                else if (exists) event.getChannel().sendMessage(" ").addFile(getClass().getResourceAsStream("/images/" + filename + ".gif"), "Test.gif").queue();
            }
        } catch(URISyntaxException e){
            e.printStackTrace();
        }
    }
}
