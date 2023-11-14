package tn.esprit.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import tn.esprit.androidproject.Config.UserDatabase;
import tn.esprit.androidproject.ProfileUser.ProfileUserActivity;
import tn.esprit.androidproject.ProfileUser.UserModel;

public class MainActivity extends AppCompatActivity {
    Button login;
    TextView username_input,password_input;
    String username,password;

    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.loginButton);
        signup = findViewById(R.id.signupInput);
        username_input = findViewById(R.id.usernameLogin);
        password_input = findViewById(R.id.passwordLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 username = username_input.getText().toString();
                 password = password_input.getText().toString();
                authenticateUser(username,password);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });


    }

    public boolean authenticateUser(String username, String password) {
        UserDatabase userDatabase = new UserDatabase(MainActivity.this);
        Cursor cursor = userDatabase.getUser(username);

        if (cursor != null && cursor.moveToFirst()) {
            // Retrieve data from the cursor
            int userId = cursor.getInt(0);
            String storedFirstname = cursor.getString(1);
            String storedLastname = cursor.getString(2);
            String storedUsername = cursor.getString(3);
            String storedEmail = cursor.getString(4);
            String storedPassword = cursor.getString(5);

            // Create a UserModel object or use the data as needed
            UserModel user = new UserModel(userId,storedFirstname,storedLastname, storedUsername,storedEmail, storedPassword);
            if (user!=null && user.getPassword().equals(password) && user.getUsername().equals(username)){
                SharedPreferences sharedPreferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("_id",userId);
                editor.putString("username",storedUsername);
                editor.apply();
                // Close the cursor
                cursor.close();
                Toast.makeText(this, "Connected!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ProfileUserActivity.class);
                startActivity(intent);
                return true;
            }else {
                Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}