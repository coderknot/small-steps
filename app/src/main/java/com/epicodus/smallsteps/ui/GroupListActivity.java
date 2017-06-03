package com.epicodus.smallsteps.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.epicodus.smallsteps.R;
import com.epicodus.smallsteps.models.Group;
import com.epicodus.smallsteps.services.MeetupGroupService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GroupListActivity extends AppCompatActivity {
    @Bind(R.id.groupRecyclerView) RecyclerView mGroupRecyclerView;

    public ArrayList<Group> mGroupList = new ArrayList<>();

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

        getGroupList(searchText, searchZip);
    }

    private void getGroupList(String searchText, String searchZip) {
        final MeetupGroupService meetupGroupService = new MeetupGroupService();
        meetupGroupService.getGroups(searchText, searchZip, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mGroupList = meetupGroupService.processResults(response);
            }
        });
    }
}
