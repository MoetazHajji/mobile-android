package tn.esprit.androidproject.Config;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import tn.esprit.androidproject.ProfileUser.UserModel;

public class UserDatabase extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME= "User.db";
    private static final int DATABASE_VERSION= 1;

    private static final String TABLE_NAME= "User";
    private static final String COLUMN_ID= "_id";
    private static final String COLUMN_FIRSTNAME= "firstname";
    private static final String COLUMN_LASTNAME= "lastname";
    private static final String COLUMN_USERNAME= "username";
    private static final String COLUMN_EMAIL= "email";
    private static final String COLUMN_PASSWORD= "password";

    public UserDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE User" +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COLUMN_FIRSTNAME + " TEXT, " +
                COLUMN_LASTNAME+ " TEXT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PASSWORD + " TEXT); ";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addUser(
            String firstname,
            String lastname,
            String username,
            String email,
            String password
    ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues() ;
        cv.put(COLUMN_FIRSTNAME,firstname);
        cv.put(COLUMN_LASTNAME,lastname);
        cv.put(COLUMN_USERNAME,username);
        cv.put(COLUMN_EMAIL,email);
        cv.put(COLUMN_PASSWORD,password);
        long result = db.insert("User",null,cv);
        if (result == -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
            System.out.println("failed");
        } else {
            Toast.makeText(context,"Added Successfully!",Toast.LENGTH_SHORT).show();
            System.out.println("success");
        }
    }

    public Cursor getUser(String username){
        String query = "SELECT * FROM User where username=?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query,new String[]{username});
        }
        System.out.println("Data :" + cursor);
        return cursor;
    }
}
