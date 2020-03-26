package com.datdeveloper.jai.misc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class GetStuff {
    public static String calculateTravelTime(String start, String end, String mult){
        try {
            Document doc = Jsoup.connect("http://d6holocron.com/astrogation/traveltime.php?System1=" + start + "&System2=" + end + "&Submit2=Enter&mult=" + mult).get();
            return doc.getElementsByClass("layer4").get(0).text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Something Broke";
    }

    public static String randomNames(String type){
        type = type.substring(0,1).toUpperCase() + type.substring(1);
        if (type.equals("Male") || type.equals("Female") || type.equals("Location") || type.equals("Planet")){
            type = "Star+Wars+" + type;
        }
        try {
            return Jsoup.connect("https://donjon.bin.sh/name/rpc-name.fcgi?type=" + type + "&n=1").get().body().text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Something Broke";
    }

    // TODO: Get random monster at level;
}
