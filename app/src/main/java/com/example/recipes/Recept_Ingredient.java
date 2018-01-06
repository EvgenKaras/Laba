package com.example.recipes;

import io.realm.RealmObject;

/**
 * Created by kacku on 19.12.2017.
 */

public class Recept_Ingredient extends RealmObject {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_recept() {
        return id_recept;
    }

    public void setId_recept(int id_recept) {
        this.id_recept = id_recept;
    }

    public int getId_ingredient() {
        return id_ingredient;
    }

    public void setId_ingredient(int id_ingredient) {
        this.id_ingredient = id_ingredient;
    }

    public int getCountOfIngredient() {
        return countOfIngredient;
    }

    public void setCountOfIngredient(int countOfIngredient) {
        this.countOfIngredient = countOfIngredient;
    }

    private int id;
    private int id_recept;
    private int id_ingredient;
    private int countOfIngredient;
}
