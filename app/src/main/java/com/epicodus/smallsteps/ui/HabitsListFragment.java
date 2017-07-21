package com.epicodus.smallsteps.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.epicodus.smallsteps.Constants;
import com.epicodus.smallsteps.R;
import com.epicodus.smallsteps.adapters.FirebaseHabitListAdapter;
import com.epicodus.smallsteps.adapters.FirebaseHabitViewHolder;
import com.epicodus.smallsteps.models.Habit;
import com.epicodus.smallsteps.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HabitsListFragment extends Fragment implements View.OnClickListener {
    private DatabaseReference mHabitReference;
    private FirebaseRecyclerAdapter mFirebaseRecyclerAdapter;
//    private FirebaseHabitListAdapter mFirebaseHabitListAdapter;
//    private ItemTouchHelper mItemTouchHolder;

    @Bind(R.id.habitsRecyclerView) RecyclerView mHabitsRecyclerView;
    @Bind(R.id.habitsListNewHabitButton) Button mHabitsListNewHabitButton;

    public HabitsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ButterKnife.bind(getActivity());

        setUpFirebaseRecyclerAdapter();

        mHabitsListNewHabitButton.setOnClickListener(this);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_habits_list, container, false);
    }

    private void setUpFirebaseRecyclerAdapter() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = user.getUid();
//
//        Query query = FirebaseDatabase
//                .getInstance()
//                .getReference(Constants.FIREBASE_CHILD_HABITS)
//                .child(uid)
//                .orderByChild(Constants.FIREBASE_QUERY_INDEX);
//
//        mFirebaseHabitListAdapter = new FirebaseHabitListAdapter(Habit.class,
//                R.layout.habit_list_item,
//                FirebaseHabitViewHolder.class,
//                query,
//                getActivity(),
//                this);

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
//        mHabitsRecyclerView.setAdapter(mFirebaseHabitListAdapter);

//        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseHabitListAdapter);
//        mItemTouchHolder = new ItemTouchHelper(callback);
//        mItemTouchHolder.attachToRecyclerView(mHabitsRecyclerView);
    }

    @Override
    public void onClick(View v) {
//        if(v == mHabitsListNewHabitButton) {
//            Intent newHabitIntent = new Intent(getActivity(), NewHabitActivity.class);
//            startActivity(newHabitIntent);
//        }
    }

//    @Override
//    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
//        mItemTouchHolder.startDrag(viewHolder);
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFirebaseRecyclerAdapter.cleanup();
//        mFirebaseHabitListAdapter.cleanup();
    }

}
