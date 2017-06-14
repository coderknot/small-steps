package com.epicodus.smallsteps.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.smallsteps.R;
import com.epicodus.smallsteps.models.Habit;

public class FirebaseHabitViewHolder extends RecyclerView.ViewHolder {
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
        mHabitDragIconImageView = (ImageView) this.itemView.findViewById(R.id.habitDragIconImageView);

        habitTitleTextView.setText(habit.getTitle());
    }
}
