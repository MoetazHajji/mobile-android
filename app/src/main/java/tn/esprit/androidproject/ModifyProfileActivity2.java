package tn.esprit.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ModifyProfileActivity2 extends AppCompatActivity {

    private EditText prenomEditText;
    private EditText telEditText;
    private EditText nomEntrepriseEditText;
    private EditText secteurActiviteEditText;
    private EditText regionEditText;
    private EditText dateFondationEditText;
    private EditText addressEditText;
    private EditText siteEditText;
    private EditText pwdEditText;
    private MaBaseDeDonneesHelper dbHelper;
    private String displayedEmail;  // Ajout de la variable pour stocker l'e-mail affiché

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_profile2);

        // Initialisation des éléments d'interface utilisateur
        TextView firstNameTextView = findViewById(R.id.firstNameTextView);
        TextView emailTextView = findViewById(R.id.emailTextView);
        TextView phoneTextView = findViewById(R.id.phoneTextView);
        TextView companyTextView = findViewById(R.id.companyTextView);
        TextView datefondationTextView = findViewById(R.id.datefondationTextView);
        TextView addresTextView = findViewById(R.id.addresTextView);
        TextView companyWebsiteTextView = findViewById(R.id.companyWebsiteTextView);
        TextView secteurActiviteTextView = findViewById(R.id.secteurActiviteTextView);
        TextView regionActiviteTextView = findViewById(R.id.regionActiviteTextView);



        displayedEmail = getIntent().getStringExtra("EMAIL");

        // Récupération des données du profil depuis la base de données
        dbHelper = new MaBaseDeDonneesHelper(this);


        // Gestionnaire de clic sur le bouton de mise à jour
        Button updateButton = findViewById(R.id.updateImageBtn);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });
    }

    private void updateProfile() {
        // Obtention des nouvelles données du formulaire
        String newPrenom = prenomEditText.getText().toString();
        String newTel = telEditText.getText().toString();
        String newNomEntreprise = nomEntrepriseEditText.getText().toString();
        String newSecteurActivite = secteurActiviteEditText.getText().toString();
        String newRegion = regionEditText.getText().toString();
        String newDateFondation = dateFondationEditText.getText().toString();
        String newAddress = addressEditText.getText().toString();
        String newSite = siteEditText.getText().toString();

        // Création d'un nouvel objet Profile avec les nouvelles données
        Profile updatedProfile = new Profile(newPrenom, displayedEmail, newTel, "", newNomEntreprise,
                newSecteurActivite, newRegion, newDateFondation, newAddress, newSite);

        // Mise à jour du profil dans la base de données
        boolean success = dbHelper.modifierProfil(updatedProfile);

        // Affichage d'un message en fonction du succès ou de l'échec
        if (success) {
            Toast.makeText(this, "Profil mis à jour avec succès", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Échec de la mise à jour du profil", Toast.LENGTH_SHORT).show();
        }

        // Vous pouvez également ajouter ici une redirection vers l'activité de profil
    }
}