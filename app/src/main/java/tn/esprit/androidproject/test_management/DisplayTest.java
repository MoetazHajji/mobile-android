package tn.esprit.androidproject.test_management;

import static tn.esprit.androidproject.test_management.DBmain.TABLENAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

import tn.esprit.androidproject.R;

public class DisplayTest extends AppCompatActivity {

    DBmain dbmain;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_test);

        dbmain= new DBmain(this);
        findId();
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        
    }

    private void displayData() {
        sqLiteDatabase=dbmain.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select *from "+TABLENAME+"",null);
        ArrayList<TestModel>models=new ArrayList<>();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String testN=cursor.getString(1);
            String testD=cursor.getString(2);
            byte[]quiz=cursor.getBlob(3);
            models.add(new TestModel(id,testN, testD, quiz));
        }
        cursor.close();
        myAdapter= new MyAdapter(this, R.layout.singledata,models,sqLiteDatabase);
        recyclerView.setAdapter(myAdapter);
    }

    private void findId() {
        recyclerView=findViewById(R.id.rv);
    }

}