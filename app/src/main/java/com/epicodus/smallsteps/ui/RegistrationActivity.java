package com.epicodus.smallsteps.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
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
import com.google.firebase.auth.UserProfileChangeRequest;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = RegistrationActivity.class.getSimpleName();

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mAuthProgressDialog;
    private String mUserName;

    @Bind(R.id.registrationAppNameTextView) TextView mRegistrationAppNameTextView;
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

        Typeface appNameFont = Typeface.createFromAsset(getAssets(), "fonts/app_name.ttf");
        mRegistrationAppNameTextView.setTypeface(appNameFont);

        mAuth = FirebaseAuth.getInstance();
        createAuthStateListener();
        createAuthProgressDialog();

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

        if(mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
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
                    Intent authStateIntent = new Intent(RegistrationActivity.this, Main2Activity.class);
                    authStateIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(authStateIntent);
                    finish();
                }
            }
        };
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating account information...");
        mAuthProgressDialog.setCancelable(false);
    }

    private void createUserProfile(final FirebaseUser firebaseUser) {
        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(mUserName)
                .build();

        firebaseUser.updateProfile(addProfileName).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Log.d(TAG, firebaseUser.getDisplayName());
                }
            }
        });
    }

    private void registerUser() {
        mUserName = mRegistrationNameEditText.getText().toString().trim();
        final String email = mRegistrationEmailEditText.getText().toString().trim();
        String password = mRegistrationPasswordEditText.getText().toString().trim();
        String confirmPassword = mRegistrationConfirmPasswordEditText.getText().toString().trim();

        if (!isValidName(mUserName) ||
                !isValidEmail(email) ||
                !isValidPassword(password) ||
                !passwordsMatch(password, confirmPassword)) return;

        mAuthProgressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mAuthProgressDialog.dismiss();

                        if(task.isSuccessful()) {
                            createUserProfile(task.getResult().getUser());
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Authentication failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private boolean isValidName(String name) {
        if (name.equals("")) {
            mRegistrationNameEditText.setError("Please enter your name.");
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());

        if (!isGoodEmail) {
            mRegistrationEmailEditText.setError("Please enter a valid email address.");
            return false;
        }

        return isGoodEmail;
    }

    private boolean isValidPassword(String password) {
        if (password.length() < 6) {
            mRegistrationPasswordEditText.setError("Please create a password containing at least 6 characters.");
            return false;
        }

        return true;
    }

    private boolean passwordsMatch(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            mRegistrationConfirmPasswordEditText.setError("Passwords do not match!");
            return false;
        }

        return true;
    }
}
