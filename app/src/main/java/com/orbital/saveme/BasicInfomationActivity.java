package com.orbital.saveme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orbital.saveme.model.User;

public class BasicInfomationActivity extends AppCompatActivity {
    private EditText etExpenditureBudget, etSavingBudget;
    private Button doneBtn;

    private FirebaseUser mUser;
    private DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_infomation);

        setUpBtn();

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                done();
            }
        });
    }

    public void setUpBtn() {
        etExpenditureBudget = findViewById(R.id.etExpenditureBudget);
        etSavingBudget = findViewById(R.id.etSavingsBudget);
        doneBtn = findViewById(R.id.btnDone);
    }

    public void done() {
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mReference = FirebaseDatabase.getInstance().getReference(mUser.getUid()).child("INFORMATION");

        mReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                double expenditureBudget = Double.parseDouble(etExpenditureBudget.getText().toString());
                double savingBudget = Double.parseDouble(etSavingBudget.getText().toString());
                user.setSavingsBudget(savingBudget);
                user.setSpendingBudget(expenditureBudget);
                mReference.setValue(user);
                startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
