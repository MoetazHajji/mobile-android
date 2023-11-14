package tn.esprit.androidproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText Description, Objectife, titre;
    Button update_button, delete_button;

    String id, titree, Objectifee, Descriptione;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        titre = findViewById(R.id.titreU);
        Description = findViewById(R.id.DescriptionU);
        Objectife = findViewById(R.id.ObjectifeU);

        update_button = findViewById(R.id.update_button);

        delete_button = findViewById(R.id.delete_button);
        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
          ab.setTitle(titree);
        }
        update_button.setOnClickListener(view -> {
             MyDatabase myDB = new MyDatabase(UpdateActivity.this);
            titree = titre.getText().toString().trim();
            Descriptione = Description.getText().toString().trim();
            Objectifee = Objectife.getText().toString().trim();
            myDB.updateData(id, titree, Descriptione, Objectifee);      });

        delete_button.setOnClickListener(view -> confirmDialog());

    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("titre") &&
                getIntent().hasExtra("description") && getIntent().hasExtra("objectif")){
            // Récupération des données depuis l'intent
            id = getIntent().getStringExtra("id");
            titree = getIntent().getStringExtra("titre");
            Descriptione = getIntent().getStringExtra("description");
            Objectifee = getIntent().getStringExtra("objectif");

            // Configuration des données de l'intent
            titre.setText(titree);
            Description.setText(Descriptione);
            Objectife.setText(Objectifee);
            Log.d("stev", titree + " " + Descriptione + " " + Objectifee);
        } else {
            Toast.makeText(this, "Pas de données.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Supprimer " + titree + " ?");
        builder.setMessage("Êtes-vous sûr de vouloir supprimer " + titree + " ?");
        builder.setPositiveButton("Oui", (dialogInterface, i) -> {
            MyDatabase myDB = new MyDatabase(UpdateActivity.this);
            myDB.deleteOneRow(id);
            finish();
        });
        builder.setNegativeButton("Non", (dialogInterface, i) -> {
            // Ne rien faire en cas de refus
        });
        builder.create().show();
    }


}