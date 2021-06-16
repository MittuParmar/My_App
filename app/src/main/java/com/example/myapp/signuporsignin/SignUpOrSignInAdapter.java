package com.example.myapp.signuporsignin;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SignUpOrSignInAdapter extends FragmentPagerAdapter {


    int tabCount;

    public SignUpOrSignInAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new RegisterFragment();
            case 1:
                return new LogInFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

}
