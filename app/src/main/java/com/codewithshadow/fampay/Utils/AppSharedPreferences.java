package com.codewithshadow.fampay.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSharedPreferences {
    private SharedPreferences sharedPreference;
    private  SharedPreferences.Editor editor;
    private static final String remindLater = "remindLater";
    private static final String dismissNow = "dismissNow";

    public AppSharedPreferences(Context context) {
        String Pref_Name = "FamPay";
        sharedPreference = context.getSharedPreferences(Pref_Name, Context.MODE_PRIVATE);
        editor = sharedPreference.edit();
        editor.apply();
    }

    public  void setRemindLater(String remindLater)
    {
        editor.putString("remindLater",remindLater);
        editor.apply();
    }
    public String getRemindLater() {
        return sharedPreference.getString("remindLater",remindLater);
    }



    public  void setDismissNow(String dismissNow)
    {
        editor.putString("dismissNow",dismissNow);
        editor.apply();
    }
    public String getDismissNow() {
        return sharedPreference.getString("dismissNow",dismissNow);
    }
}
