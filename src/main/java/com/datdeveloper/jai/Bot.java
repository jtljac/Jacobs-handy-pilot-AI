package com.datdeveloper.jai;

import com.datdeveloper.jai.listeners.CommandListener;
import com.datdeveloper.jai.listeners.MessageListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.Random;

public class Bot {
    static final Activity[] activities = {Activity.playing("with the ships navigation system"), Activity.playing("with the protocol droids"), Activity.playing("with the airlock"), Activity.listening("the hum of the engine"), Activity.listening("the pilots inner demons"), Activity.listening("the bickering of the droids"), Activity.listening("David Bowie - Space Oddity"), Activity.listening("John Williams - Duel of the Fates"), Activity.listening("John Williams - Battle of the Heroes"), Activity.watching("Space porn"), Activity.watching("Star wars"),  Activity.watching("The stary sky"), Activity.watching("The stars go by")};

    public static void main(String[] args) throws LoginException, InterruptedException {
        JDA api = JDABuilder.createDefault(SecretStuff.DISCORDBOTKEY)
                .enableCache(CacheFlag.VOICE_STATE)
                .build();
        api.addEventListener(new CommandListener());
        api.addEventListener(new MessageListener());
        Activity nextActivityStatus;
        while(true){
            nextActivityStatus = activities[new Random().nextInt(activities.length)];
            System.out.println("Next activity: " + nextActivityStatus.getType().toString() + " " + nextActivityStatus.toString());
            api.getPresence().setActivity(nextActivityStatus);
            Thread.sleep(60000L);
        }
    }
}
