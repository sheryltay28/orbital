package com.orbital.saveme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orbital.saveme.adapter.TransactionAdapter;
import com.orbital.saveme.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StatsIncomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnDate, btnIncome, btnExpenditure;
    private ImageButton btnBack;
    private TransactionAdapter transactionAdapter;
    private List<Transaction> mTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_income);

        setUpViews();

        mTransaction = new ArrayList<>();

        readExpenditure();

        clickDate();
        clickExpenditure();
        clickBack();
    }

    public void setUpViews() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnDate = findViewById(R.id.byDate);
        btnIncome = findViewById(R.id.byIncome);
        btnExpenditure = findViewById(R.id.byExpenditure);
        btnBack = findViewById(R.id.btnPet);
    }

    private void readExpenditure() {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mReference = FirebaseDatabase.getInstance()
                .getReference(firebaseUser.getUid()).child("TRANSACTIONS");

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mTransaction.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Transaction transaction = snapshot.getValue(Transaction.class);

                    if (transaction.getTransactionType().equals("INCOME")) {
                        mTransaction.add(transaction);
                    }
                }

                transactionAdapter = new TransactionAdapter(getApplicationContext(), mTransaction);
                recyclerView.setAdapter(transactionAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void clickDate() {
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                openStatsDateActivity();
            }
        });
    }

    public void openStatsDateActivity() {
        Intent i = new Intent(StatsIncomeActivity.this, StatisticsActivity.class);
        startActivity(i);
    }

    private void clickExpenditure() {
        btnExpenditure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                openStatsExpenditureActivity();
            }
        });
    }

    public void openStatsExpenditureActivity() {
        Intent i = new Intent(StatsIncomeActivity.this, StatsExpenditureActivity.class);
        startActivity(i);
    }

    private void clickBack() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                openHomePageActivity();
            }
        });
    }

    public void openHomePageActivity() {
        Intent i = new Intent(StatsIncomeActivity.this, HomePageActivity.class);
        startActivity(i);
    }
}
