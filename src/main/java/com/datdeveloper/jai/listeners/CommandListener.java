package com.datdeveloper.jai.listeners;


import com.datdeveloper.jai.misc.GetStuff;
import com.datdeveloper.jai.voice.VoiceManager;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CommandListener extends ListenerAdapter {
    static final String[] helpResponses = {"Nope", "Nah", "Can't be bothered", "Maybe later"};
    static final String helpText = "\n" +
            "Commands (Syntax: !jai <command> [args...])\n" +
            "help - Unmotivated response\n" +
            "help please - Help\n" +
            "join - Join the users current voice channel\n" +
            "quit - Leave the voice channel the bot is connected to\n" +
            "traveltime - calculate the travel time between two systems\n" +
            "randomname - Generates a random name for a given race\n" +
            "\n" +
            "Sound effect and images:\n" +
            "dewit - Do it\n" +
            "hello there - Hello there\n" +
            "general kenobi - General Kenobi\n" +
            "unlimited power - Unlimited powah\n" +
            "destroyers - master, destoryers\n" +
            "\n" +
            "Only sound effects:\n" +
            "heavy breathing - darth vader breathing\n" +
            "wookie - chewbacca roar\n" +
            "swoosh - random lightsaber\n" +
            "swoosh clash - clashing lightsabers\n" +
            "swoosh hum - humming lightsabers\n" +
            "swoosh start - starting lightsabers\n" +
            "swoosh stop - stopping lightsabers\n" +
            "swoosh twirl - twirling lightsabers\n" +
            "blaster - blaster shot\n" +
            "big blaster - spaceship blaster shot\n" +
            "alarm - ship alarm\n" +
            "flyby - ship flyby\n" +
            "tie flyby - tie flyby\n";
    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String content = message.getContentRaw();
        if (content.startsWith("!jai") && content.length() > 4) {
            System.out.println("Received command: " + content + ", from " + event.getAuthor().getAsTag());
            // check commands
            ArrayList<String> command = new ArrayList<>(Arrays.asList(content.substring(5).split(" ")));
            switch(command.get(0)){
                case "join":
                    VoiceChannel playerChannel = event.getMember().getVoiceState().getChannel();
                    if(playerChannel != null){
                        if (!VoiceManager.getInstance().connect(playerChannel, event.getGuild())){
                            event.getChannel().sendMessage("Unable to join voice channel").queue();
                        }
                    } else {
                        event.getChannel().sendMessage(event.getAuthor().getAsMention() + " You must be in a voice channel to do that").queue();
                    }
                    break;
                case "quit":
                    if (VoiceManager.getInstance().isConnected()) {
                        if (!VoiceManager.getInstance().quit()) {
                            event.getChannel().sendMessage("Failed to disconnect").queue();
                        }
                    } else {
                        event.getChannel().sendMessage("Cannot quit without being in a voice channel").queue();
                    }
                    break;
                case "help":
                    if (command.size() > 1 && command.get(1).equals("please")) {
                        event.getChannel().sendMessage(helpText).queue();
                    } else {
                        event.getChannel().sendMessage(helpResponses[new Random().nextInt(helpResponses.length)]).queue();
                    }
                    break;
                case "traveltime":
                    if (command.size() == 3) event.getChannel().sendMessage(GetStuff.calculateTravelTime(command.get(1), command.get(2), "1")).queue();
                    else if (command.size() == 4) event.getChannel().sendMessage(GetStuff.calculateTravelTime(command.get(1), command.get(2), command.get(3))).queue();
                    else event.getChannel().sendMessage("Command syntax: !jai traveltime origin destination (multiplier)").queue();
                    break;
                case "randomname":
                    if (command.size() == 2 || command.size() == 3) {
                        if (command.size() == 3) {
                            command.set(1, command.get(1) + " " + command.get(2).substring(0,1).toUpperCase() + command.get(2).substring(1));
                        }
                        String response = GetStuff.randomNames(command.get(1));
                        if (!response.isEmpty()) event.getChannel().sendMessage(response).queue();
                        else event.getChannel().sendMessage("Command syntax: !jai randomname type\nAvailable types are: Male, Female, Duros, Gamorrean, Hutt, Ithorian, Mon Calamari, Quarren, Rodian, Sullustan, Trandosan, Twilek, Wookie, Droid, Location, Planet").queue();
                    }
                    else event.getChannel().sendMessage("Command syntax: !jai randomname type\nAvailable types are: Male, Female, Duros, Gamorrean, Hutt, Ithorian, Mon Calamari, Quarren, Rodian, Sullustan, Trandosan, Twilek, Wookie, Droid, Location, Planet").queue();
                    break;
            }
        }
    }
}
