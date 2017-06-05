package com.epicodus.smallsteps.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.epicodus.smallsteps.Constants;
import com.epicodus.smallsteps.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.githubButton) Button mGithubButton;
    @Bind(R.id.backButton) Button mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        mBackButton.setOnClickListener(this);
        mGithubButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mGithubButton) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(Constants.GITHUB_PROJECT_URL));
            startActivity(webIntent);
        }
        if(v == mBackButton) {
            Intent intent = new Intent(AboutActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
