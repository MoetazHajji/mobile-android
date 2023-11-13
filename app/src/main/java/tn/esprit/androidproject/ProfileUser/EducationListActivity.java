package tn.esprit.androidproject.ProfileUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import tn.esprit.androidproject.Config.EducationAdapter;
import tn.esprit.androidproject.Config.EducationDatabase;
import tn.esprit.androidproject.R;

public class EducationListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    EducationDatabase educationDB;

    ArrayList<String> educ_school,educ_degree,educ_startDate,educ_endDate;
    EducationAdapter educationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        recyclerView = findViewById(R.id.recyclerView);
        educationDB = new EducationDatabase(EducationListActivity.this);
        educ_school= new ArrayList<>();
        educ_degree= new ArrayList<>();
        educ_endDate= new ArrayList<>();
        educ_startDate= new ArrayList<>();

        displayEducationData();

        educationAdapter = new EducationAdapter(EducationListActivity.this,educ_school, educ_degree, educ_endDate, educ_startDate);
        recyclerView.setAdapter(educationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(EducationListActivity.this));
    }

    void displayEducationData(){
        Cursor cursor = educationDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                educ_school.add(cursor.getString(1));
                educ_degree.add(cursor.getString(2));
                educ_startDate.add(cursor.getString(4));
                educ_endDate.add(cursor.getString(5));
            }
        }
    }
}