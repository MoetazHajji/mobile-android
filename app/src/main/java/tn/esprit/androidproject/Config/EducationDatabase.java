package tn.esprit.androidproject.Config;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class EducationDatabase extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME= "Education.db";
    private static final int DATABASE_VERSION= 1;

    private static final String TABLE_NAME= "Education";
    private static final String COLUMN_ID= "_id";
    private static final String COLUMN_SCHOOL= "school";
    private static final String COLUMN_DEGREE= "degree";
    private static final String COLUMN_FIELDOFSTUDY= "fieldOfStudy";
    private static final String COLUMN_STARTDATE= "startDate";
    private static final String COLUMN_ENDDATE= "endDate";
    private static final String COLUMN_GRADE= "grade";
    private static final String COLUMN_ACTIVITIES= "activities";
    private static final String COLUMN_DESCRIPTION= "description";

    public EducationDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE Education" +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COLUMN_SCHOOL + " TEXT, " +
                COLUMN_DEGREE + " TEXT, " +
                COLUMN_FIELDOFSTUDY + " TEXT, " +
                COLUMN_STARTDATE + " TEXT, " +
                COLUMN_ENDDATE + " TEXT, " +
                COLUMN_GRADE + " TEXT, " +
                COLUMN_ACTIVITIES + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT); ";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addEducation(
            String school,
            String degree,
            String fieldOfStudy,
            String startDate,
            String endDate,
            String grade,
            String activities,
            String description
    ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues() ;
        cv.put(COLUMN_SCHOOL,school);
        cv.put(COLUMN_DEGREE,degree);
        cv.put(COLUMN_FIELDOFSTUDY,fieldOfStudy);
        cv.put(COLUMN_STARTDATE,startDate);
        cv.put(COLUMN_ENDDATE,endDate);
        cv.put(COLUMN_GRADE,grade);
        cv.put(COLUMN_ACTIVITIES,activities);
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
        String query = "SELECT * FROM Education";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query,null);

        }
        return cursor;
    }
}
