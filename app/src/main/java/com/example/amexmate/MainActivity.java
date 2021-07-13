package com.example.amexmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Fragment first,second,third;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        first=new AccountFrag();
        second=new PaymentFrag();
        third=new SavingFrag();



        setCurrentFragment(first);

        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.account:
                    setCurrentFragment(first);
                    return true;
                case R.id.payments:
                    setCurrentFragment(second);
                    return true;
                case R.id.savings:
                    setCurrentFragment(third);
                    return true;
            }
            return false;
        }
    };
    private void setCurrentFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,fragment).commit();
    }


}