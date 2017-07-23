package com.epicodus.smallsteps.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.smallsteps.R;

public class FirebaseHabitViewHolder extends RecyclerView.ViewHolder {
    private FirebaseHabitViewHolder.ClickListener mClickListener;
    public TextView habitTitleTextView;

    public FirebaseHabitViewHolder(View itemView) {
        super(itemView);

        habitTitleTextView = (TextView) itemView.findViewById(R.id.habitTitleTextView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        });
    }

    public interface ClickListener {
        public void onItemClick(View v, int position);
    }

    public void setOnClickListener(FirebaseHabitViewHolder.ClickListener clickListener) {
        mClickListener = clickListener;
    }

}
