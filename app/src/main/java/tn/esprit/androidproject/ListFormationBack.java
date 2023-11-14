package tn.esprit.androidproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListFormationBack extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button, sertif_button;
    ImageView empty_imageview;
    TextView no_data;

    MyDatabase myDB;
    ArrayList<String> formation_id, formation_titre, formation_description, formation_objectif;
    CustomAdapter customAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_formation_back);
        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        sertif_button = findViewById(R.id.sertif_button);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);
        add_button.setOnClickListener(view -> {
            Intent intent = new Intent(ListFormationBack.this, AddFormat.class);
            startActivity(intent);
        });
        sertif_button.setOnClickListener(view -> {
            Intent intent = new Intent(ListFormationBack.this, ApplyActivity.class);
            startActivity(intent);
        });
        myDB = new MyDatabase(ListFormationBack.this);
        formation_id = new ArrayList<>();
        formation_titre = new ArrayList<>();
        formation_description = new ArrayList<>();
        formation_objectif = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(ListFormationBack.this,this, formation_id, formation_titre, formation_description,
                formation_objectif);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListFormationBack.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                formation_id.add(cursor.getString(0));
                formation_titre.add(cursor.getString(1));
                formation_description.add(cursor.getString(2));
                formation_objectif.add(cursor.getString(3));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("supprimer tous");
        builder.setMessage("Êtes-vous sûr de vouloir supprimer Toutes les formations?");
        builder.setPositiveButton("oui", (dialogInterface, i) -> {
            MyDatabase myDB = new MyDatabase(ListFormationBack.this);
            myDB.deleteAllData();
            //Refresh Activity
            Intent intent = new Intent(ListFormationBack.this, ListFormationBack.class);
            startActivity(intent);
            finish();
        });
        builder.setNegativeButton("Non", (dialogInterface, i) -> {

        });
        builder.create().show();
    }



}