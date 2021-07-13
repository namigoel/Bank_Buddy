package com.example.amexmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.logging.Logger;

public class DetailActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textView=findViewById(R.id.label);

        String label=getIntent().getStringExtra("label");

        textView.setText(label);


        ArrayList<String> statement = getIntent().getStringArrayListExtra("test");
        ArrayList<String> amount = getIntent().getStringArrayListExtra("testn");

        Log.e("sizee",String.valueOf(statement.size()));

        recyclerView=findViewById(R.id.rvNew);

        Adapter adapter = new Adapter(statement,amount);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);





    }
}