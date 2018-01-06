package com.example.recipes.Fragments;

/**
 * Created by Оленька on 22.11.2017.
 */

public class Product {
    private String name;
    private int count;
    private String unit;
    private int price;
    private int idItemForBuy;
    private boolean isSelected;

    Product(String name, String unit, int count, int price,int idItemForBuy){
        this.name = name;
        this.count = count;
        this.unit = unit;
        this.price = price;
        this.idItemForBuy = idItemForBuy;
    }
    public String getUnit() {
        return this.unit;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getIdItemForBuy() {
        return idItemForBuy;
    }

    public void setIdItemForBuy(int idItemForBuy) {
        this.idItemForBuy = idItemForBuy;
    }
}
