package tn.esprit.androidproject.ProfileUser;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import tn.esprit.androidproject.Config.PositionAdapter;
import tn.esprit.androidproject.Config.PositionDatabase;
import tn.esprit.androidproject.R;

public class PositionsListActivity extends AppCompatActivity {

    ArrayList<String> _id,pos_title, pos_companyName, pos_startDate, pos_endDate,pos_location,pos_industry,pos_description;
    PositionDatabase positionDB;

    RecyclerView recyclerView;
    PositionAdapter positionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        recyclerView = findViewById(R.id.recyclerView);
        positionDB = new PositionDatabase(PositionsListActivity.this);
        _id = new ArrayList<>();
        pos_title = new ArrayList<>();
        pos_companyName = new ArrayList<>();
        pos_startDate = new ArrayList<>();
        pos_endDate = new ArrayList<>();
        pos_description = new ArrayList<>();
        pos_location = new ArrayList<>();
        pos_industry = new ArrayList<>();


        displayPositionData();
        positionAdapter = new PositionAdapter(PositionsListActivity.this,this,_id, pos_title, pos_companyName, pos_startDate, pos_endDate,pos_location,pos_industry,pos_description);
        recyclerView.setAdapter(positionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(PositionsListActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void displayPositionData() {
        Cursor cursor = positionDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                _id.add(cursor.getString(0));
                pos_title.add(cursor.getString(1));
                pos_companyName.add(cursor.getString(2));
                pos_location.add(cursor.getString(3));
                pos_startDate.add(cursor.getString(4));
                pos_endDate.add(cursor.getString(5));
                pos_industry.add(cursor.getString(6));
                pos_description.add(cursor.getString(7));
            }
        }
    }
}