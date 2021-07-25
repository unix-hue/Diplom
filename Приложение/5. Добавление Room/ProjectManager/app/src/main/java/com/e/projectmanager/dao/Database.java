package com.e.projectmanager.dao;

import androidx.room.RoomDatabase;

import com.e.projectmanager.dao.models.Desk;
import com.e.projectmanager.dao.models.Task;
import com.e.projectmanager.dao.models.User;

@androidx.room.Database(entities = {Desk.class, Task.class, User.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract InterfaceDao interfaceDao();
}
