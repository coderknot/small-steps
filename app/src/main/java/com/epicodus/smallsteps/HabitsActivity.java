package com.epicodus.smallsteps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HabitsActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.addHabitButton) Button mAddHabitButton;
    @Bind(R.id.habitNameEditText) EditText mHabitNameEditText;
    @Bind(R.id.habitsListView) ListView mHabitsListView;

    private ArrayList<String> habits;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits);
        ButterKnife.bind(this);

        habits = new ArrayList<String>();

        habits.add("Brush Teeth");
        habits.add("Study");
        habits.add("Exercise");
        habits.add("Read");
        habits.add("Eat");
        habits.add("Wash Dishes");
        habits.add("Meditate");

        mHabitsListView = (ListView) findViewById(R.id.habitsListView);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, habits);

        mHabitsListView.setAdapter(adapter);
        mAddHabitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mAddHabitButton) {
            //TODO: Form Input Validation
            String habitName = mHabitNameEditText.getText().toString();
            habits.add(habitName);
            Toast.makeText(HabitsActivity.this, "New Habit Added!", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
            mHabitNameEditText.setText("");
        }
    }
}
