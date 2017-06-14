package com.epicodus.smallsteps.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.epicodus.smallsteps.Constants;
import com.epicodus.smallsteps.R;
import com.epicodus.smallsteps.adapters.FirebaseHabitViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.epicodus.smallsteps.models.Habit;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HabitsListActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mHabitReference;
    private FirebaseRecyclerAdapter mFirebaseRecyclerAdapter;

    @Bind(R.id.habitsRecyclerView) RecyclerView mHabitsRecyclerView;
    @Bind(R.id.habitsListNewHabitButton) Button mHabitsListNewHabitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits_list);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mHabitReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_HABITS)
                .child(uid);

        setUpFirebaseRecyclerAdapter();

        mHabitsListNewHabitButton.setOnClickListener(this);
    }

    private void setUpFirebaseRecyclerAdapter() {
        mFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Habit, FirebaseHabitViewHolder>
                (Habit.class, R.layout.habit_list_item, FirebaseHabitViewHolder.class, mHabitReference) {
            @Override
            public void populateViewHolder(FirebaseHabitViewHolder habitViewHolder, Habit habit, int position) {
                habitViewHolder.bindHabit(habit);
            }
        };

        mHabitsRecyclerView.setHasFixedSize(true);
        mHabitsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHabitsRecyclerView.setAdapter(mFirebaseRecyclerAdapter);
    }

    @Override
    public void onClick(View v) {
        if(v == mHabitsListNewHabitButton) {
            Intent newHabitIntent = new Intent(HabitsListActivity.this, NewHabitActivity.class);
            startActivity(newHabitIntent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseRecyclerAdapter.cleanup();
    }
}
