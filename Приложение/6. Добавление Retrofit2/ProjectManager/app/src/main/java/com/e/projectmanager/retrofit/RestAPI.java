package com.e.projectmanager.retrofit;

import com.e.projectmanager.retrofit.models.Desk;
import com.e.projectmanager.retrofit.models.LastId;
import com.e.projectmanager.retrofit.models.Task;
import com.e.projectmanager.retrofit.models.User;
import com.e.projectmanager.retrofit.models.UserData;
import com.e.projectmanager.retrofit.models.UserPass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestAPI {

    @GET("/user/emails")
    Call<List<User>> getUserEmails();

    @GET("/get/id")
    Call<LastId> getId();

    @GET("/get/userdata")
    Call<List<UserData>> getUsersData();

    @POST("/add/user")
    Call<Integer> addUser(@Body User user);

    @POST("/add/desk")
    Call<Integer> addDesk(@Body Desk desk);

    @POST("/add/task")
    Call<Integer> addTask(@Body Task task);

    @GET("/get/password/{id}")
    Call<UserPass> getPassword(@Path("id") int id);

    @GET("/complete/task/{id}")
    Call<Integer> completeTask(@Path("id") int id);

    @GET("/get/desks/{id}")
    Call<List<Desk>> getDesks(@Path("id") int id);

    @GET("/get/deskname/{id}")
    Call<Desk> getDeskName(@Path("id") int id);

    @GET("/get/tasks/{id}")
    Call<List<Task>> getTasks(@Path("id") int id);

    @GET("/get/task/{id}")
    Call<Task> getTask(@Path("id") int id);
}
