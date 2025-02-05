package com.orbital.saveme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class IncomeExpenditureHomepageActivity extends AppCompatActivity {
    private ImageButton incomeButton, expenditureButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_expenditure_homepage);

        setUpBtn();
        clickBack();
        clickIncome();
        clickExpenditure();
    }

    public void setUpBtn() {
        incomeButton = findViewById(R.id.btnIncome);
        expenditureButton = findViewById(R.id.btnExpenditure);
    }

    public void clickBack() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent i = new Intent(IncomeExpenditureHomepageActivity.this,
                HomePageActivity.class);
        startActivityForResult(i, 0);
        return true;
    }

    public void clickIncome() {
        incomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIncomeEntryPage();
            }
        });
    }

    public void openIncomeEntryPage() {
        Intent i = new Intent(IncomeExpenditureHomepageActivity.this,
                IncomeEntryActivity.class);
        startActivity(i);
    }

    public void clickExpenditure() {
        expenditureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExpenditureEntryPage();
            }
        });
    }

    public void openExpenditureEntryPage() {
        Intent i = new Intent(IncomeExpenditureHomepageActivity.this,
                ExpenditureEntryActivity.class);
        startActivity(i);
    }

}
