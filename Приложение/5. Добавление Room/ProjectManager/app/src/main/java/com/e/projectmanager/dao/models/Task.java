package com.e.projectmanager.dao.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Desk.class, parentColumns = "id", childColumns = "desk_id", onDelete = CASCADE, onUpdate = CASCADE))
public class Task {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private long desk_id;

    private String name;

    private String description;

    private String owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDesk_id() {
        return desk_id;
    }

    public void setDesk_id(long desk_id) {
        this.desk_id = desk_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
