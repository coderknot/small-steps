package com.epicodus.smallsteps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HabitsActivity extends AppCompatActivity {
    private String[] habits = new String[] {"Brush Teeth", "Study", "Exercise", "Read", "Eat", "Wash Dishes", "Meditate"};
    private ListView mHabitsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits);
        ButterKnife.bind(this);

        mHabitsListView = (ListView) findViewById(R.id.habitsListView);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, habits);

        mHabitsListView.setAdapter(adapter);
    }
}
