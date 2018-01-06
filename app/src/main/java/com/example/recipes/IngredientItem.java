package com.example.recipes;

/**
 * Created by kacku on 04.01.2018.
 */

public class IngredientItem {
    private int id;
    private String name;
    private int count;
    private boolean isSelected;

    public IngredientItem(int id,String name, int count)
    {
        this.name = name;
        this.count = count;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
