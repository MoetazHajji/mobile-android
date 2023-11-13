package tn.esprit.androidproject.ProfileUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import tn.esprit.androidproject.Config.PositionAdapter;
import tn.esprit.androidproject.Config.PositionDatabase;
import tn.esprit.androidproject.R;

public class PositionsListActivity extends AppCompatActivity {

    ArrayList<String> pos_title, pos_companyName, pos_startDate, pos_endDate;
    PositionDatabase positionDB;

    RecyclerView recyclerView;
    PositionAdapter positionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        recyclerView = findViewById(R.id.recyclerView);
        positionDB = new PositionDatabase(PositionsListActivity.this);
        pos_title = new ArrayList<>();
        pos_companyName = new ArrayList<>();
        pos_startDate = new ArrayList<>();
        pos_endDate = new ArrayList<>();

        displayPositionData();
        positionAdapter = new PositionAdapter(PositionsListActivity.this, pos_title, pos_companyName, pos_startDate, pos_endDate);
        recyclerView.setAdapter(positionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(PositionsListActivity.this));

    }

    void displayPositionData() {
        Cursor cursor = positionDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                pos_title.add(cursor.getString(1));
                pos_companyName.add(cursor.getString(2));
                pos_startDate.add(cursor.getString(4));
                pos_endDate.add(cursor.getString(5));
            }
        }
    }
}