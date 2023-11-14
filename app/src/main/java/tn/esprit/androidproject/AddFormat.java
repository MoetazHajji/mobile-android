package tn.esprit.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import java.sql.Date;

import tn.esprit.androidproject.Activities.SplashScrennActivity;

public class AddFormat extends AppCompatActivity {
     Button buttonPost;


    EditText Description, Objectife, titre;
    CalendarView Datedebut, DateFin;
    Button add_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_format);

        titre = findViewById(R.id.titre);

        Description = findViewById(R.id.Description);
        Objectife = findViewById(R.id.Objectife);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabase myDB = new MyDatabase(AddFormat.this);
                myDB.addBook(titre.getText().toString().trim(),
                        Description.getText().toString().trim(),
                        Objectife.getText().toString().trim());
            }
        });
    }
   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_format);
        buttonPost =(Button) findViewById(R.id.buttonPost);
        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityFormation();
            }
        });
    }*/
   /* public void openActivityFormation(){
        Intent intent = new Intent(this, SplashScrennActivity.class);
        startActivity(intent);
    }*/
    }
