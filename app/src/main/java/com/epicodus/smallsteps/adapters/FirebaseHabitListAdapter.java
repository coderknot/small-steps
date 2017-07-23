package com.epicodus.smallsteps.adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.epicodus.smallsteps.models.Habit;
import com.epicodus.smallsteps.util.ItemTouchHelperAdapter;
import com.epicodus.smallsteps.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseHabitListAdapter extends FirebaseRecyclerAdapter<Habit, FirebaseHabitViewHolder> /*implements ItemTouchHelperAdapter*/ {
    private DatabaseReference mRef;
    private ChildEventListener mChildEventListener;
    private ArrayList<Habit> mHabits = new ArrayList<>();

    public FirebaseHabitListAdapter(Class<Habit> modelClass,
                                    int modelLayout,
                                    Class<FirebaseHabitViewHolder> viewHolderClass,
                                    Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mRef = ref.getRef();

    }

    @Override
    protected void populateViewHolder(final FirebaseHabitViewHolder habitViewHolder, Habit habitModel, int position) {
//        habitViewHolder.bindHabit(habitModel);
    }



    @Override
    public void cleanup() {
        super.cleanup();
//        setIndexInFirebase();
//        mRef.removeEventListener(mChildEventListener);
    }

}
