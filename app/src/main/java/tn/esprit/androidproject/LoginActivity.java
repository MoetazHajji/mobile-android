package tn.esprit.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import tn.esprit.androidproject.test_management.activities.MainTest;
import tn.esprit.androidproject.test_management.activities.DaMainActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get entered credentials
                String enteredUsername = usernameEditText.getText().toString().trim();
                String enteredPassword = passwordEditText.getText().toString().trim();

                // Check if credentials match the allowed credentials
                if (isValidCredentials(enteredUsername, enteredPassword)) {
                    // If credentials are valid, open DaMainActivity
                    openDaMainActivity();
                } else {
                    // If credentials are invalid, open MainTest
                    openMainTest();
                }
            }
            });
        }

    private boolean isValidCredentials(String username, String password) {
        // Check if entered credentials match the allowed credentials
        return "admin".equals(username) && "admin".equals(password);
    }

    private void openDaMainActivity() {
        Intent intent = new Intent(LoginActivity.this, DaMainActivity.class);
        startActivity(intent);
        finish(); // Optional: Close the LoginActivity so the user can't go back to it
    }

    private void openMainTest() {
        Intent intent = new Intent(LoginActivity.this, MainTest.class);
        startActivity(intent);
        finish(); // Optional: Close the LoginActivity so the user can't go back to it
    }



}
