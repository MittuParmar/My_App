package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.myapp.faverate.FaverateFragment;
import com.example.myapp.home.HomeFragment;
import com.example.myapp.notification.NotificationFragment;
import com.example.myapp.orders.OrdersFragment;
import com.example.myapp.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout=findViewById(R.id.frame_container);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),new HomeFragment()).commit();

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_home:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(frameLayout.getId(),new HomeFragment()).commit();
                        break;

                    case R.id.menu_orders:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(frameLayout.getId(),new OrdersFragment()).commit();
                        break;

                    case R.id.menu_faverate:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(frameLayout.getId(),new FaverateFragment()).commit();
                        break;

                    case R.id.menu_notification:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(frameLayout.getId(),new NotificationFragment()).commit();
                        break;

                    case R.id.menu_profile:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(frameLayout.getId(),new ProfileFragment()).commit();
                        break;
                }

                return true;
            }
        });
    }
}
