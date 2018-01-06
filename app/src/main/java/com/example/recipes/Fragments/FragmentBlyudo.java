package com.example.recipes.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipes.BlydoIngredientAdapter;
import com.example.recipes.IngredientItem;
import com.example.recipes.ItemForBuy;
import com.example.recipes.R;
import com.example.recipes.Recept;
import com.example.recipes.Recept_Ingredient;
import com.example.recipes.ingredient;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentBlyudo.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentBlyudo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBlyudo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int id =-99;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentBlyudo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentBlyudo.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentBlyudo newInstance(String param1, String param2) {
        FragmentBlyudo fragment = new FragmentBlyudo();
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
            id = getArguments().getInt("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootElement = inflater.inflate(R.layout.fragment_fragment_blyudo, container, false);

        Realm.init(getContext());
        final int finalId = id;

        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Recept> recept =  realm.where(Recept.class).equalTo("id", finalId).findAll();
                Recept r = recept.last();
                if(r !=null)
                {
                    ((ImageView)rootElement.findViewById(R.id.ImageBlydo)).setImageResource(r.getImgID());
                    ((TextView) rootElement.findViewById(R.id.NameBlydo)).setText(r.getName());
                    ((RatingBar)rootElement.findViewById(R.id.Rating)).setRating(r.getRating());
                    ((TextView)rootElement.findViewById(R.id.Instruction)).setText(r.getInstruction());
                    ((ImageView)rootElement.findViewById(R.id.ImageBludo2)).setImageResource(r.getImgID());
                    ((RatingBar)rootElement.findViewById(R.id.rating2)).setRating(r.getUserRating());
                }

            }
        });

        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Recept_Ingredient> ingr = realm.where(Recept_Ingredient.class).equalTo("id_recept",finalId).findAll();
                List<IngredientItem> ingrs = new ArrayList<>();
                for(Recept_Ingredient r: ingr)
                {
                    String name = (realm.where(ingredient.class).equalTo("id", r.getId_ingredient()).findAll()).last().getName();
                    int count = r.getCountOfIngredient();
                    ingrs.add(new IngredientItem(r.getId_ingredient(),name,count));
                }
                ((ListView)rootElement.findViewById(R.id.ingredients)).setAdapter(new BlydoIngredientAdapter(ingrs,getContext(),getFragmentManager()));
            }
        });

        ((Button)rootElement.findViewById(R.id.addProducts)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListView ls = rootElement.findViewById(R.id.ingredients);
                Adapter a = ls.getAdapter();
                final List<IngredientItem> ingrList = new ArrayList<>();
                for(int i=0;i<a.getCount();i++)
                {
                    IngredientItem item = (IngredientItem) a.getItem(i);
                    if(item.isSelected() == true)
                    {
                        ingrList.add(item);
                    }
                }
                Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        for (IngredientItem i:ingrList)
                        {
                            int lastid;
                            try
                            {
                                lastid = realm.where(ItemForBuy.class).max("id").intValue();
                            }
                            catch (NullPointerException e)
                            {
                                lastid =0;
                            }
                            ItemForBuy b = new ItemForBuy();
                            b.setId(lastid);
                            b.setId_ingr(i.getId());
                            b.setCount(i.getCount());
                            realm.insertOrUpdate(b);
                        }
                    }
                });
            }
        });
        return rootElement;
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
