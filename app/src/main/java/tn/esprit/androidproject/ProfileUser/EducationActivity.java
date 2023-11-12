package tn.esprit.androidproject.ProfileUser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import tn.esprit.androidproject.Config.EducationDatabase;
import tn.esprit.androidproject.R;

public class EducationActivity extends AppCompatActivity implements View.OnClickListener{
    EditText endDate;
    EditText startDate;
    EditText school,degree,fieldOfStudy,grade,activities,description;
    Button addSkill,SaveEducation;
    private int mYear, mMonth, mDay;
    private SharedPreferences sharedPreferences;
    private static final String sharedFile ="myPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        endDate=findViewById(R.id.endDateInput);
        startDate=findViewById(R.id.startDateInput);
        addSkill =findViewById(R.id.addSkillButton);
        school = findViewById(R.id.schoolInput);
        degree = findViewById(R.id.degreeInput);
        fieldOfStudy = findViewById(R.id.fieldOfStudyInput);
        grade = findViewById(R.id.gradeInput);
        activities = findViewById(R.id.activitiesInput);
        description = findViewById(R.id.descriptionInput);
        SaveEducation = findViewById(R.id.saveButton);

        startDate.setOnClickListener(this);
        endDate.setOnClickListener(this);
        SaveEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EducationDatabase myDB = new EducationDatabase(EducationActivity.this);
                myDB.addEducation(
                        school.getText().toString().trim(),
                        degree.getText().toString().trim(),
                        fieldOfStudy.getText().toString().trim(),
                        startDate.getText().toString().trim(),
                        endDate.getText().toString().trim(),
                        grade.getText().toString().trim(),
                        activities.getText().toString().trim(),
                        description.getText().toString().trim()
                );
            }
        });


    }

    @Override
    public void onClick(View view){
        if ( view==startDate){
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            startDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (view==endDate ){
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            endDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
}