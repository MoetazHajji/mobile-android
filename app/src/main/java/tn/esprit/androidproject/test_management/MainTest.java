package tn.esprit.androidproject.test_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;

import tn.esprit.androidproject.R;

public class MainTest extends AppCompatActivity {

    private EditText searchEditText;
    private TestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        RecyclerView testYo = findViewById(R.id.idTestyo);
        searchEditText = findViewById(R.id.search);

//        searchEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//
////            @Override
////            public void onTextChanged(CharSequence s, int start, int before, int count) {
////                adapter.getFilter().filter(s);
////            }
//
//            @Override
//            public void afterTextChanged(Editable s) {}
//        });

        // Here, we have created new array list and added data to it
        ArrayList<TestModel> testModelArrayList = new ArrayList<TestModel>();
        testModelArrayList.add(new TestModel(1, "Semaine 1","quiz 1"));
        testModelArrayList.add(new TestModel(2, "Semaine 2","quiz 2"));
        testModelArrayList.add(new TestModel(3, "Semaine 3", "quiz 3"));
        testModelArrayList.add(new TestModel(4, "Semaine 4", "quiz 4"));
        testModelArrayList.add(new TestModel(5, "Semaine 5", "quiz 5"));
        testModelArrayList.add(new TestModel(6, "Semaine 6", "quiz 6"));
        testModelArrayList.add(new TestModel(7, "Semaine 7","quiz 7"));

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