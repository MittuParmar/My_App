package com.example.myapp.signuporsignin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.myapp.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class SignUpOrSignInActivity extends AppCompatActivity {


    TabLayout tabLayout;
    TabItem registerTabItem, logInTabItem;
    ViewPager viewPager;
    SignUpOrSignInAdapter signUpOrSignInAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_or_sign_in);

        tabLayout=findViewById(R.id.tab_layout);
        registerTabItem=findViewById(R.id.tab_item_register);
        logInTabItem=findViewById(R.id.tab_item_log_in);
        viewPager=findViewById(R.id.view_pager);

        signUpOrSignInAdapter=new SignUpOrSignInAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(signUpOrSignInAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                signUpOrSignInAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}
