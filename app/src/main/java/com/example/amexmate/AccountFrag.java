package com.example.amexmate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class AccountFrag extends Fragment {

    SharedPreferences sharedPreferences;



    public AccountFrag() {
        // Required empty public constructor
    }


    TextView gpay;

    String GOOGLE_PAY_PACKAGE_NAME = " com.android.vending";
    int GOOGLE_PAY_REQUEST_CODE = 123;
    CardView gopay,logout;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_account, container, false);

        sharedPreferences =  getContext().getSharedPreferences("login", Context.MODE_PRIVATE);

        gpay=view.findViewById(R.id.gpay);
        gopay=view.findViewById(R.id.goopay);
        logout=view.findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putBoolean("alreadylogin",false);
                editor.commit();
                FirebaseAuth.getInstance().signOut();
                Intent in=new Intent(getActivity(),LoginActivity.class);
                startActivity(in);
                getActivity().finish();
            }
        });


        gopay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("clicked","yes");
                open(GOOGLE_PAY_PACKAGE_NAME);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
    public void open(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.nbu.paisa.user&hl=en_IN&gl=US"));
        startActivity(browserIntent);
    }
}