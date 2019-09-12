package com.example.newsapp.utils;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class TimeParser {
    public static ColorDrawable[] vibrantLightColorList =
            {
                    new ColorDrawable(Color.parseColor("#ffeead")),
                    new ColorDrawable(Color.parseColor("#93cfb3")),
                    new ColorDrawable(Color.parseColor("#fd7a7a")),
                    new ColorDrawable(Color.parseColor("#faca5f")),
                    new ColorDrawable(Color.parseColor("#1ba798")),
                    new ColorDrawable(Color.parseColor("#6aa9ae")),
                    new ColorDrawable(Color.parseColor("#ffbf27")),
                    new ColorDrawable(Color.parseColor("#d93947"))
            };

    public static ColorDrawable getRandomDrawbleColor() {
        int idx = new Random().nextInt(vibrantLightColorList.length);
        return vibrantLightColorList[idx];
    }


    public static String dateToTime(String dateP){
        PrettyTime prettyTime = new PrettyTime(new Locale(getCountry()));
        String isTime = null;
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",Locale.ENGLISH);
            Date date = simpleDateFormat.parse(dateP);
            isTime = prettyTime.format(date);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return isTime;
    }
    public static String dateFormat(String dateP){
        String nDate;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E,d MMM yyyy",new Locale(getCountry()));

        try{
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(dateP);
            nDate = simpleDateFormat.format(date);

        }
        catch (Exception e){
            e.printStackTrace();
            nDate = dateP;
        }
        return  nDate;

    }


    public static String getCountry(){
        Locale locale = Locale.getDefault();
        String contry = String.valueOf(locale.getCountry());
        return contry.toLowerCase();
    }


}
