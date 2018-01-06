package com.example.recipes.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.recipes.ItemForBuy;
import com.example.recipes.R;
import com.example.recipes.ingredient;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentShopping.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentShopping#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentShopping extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    ArrayList<Product> products = new ArrayList();
    ListView productList;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int massCount = 2;

    private OnFragmentInteractionListener mListener;

    public FragmentShopping() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentShopping.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentShopping newInstance(String param1, String param2) {
        FragmentShopping fragment = new FragmentShopping();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
       // setContentView(R.layout.fragment_fragment_shopping);

     /*   if(products.size()==0){
            products.add(new Product("Картофель", "кг."));
            products.add(new Product("Чай", "шт."));
            products.add(new Product("Яйца", "шт."));
            products.add(new Product("Молоко", "л."));
            products.add(new Product("Макароны", "кг."));
        }
        productList = (ListView) findViewById(R.id.productList);
        ProductAdapter adapter = new ProductAdapter(this, R.layout.fragment_fragment_shopping, products);
        productList.setAdapter(adapter);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_fragment_shopping, container, false);


        View v = inflater.inflate(R.layout.fragment_fragment_shopping, container, false);
       /* if(products.size()==0){
            products.add(new Product("Картофель ", "кг"));
            products.add(new Product("Чай ", "шт"));
            products.add(new Product("Яйца ", "шт"));
            products.add(new Product("Молоко ", "л"));
            products.add(new Product("Макароны ", "кг"));
        }*/
        Realm.init(getContext());
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<ItemForBuy> results = realm.where(ItemForBuy.class).findAll();
                for(ItemForBuy i: results)
                {
                    String name = (realm.where(ingredient.class).equalTo("id", i.getId_ingr()).findAll()).last().getName();
                    int priceforunit = (realm.where(ingredient.class).equalTo("id", i.getId_ingr()).findAll()).last().getPrice();
                    products.add(new Product(name,"шт",i.getCount(),priceforunit*i.getCount(),i.getId()));

                }
            }
        });

        final ListView productList = (ListView) v.findViewById(R.id.productList);
        ProductAdapter adapter = new ProductAdapter(getContext(), R.layout.shop_item, products);
        productList.setAdapter(adapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setCancelable(false)
                .setView(R.layout.add_ing)
                .setPositiveButton("ОК", null)
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        final AlertDialog addDialog = builder.create();

        addDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {
                Button b = ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener()
                {
                                        @Override
                                         public void onClick(View view)
                                         {
                                             EditText ed1 = (EditText) ((AlertDialog) addDialog).findViewById(R.id.editText);
                                             EditText ed2 = (EditText) ((AlertDialog) addDialog).findViewById(R.id.editText1);
                                             EditText ed3 = (EditText) ((AlertDialog) addDialog).findViewById(R.id.editText2);
                                             TextView ed4 = (TextView) ((AlertDialog) addDialog).findViewById(R.id.textView14);

                                             if(!ed3.getText().toString().equals("") && !ed2.getText().toString().equals("") && !ed1.getText().toString().equals("")) {
                                                 final int[] lastid = {0};
                                                 Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                                                     @Override
                                                     public void execute(Realm realm) {
                                                         lastid[0] = realm.where(ItemForBuy.class).max("id").intValue();
                                                     }
                                                 });
                                                 products.add(new Product(ed1.getText().toString(), " шт", Integer.valueOf(ed2.getText().toString()), Integer.valueOf(ed3.getText().toString()),lastid[0]+1));

                                                 Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                                                     @Override
                                                     public void execute(Realm realm) {
                                                         ItemForBuy i = new ItemForBuy();
                                                         i.setId(products.get(products.size()-1).getIdItemForBuy());
                                                         i.setCount(products.get(products.size()-1).getCount());
                                                         int ingrId = realm.where(ingredient.class).equalTo("name",products.get(products.size()-1).getName()).max("id").intValue();

                                                         i.setId_ingr(ingrId);
                                                         realm.insert(i);
                                                     }
                                                 });
                                                 // products.add(new Product(ed1.getText().toString(), " шт", Integer.valueOf(ed2.getText().toString()), Integer.valueOf(ed2.getText().toString())));
                                                 ProductAdapter adapter = new ProductAdapter(getContext(), R.layout.shop_item, products);
                                                 ed4.setVisibility(View.INVISIBLE);
                                                 productList.setAdapter(adapter);
                                                 addDialog.cancel();
                                             }
                                             else
                                             {
                                                 ed4.setVisibility(View.VISIBLE);
                                             }
                                         }
                 });
            }
        });
        final Button addButton = (Button) v.findViewById(R.id.button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialog.show();
            }
        });
//        // Получаем флажок
//        CheckBox language = (CheckBox) v;
//        // Получаем, отмечен ли данный флажок
//        boolean checked = language.isChecked();
//
//        TextView selection = (TextView) v.findViewById(R.id.checkBox);

        AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());

        builder2.setCancelable(false)
                .setMessage("Вы действительно хотите удалить эти продукты?")
                .setPositiveButton("Да",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                List<Product> removeProduct = new ArrayList<>();
                                for(final Product p:products)
                                {
                                    if(p.isSelected())
                                    {
                                        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                                            @Override
                                            public void execute(Realm realm) {
                                                RealmResults<ItemForBuy> realmResults =  realm.where(ItemForBuy.class).equalTo("id",p.getIdItemForBuy()).findAll();
                                                realmResults.deleteAllFromRealm();
                                            }
                                        });
                                        removeProduct.add(p);
                                    }
                                }
                                products.removeAll(removeProduct);
                                ProductAdapter adapter = new ProductAdapter(getContext(), R.layout.shop_item, products);
                                productList.setAdapter(adapter);
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("Нет",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        final AlertDialog removeDialog = builder2.create();

        final ImageView deleteButton = (ImageView) v.findViewById(R.id.buttonDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                    }
                });
                removeDialog.show();

            }
        });

        return v;
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
