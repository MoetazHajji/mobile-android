package tn.esprit.androidproject.test_management.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.androidproject.LoginActivity;
import tn.esprit.androidproject.R;
import tn.esprit.androidproject.test_management.Test;
import tn.esprit.androidproject.test_management.adaptors.TestAdapter;
import tn.esprit.androidproject.test_management.adaptors.TestAdaptor;
import tn.esprit.androidproject.test_management.database.AppDatabase;
import tn.esprit.androidproject.test_management.database.AppExecutors;
import tn.esprit.androidproject.test_management.models.TestModel;

public class MainTest extends AppCompatActivity {

    private EditText searchEditText;
    private TestAdaptor adapter;
    private RecyclerView mRecyclerView;
    private AppDatabase mDb;


    ImageButton logoutButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_test);
        RecyclerView testYo = findViewById(R.id.idTestyo);
       searchEditText = findViewById(R.id.search);
        logoutButton = findViewById(R.id.logoutButton); // Initialize the logout button


        mRecyclerView = findViewById(R.id.idTestyo);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter and attach it to the RecyclerView
        adapter = new TestAdaptor(this);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new TestAdaptor.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainTest.this, Test.class);
                startActivity(intent);
            }
        });
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Filter the list as the user types in the search EditText
                filterTests(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not needed for this implementation
            }
        });

        mDb = AppDatabase.getInstance(getApplicationContext());

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle logout action here
                showLogoutConfirmationDialog();
            }
        });

        retrieveTasks();
    }

    private void filterTests(String searchText) {
        // Retrieve all tests from the database
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<TestModel> allTests = mDb.testDao().loadAllTests();

                // Filter the tests based on the search query
                List<TestModel> filteredTests = new ArrayList<>();
                for (TestModel test : allTests) {
                    if (test.getTestName().toLowerCase().contains(searchText.toLowerCase())) {
                        filteredTests.add(test);
                    }
                }

                // Update the UI with the filtered tests
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setTasks(filteredTests);
                    }
                });
            }
        });
    }


    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // User clicked the "Logout" button
                logout();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // User clicked the "Cancel" button, do nothing
            }
        });
        builder.show();
    }


    private void logout() {
        // Implement the logout logic here, such as clearing user session, updating preferences, etc.
        // You might want to redirect the user to the login screen or perform any necessary cleanup.
        // For example:
        Intent intent = new Intent(MainTest.this, LoginActivity.class);
        startActivity(intent);
        finish(); // Finish the current activity to prevent going back to it using the back button
    }

    private void retrieveTasks() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<TestModel> tests = mDb.testDao().loadAllTests();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        adapter.setTasks(tests);
                    }
                });
            }
        });


    }

}