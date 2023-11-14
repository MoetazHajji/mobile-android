package tn.esprit.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class AddProfileActivity2 extends AppCompatActivity {

    // Définissez vos choix de spinner comme vous l'avez fait pour AddAnnonceActivity2
    private String[] secteurActiviteChoices = {"Sélectionner un secteur d'activité",
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
    private String[] regionChoices = {  "Sélectionner une region",
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);
        // Configurez le bouton de soumission
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText firstNameEditText = findViewById(R.id.firstNameEditText);
                EditText emailEditText = findViewById(R.id.emailEditText);
                EditText phoneEditText = findViewById(R.id.phoneEditText);
                EditText passwordEditText = findViewById(R.id.passwordEditText);
                EditText companyNameEditText = findViewById(R.id.companyNameEditText);
                EditText datefondationEditText = findViewById(R.id.datefondationEditText);
                EditText addressEditText = findViewById(R.id.addressEditText);
                EditText companyWebsiteEditText = findViewById(R.id.companyWebsiteEditText);
                Spinner secteurActiviteSpinner = findViewById(R.id.secteurActiviteSpinner);
                Spinner regionSpinner = findViewById(R.id.regionSpinner);


                Profile profile = new Profile(
                        firstNameEditText.getText().toString(),
                        emailEditText.getText().toString(),
                        phoneEditText.getText().toString(),
                        passwordEditText.getText().toString(),
                        companyNameEditText.getText().toString(),
                        secteurActiviteSpinner.getSelectedItem().toString(),
                        datefondationEditText.getText().toString(),
                        addressEditText.getText().toString(),
                        companyWebsiteEditText.getText().toString(),
                        regionSpinner.getSelectedItem().toString()
                );



                ajouterProfileDansBaseDeDonnees(profile);
            }
        });


        Spinner secteurActiviteSpinner = findViewById(R.id.secteurActiviteSpinner);
        Spinner regionSpinner = findViewById(R.id.regionSpinner);
        setUpSpinner(secteurActiviteSpinner, secteurActiviteChoices);
        setUpSpinner(regionSpinner, regionChoices);
        Log.d("AfficheProfileActivity2", "Activity started");
    }

    private void setUpSpinner(Spinner spinner, String[] choices) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, choices);
        spinner.setAdapter(adapter);
    }

    private void ajouterProfileDansBaseDeDonnees(Profile profile) {
        MaBaseDeDonneesHelper dbHelper = new MaBaseDeDonneesHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.execSQL(MaBaseDeDonneesHelper.CREER_TABLE_PROFILE);

        SQLiteStatement statement = db.compileStatement("INSERT INTO ProfileTable ( prenom,email,tel, pwd, nom_entreprise, " +
                "secteur_activite, region, datefondtion, adress, site) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");




        if (profile != null) {

            statement.bindString(1, profile.getPrenom());
            statement.bindString(2, profile.getEmail());
            statement.bindString(3, profile.getTel());
            statement.bindString(4, profile.getPwd());
            statement.bindString(5, profile.getNom_entreprise());
            statement.bindString(6, profile.getSecteur_activité());
            statement.bindString(7, profile.getRegion());
            statement.bindString(8, profile.getDatefondtion());
            statement.bindString(9, profile.getAdress());
            statement.bindString(10, profile.getSite());
        } else {

            Log.e("AddProfileActivity2", "Le profil est null lors de la liaison des valeurs dans la base de données");

            return;
        }

        long newRowId = statement.executeInsert();

        if (newRowId == -1) {
            Log.e("MaApplication", "Erreur lors de l'insertion de l'offre. ID: " + newRowId);
            Toast.makeText(this, "Erreur lors de l'ajout de l'offre. Veuillez réessayer.", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("MaApplication", "Offre ajoutée avec succès! Nouvelle ligne ID: " + newRowId);
            Toast.makeText(this, "Offre ajoutée avec succès!", Toast.LENGTH_SHORT).show();


            Intent intent = new Intent(AddProfileActivity2.this, AfficheProfileActivity2.class);


            intent.putExtra("PRENOM", profile.getPrenom());
            intent.putExtra("EMAIL", profile.getEmail());
            intent.putExtra("TEL", profile.getTel());
            intent.putExtra("PWD", profile.getPwd());
            intent.putExtra("NOM_ENTREPRISE", profile.getNom_entreprise());
            intent.putExtra("SECTEUR_ACTIVITE", profile.getSecteur_activité());
            intent.putExtra("REGION", profile.getRegion());
            intent.putExtra("DATE_FONDATION", profile.getDatefondtion());
            intent.putExtra("ADRESS", profile.getAdress());
            intent.putExtra("SITE", profile.getSite());
                startActivity(intent);
            startActivity(intent);

            }

        db.close();
    }
}