package com.epicodus.smallsteps.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;

import com.epicodus.smallsteps.Constants;
import com.epicodus.smallsteps.R;
import com.epicodus.smallsteps.adapters.FirebaseHabitListAdapter;
import com.epicodus.smallsteps.adapters.FirebaseHabitViewHolder;
import com.epicodus.smallsteps.util.OnStartDragListener;
import com.epicodus.smallsteps.util.SimpleItemTouchHelperCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.epicodus.smallsteps.models.Habit;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HabitsListActivity extends AppCompatActivity implements OnStartDragListener, View.OnClickListener {
    private DatabaseReference mHabitReference;
    private FirebaseHabitListAdapter mFirebaseHabitListAdapter;
    private ItemTouchHelper mItemTouchHolder;

    @Bind(R.id.habitsRecyclerView) RecyclerView mHabitsRecyclerView;
    @Bind(R.id.habitsListNewHabitButton) Button mHabitsListNewHabitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits_list);
        ButterKnife.bind(this);

        setUpFirebaseRecyclerAdapter();

        mHabitsListNewHabitButton.setOnClickListener(this);
    }

    private void setUpFirebaseRecyclerAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_HABITS)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);

//        mFirebaseHabitListAdapter = new FirebaseHabitListAdapter(Habit.class,
//                                                                 R.layout.habit_list_item,
//                                                                 FirebaseHabitViewHolder.class,
//                                                                 query,
//                                                                 this,
//                                                                 this);
//
//        mHabitsRecyclerView.setHasFixedSize(true);
//        mHabitsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mHabitsRecyclerView.setAdapter(mFirebaseHabitListAdapter);
//
//        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseHabitListAdapter);
//        mItemTouchHolder = new ItemTouchHelper(callback);
//        mItemTouchHolder.attachToRecyclerView(mHabitsRecyclerView);
    }

    @Override
    public void onClick(View v) {
        if(v == mHabitsListNewHabitButton) {
            Intent newHabitIntent = new Intent(HabitsListActivity.this, NewHabitActivity.class);
            startActivity(newHabitIntent);
        }
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHolder.startDrag(viewHolder);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseHabitListAdapter.cleanup();
    }
}
