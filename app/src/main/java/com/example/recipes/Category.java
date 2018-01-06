package com.example.recipes;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kacku on 19.12.2017.
 */

public class Category extends RealmObject {
    @PrimaryKey
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}

