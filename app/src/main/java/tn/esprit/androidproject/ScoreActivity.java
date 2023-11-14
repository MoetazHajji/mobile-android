package tn.esprit.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import tn.esprit.androidproject.databinding.ActivityScoreBinding;

public class ScoreActivity extends AppCompatActivity {
ActivityScoreBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityScoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int totalScore= getIntent().getIntExtra("total",0);
        int correctAnsw= getIntent().getIntExtra("score",0);
        int wrong = totalScore - correctAnsw;
        binding.totalQuations.setText(String.valueOf( totalScore));
        binding.rightAnsw.setText(String.valueOf( correctAnsw ));
        binding.wrongAnsw.setText(String.valueOf(wrong));



    }
}