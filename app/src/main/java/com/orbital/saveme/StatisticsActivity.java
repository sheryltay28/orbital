package com.orbital.saveme;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orbital.saveme.adapter.ExpenditureAdapter;
import com.orbital.saveme.model.Expenditure;

import java.util.ArrayList;
import java.util.List;

public class StatisticsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ExpenditureAdapter expenditureAdapter;
    private List<Expenditure> mExpenditure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExpenditure = new ArrayList<>();

        readExpenditure();
    }

    private void readExpenditure() {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mReference = FirebaseDatabase.getInstance()
                .getReference("expenditures").child(firebaseUser.getUid());

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mExpenditure.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Expenditure expenditure = snapshot.getValue(Expenditure.class);
                    mExpenditure.add(expenditure);
                }
                expenditureAdapter = new ExpenditureAdapter(getApplicationContext(), mExpenditure);
                recyclerView.setAdapter(expenditureAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
