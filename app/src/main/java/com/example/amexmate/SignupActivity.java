package com.example.amexmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private EditText emailTextView, passwordTextView,budgetET;
    private CardView Btn;

    SharedPreferences sharedPreferences;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private DatabaseReference desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

         sharedPreferences =  getSharedPreferences("login", Context.MODE_PRIVATE);

        mAuth = FirebaseAuth.getInstance();
        mDatabase =  FirebaseDatabase.getInstance().getReference();

        // initialising all views through id defined above
        emailTextView = findViewById(R.id.email);
        passwordTextView = findViewById(R.id.password);
        Btn = findViewById(R.id.btnsignup);
        budgetET=findViewById(R.id.budget);




        // Set on Click Listener on Registration button
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                registerNewUser();
            }
        });
    }
    private void registerNewUser()
    {

        // show the visibility of progress bar to show loading


        // Take the value of two edit texts in Strings
        String email, password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();

        // Validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter email!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter password!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }



        // create new user or register new user
        mAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                    "Registration successful!",
                                    Toast.LENGTH_LONG)
                                    .show();

                            desc = mDatabase.child("budget");

                        //    FirebaseModel firebaseModel=new FirebaseModel(mAuth.getCurrentUser().getUid().toString(),budgetET.getText().toString());
                            mDatabase.child("budget").child(mAuth.getCurrentUser().getUid().toString()).setValue(budgetET.getText().toString());
                            // if the user created intent to login activity

                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putBoolean("alreadylogin",true);
                            editor.commit();
                            Intent intent
                                    = new Intent(SignupActivity.this,
                                    MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {

                            // Registration failed
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Registration failed!!"
                                            + " Please try again later",
                                    Toast.LENGTH_LONG)
                                    .show();

                        }
                    }
                });
    }
}