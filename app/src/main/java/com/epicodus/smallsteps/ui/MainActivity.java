package com.epicodus.smallsteps.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.smallsteps.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Bind(R.id.mainAppNameTextView) TextView mMainAppNameTextView;
    @Bind(R.id.groupSearchButton) Button mGroupSearchButton;
    @Bind(R.id.aboutButton) Button mAboutButton;
    @Bind(R.id.habitsListButton) Button mHabitsListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface appNameFont = Typeface.createFromAsset(getAssets(), "fonts/app_name.ttf");
        mMainAppNameTextView.setTypeface(appNameFont);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                } else {
                    getSupportActionBar().setTitle("Welcome!");
                }
            }
        };

        mHabitsListButton.setOnClickListener(this);
        mGroupSearchButton.setOnClickListener(this);
        mAboutButton.setOnClickListener(this);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int menuId = menuItem.getItemId();
        if (menuId == R.id.action_logout_old) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent logoutIntent = new Intent(MainActivity.this, LoginActivity.class);
        logoutIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(logoutIntent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if(v == mHabitsListButton) {
            Intent habitsListIntenet = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(habitsListIntenet);
        }
        if(v == mGroupSearchButton) {
            Intent searchIntent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(searchIntent);
        }
        if(v == mAboutButton) {
            Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(aboutIntent);
        }
    }
}