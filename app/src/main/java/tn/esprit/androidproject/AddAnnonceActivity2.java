package tn.esprit.androidproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class AddAnnonceActivity2 extends AppCompatActivity {




    private ImageView companyLogoImageView;
    private Button selectLogoButton;

    private String selectedDate;
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_annonce2);

        selectLogoButton = findViewById(R.id.selectLogoButton);
        selectLogoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        Button validerButton = findViewById(R.id.validerAnnonceButton);
        validerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AddAnnonceActivity2.this);
                builder.setMessage("Êtes-vous sûr de vouloir valider?");
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(AddAnnonceActivity2.this, "Félicitations Votre Offre est Publié", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(AddAnnonceActivity2.this, "OK", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        ImageView profileLogo = findViewById(R.id.profileLogo);

        profileLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAnnonceActivity2.this, AfficheProfileActivity2.class);
                startActivity(intent);
            }
        });





        ImageView homephoto = findViewById(R.id.home);

        homephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAnnonceActivity2.this, acceuilprofileActivity2.class);
                startActivity(intent);
            }
        });













        Spinner secteurActiviteSpinner = findViewById(R.id.secteurActiviteSpinner);
        String[] secteurActiviteOptions = { "Sélectionner Secteur D'Activité","CENTRE D'APPELS",
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
        ArrayAdapter<String> secteurActiviteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, secteurActiviteOptions);
        secteurActiviteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secteurActiviteSpinner.setAdapter(secteurActiviteAdapter);



        Spinner regionSpinner = findViewById(R.id.regionSpinner);
        String[] regionSpinnerOptions = { "Sélectionner  région" ,"Ariana",
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
        ArrayAdapter<String> RegionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, regionSpinnerOptions);
        RegionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regionSpinner.setAdapter(RegionAdapter);


        Spinner TypecontratSpinner = findViewById(R.id.TypecontratSpinner);
        String[] TypecontratOptions = { "Sélectionner Contrat","CIVP",
               "CDD",
                "CDI",
                "KARAMA",
                "SEASONAL",
                "ALTERNATIION",
                "FREELANCER"};
        ArrayAdapter<String> TypecontratAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, TypecontratOptions);

        TypecontratAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        TypecontratSpinner.setAdapter(TypecontratAdapter);











        final EditText delaiExpirationEditText = findViewById(R.id.delaiExpirationEditText);
        delaiExpirationEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(delaiExpirationEditText);
            }
        });
    }

    private void showDatePicker(final EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                selectedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, monthOfYear + 1, dayOfMonth);
                editText.setText(selectedDate);
            }
        }, year, month, day);

        datePickerDialog.show();


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            companyLogoImageView.setImageURI(selectedImageUri);
        }
    }
        }
