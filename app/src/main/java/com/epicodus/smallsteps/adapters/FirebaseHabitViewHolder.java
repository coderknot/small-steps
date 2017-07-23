package com.epicodus.smallsteps.adapters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.epicodus.smallsteps.R;
import com.epicodus.smallsteps.models.Habit;
import com.epicodus.smallsteps.util.ItemTouchHelperViewHolder;

public class FirebaseHabitViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener /* implements ItemTouchHelperViewHolder */ {
    public ImageView mHabitDragIconImageView;
    View itemView;
    Context context;

    public FirebaseHabitViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.context = itemView.getContext();
    }

    public void bindHabit(Habit habit) {
        TextView habitTitleTextView = (TextView) this.itemView.findViewById(R.id.habitTitleTextView);
//        mHabitDragIconImageView = (ImageView) this.itemView.findViewById(R.id.habitDragIconImageView);

        habitTitleTextView.setText(habit.getTitle());
    }

    @Override
    public void onClick(View v) {
        Log.v("FirebaseHabitViewHolder", "onClick()");
        int position = getLayoutPosition();
        Log.v("FirebaseHabitViewHolder", String.valueOf(position));
    }

//    @Override
//    public void onItemSelected() {
//        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(context,
//                R.animator.drag_scale_on);
//        set.setTarget(itemView);
//        set.start();
//    }
//
//    @Override
//    public void onItemClear() {
//        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(context,
//                R.animator.drag_scale_off);
//        set.setTarget(itemView);
//        set.start();
//    }
}
