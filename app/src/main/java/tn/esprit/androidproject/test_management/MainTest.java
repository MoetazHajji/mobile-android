package tn.esprit.androidproject.test_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import tn.esprit.androidproject.R;

public class MainTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        RecyclerView testYo = findViewById(R.id.idTestyo);



        // Here, we have created new array list and added data to it
        ArrayList<TestModel> testModelArrayList = new ArrayList<TestModel>();
        testModelArrayList.add(new TestModel(1, "Semaine 1", "quiz 1"));
        testModelArrayList.add(new TestModel(2, "Semaine 2","quiz 1"));
        testModelArrayList.add(new TestModel(3, "Semaine 3", "quiz 1"));
        testModelArrayList.add(new TestModel(4, "Semaine 4", "quiz 1"));
        testModelArrayList.add(new TestModel(5, "Semaine 5", "quiz 1"));
        testModelArrayList.add(new TestModel(6, "Semaine 6", "quiz 1"));
        testModelArrayList.add(new TestModel(7, "Semaine 7","quiz 1"));

        // we are initializing our adapter class and passing our arraylist to it.


        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
       // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        //testYo.setLayoutManager(linearLayoutManager);
        testYo.setLayoutManager(new LinearLayoutManager(this));
        TestAdapter testAdapter = new TestAdapter( this, testModelArrayList);
        testYo.setAdapter(testAdapter);



    }
}