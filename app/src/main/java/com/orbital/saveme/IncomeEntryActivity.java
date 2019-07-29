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
import com.orbital.saveme.model.Transaction;
import com.orbital.saveme.model.User;

import java.util.Date;

public class IncomeEntryActivity extends AppCompatActivity {

    private EditText etIncomeType;
    private EditText etAmount;
    private Button btnSubmit;

    private FirebaseUser mUser;
    private DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_entry);

        setUpBtn();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    private void setUpBtn() {
        etIncomeType = findViewById(R.id.etIncomeType);
        etAmount = findViewById(R.id.etAmount);
        btnSubmit = findViewById(R.id.btnSubmit);

    }

    private void submit() {
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mReference = FirebaseDatabase.getInstance().getReference();

        String incomeType = etIncomeType.getText().toString();
        final double amount = Double.parseDouble(etAmount.getText().toString());
        final Date date = new Date();
        final Transaction incomeT = new Transaction("INCOME", incomeType, amount, date);

        final DatabaseReference iReference = mReference.child(mUser.getUid()).child("INFORMATION");
        iReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                double income = user.getIncome();
                income += amount;
                user.setIncome(income);
                iReference.setValue(user);
                mReference.child(mUser.getUid()).child("TRANSACTIONS").child(Long.toString(date.getTime()))
                        .setValue(incomeT);
                startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
                finish();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
