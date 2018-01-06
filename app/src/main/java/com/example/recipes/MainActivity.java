package com.example.recipes;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

import android.support.v4.app.FragmentManager;

import com.example.recipes.Fragments.FragmentAbout;
import com.example.recipes.Fragments.FragmentBlyudo;
import com.example.recipes.Fragments.FragmentInteresting;
import com.example.recipes.Fragments.FragmentList;
import com.example.recipes.Fragments.FragmentMain;
import com.example.recipes.Fragments.FragmentRecipes;
import com.example.recipes.Fragments.FragmentShopping;

import java.io.InputStream;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Realm realm;
    FragmentAbout fragmentAbout;
    FragmentMain fragmentMain;
    FragmentRecipes fragmentRecipes;
    FragmentShopping fragmentShopping;
    FragmentInteresting fragmentInteresting;
    FragmentList fragmentList;
    FragmentBlyudo fragmentBlyudo;
    NavigationView navigationView;

    private final static String TAG = "MyLOG";
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                InputStream is;
                try {
                    //if (realm.where(TaskCategory.class).count() != 0) {
                    is = getApplicationContext().getResources().openRawResource(R.raw.recept);
                    realm.createAllFromJson(Recept.class, is);

                    is = getApplicationContext().getResources().openRawResource(R.raw.recept_ingredient);
                    realm.createAllFromJson(Recept_Ingredient.class, is);

                    is = getApplicationContext().getResources().openRawResource(R.raw.category);
                    realm.createAllFromJson(Category.class, is);

                    is = getApplicationContext().getResources().openRawResource(R.raw.ingredient);
                    realm.createAllFromJson(ingredient.class, is);

                    is = getApplicationContext().getResources().openRawResource(R.raw.favorites);
                    realm.createAllFromJson(Favorite.class,is);

                    is =getApplicationContext().getResources().openRawResource(R.raw.subcategory);
                    realm.createAllFromJson(SubCategory.class,is);
                }
                catch (Exception e)
                {
                    Log.w("warning", "realm exeption");
                }
            }
        });


        Log.d(TAG, "onCreate");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "назад...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //инициализируем фрагменты

        fragmentAbout = new FragmentAbout();
        fragmentMain = new FragmentMain();
        fragmentRecipes = new FragmentRecipes();
        fragmentShopping = new FragmentShopping();
        fragmentInteresting = new FragmentInteresting();
        fragmentList = new FragmentList();
        fragmentBlyudo = new FragmentBlyudo();



    }

    //обработчик вызова
    public void dial(View v) {
        EditText number = (EditText) findViewById(R.id.number);
        String toDial = "tel: " + number.getText().toString();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(toDial)));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fm = this.getSupportFragmentManager();
            fm.popBackStack();
        }
        Log.d(TAG, "onBackPressed");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.FMAIN));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   // @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_glavnaya) {
            fragmentTransaction.replace(R.id.container, fragmentMain).addToBackStack(null);

        } else if (id == R.id.nav_recipes) {
            fragmentTransaction.replace(R.id.container, fragmentRecipes).addToBackStack(null);

        } else if (id == R.id.nav_shopping) {
            fragmentTransaction.replace(R.id.container, fragmentShopping).addToBackStack(null);

        } else if (id == R.id.nav_interesting) {
            fragmentTransaction.replace(R.id.container, fragmentInteresting).addToBackStack(null);

        } else if (id == R.id.nav_about) {
            fragmentTransaction.replace(R.id.container, fragmentAbout).addToBackStack(null);
        }
        else if (id == R.id.FMAIN) {
            fragmentTransaction.replace(R.id.container, fragmentMain);}
        //для того что бы отображалось, добавим комит
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
