package com.example.airbag.airbag.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.airbag.airbag.R;

/**
 * Created by shantanu on 21/3/17.
 */

public class ReportLostFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.report_lost_fragment,container,false);
        return rootview;
    }
}
