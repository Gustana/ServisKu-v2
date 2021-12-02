package com.example.serviceku.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.serviceku.remote.model.profile.profileDetail.GetProfileItem;

public class SPManager {
    public static final String ID_USER_KEY = "idUser";
    public static final String EMAIL_KEY = "email";
    public static final String PHONE_NO_KEY = "no_hp";
    public static final String NAME_KEY = "nama";
    public static final String LEVEL_KEY = "level";
    public static final String GENDER_KEY = "gender";

    public static final String LOGGED_IN_KEY = "loggedIn";
    private static final String SHARED_PREF = "sharedPref";


    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private Context context;

    public SPManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
    }

    public void setLogin(GetProfileItem profileItem) {
        editor = sharedPreferences.edit();

        editor.putString(EMAIL_KEY, profileItem.getEmail());
        editor.putString(ID_USER_KEY, profileItem.getIdUser());
        editor.putString(PHONE_NO_KEY, profileItem.getNoHp());
        editor.putString(NAME_KEY, profileItem.getNama());
        editor.putString(GENDER_KEY, profileItem.getJenisKelamin());
        editor.putString(LEVEL_KEY, profileItem.getLevelUser());

        editor.putBoolean(LOGGED_IN_KEY, true);

        editor.commit();
    }

    public String getLevelUser(){
        return sharedPreferences.getString(LEVEL_KEY, null);
    }

    public String getEmail(){
        return sharedPreferences.getString(EMAIL_KEY, null);
    }

    public String getPhoneNo(){
        return sharedPreferences.getString(PHONE_NO_KEY, null);
    }

    public String getName(){
        return sharedPreferences.getString(NAME_KEY, null);
    }

    public String getGender(){
        return sharedPreferences.getString(GENDER_KEY, null);
    }

    public int getIdUser(){
        return Integer.parseInt(sharedPreferences.getString(ID_USER_KEY, "0"));
    }

    public boolean isLevelAdmin(){
        String level = sharedPreferences.getString(LEVEL_KEY, null);

        return level.equals("0");
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(LOGGED_IN_KEY, false);
    }

    public void clearSP(){
        editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
