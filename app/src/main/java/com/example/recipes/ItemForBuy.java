package com.example.recipes;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kacku on 04.01.2018.
 */

public class ItemForBuy extends RealmObject {
    @PrimaryKey
    private int id;
    private int id_ingr;
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_ingr() {
        return id_ingr;
    }

    public void setId_ingr(int id_ingr) {
        this.id_ingr = id_ingr;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}
