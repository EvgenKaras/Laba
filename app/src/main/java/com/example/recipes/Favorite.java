package com.example.recipes;

import io.realm.RealmObject;

/**
 * Created by kacku on 04.01.2018.
 */

public class Favorite extends RealmObject {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReceptID() {
        return receptID;
    }

    public void setReceptID(int receptID) {
        this.receptID = receptID;
    }

    private int receptID;

}

