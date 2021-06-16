package com.example.myapp.signuporsignin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapp.MainActivity;
import com.example.myapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LogInFragment extends Fragment {

    EditText email, password;
    ProgressBar loginProgressBar;
    Button loginButton;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_log_in, container, false);

        email = view.findViewById(R.id.log_in_email);
        password = view.findViewById(R.id.log_in_password);
        loginProgressBar = view.findViewById(R.id.log_in_progress_bar);
        loginButton = view.findViewById(R.id.log_in_button);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(email.getText())) {
                    if (email.getText().toString().trim().matches(emailPattern)) {
                        if (!TextUtils.isEmpty(password.getText()) && password.getText().length() > 6) {

                            loginProgressBar.setVisibility(View.VISIBLE);
                            loginButton.setEnabled(false);

                            FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()){
                                                startActivity(new Intent(view.getContext(), MainActivity.class));
                                                getActivity().finish();
                                            }else{
                                                loginProgressBar.setVisibility(View.INVISIBLE);
                                                loginButton.setEnabled(true);
                                                Toast.makeText(view.getContext(), ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                        } else {
                            password.requestFocus();
                            password.setError("Your password should be at least 6 characters");
                        }
                    } else {
                        email.requestFocus();
                        email.setError("Please Enter correct email");
                    }
                } else {
                    email.requestFocus();
                    email.setError("Please Enter Email");
                }


            }
        });


        return view;
    }
}
