package com.example.amexmate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;


public class SavingFrag extends Fragment {

    private DatabaseReference mDatabase;
    TextView amount;

    ProgressDialog progress;
    TextView desc;

    ProgressBar progressBar;
    public SavingFrag() {
        // Required empty public constructor
    }


    Integer budget;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_saving, container, false);
        desc=view.findViewById(R.id.desc);
        progress = new ProgressDialog(getContext());
        progress.setTitle("Loading");
        progress.setCancelable(false);
        progress.show();
        mDatabase =  FirebaseDatabase.getInstance().getReference();
        amount=view.findViewById(R.id.amt);
        progressBar=view.findViewById(R.id.simpleProgressBar);

        mDatabase.child("budget").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if(task.isSuccessful())
                {
                    budget=Integer.parseInt( task.getResult().getValue().toString());
                    budget+=10000;
                    amount.setText(task.getResult().getValue().toString());
                    progressBar.setMax(budget);
                    if(budget<=25000)
                    {


                        progressBar.setProgress(budget);
                        desc.setTextColor(Color.parseColor("#F44336"));
                        desc.setText("Uh-Oh!, You have passed your monthly budget by $"+String.valueOf(25000-budget)+"till now. Try saving more");

                    }
                    else{
                        progressBar.setProgress(25000);
                        desc.setTextColor(Color.parseColor("#33691E"));
                        desc.setText("Congratulations! You have managed to save $"+ String.valueOf(budget-25000)+"for your dream goal. Keep Going.");
                    }


                    progress.dismiss();
                }
                else
                {
                    Toast.makeText(getActivity(),task.getException().toString(),Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }

            }
        });




        // Inflate the layout for this fragment
        return view;
    }
}