package com.example.airbag.airbag.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.airbag.airbag.R;
import com.example.airbag.airbag.activities.Popup2;
import com.example.airbag.airbag.classes.Bag;
import com.example.airbag.airbag.models.bagdetailspostmodel.BagDetailsPostModel;
import com.example.airbag.airbag.models.getotpmodel.GetOtpModel;
import com.example.airbag.airbag.rest.ApiClient;
import com.example.airbag.airbag.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rishabh Jain on 3/22/2017.
 */

public class MainFragment extends Fragment {

    String TicketNumber,BagType,BagName,BagColor,BagBrand,mobile;
    EditText et_TicketNumber,et_BagType,et_BagName,et_BagColor,et_BagBrand;
    Button finalSaveButton;
    ImageButton FloatingButton,fab;
    PopupWindow pw;
    List<Bag> bags;
    SharedPreferences shared;
    View rootview;
    Context mcontext;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_main,container,false);
        bags=new ArrayList<Bag>();
        et_TicketNumber= (EditText) rootview.findViewById(R.id.et_ticketNumber);

        FloatingButton= (ImageButton) rootview.findViewById(R.id.floatingActionButton);

        mcontext=getContext();

        FloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TicketNumber=et_TicketNumber.getText().toString();
                shared=getContext().getSharedPreferences("MyPrefrences",0);
                SharedPreferences.Editor editor=shared.edit();
                editor.putString("TicketNumber",TicketNumber);
                editor.commit();
             Intent intent=new Intent(getContext(), Popup2.class);
                startActivity(intent);
            }
        });


        return rootview;
    }

}
