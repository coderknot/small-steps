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

public class FirebaseHabitListAdapter extends FirebaseRecyclerAdapter<Habit, FirebaseHabitViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Habit> mHabits = new ArrayList<>();

    public FirebaseHabitListAdapter(Class<Habit> modelClass,
                                    int modelLayout,
                                    Class<FirebaseHabitViewHolder> viewHolderClass,
                                    Query ref,
                                    OnStartDragListener onStartDragListener,
                                    Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.mRef = ref.getRef();
        this.mOnStartDragListener = onStartDragListener;
        this.mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mHabits.add(dataSnapshot.getValue(Habit.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void populateViewHolder(final FirebaseHabitViewHolder habitViewHolder, Habit habitModel, int position) {
        habitViewHolder.bindHabit(habitModel);
        habitViewHolder.mHabitDragIconImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(MotionEventCompat.getActionMasked(motionEvent) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(habitViewHolder);
                }

                return false;
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mHabits, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mHabits.remove(position);
        getRef(position).removeValue();
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }

    private void setIndexInFirebase() {
        for(Habit habit : mHabits) {
            int index = mHabits.indexOf(habit);
            DatabaseReference ref = getRef(index);
            habit.setIndex(Integer.toString(index));
            ref.setValue(habit);
        }
    }
}
