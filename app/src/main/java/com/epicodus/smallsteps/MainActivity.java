package com.epicodus.smallsteps;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.beginButton) Button mBeginButton;
    @Bind(R.id.aboutButton) Button mAboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface appNameFont = Typeface.createFromAsset(getAssets(), "fonts/app_name.ttf");
        mAppNameTextView.setTypeface(appNameFont);

        mBeginButton.setOnClickListener(this);
        mAboutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mBeginButton) {
            Toast.makeText(MainActivity.this, "Begin button clicked", Toast.LENGTH_LONG).show();
        }
        if(v == mAboutButton) {
            Toast.makeText(MainActivity.this, "About button clicked", Toast.LENGTH_LONG).show();
        }
    }
}
