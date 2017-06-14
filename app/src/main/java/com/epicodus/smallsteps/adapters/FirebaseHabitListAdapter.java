package com.epicodus.smallsteps.adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.epicodus.smallsteps.models.Habit;
import com.epicodus.smallsteps.util.ItemTouchHelperAdapter;
import com.epicodus.smallsteps.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class FirebaseHabitListAdapter extends FirebaseRecyclerAdapter<Habit, FirebaseHabitViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

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
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }
}
