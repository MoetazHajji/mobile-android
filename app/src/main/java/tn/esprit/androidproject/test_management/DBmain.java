package tn.esprit.androidproject.test_management;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBmain extends SQLiteOpenHelper {


    public static final String DBNAME= "test.db";
    public static final String TABLENAME="course";
    public static final int VER=1;
    String query;

    public DBmain(@Nullable Context context) {
        super(context, DBNAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        query=" create table "+TABLENAME+" (id integer primary key, testName text, testDate text, quizImage text) ";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    query = "drop table if exists " +TABLENAME + "" ;
    sqLiteDatabase.execSQL(query);
    onCreate(sqLiteDatabase);
    }
}
