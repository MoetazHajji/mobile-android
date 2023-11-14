package tn.esprit.androidproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.androidproject.databinding.ActivityListOffre3Binding;
import androidx.recyclerview.widget.LinearLayoutManager;


public class ListOffreActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_offre3);

        TextView titreTextView = findViewById(R.id.titreTextView);
        TextView entrepriseTextView = findViewById(R.id.entrepriseTextView);
        TextView secteurActiviterTextView = findViewById(R.id.secteurActiviteTextView);
        TextView regionTextView = findViewById(R.id.regionTextView);
        TextView typeContratTextView = findViewById(R.id.typeContratTextView);
        TextView delaiExpirationTextView = findViewById(R.id.delaiExpirationTextView);
        TextView typeOffreTextView  = findViewById(R.id.typeoffreTextView);
        TextView etatoffreTextView  = findViewById(R.id.etatoffreTextView);
        TextView descriptionTextView  = findViewById(R.id.descriptionTextView);

        Intent intent = getIntent();
        if (intent != null) {
            String titreOffre = intent.getStringExtra("TITRE_OFFRE");
            String nomEntreprise = intent.getStringExtra("NOM_ENTREPRISE");
            String secteurActivite = intent.getStringExtra("Secteur_Activite");
            String region = intent.getStringExtra("REGION");
            String typeContrat = intent.getStringExtra("TYPE_CONTRAT");
            String delaiExpiration = intent.getStringExtra("DELAI_EXPIRATION");
            String typeOffre = intent.getStringExtra("TYPE_OFFRE");
            String etatOffre = intent.getStringExtra("ETAT_OFFRE");
            String description = intent.getStringExtra("DESCRIPTION");

            // Set the data to TextViews
            titreTextView.setText("Titre de l'offre : " + titreOffre);
            entrepriseTextView.setText("Nom de l'entreprise : " + nomEntreprise);
            secteurActiviterTextView.setText("Secteur Activite : " + secteurActivite);
            regionTextView.setText("Région : " + region);
            typeContratTextView.setText("Type de contrat : " + typeContrat);
            delaiExpirationTextView.setText("Délai d'expiration : " + delaiExpiration);
            typeOffreTextView.setText("Type d'offre : " + typeOffre);
            etatoffreTextView.setText("État de l'offre : " + etatOffre);
            descriptionTextView.setText("Description : " + description);
        }
    }
}