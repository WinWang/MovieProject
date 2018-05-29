package com.lepoint.ljfmvp.utils;

import android.content.Context;

/**
 * Created by linyujie on 16/7/27.
 */
public class SpUtils {
    public static String getString(Context context, String spName, String name) {
        try {
            return context.getSharedPreferences(spName, Context.MODE_PRIVATE).getString(name, null);
        } catch (Exception e) {
            return "";
        }
    }

    //
    public static void setString(Context context, String spName, String name, String value) {
        try {
            context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit().putString(name, value).commit();
        } catch (Exception e) {

        }

    }

    public static Boolean getBoolean(Context context, String spName, String name) {
        try {
            return context.getSharedPreferences(spName, Context.MODE_PRIVATE).getBoolean(name, false);
        } catch (Exception e) {
            return false;
        }
    }




    //
    public static void setBoolean(Context context, String spName, String name, boolean value) {
        try {
            context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit().putBoolean(name, value).commit();
        } catch (Exception e) {

        }
    }

    public static void setInt(Context context, String spName, String name, int value) {
        try {
            context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit().putInt(name, value).commit();
        } catch (Exception e) {

        }
    }

    public static int getInt(Context context, String spName, String name) {
        try {
            return context.getSharedPreferences(spName, Context.MODE_PRIVATE).getInt(name, 0);
        } catch (Exception e) {
            return 0;
        }
    }

}
