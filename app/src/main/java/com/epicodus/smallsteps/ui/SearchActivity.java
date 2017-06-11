package com.epicodus.smallsteps.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.smallsteps.Constants;
import com.epicodus.smallsteps.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences mLocationSharedPreferences;
    private SharedPreferences.Editor mLocationEditor;

    @Bind(R.id.searchButton) Button mSearchButton;
    @Bind(R.id.searchEditText) EditText mSearchEditText;
    @Bind(R.id.zipEditText) EditText mZipEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        mLocationSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mLocationEditor = mLocationSharedPreferences.edit();

        determineLocationDisplay();

        mSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mSearchButton) {
            String searchText = mSearchEditText.getText().toString().toLowerCase();
            String searchZip = mZipEditText.getText().toString();

            if (!isValidSearchText(searchText) || !isValidSearchZip(searchZip)) {
                return;
            }

            if(!(isLocationSaved())) {
                addToSharedPreferences(searchZip);
            }

            Intent searchIntent = new Intent(SearchActivity.this, GroupListActivity.class);
            searchIntent.putExtra("searchText", searchText);
            searchIntent.putExtra("searchZip", searchZip);
            startActivity(searchIntent);
        }
    }

    private void determineLocationDisplay() {
        if(isLocationSaved()) {
            String savedLocation = mLocationSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
            setLocationDisplay(savedLocation);
        }
    }

    private void setLocationDisplay(String location) {
        mZipEditText.setText(location);
    }

    private void addToSharedPreferences(String location) {
        mLocationEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }

    private boolean isLocationSaved() {
        String location = mLocationSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);

        if(location == null) {
            return false;
        }

        return true;
    }

    private boolean isValidSearchText(String searchText) {
        if (searchText.equals("")) {
            mSearchEditText.setError("Please enter a topic.");
            return false;
        }

        return true;
    }

    private boolean isValidSearchZip(String searchZip) {
        Pattern locationPattern = Pattern.compile(Constants.ZIP_REGEX);
        Matcher locationMatcher = locationPattern.matcher(searchZip);
        if(!(locationMatcher.matches())) {
            mZipEditText.setError("Please enter a valid ZIP Code.");
            return false;
        }

        return true;
    }
}
