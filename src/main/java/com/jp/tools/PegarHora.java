package com.jp.tools;

import java.util.Calendar;

public class PegarHora {

    public static String bomDia(){
        Calendar c = Calendar.getInstance();
        int h = c.getTime().getHours();
        if(h >=0 && h < 12){
            return "Bom Dia!";
        }else{
            if(h < 18){
                return "Boa Tarde!";
            }else{
                return "Boa Noite!";
            }
        }
    }
}
