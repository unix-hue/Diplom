package com.e.projectmanager.dao;

import android.content.Context;

import androidx.room.Room;

public class Query {
    private static Database database;
    private static InterfaceDao interfaceDao;
    private static long current_user;

    public static void create(Context context){
        if (database == null) {
            database = Room.databaseBuilder(context, Database.class, "database1").build();
            interfaceDao = database.interfaceDao();
        }
    }

    public static InterfaceDao getAccess(){
        return interfaceDao;
    }

    public static long getCurrent_user() {
        return current_user;
    }

    public static void setCurrent_user(long current_user) {
        Query.current_user = current_user;
    }
}
