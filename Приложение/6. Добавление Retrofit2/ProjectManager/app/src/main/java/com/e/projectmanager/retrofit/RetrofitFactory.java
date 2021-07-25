package com.e.projectmanager.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static int current_user;
    private static final Retrofit retrofit;

    static{
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.9.4:8080")  //Задать ip сервера
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RestAPI makeRequest(){
        return retrofit.create(RestAPI.class);
    }

    public static int getCurrent_user() {
        return current_user;
    }

    public static void setCurrent_user(int current_user) {
        RetrofitFactory.current_user = current_user;
    }
}
