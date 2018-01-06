package com.example.recipes.Fragments;

/**
 * Created by Оленька on 22.11.2017.
 */

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.recipes.R;

import java.util.ArrayList;

class ProductAdapter extends ArrayAdapter<Product> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Product> productList;

    ProductAdapter(Context context, int resource, ArrayList<Product> products) {
        super(context, resource, products);
        this.productList = products;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Product product = productList.get(position);

        viewHolder.nameView.setText(product.getName());
        viewHolder.countView.setText(formatValue(product.getCount(), product.getUnit()));
        viewHolder.zenaView.setText(String.valueOf(product.getPrice()));

        viewHolder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = product.getCount() - 1;
                if (count < 0) count = 0;
                product.setCount(count);
                viewHolder.countView.setText(formatValue(count, product.getUnit()));
            }
        });
        viewHolder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = product.getCount() + 1;
                product.setCount(count);
                viewHolder.countView.setText(formatValue(count, product.getUnit()));
            }
        });


        viewHolder.checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    viewHolder.countView.setPaintFlags(viewHolder.countView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    viewHolder.nameView.setPaintFlags(viewHolder.nameView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    product.setSelected(true);
                } else {
                    viewHolder.countView.setPaintFlags(viewHolder.countView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                    viewHolder.nameView.setPaintFlags(viewHolder.nameView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                    product.setSelected(false);
                }
            }
        });
        return convertView;
    }

    private String formatValue(int count, String unit) {
        return String.valueOf(count) + " " + unit;
    }

    private class ViewHolder {
        final CheckBox checked;
        final Button addButton, removeButton;
        final TextView nameView, countView;
        final EditText zenaView;
     //   final EditText zenaView;

        ViewHolder(View view) {
            checked = (CheckBox) view.findViewById(R.id.checkBox);
            addButton = (Button) view.findViewById(R.id.addButton);
            removeButton = (Button) view.findViewById(R.id.removeButton);
            nameView = (TextView) view.findViewById(R.id.nameView);
            countView = (TextView) view.findViewById(R.id.countView);
            zenaView = (EditText) view.findViewById(R.id.zenaView);
     //       zenaView = (EditText) view.findViewById(R.id.zenaView);
        }
    }
}
/* CheckBox checked = (CheckBox) v.findViewById(R.id.checkBox);
        checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    count.setPaintFlags(count.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    nameView.setPaintFlags(nameView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    count.setPaintFlags(count.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                    nameView.setPaintFlags(nameView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                }
            }
        }
); */