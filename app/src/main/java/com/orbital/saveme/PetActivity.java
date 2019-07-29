package com.orbital.saveme;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orbital.saveme.model.Progress;
import com.orbital.saveme.model.User;

public class PetActivity extends AppCompatActivity {
    private Button btnFeed;
    private ProgressBar pgrsBarRabbit;
    private DatabaseReference mReference;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        setUpViews();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mReference = FirebaseDatabase.getInstance().getReference(mUser.getUid()).child("INFORMATION");
        mReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                double targetAmount = user.getSavingsBudget();
                double expenditure = user.getExpenditure();
                if (targetAmount < expenditure) { //if spend more than target amount user cant feed the pet
                    reduceProgress();
                    btnFeed.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(PetActivity.this, "Oops you spent more than your target amount today " +
                                    "so your pet will have to go hungry :(", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    btnFeed.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            feedPet();
                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void setUpViews() {
        btnFeed = findViewById(R.id.btnFeed);
        pgrsBarRabbit = findViewById(R.id.pgrsBarRabbit);
    }

    private void feedPet() {
        Progress progress = new Progress();
        mReference.child(mUser.getUid()).child("Progress").setValue(progress);
        pgrsBarRabbit.setProgress(100);
        Toast.makeText(PetActivity.this, "Yay you successfully fed your pet!!", Toast.LENGTH_SHORT).show();
    }

    private void reduceProgress() {
        DatabaseReference petProgress = mReference.child(mUser.getUid()).child("Progress");
        petProgress.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Progress progress = dataSnapshot.getValue(Progress.class);
                int howFed = progress.getProgress() - 20;
                progress.setProgress(howFed);
                mReference.child(mUser.getUid()).child("Progress").setValue(progress);
                pgrsBarRabbit.setProgress(howFed);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
