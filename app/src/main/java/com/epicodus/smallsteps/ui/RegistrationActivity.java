package com.epicodus.smallsteps.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.smallsteps.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = RegistrationActivity.class.getSimpleName();

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Bind(R.id.registrationNameEditText) EditText mRegistrationNameEditText;
    @Bind(R.id.registrationEmailEditText) EditText mRegistrationEmailEditText;
    @Bind(R.id.registrationPasswordEditText) EditText mRegistrationPasswordEditText;
    @Bind(R.id.registrationConfirmPasswordEditText) EditText mRegistrationConfirmPasswordEditText;
    @Bind(R.id.registrationSignUpButton) Button mRegistrationSignUpButton;
    @Bind(R.id.registrationLoginTextView) TextView mRegistrationLoginTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        createAuthStateListener();

        mRegistrationSignUpButton.setOnClickListener(this);
        mRegistrationLoginTextView.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    public void onClick(View v) {
        if(v == mRegistrationLoginTextView) {
            Intent loginIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
            loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(loginIntent);
            finish();
        }
        if(v == mRegistrationSignUpButton) {
            registerUser();
        }
    }

    private void createAuthStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    Intent authStateIntent = new Intent(RegistrationActivity.this, MainActivity.class);
                    authStateIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(authStateIntent);
                    finish();
                }
            }
        };
    }

    private void registerUser() {
        final String name = mRegistrationNameEditText.getText().toString().trim();
        final String email = mRegistrationEmailEditText.getText().toString().trim();
        String password = mRegistrationPasswordEditText.getText().toString().trim();
        String confirmPassword = mRegistrationConfirmPasswordEditText.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Log.v(TAG, "Authentication successful");
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Authentication failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
