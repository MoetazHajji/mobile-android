package tn.esprit.androidproject.ProfileUser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

import tn.esprit.androidproject.Config.EducationDatabase;
import tn.esprit.androidproject.Config.PositionDatabase;
import tn.esprit.androidproject.R;

public class PositionActivity extends AppCompatActivity implements View.OnClickListener {

    EditText endDate;
    EditText startDate;
    Button addSkill,SaveEducation;
    private int mYear, mMonth, mDay;
    EditText companyName,location,industry,description;
    Spinner title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position);

        endDate=findViewById(R.id.endDateInput);
        startDate=findViewById(R.id.startDateInput);
        addSkill =findViewById(R.id.addSkillButton);
        title = findViewById(R.id.typeSpinner);
        companyName = findViewById(R.id.companyInput);
        location = findViewById(R.id.locationInput);
        industry = findViewById(R.id.industryInput);
        description = findViewById(R.id.descriptionInput);
        SaveEducation = findViewById(R.id.saveButton);

        startDate.setOnClickListener(this);
        endDate.setOnClickListener(this);

        SaveEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PositionDatabase myDB = new PositionDatabase(PositionActivity.this);
                myDB.addPosition(
                        title.getSelectedItem().toString().trim(),
                        companyName.getText().toString().trim(),
                        location.getText().toString().trim(),
                        startDate.getText().toString().trim(),
                        endDate.getText().toString().trim(),
                        industry.getText().toString().trim(),
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