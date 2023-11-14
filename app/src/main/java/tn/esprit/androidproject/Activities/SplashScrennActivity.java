package tn.esprit.androidproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import tn.esprit.androidproject.AddFormationActivity;
import tn.esprit.androidproject.ApplyActivity;
import tn.esprit.androidproject.R;

public class SplashScrennActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screnn);

      //getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intenttt = new Intent(SplashScrennActivity.this, ApplyActivity.class);
                startActivity(intenttt);
            }
        },2000);
    }
}