package tn.esprit.androidproject.test_management.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import tn.esprit.androidproject.R;
import tn.esprit.androidproject.test_management.models.TestModel;
import tn.esprit.androidproject.test_management.constants.Constants;
import tn.esprit.androidproject.test_management.database.AppDatabase;
import tn.esprit.androidproject.test_management.database.AppExecutors;

public class EditActivity extends AppCompatActivity {

    EditText testNameET, testDateET;
    Button button;
    int tTestId;
    Intent intent;
    private AppDatabase mDb;

    private LinearLayout calendarContainer;
    private CalendarView testDateEdFull;
    private ImageView testDateEdIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ActionBar actionBar = getSupportActionBar();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initViews();
        mDb = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.UPDATE_Test_Id)) {
            button.setText("Update");

            tTestId = intent.getIntExtra(Constants.UPDATE_Test_Id, -1);

           AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    TestModel test = mDb.testDao().loadPersonById(tTestId);
                    populateUI(test);
                }
            }

            );


        }
    }

    private void populateUI(TestModel test) {

        if (test == null) {
            return;
        }
        Date testDate = test.getTestDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // You can choose the desired date format
        String formattedDate = dateFormat.format(testDate);

        testNameET.setText(test.getTestName());
        testDateET.setText(formattedDate);
    }

    private void initViews() {
        testNameET = findViewById(R.id.test_name_ed);
        testDateET = findViewById(R.id.test_date_ed);
        button = findViewById(R.id.button_edit);
        button.setOnClickListener(new View.OnClickListener()
         {
            @Override
            public void onClick(View v) {
                onSaveButtonClicked();
            }
        });

        testDateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendarDialog();
            }
        });
    }

    private void showCalendarDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.calendar_dialog);

        CalendarView calendarView = dialog.findViewById(R.id.calendarView);
        // Set up CalendarView as needed

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // Handle the selected date
                String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                testDateET.setText(selectedDate);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void onSaveButtonClicked() {

//        Date testDate = parseDate(testDateET.getText().toString());
//        final TestModel test = new TestModel(
//
//                testNameET.getText().toString(),
//                testDate
//        );
//
//
//        AppExecutors.getInstance().diskIO().execute(new Runnable() {
//            @Override
//            public void run() {
//                if (!intent.hasExtra(Constants.UPDATE_Test_Id)) {
//                    mDb.testDao().insertTest(test);
//                } else {
//                    test.setIdTest(tTestId);
//                    mDb.testDao().updateTest(test);
//                }
//                finish();
//            }
//        });
        String testName = testNameET.getText().toString().trim();
        String testDateText = testDateET.getText().toString().trim();

        // Check if testName and testDateText are not null or empty
        if (testName.isEmpty() || testDateText.isEmpty()) {
            // Show a Toast message indicating that the fields cannot be empty
            Toast.makeText(EditActivity.this, "Test Name and Test Date cannot be empty", Toast.LENGTH_SHORT).show();
            return; // Exit the method if either attribute is empty
        }

        Date testDate = parseDate(testDateText);

        // Check if testDate is not null
        if (testDate == null) {
            // Show a Toast message indicating that the date is invalid
            Toast.makeText(EditActivity.this, "Invalid Test Date", Toast.LENGTH_SHORT).show();
            return; // Exit the method if the date is null
        }

        final TestModel test = new TestModel(testName, testDate);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (!intent.hasExtra(Constants.UPDATE_Test_Id)) {
                    mDb.testDao().insertTest(test);
                } else {
                    test.setIdTest(tTestId);
                    mDb.testDao().updateTest(test);
                }
                finish();
            }
        });
    }



    private Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // adjust the format as needed
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the exception according to your needs
            return null; // or throw an exception
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}