package com.example.recipes.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TableRow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.example.recipes.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentRecipes.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentRecipes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRecipes extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private AnimationDrawable mAnimationDrawable;
    TableRow layout;


    private OnFragmentInteractionListener mListener;

    public FragmentRecipes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentRecipes.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentRecipes newInstance(String param1, String param2) {
        FragmentRecipes fragment = new FragmentRecipes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    ExpandableListView expListView;
    ExpandableListAdapter expListAdapter;
    List<String> expListTitle;
    HashMap<String, List<String>> expListDetail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_fragment_recipes);

       // layout = (TableRow) findViewById(R.id.layout_root);

       // mAnimationDrawable = (AnimationDrawable) layout.getBackground();
       // mAnimationDrawable.setEnterFadeDuration(2500);
        // mAnimationDrawable.setExitFadeDuration(5000);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //все рецепты на главной
       View rootView = inflater.inflate(R.layout.fragment_fragment_recipes, container, false);
        /*TextView i = (TextView) rootView.findViewById(R.id.expandedListItem);
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment yoursFragment = new FragmentList();
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.container, yoursFragment).addToBackStack(null);
                trans.commit();
            }

        });*/
        //return inflater.inflate(R.layout.fragment_fragment_main, container, false);


        expListView = (ExpandableListView) rootView.findViewById(R.id.expListView);
        expListDetail = ListData.loadData(getContext());

        expListTitle = new ArrayList<>(expListDetail.keySet());
        expListAdapter = new ListAdapter(getContext(), expListTitle, expListDetail);


        expListView.setAdapter(expListAdapter);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


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
