package com.epicodus.smallsteps.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.epicodus.smallsteps.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GroupListActivity extends AppCompatActivity {
    @Bind(R.id.groupRecyclerView) RecyclerView mGroupRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);
        ButterKnife.bind(this);

        Intent searchIntent = getIntent();
        String searchText = searchIntent.getStringExtra("searchText");
        String searchZip = searchIntent.getStringExtra("searchZip");

        Log.v("Group List Activity", searchText);
        Log.v("Group List Activity", searchZip);
    }
}
