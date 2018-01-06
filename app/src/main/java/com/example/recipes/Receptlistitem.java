package com.example.recipes;

/**
 * Created by kacku on 28.12.2017.
 */

public class Receptlistitem {

    public Receptlistitem(int IdPic, String name, String descr, int rating, int idRecept)
    {
        this.descr = descr;
        this.idPic = IdPic;
        this.name = name;
        this.rating = rating;
        this.idRecept = idRecept;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    private String name;
    private String descr;
    private int rating;

    public int getIdPic() {
        return idPic;
    }

    public void setIdPic(int idPic) {
        this.idPic = idPic;
    }

    private int idPic;
    private int idRecept;


    public int getIdRecept() {
        return idRecept;
    }

    public void setIdRecept(int idRecept) {
        this.idRecept = idRecept;
    }
}
