package tn.esprit.androidproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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

public class ModifieOffreActivity2 extends AppCompatActivity {

    private EditText editTitre, editNomEntreprise,
            editDelaiExpiration, editDescription;

    private Spinner editSecteurActivite, editRegion ,editTypeContrat,editTypeOffre,editEtatOffre;
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

    private MaBaseDeDonneesHelper dbHelper;
    private Offre selectedOffre;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifie_offre2);

        dbHelper = new MaBaseDeDonneesHelper(this);

        // Initialize UI components
        initUI();

        // Get the selected offer from the intent
        selectedOffre = getIntent().getParcelableExtra("selectedOffre");

        // Pre-fill the fields with the values of the selected offer
        if (selectedOffre != null) {
            prefillFields(selectedOffre);
        }

        Button validerAnnonceButton = findViewById(R.id.validerAnnonceButton);
        validerAnnonceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update the selected offer with the edited values
                updateSelectedOffre();

                // Update the offer in the database
                dbHelper.updateOffre(selectedOffre);
                finish();  // Close the activity after updating
            }
        });
    }


    private void showToast(String message) {
        Toast.makeText(ModifieOffreActivity2.this, message, Toast.LENGTH_SHORT).show();
    }

    private void initUI() {
        editTitre = findViewById(R.id.typeAnnonceEditText);
        editNomEntreprise = findViewById(R.id.nameEditText);
        editSecteurActivite = findViewById(R.id.secteurActiviteSpinner);
        editRegion = findViewById(R.id.regionSpinner);
        editTypeContrat = findViewById(R.id.TypecontratSpinner);
        editDelaiExpiration = findViewById(R.id.delaiExpirationEditText);
        editTypeOffre = findViewById(R.id.Typeemploi);
        editEtatOffre = findViewById(R.id.etatoffre);
        editDescription = findViewById(R.id.texteAnnonceEditText);

        // Set up Spinners
        setUpSpinner(editSecteurActivite, secteurActiviteChoices);
        setUpSpinner(editRegion, regionChoices);
        setUpSpinner(editTypeContrat, typeContratChoices);
        setUpSpinner(editTypeOffre, typeOffreChoices);
        setUpSpinner(editEtatOffre, EtatOffreChoices);
    }

    private void prefillFields(Offre offre) {
        editTitre.setText(offre.getTitreOffre());
        editNomEntreprise.setText(offre.getNomEntreprise());
        setSpinnerSelection(editSecteurActivite, offre.getSecteurActivite());
        setSpinnerSelection(editRegion, offre.getRegion());
        setSpinnerSelection(editTypeContrat, offre.getTypeContrat());
        editDelaiExpiration.setText(offre.getDelaiExpiration());
        setSpinnerSelection(editTypeOffre, offre.getTypeOffre());
        setSpinnerSelection(editEtatOffre, offre.getEtatoffre());
        editDescription.setText(offre.getDescription());
    }

    private void setUpSpinner(Spinner spinner, String[] choices) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, choices);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    private void updateSelectedOffre() {
        // Update the selected offer with the edited values
        selectedOffre.setTitreOffre(editTitre.getText().toString());
        selectedOffre.setNomEntreprise(editNomEntreprise.getText().toString());
        selectedOffre.setSecteurActivite(editSecteurActivite.getSelectedItem().toString());
        selectedOffre.setRegion(editRegion.getSelectedItem().toString());
        selectedOffre.setTypeContrat(editTypeContrat.getSelectedItem().toString());
        selectedOffre.setDelaiExpiration(editDelaiExpiration.getText().toString());
        selectedOffre.setTypeOffre(editTypeOffre.getSelectedItem().toString());
        selectedOffre.setEtatoffre(editEtatOffre.getSelectedItem().toString());
        selectedOffre.setDescription(editDescription.getText().toString());
    }
    private void setSpinnerSelection(Spinner spinner, String value) {
        if (spinner != null && value != null) {
            ArrayAdapter<String> adapter = (ArrayAdapter<String>) spinner.getAdapter();
            if (adapter != null) {
                int position = adapter.getPosition(value);
                spinner.setSelection(position);
            }
        }
    }
}