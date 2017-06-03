package com.epicodus.smallsteps.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.smallsteps.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.searchButton) Button mSearchButton;
    @Bind(R.id.searchEditText) EditText mSearchEditText;
    @Bind(R.id.zipEditText) EditText mZipEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        mSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mSearchButton) {
            String searchText = mSearchEditText.getText().toString();
            String searchZip = mZipEditText.getText().toString();

            Log.v("Search Activity", searchText);
            Log.v("Search Activity", searchZip);

            Intent searchIntent = new Intent(SearchActivity.this, GroupListActivity.class);
            searchIntent.putExtra("searchText", searchText);
            searchIntent.putExtra("searchZip", searchZip);
            startActivity(searchIntent);
        }
    }
}
