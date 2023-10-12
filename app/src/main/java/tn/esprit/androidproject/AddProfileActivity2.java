package tn.esprit.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
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

    private EditText firstNameEditText, emailEditText, phoneEditText, passwordEditText,
            confirmPasswordEditText, companyNameEditText, businessSectorEditText, regionEditText,
            addressEditText, companyWebsiteEditText;
    private Button submitButton;
    private String selectedDate;
    private ImageView companyLogoImageView;
    private Button selectLogoButton;
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        firstNameEditText = findViewById(R.id.firstNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        companyNameEditText = findViewById(R.id.companyNameEditText);

        addressEditText = findViewById(R.id.addressEditText);
        companyWebsiteEditText = findViewById(R.id.companyWebsiteEditText);
        submitButton = findViewById(R.id.submitButton);
        companyLogoImageView = findViewById(R.id.companyLogoImageView);
        selectLogoButton = findViewById(R.id.selectLogoButton);



              selectLogoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, PICK_IMAGE_REQUEST);
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














        final EditText delaiExpirationEditText = findViewById(R.id.delaiExpirationEditText);
        delaiExpirationEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(delaiExpirationEditText);
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEditText.getText().toString();
                String Telephone = phoneEditText.getText().toString();
                String MotDePasse = passwordEditText.getText().toString();
                String confirmMDP = confirmPasswordEditText.getText().toString();
                String NomCompanie = companyNameEditText.getText().toString();
                String Addresse = addressEditText.getText().toString();
                String SiteWeb = companyWebsiteEditText.getText().toString();
                String Description = firstNameEditText.getText().toString();


                Log.d("MyApp", "email: " + email);


                if (isValidForm( email, Telephone, MotDePasse, confirmMDP, NomCompanie, Addresse, SiteWeb, Description)) {

                    String successMessage = "Profil ajouté avec succès!";
                    Toast.makeText(AddProfileActivity2.this, successMessage, Toast.LENGTH_SHORT).show();
                } else {

                    String errorMessage = "Le formulaire n'est pas valide. Veuillez vérifier vos données.";
                    Toast.makeText(AddProfileActivity2.this, errorMessage, Toast.LENGTH_SHORT).show();
                }



            }
        });
        ImageView homephoto = findViewById(R.id.home);

        homephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddProfileActivity2.this, acceuilprofileActivity2.class);
                startActivity(intent);
            }
        });

    }

    private boolean isValidForm(String firstName, String email, String phone, String password, String confirmPassword, String companyName,String address, String companyWebsite) {

        if (firstName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || companyName.isEmpty() || address.isEmpty()|| companyWebsite.isEmpty()) {
            return false;
        }

        if (!password.equals(confirmPassword)) {
            return false;
        }


        return true;
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

        }
    }
}
