package com.e.projectmanager.JSONAdapter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONAdapter {
    private static final Gson gson;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    public static String toJson(Object object) {
        Log.i("GSON", "Объект преобразован в json " + gson.toJson(object));
        return gson.toJson(object);
    }

    public static Object fromJson(String json, Class className) {
        Log.i("GSON", "Json " + json + " преобразован в объект");
        return gson.fromJson(json, className);
    }
}
