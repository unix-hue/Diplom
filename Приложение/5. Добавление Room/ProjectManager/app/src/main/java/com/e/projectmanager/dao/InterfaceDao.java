package com.e.projectmanager.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.e.projectmanager.dao.models.Desk;
import com.e.projectmanager.dao.models.Task;
import com.e.projectmanager.dao.models.User;

import java.util.List;

@Dao
public interface InterfaceDao {

    @Query("SELECT * FROM User")
    List<User> getUsersEmails();

    @Query("SELECT password from User where id = :id")
    String getPassword(long id);

    @Insert
    void addUser(User user);

    @Insert
    void addDesk(Desk desk);

    @Insert
    void addTask(Task task);

    @Delete
    void completeTask(Task task);

    @Query("SELECT id FROM User ORDER BY id DESC LIMIT 1")
    long getId();

    @Query("SELECT * FROM Desk where user_id = :id")
    List<Desk> getDesks(long id);

    @Query("SELECT name FROM Desk where id = :id")
    String getDeskName(long id);

    @Query("SELECT * FROM Task where desk_id = :id")
    List<Task> getTasks(long id);

    @Query("SELECT * FROM Task where id = :id")
    Task getTask(long id);

    @Query("SELECT name || ' ' || surname || ', ' || email FROM User")
    List<String> getUsersData();
}
