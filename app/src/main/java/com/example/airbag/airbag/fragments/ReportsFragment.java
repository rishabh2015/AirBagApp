package com.example.airbag.airbag.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.airbag.airbag.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Rishabh Jain on 21/3/17.
 */

public class ReportsFragment extends android.support.v4.app.Fragment {
    ListView listview;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.reports_layout,container,false);
        getReports();
        return rootview;
    }
    public void getReports()
    {
        final ProgressDialog dialog = ProgressDialog.show(this.getContext(), "Fetching Data", "Please wait...", false, false);
        dialog.show();
        long delayInMillis = 5000;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, delayInMillis);

    }
}
