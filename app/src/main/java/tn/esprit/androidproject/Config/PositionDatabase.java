package tn.esprit.androidproject.Config;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class PositionDatabase extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME= "Position.db";
    private static final int DATABASE_VERSION= 1;

    private static final String TABLE_NAME= "Position";
    private static final String COLUMN_ID= "_id";
    private static final String COLUMN_TITLE= "title";
    private static final String COLUMN_COMPANYNAME= "companyName";
    private static final String COLUMN_LOCATION= "location";
    private static final String COLUMN_STARTDATE= "startDate";
    private static final String COLUMN_ENDDATE= "endDate";
    private static final String COLUMN_INDUSTRY= "industry";
    private static final String COLUMN_DESCRIPTION= "description";

    public PositionDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE Position" +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_COMPANYNAME+ " TEXT, " +
                COLUMN_LOCATION + " TEXT, " +
                COLUMN_STARTDATE + " TEXT, " +
                COLUMN_ENDDATE + " TEXT, " +
                COLUMN_INDUSTRY + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT); ";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addPosition(
            String school,
            String degree,
            String fieldOfStudy,
            String startDate,
            String endDate,
            String grade,
            String description
    ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues() ;
        cv.put(COLUMN_TITLE,school);
        cv.put(COLUMN_COMPANYNAME,degree);
        cv.put(COLUMN_LOCATION,fieldOfStudy);
        cv.put(COLUMN_STARTDATE,startDate);
        cv.put(COLUMN_ENDDATE,endDate);
        cv.put(COLUMN_INDUSTRY,grade);
        cv.put(COLUMN_DESCRIPTION,description);
        long result = db.insert(TABLE_NAME,null,cv);
        if (result == -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
            System.out.println("failed");
        } else {
            Toast.makeText(context,"Added Successfully!",Toast.LENGTH_SHORT).show();
            System.out.println("success");
        }
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM Position";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query,null);

        }
        System.out.println("Data :" + cursor);
        return cursor;
    }
}
