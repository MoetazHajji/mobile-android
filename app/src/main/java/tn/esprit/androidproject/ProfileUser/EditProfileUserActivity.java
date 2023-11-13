package tn.esprit.androidproject.ProfileUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tn.esprit.androidproject.Config.EducationDatabase;
import tn.esprit.androidproject.Config.PositionDatabase;
import tn.esprit.androidproject.R;

public class EditProfileUserActivity extends AppCompatActivity {
    TextView addEducation;
    TextView addPosition ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        addEducation = findViewById(R.id.addEducation);
        addPosition = findViewById(R.id.addPosition);

        addEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfileUserActivity.this, EducationActivity.class);
                startActivity(intent);
            }
        });
        addPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfileUserActivity.this, PositionActivity.class);
                startActivity(intent);
            }
        });
    }

}