package com.epicodus.smallsteps.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.epicodus.smallsteps.R;
import com.epicodus.smallsteps.models.Habit;

import org.parceler.Parcels;

public class HabitDetailFragment extends Fragment {
    Habit habit;

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

        if(getActivity() instanceof Main2Activity) {
            ((Main2Activity) getActivity()).hideFloatingActionButton();
        }

        Toast.makeText(getActivity(), "Passed Habit: " + habit.getTitle(), Toast.LENGTH_SHORT).show();


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_habit_detail, container, false);
    }

}
