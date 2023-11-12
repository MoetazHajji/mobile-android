package tn.esprit.androidproject.test_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tn.esprit.androidproject.R;

public class LeMain extends AppCompatActivity {
DBmain dBmain;
SQLiteDatabase sqLiteDatabase;
EditText tname,tdate;
Button submit, display, edit;
int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_le_main);
        dBmain = new DBmain(this);


        //create method
        findid();
        insertData();
    }

    private void insertData() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                cv.put("tname", tname.getText().toString());
                cv.put("tdate", tdate.getText().toString());

                sqLiteDatabase=dBmain.getWritableDatabase();
                Long recinsert= sqLiteDatabase.insert(DBmain.TABLENAME,null,cv);
                if (recinsert!=-1 ) {
                    Toast.makeText(LeMain.this, "Successfully inserted data", Toast.LENGTH_SHORT).show();
                    //clear when click on submit
                    tname.setText("");
                    tdate.setText("");
                }else {
                    Toast.makeText(LeMain.this, "something isn't right, try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findid() {
        tname=(EditText)findViewById(R.id.edit_tname);
        tdate=(EditText)findViewById(R.id.edit_tdate);
        submit=(Button) findViewById(R.id.submit_btn);
        display=(Button)findViewById(R.id.display_btn);
        edit=(Button)findViewById(R.id.edit_btn);
    }
}