package com.epicodus.smallsteps.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.smallsteps.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.startButton) Button mStartButton;
    @Bind(R.id.habitsButton) Button mHabitsButton;
    @Bind(R.id.aboutButton) Button mAboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface appNameFont = Typeface.createFromAsset(getAssets(), "fonts/app_name.ttf");
        mAppNameTextView.setTypeface(appNameFont);

        mStartButton.setOnClickListener(this);
        mHabitsButton.setOnClickListener(this);
        mAboutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mStartButton) {
            Intent habitsIntent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(habitsIntent);
        }
        if(v == mHabitsButton) {
            Intent habitsIntent = new Intent(MainActivity.this, HabitsActivity.class);
            startActivity(habitsIntent);
        }
        if(v == mAboutButton) {
            Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(aboutIntent);
        }
    }
}