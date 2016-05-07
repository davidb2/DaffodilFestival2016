package com.example.gloucester.daffodilfestival2016;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment_3 extends Fragment {




    public MyFragment_3() {
        // Required empty public constructor
    }

    // This is called when this fragment gets ATTACHED to an activity.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    // This is called when the activity to which this fragment is attached is created.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_fragment_3, container, false);
    }

    // Instance of the current activity's onCreate (this override is not needed)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    // This is called when the activity to which this fragment is attached is destroyed.
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // This is called when this fragment gets DETACHED to an activity.
    @Override
    public void onDetach() {
        super.onDetach();
    }
}
