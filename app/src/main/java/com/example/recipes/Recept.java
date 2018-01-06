package com.example.recipes;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kacku on 19.12.2017.
 */

public class Recept extends RealmObject {
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

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    @PrimaryKey
    private int id;
    private String name;
    private String instruction;
    private int imgID;
    private int rating;
    private int id_cat;
    private String descr;
    private int userRating;

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public int getUserRating() {
        return userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }
}
