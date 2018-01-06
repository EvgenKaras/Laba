package com.example.recipes;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kacku on 04.01.2018.
 */

public class BlydoIngredientAdapter extends BaseAdapter {

    FragmentManager manager;
    List<IngredientItem> list;
    Context context;
    public BlydoIngredientAdapter(List<IngredientItem> list, Context context, FragmentManager manager)
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
        final IngredientItem ingr = (IngredientItem) getItem(i);

        view = LayoutInflater.from(context).inflate(R.layout.blydoingr,null);

        ((TextView)view.findViewById(R.id.Descr)).setText(ingr.getName()+" - "+ingr.getCount()+" шт");
        ((CheckBox)view.findViewById(R.id.checkBox)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    ingr.setSelected(true);
                }
                else
                {
                    ingr.setSelected(false);
                }
            }
        });
        return view;
    }
}
