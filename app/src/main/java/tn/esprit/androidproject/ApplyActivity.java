package tn.esprit.androidproject;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ApplyActivity extends AppCompatActivity {
    CardView net,nit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        //getSupportActionBar().hide();
        net= findViewById(R.id.net);
        nit= findViewById(R.id.nit);
        net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ApplyActivity.this, SetsActivity.class);
                startActivity(intent);
            }
        });

        
    }
}