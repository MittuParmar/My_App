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
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.MainActivity;
import com.example.myapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class RegisterFragment extends Fragment {


    EditText firstName, lastName, email, password, confirmPassword;
    ProgressBar registerProgressBar;
    Button registerButton;
    TextView skipTextView;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_register, container, false);

        firstName = view.findViewById(R.id.register_first_name);
        lastName = view.findViewById(R.id.register_last_name);
        email = view.findViewById(R.id.register_email);
        password = view.findViewById(R.id.register_password);
        confirmPassword = view.findViewById(R.id.register_re_enter_password);
        registerProgressBar = view.findViewById(R.id.register_progress_bar);
        registerButton = view.findViewById(R.id.register_button);
        skipTextView = view.findViewById(R.id.skip_text_view);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(firstName.getText())) {
                    if (!TextUtils.isEmpty(lastName.getText())) {
                        if (!TextUtils.isEmpty(email.getText())) {
                            if (email.getText().toString().trim().matches(emailPattern)) {
                                if (!TextUtils.isEmpty(password.getText()) && password.getText().length() > 6) {
                                    if (password.getText().toString().trim().equals(confirmPassword.getText().toString().trim())) {

                                        registerProgressBar.setVisibility(View.VISIBLE);
                                        registerButton.setEnabled(false);

                                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if (task.isSuccessful()) {

                                                            Map<String, String> user = new HashMap<>();
                                                            user.put("first_name", firstName.getText().toString());
                                                            user.put("Last_name", lastName.getText().toString());
                                                            user.put("email", email.getText().toString());
                                                            user.put("password", password.getText().toString());

                                                            FirebaseFirestore.getInstance().collection("USERS").document(FirebaseAuth.getInstance().getUid())
                                                                    .set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if (task.isSuccessful()) {

                                                                        startActivity(new Intent(view.getContext(), MainActivity.class));
                                                                        getActivity().finish();

                                                                    } else {
                                                                        registerProgressBar.setVisibility(View.INVISIBLE);
                                                                        registerButton.setEnabled(true);
                                                                        Toast.makeText(view.getContext(), "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }
                                                            });
                                                        } else {
                                                            registerProgressBar.setVisibility(View.INVISIBLE);
                                                            registerButton.setEnabled(true);
                                                            Toast.makeText(view.getContext(), "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });

                                    } else {
                                        confirmPassword.requestFocus();
                                        confirmPassword.setError("Please Enter correct Password");
                                    }
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
                    } else {
                        lastName.requestFocus();
                        lastName.setError("Please Enter Last Name");

                    }
                } else {
                    firstName.requestFocus();
                    firstName.setError("Please Enter First Name");
                }
            }
        });


        skipTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), MainActivity.class));
                getActivity().finish();
            }
        });

        return view;
    }
}
