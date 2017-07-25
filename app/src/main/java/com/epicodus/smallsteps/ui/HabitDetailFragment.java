package com.epicodus.smallsteps.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.smallsteps.R;
import com.epicodus.smallsteps.models.Habit;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HabitDetailFragment extends Fragment {
    Habit habit;

    @Bind(R.id.habitDetailTitleTextView) TextView mHabitDetailTitleTextView;
    @Bind(R.id.habitDetailReasonTextView) TextView mHabitDetailReasonTextView;

    public HabitDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        habit = Parcels.unwrap(getArguments().getParcelable("Habit"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habit_detail, container, false);
        ButterKnife.bind(this, view);

        if(getActivity() instanceof Main2Activity) {
            ((Main2Activity) getActivity()).hideFloatingActionButton();
        }

        mHabitDetailTitleTextView.setText(habit.getTitle());
        mHabitDetailReasonTextView.setText(habit.getReason());

        // Inflate the layout for this fragment
        return view;
    }

}
