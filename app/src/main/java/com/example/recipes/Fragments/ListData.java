package com.example.recipes.Fragments;

/**
 * Created by Оленька on 21.11.2017.
 */

import android.content.Context;

import com.example.recipes.Category;
import com.example.recipes.SubCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class ListData {
    public static HashMap<String, List<String>> loadData(Context context){
        HashMap<String, List<String>> expDetails = new HashMap<>();



        Realm.init(context);

        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Category> categoryList = realm.where(Category.class).findAll();
                RealmResults<SubCategory> subCategoryList = realm.where(SubCategory.class).findAll();

                //List<List<>>
            }
        });
        /*
        List<String> one = new ArrayList<>();
        one.add("Щи");
        one.add("Борщ");

        List<String> two = new ArrayList<>();
        two.add("Макароны");
        two.add("Блюда из картофеля");


        List<String> cake = new ArrayList<>();
        cake.add("Торты");
        cake.add("Кексы");


        List<String> vse = new ArrayList<>();


        List<String> myaso = new ArrayList<>();
        myaso.add("Блюда из курицы");
        myaso.add("Блюда из утки");

        List<String> fish = new ArrayList<>();
        fish.add("Запеченная рыба");
        fish.add("Жареная рыба");

        expDetails.put("Первые блюда", one);
        expDetails.put("Вторые блюда", two);
        expDetails.put("Десерты", cake);
        expDetails.put("Мясные блюда", myaso);
        expDetails.put("Рыбные блюда", fish);*/

        return expDetails;
    }
}
