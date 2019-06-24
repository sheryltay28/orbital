package com.orbital.saveme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomePageActivity extends AppCompatActivity {
    private ImageButton petButton, incomeExpenditureButton, menuBarButton, statisticsButton;
    private Button petText, incomeExpenditureText, menuBarText, statisticsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        setUpBtn();

        clickPet();
        clickIncomeExpenditure();
        clickMenuBar();
        clickStatistics();
    }

    public void setUpBtn() {
        petButton = findViewById(R.id.btnRabbit);
        petText = findViewById(R.id.textPet);
        incomeExpenditureButton = findViewById(R.id.btnIncomeExpenditure);
        incomeExpenditureText = findViewById(R.id.textIncomeExpenditure);
        menuBarButton = findViewById(R.id.btnMenuBar);
        menuBarText = findViewById(R.id.textMenuBar);
        statisticsButton = findViewById(R.id.btnStatistics);
        statisticsText = findViewById(R.id.textStatistics);
    }

    public void clickPet() {
        petButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPetActivity();
            }
        });

        petText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPetActivity();
            }
        });
    }

    public void openPetActivity() {
        Intent i = new Intent(HomePageActivity.this, PetActivity.class);
        startActivity(i);
    }

    public void clickIncomeExpenditure() {
        incomeExpenditureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIncomeExpenditure();
            }
        });

        incomeExpenditureText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIncomeExpenditure();
            }
        });
    }

    public void openIncomeExpenditure() {
        Intent i = new Intent(HomePageActivity.this,
                IncomeExpenditureHomepageActivity.class);
        startActivity(i);
    }

    public void clickMenuBar() {
        menuBarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenuBar();
            }
        });

        menuBarText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenuBar();
            }
        });
    }

    public void openMenuBar() {
        Intent i = new Intent(HomePageActivity.this, MenuBarActivity.class);
        startActivity(i);
    }

    public void clickStatistics() {
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatistics();
            }
        });
        statisticsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatistics();
            }
        });
    }

    public void openStatistics() {
        Intent i = new Intent(HomePageActivity.this, StatisticsActivity.class);
        startActivity(i);
    }
}