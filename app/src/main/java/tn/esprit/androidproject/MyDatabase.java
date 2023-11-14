package tn.esprit.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Date;

class MyDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "FormationDb.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Formation";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_Titre = "Titre";
    private static final String COLUMN_Description = "Description";
    private static final String COLUMN_OBJECTIF = "OBJECTIF";
    MyDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_Titre + " TEXT, " +
                COLUMN_Description + " TEXT, " +
                COLUMN_OBJECTIF + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addBook(String titre, String description, String objectif){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_Titre, titre);
        cv.put(COLUMN_Description, description);
        cv.put(COLUMN_OBJECTIF, objectif);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Échec", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "avec succès", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

     void updateData(String row_id, String titre, String description, String objectif){
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues cv = new ContentValues();
         cv.put(COLUMN_Titre, titre);
         cv.put(COLUMN_Description, description);
         cv.put(COLUMN_OBJECTIF, objectif);

         long result = db.update(TABLE_NAME, cv, COLUMN_ID + "=?", new String[]{row_id});
         if(result == -1){
             Toast.makeText(context, "Échec", Toast.LENGTH_SHORT).show();
         } else {
             Toast.makeText(context, "Mis à jour avec succès !", Toast.LENGTH_SHORT).show();
         }
     }


    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Échec.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "supprimer avec succès.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
