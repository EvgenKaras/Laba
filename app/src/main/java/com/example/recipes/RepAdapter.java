package com.example.recipes;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.recipes.Fragments.FragmentBlyudo;

import java.util.List;

/**
 * Created by kacku on 28.12.2017.
 */

public class RepAdapter extends BaseAdapter {
    List<Receptlistitem> list;
    Context context;
    FragmentManager manager;
    public RepAdapter(List<Receptlistitem> list, Context context, FragmentManager manager)
    {
        this.list = list;
        this.context = context;
        this.manager = manager;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Receptlistitem cat = (Receptlistitem) getItem(i);

        if (view == null) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.list_recept, null);
        }
        ((ImageView)view.findViewById(R.id.imageView3)).setImageResource(cat.getIdPic());
        ((TextView)view.findViewById(R.id.NameBlydo)).setText(cat.getName());
        ((TextView)view.findViewById(R.id.textView2)).setText(cat.getDescr());
        ((RatingBar)view.findViewById(R.id.ratingBar_small1)).setNumStars(5);
        ((RatingBar)view.findViewById(R.id.ratingBar_small1)).setRating(cat.getRating());
        ((EditText)view.findViewById(R.id.idField)).setText(String.valueOf(cat.getIdRecept()));
        ((LinearLayout)view.findViewById(R.id.first)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText t = (EditText) view.findViewById(R.id.idField);
                String s = t.getText().toString();

                Fragment yoursFragment=new FragmentBlyudo();
                Bundle b = new Bundle();
                b.putInt("id",Integer.valueOf(s));
                yoursFragment.setArguments(b);
                FragmentTransaction trans=manager.beginTransaction();
                trans.replace(R.id.container, yoursFragment).addToBackStack(null);
                trans.commit();
            }
        });
        return view;
    }
}
