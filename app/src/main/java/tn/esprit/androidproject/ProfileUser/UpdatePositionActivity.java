package tn.esprit.androidproject.ProfileUser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import tn.esprit.androidproject.Config.PositionDatabase;
import tn.esprit.androidproject.R;

public class UpdatePositionActivity extends AppCompatActivity implements View.OnClickListener{

    EditText endDate_input;
    EditText startDate_input;

    Button updateEducation,delete_button;

    private int mYear, mMonth, mDay;
    EditText companyName_input,location_input,industry_input,description_input;
    String id,title,companyName,location,startDate,endDate,industry,description;
    Spinner title_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_position);

//        startDate_input.setOnClickListener(this);
//        endDate_input.setOnClickListener(this);

        endDate_input=findViewById(R.id.endDateInputU);
        startDate_input=findViewById(R.id.startDateInputU);
        title_spinner = findViewById(R.id.typeSpinnerU);
        companyName_input = findViewById(R.id.companyInputU);
        location_input = findViewById(R.id.locationInputU);
        industry_input = findViewById(R.id.industryInputU);
        description_input = findViewById(R.id.descriptionInputU);
        updateEducation = findViewById(R.id.updateButton);
        delete_button = findViewById(R.id.deletePosition);

        getAndSetIntentData();
        updateEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PositionDatabase positionDB = new PositionDatabase(UpdatePositionActivity.this);
                positionDB.updateData(id,title,companyName,location,startDate,endDate,industry,description);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });


    }

    @Override
    public void onClick(View view){
        if ( view==startDate_input){
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            startDate_input.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (view==endDate_input ){
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            endDate_input.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("_id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("companyName") && getIntent().hasExtra("location") &&
                getIntent().hasExtra("startDate") && getIntent().hasExtra("endDate") &&
                getIntent().hasExtra("industry") && getIntent().hasExtra("description")
        ){
            //Getting Data from Intent
            id = getIntent().getStringExtra("_id");
            title = getIntent().getStringExtra("title");
            companyName = getIntent().getStringExtra("companyName");
            location = getIntent().getStringExtra("location");
            startDate = getIntent().getStringExtra("startDate");
            endDate = getIntent().getStringExtra("endDate");
            industry = getIntent().getStringExtra("industry");
            description = getIntent().getStringExtra("description");

            //Setting Intent Data
            companyName_input.setText(companyName);
            location_input.setText(location);
            startDate_input.setText(startDate);
            endDate_input.setText(endDate);
            industry_input.setText(industry);
            description_input.setText(description);
            Log.d("Dev", title+" "+companyName+" "+location+" "+ industry+" "+description);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PositionDatabase myDB = new PositionDatabase(UpdatePositionActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}