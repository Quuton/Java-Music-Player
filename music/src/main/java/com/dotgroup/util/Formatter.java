package com.dotgroup.util;

public class Formatter {
    public static String durationFormatter(int duration) {
        int minute = 0;
        int second = 0;

        if(duration>=60) { 
            second = duration % 60 ; //01
            minute = duration / 60;
            
        }
        else{
            second = duration;
        }
        if(second<10) {
            return Integer.toString(minute) + ":" + "0" + Integer.toString(second);
        }
        
        return Integer.toString(minute) + ":" + Integer.toString(second);
    }
}
