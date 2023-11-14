package tn.esprit.androidproject;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import android.os.Bundle;

import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import android.os.Bundle;

public class AddAnnonceActivity2 extends AppCompatActivity {

    private String[] secteurActiviteChoices = {
            "Sélectionner un secteur d'activité",
            "ENTRAÎNEMENT",
            "RESSOURCES HUMAINES",
            "INGÉNIERIE",
            "COMMERCIALISATION",
            "L'INFORMATIQUE",
            "TÉLÉCOMMUNICATIONS",
            "INDUSTRIE",
            "INFORMATIQUE",
            "COMMERCE",
            "VENTE",
            "TRANSPORT",
            "SCIENCE",
            "RECHERCHE",
            "IMMOBILIER",
            "CONTRÔLE DE QUALITÉ",
            "ACHATS_PROCUEMENT",
            "MÉDICAMENTS",
            "SERVICES CLIENTS",
            "Médias_JOURNALISME",
            "GESTION",
            "LÉGAL",
            "ASSURANCE",
            "INSTALLATION_MAINTENANCE_REPAIR",
            "SANTÉ",
            "CONCEPTION"
    };
    private String[] regionChoices = {
            "Sélectionner une region",
            "Béja",
            "Ben_Arous",
            "Bizerte",
            "Gabes",
            "Gafsa",
            "Jendouba",
            "Kairouan",
            "Kasserine",
            "Kebili",
            "Manouba",
            "Kef",
            "Mahdia",
            "Médenine",
            "Monastir",
            "Nabeul",
            "Sfax",
            "Sidi_Bouzid",
            "Siliana",
            "Sousse",
            "Tataouine",
            "Tozeur",
            "Tunis",
            "Zaghouan"};

    private String[] typeContratChoices = {
            "Sélectionner un type contrat",
            "CDD",
            "CDI",
            "KARAMA",
            "SEASONAL",
            "ALTERNATIION",
            "FREELANCER"};
    private String[] typeOffreChoices = {
            "Sélectionner un type d'offre",
            "emploi"
            , "Stage"};
    private String[] EtatOffreChoices = {
            "Sélectionner une etat d'offre",
            "EXPIRé",
            "EN ATTENTE",
            "PUBLIE"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_annonce2);

        Button validerAnnonceButton = findViewById(R.id.validerAnnonceButton);
        validerAnnonceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText titreEditText = findViewById(R.id.typeAnnonceEditText);
                EditText entrepriseEditText = findViewById(R.id.nameEditText);
                Spinner secteurActiviteSpinner = findViewById(R.id.secteurActiviteSpinner);
                Spinner regionSpinner = findViewById(R.id.regionSpinner);
                Spinner TypecontratSpinner = findViewById(R.id.TypecontratSpinner);

                EditText delaiExpirationEditText = findViewById(R.id.delaiExpirationEditText);
                Spinner typeEmploiSpinner = findViewById(R.id.Typeemploi);
                Spinner etatOffreSpinner = findViewById(R.id.etatoffre);
                EditText descriptionEditText = findViewById(R.id.texteAnnonceEditText);

                // Utilisez les valeurs des champs de formulaire pour créer l'objet Offre
                Offre offre = new Offre(
                        titreEditText.getText().toString(),
                        entrepriseEditText.getText().toString(),
                        secteurActiviteSpinner.getSelectedItem().toString(),
                        regionSpinner.getSelectedItem().toString(),
                        TypecontratSpinner.getSelectedItem().toString(),
                        delaiExpirationEditText.getText().toString(),
                        descriptionEditText.getText().toString(),
                        typeEmploiSpinner.getSelectedItem().toString(),
                        etatOffreSpinner.getSelectedItem().toString()
                );

                ajouterOffreDansBaseDeDonnees(offre);
            }
        });

        Spinner secteurActiviteSpinner = findViewById(R.id.secteurActiviteSpinner);
        Spinner regionSpinner = findViewById(R.id.regionSpinner);
        Spinner typeContratSpinner = findViewById(R.id.TypecontratSpinner);
        Spinner typeEmploiSpinner = findViewById(R.id.Typeemploi);
        Spinner etatOffreSpinner = findViewById(R.id.etatoffre);
        setUpSpinner(secteurActiviteSpinner, secteurActiviteChoices);
        setUpSpinner(typeContratSpinner, typeContratChoices);
        setUpSpinner(regionSpinner, regionChoices);
        setUpSpinner(typeEmploiSpinner, typeOffreChoices);
        setUpSpinner(etatOffreSpinner, EtatOffreChoices);
    }

    private void setUpSpinner(Spinner spinner, String[] choices) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, choices);
        spinner.setAdapter(adapter);
    }

    private void ajouterOffreDansBaseDeDonnees(Offre offre) {
        MaBaseDeDonneesHelper dbHelper = new MaBaseDeDonneesHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(MaBaseDeDonneesHelper.CREER_TABLE_OFFRE);

        SQLiteStatement statement = db.compileStatement("INSERT INTO MaTable " +
                "(titre_offre, nom_entreprise, secteur_activite, region, type_contrat, delai_expiration, Type_Offre, etat_Offre, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

        statement.bindString(1, offre.getTitreOffre());
        statement.bindString(2, offre.getNomEntreprise());
        statement.bindString(3, offre.getSecteurActivite());
        statement.bindString(4, offre.getRegion());
        statement.bindString(5, offre.getTypeContrat());
        statement.bindString(6, offre.getDelaiExpiration());
        statement.bindString(7, offre.getTypeOffre());
        statement.bindString(8, offre.getEtatoffre());
        statement.bindString(9, offre.getDescription());

        long newRowId = statement.executeInsert();

        if (newRowId == -1) {
            Log.e("MaApplication", "Erreur lors de l'insertion de l'offre. ID: " + newRowId);
            Toast.makeText(this, "Erreur lors de l'ajout de l'offre. Veuillez réessayer.", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("MaApplication", "Offre ajoutée avec succès! Nouvelle ligne ID: " + newRowId);
            Toast.makeText(this, "Offre ajoutée avec succès!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(AddAnnonceActivity2.this, ListOffreActivity3.class);
            intent.putExtra("TITRE_OFFRE", offre.getTitreOffre());
            intent.putExtra("NOM_ENTREPRISE", offre.getNomEntreprise());
            intent.putExtra("Secteur_Activite", offre.getSecteurActivite());
            intent.putExtra("REGION", offre.getRegion());
            intent.putExtra("TYPE_CONTRAT", offre.getTypeContrat());
            intent.putExtra("DELAI_EXPIRATION", offre.getDelaiExpiration());
            intent.putExtra("TYPE_OFFRE", offre.getTypeOffre());
            intent.putExtra("ETAT_OFFRE", offre.getEtatoffre());
            intent.putExtra("DESCRIPTION", offre.getDescription());

            startActivity(intent);
        }

        db.close();
    }
}