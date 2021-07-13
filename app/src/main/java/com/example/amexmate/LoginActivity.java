package com.example.amexmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    EditText email,password;
    CardView login;
    TextView link;
    SharedPreferences sharedPreferences;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences =  getSharedPreferences("login", Context.MODE_PRIVATE);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.btnlogin);
        link=findViewById(R.id.linksignup);

      firebaseAuth=FirebaseAuth.getInstance();
      link.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent in=new Intent(LoginActivity.this,SignupActivity.class);
              startActivity(in);
              finish();
          }
      });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().equals("")||password.getText().equals(""))
                {
                    Toast.makeText(LoginActivity.this,"Please provide details",Toast.LENGTH_SHORT).show();

                }
                else{
                    loginUserAccount();


                }
            }
        });
    }
    private void loginUserAccount()
    {

        // show the visibility of progress bar to show loading


        // Take the value of two edit texts in Strings
        String semail, spassword;
        semail = email.getText().toString();
        spassword = password.getText().toString();

        // validations for input email and password


        // signin existing user
        firebaseAuth.signInWithEmailAndPassword(semail, spassword)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                            "Login successful!!",
                                            Toast.LENGTH_LONG)
                                            .show();


                                    SharedPreferences.Editor editor=sharedPreferences.edit();
                                    editor.putBoolean("alreadylogin",true);
                                    editor.commit();


                                    // if sign-in is successful
                                    // intent to home activity
                                    Intent intent
                                            = new Intent(LoginActivity.this,
                                            MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }

                                else {

                                    // sign-in failed
                                    Toast.makeText(getApplicationContext(),
                                            "Login failed!!",
                                            Toast.LENGTH_LONG)
                                            .show();


                                }
                            }
                        });
    }
}