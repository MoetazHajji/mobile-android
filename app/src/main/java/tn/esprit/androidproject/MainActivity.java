package tn.esprit.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import tn.esprit.androidproject.Activities.SplashScrennActivity;

public class MainActivity extends AppCompatActivity {
    private Button loginButton;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            loginButton =(Button) findViewById(R.id.loginButton);
            loginButton.setOnClickListener(view -> openActivityFormation());
        }
        public void openActivityFormation(){
            Intent intent = new Intent(this, ListFormationBack.class);
            startActivity(intent);
        }

}