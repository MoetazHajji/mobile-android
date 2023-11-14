package tn.esprit.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import tn.esprit.androidproject.Config.UserDatabase;
import tn.esprit.androidproject.ProfileUser.ProfileUserActivity;

public class SignupActivity extends AppCompatActivity {
    Button signupButton;
    EditText firstname_input,lastname_input,username_input,email_input,password_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firstname_input = findViewById(R.id.firstnameInput);
        lastname_input = findViewById(R.id.lastnameInput);
        username_input = findViewById(R.id.usernameInput);
        email_input = findViewById(R.id.emailInput);
        password_input = findViewById(R.id.passwordInput);
        signupButton = findViewById(R.id.signupButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDatabase db = new UserDatabase(SignupActivity.this);
                db.addUser(
                        firstname_input.getText().toString().trim(),
                        lastname_input.getText().toString().trim(),
                        username_input.getText().toString().trim(),
                        email_input.getText().toString().trim(),
                        password_input.getText().toString().trim()
                );
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}