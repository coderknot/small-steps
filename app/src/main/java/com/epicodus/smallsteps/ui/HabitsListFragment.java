package com.epicodus.smallsteps.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.smallsteps.Constants;
import com.epicodus.smallsteps.R;
import com.epicodus.smallsteps.adapters.FirebaseHabitViewHolder;
import com.epicodus.smallsteps.models.Habit;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HabitsListFragment extends Fragment implements View.OnClickListener {
    private DatabaseReference mHabitReference;
    private FirebaseRecyclerAdapter mFirebaseRecyclerAdapter;

    @Bind(R.id.habitsRecyclerView) RecyclerView mHabitsRecyclerView;

    public HabitsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habits_list, container, false);
        ButterKnife.bind(this, view);

        setUpFirebaseRecyclerAdapter();

        return view;
    }

    private void setUpFirebaseRecyclerAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mHabitReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_HABITS)
                .child(uid);

        mFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Habit, FirebaseHabitViewHolder>
                (Habit.class, R.layout.habit_list_item, FirebaseHabitViewHolder.class, mHabitReference) {
            @Override
            public void populateViewHolder(FirebaseHabitViewHolder habitViewHolder, Habit habit, int position) {
                habitViewHolder.bindHabit(habit);
            }
        };

        mHabitsRecyclerView.setHasFixedSize(true);
        mHabitsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHabitsRecyclerView.setAdapter(mFirebaseRecyclerAdapter);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFirebaseRecyclerAdapter.cleanup();
    }

}
