package com.example.recipes;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kacku on 19.12.2017.
 */

public class ingredient extends RealmObject {
    @PrimaryKey
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private int price;


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
