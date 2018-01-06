package com.example.recipes;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kacku on 07.01.2018.
 */

public class SubCategory extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;

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

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    private int id_category;
}
