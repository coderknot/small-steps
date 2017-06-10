package com.epicodus.smallsteps.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.smallsteps.Constants;
import com.epicodus.smallsteps.R;
import com.epicodus.smallsteps.models.Habit;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewHabitActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.newHabitTitleEditText) EditText mNewHabitTitleEditText;
    @Bind(R.id.newHabitCreateButton) Button mNewHabitCreateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit);
        ButterKnife.bind(this);

        mNewHabitCreateButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mNewHabitCreateButton) {
            String newHabitTitle = mNewHabitTitleEditText.getText().toString();

            if(!isValidTitle(newHabitTitle)) return;

            createNewHabit(newHabitTitle);

            Intent habitsListIntent = new Intent(NewHabitActivity.this, HabitsListActivity.class);
//            habitsListIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(habitsListIntent);
//            finish();
        }
    }

    private boolean isValidTitle(String title) {
        if(title.equals("")) {
            mNewHabitTitleEditText.setError("Please enter a title for your new habit.");
            return false;
        }

        return true;
    }

    private void createNewHabit(String habitTitle) {
        Habit newHabit = new Habit(habitTitle);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference habitReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_HABITS)
                .child(uid);

        DatabaseReference pushReference = habitReference.push();
        String pushId = pushReference.getKey();
        newHabit.setPushId(pushId);
        pushReference.setValue(newHabit);
        Toast.makeText(NewHabitActivity.this, "New Habit Saved!", Toast.LENGTH_SHORT).show();
    }
}
