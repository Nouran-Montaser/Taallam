package com.example.nouran.taallam;

import android.util.Log;

import java.text.SimpleDateFormat;

public class Date {
    public static String  formatDate(String date)
    {
        SimpleDateFormat spf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        java.util.Date newDate= null;
        try {
            newDate = spf.parse(date);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            Log.i("POPOPOPOPOPOPOP",e.getMessage());
        }
        spf= new SimpleDateFormat("dd MMM yyyy");
        return spf.format(newDate);

    }

    public static String  format2Date(String date)
    {
        SimpleDateFormat spf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        java.util.Date newDate= null;
        try {
            newDate = spf.parse(date);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            Log.i("POPOPOPOPOPOPOP",e.getMessage());
        }
        spf= new SimpleDateFormat("dd MMM yyyy");
        return spf.format(newDate);

    }

}
