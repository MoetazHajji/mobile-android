package tn.esprit.androidproject.ProfileUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tn.esprit.androidproject.R;

public class ProfileUserActivity extends AppCompatActivity {
    Button editProfile;
    TextView changePassword;
    TextView ViewEducationList,ViewPositionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        editProfile = findViewById(R.id.editProfileButton);
        changePassword = findViewById(R.id.changePwd);
        ViewEducationList = findViewById(R.id.viewEducationListText);
        ViewPositionList = findViewById(R.id.viewPositionList);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileUserActivity.this, EditProfileUserActivity.class);
                startActivity(intent);
            }
        });
        ViewEducationList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileUserActivity.this, EducationListActivity.class);
                startActivity(intent);
            }
        });
        ViewPositionList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileUserActivity.this, PositionsListActivity.class);
                startActivity(intent);
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileUserActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}