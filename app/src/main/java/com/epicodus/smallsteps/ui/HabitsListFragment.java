package com.epicodus.smallsteps.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HabitsListFragment extends Fragment {
    private DatabaseReference mHabitReference;

    @Bind(R.id.habitsRecyclerView) RecyclerView mHabitsRecyclerView;

    public HabitsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habits_list, container, false);
        ButterKnife.bind(this, view);

        if(getActivity() instanceof Main2Activity) {
            ((Main2Activity) getActivity()).showFloatingActionButton();
        }

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

        FirebaseRecyclerAdapter<Habit, FirebaseHabitViewHolder> adapter = new FirebaseRecyclerAdapter<Habit, FirebaseHabitViewHolder>(Habit.class, R.layout.habit_list_item, FirebaseHabitViewHolder.class, mHabitReference) {
            ArrayList<Habit> habitsList = new ArrayList<Habit>();

            @Override
            protected void populateViewHolder(FirebaseHabitViewHolder viewHolder, Habit model, int position) {
                viewHolder.habitTitleTextView.setText(model.getTitle());
                habitsList.add(model);
            }

            @Override
            public FirebaseHabitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                FirebaseHabitViewHolder firebaseHabitViewHolder = super.onCreateViewHolder(parent, viewType);
                firebaseHabitViewHolder.setOnClickListener(new FirebaseHabitViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Habit habit = habitsList.get(position);

                        Bundle bundle = new Bundle();
                        bundle.putParcelable("Habit", Parcels.wrap(habit));

                        HabitDetailFragment habitDetailFragment = new HabitDetailFragment();
                        habitDetailFragment.setArguments(bundle);
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.content_frame, habitDetailFragment);
                        fragmentTransaction.commit();
                    }
                });
                return firebaseHabitViewHolder;
            }
        };

        mHabitsRecyclerView.setHasFixedSize(true);
        mHabitsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHabitsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
