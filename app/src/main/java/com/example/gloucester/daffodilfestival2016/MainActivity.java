package com.example.gloucester.daffodilfestival2016;

import android.support.v4.app.*;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    // global action bar listener
    private ActionBarDrawerToggle actionBarDrawerToggle;
    // global; Make sure to use the DrawerLayout object from the support package.
    private DrawerLayout drawerLayout;
    // global; navigation viewer with a list of elements (tabs)
    private ListView navList;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private int lastClickedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize drawer layout
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);
        // initialize the navigation list
        navList = (ListView)findViewById(R.id.navlist);
        // ArrayList of Section Names (tabs)
        ArrayList<String> navArray = new ArrayList<String>();
        navArray.add(getResources().getString(R.string.section_1));
        navArray.add(getResources().getString(R.string.section_2));
        navArray.add(getResources().getString(R.string.section_3));
        navArray.add(getResources().getString(R.string.section_4));
        navArray.add(getResources().getString(R.string.section_5));
        navArray.add(getResources().getString(R.string.section_6));
        navArray.add(getResources().getString(R.string.section_7));
        // retains choice in navList so that a user can see which section is currently being displayed
        navList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // Set the List view named "navList" to inherit the structure of the ArrayList "navArray"; The ListView now has elements.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, navArray);
        navList.setAdapter(adapter);
        // Action Listener for navList
        navList.setOnItemClickListener(this);
        // initialize action bar listener (only two modes, hence the name "toggle")
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
        // Synchronize drawer listener with action bar
        drawerLayout.setDrawerListener(actionBarDrawerToggle);



        // Make sure to use the ActionBar object from the support package.
        ActionBar actionBar = getSupportActionBar();
        // Set hamburger menu icon and back icon
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // manages fragments and their transactions
        fragmentManager = getSupportFragmentManager();


        // Default section is "About Us"
        loadSelection(0);
    }

    // automates clicking on a section
    private void loadSelection (int choice) {
        // Don't change the selected item if it is the map
        // because the map doesn't load a fragment but rather loads a pdf
        navList.setItemChecked(choice == 2 ? lastClickedPosition : choice, true);
        // fragment transactions
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (choice) {
            // About Us
            case 0:
                MyFragment_1 myFragment_1 = new MyFragment_1();
                // replace the current fragment holder with the selected fragment
                fragmentTransaction.replace(R.id.fragmentholder, myFragment_1);
                // execute the stored transactions
                fragmentTransaction.commit();
                // Change Title of Action Bar
                getSupportActionBar().setTitle(R.string.section_1);
                break;
            // Vendors //h
            case 1:
                MyFragment_2 myFragment_2 = new MyFragment_2();
                // replace the current fragment holder with the selected fragment
                fragmentTransaction.replace(R.id.fragmentholder, myFragment_2);
                // execute the stored transactions
                fragmentTransaction.commit();
                // Change Title of Action Bar
                getSupportActionBar().setTitle(R.string.section_2);
                break;
            // Map
            case 2:
                // performs a task asynchronously -- view pdf
                new ViewPDF().execute(this);
                break;
            case 3:
                MyFragment_3 myFragment_3 = new MyFragment_3();
                // replace the current fragment holder with the selected fragment
                fragmentTransaction.replace(R.id.fragmentholder, myFragment_3);
                // execute the stored transactions
                fragmentTransaction.commit();
                // Change Title of Action Bar
                getSupportActionBar().setTitle(R.string.section_4);
                break;
            default:
                break;
        }
    }

    // allows program to maintain data after the user does something like switch between portrait and landscape
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // synchronizes state
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        } else if (id == android.R.id.home) {           // Toggle drawer
            if (drawerLayout.isDrawerOpen(navList)) {
                drawerLayout.closeDrawer(navList);
            } else {
                drawerLayout.openDrawer(navList);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    // is executed when the user clicks on a section in the ListView
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // replaces the current fragment with the specified fragment
        loadSelection(position);
        // updates last clicked position only if not clicked on map
        lastClickedPosition = position == 2 ? lastClickedPosition : position;
        // closes the drawer after a section is clicked
        drawerLayout.closeDrawer(navList);
    }
}

