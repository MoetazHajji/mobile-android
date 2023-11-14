package tn.esprit.androidproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AfficheProfileActivity2 extends AppCompatActivity {
    private EditText input;
    private String displayedEmail;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_profile2);


        TextView firstNameTextView = findViewById(R.id.firstNameTextView);
        TextView emailTextView = findViewById(R.id.emailTextView);
        TextView phoneTextView = findViewById(R.id.phoneTextView);
        TextView companyTextView = findViewById(R.id.companyTextView);
        TextView datefondationTextView = findViewById(R.id.datefondationTextView);
        TextView addresTextView = findViewById(R.id.addresTextView);
        TextView companyWebsiteTextView = findViewById(R.id.companyWebsiteTextView);
        TextView secteurActiviteTextView = findViewById(R.id.secteurActiviteTextView);
        TextView regionActiviteTextView = findViewById(R.id.regionActiviteTextView);

        Intent intent = getIntent();
        if (intent != null) {
            String prenom = intent.getStringExtra("PRENOM");
            String email = intent.getStringExtra("EMAIL");
            String tel = intent.getStringExtra("TEL");
            String nomEntreprise = intent.getStringExtra("NOM_ENTREPRISE");
            String secteurActivite = intent.getStringExtra("SECTEUR_ACTIVITE");
            String region = intent.getStringExtra("REGION");
            String dateFondation = intent.getStringExtra("DATE_FONDATION");
            String adresse = intent.getStringExtra("ADRESS");
            String site = intent.getStringExtra("SITE");


            // Set the data to TextViews
            firstNameTextView.setText(prenom);
            emailTextView.setText("EMAIL : " + email);
            phoneTextView.setText("TELEPHONE : " + tel);
            companyTextView.setText(nomEntreprise);
            secteurActiviteTextView.setText("SECTEURDACTIVITE : " + secteurActivite);
            regionActiviteTextView.setText("REGION : " + region);
            datefondationTextView.setText("DATE_Fondation : " + dateFondation);
            addresTextView.setText("ADRESS : " + adresse);
            companyWebsiteTextView.setText(site);
        }

        input = new EditText(this);

        TextView emailTextView1 = findViewById(R.id.emailTextView);

        Intent intent1 = getIntent();
        if (intent != null) {
            String email = intent.getStringExtra("EMAIL");

            emailTextView.setText("EMAIL : " + email);

            displayedEmail = email;
        }



        ImageView updateImageBtn = findViewById(R.id.updateImageBtn);

        updateImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showPasswordVerificationDialog();
            }
        });





        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LogoutDebug", "Logout button clicked.");


                Intent intent = new Intent(AfficheProfileActivity2.this, LoginActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                finish();
            }
        });












        ImageView deleteProfileButton = findViewById(R.id.deleteImage);

        deleteProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Appel de la fonction de suppression
                deleteProfile();
            }
        });
    }

    // Méthode appelée lors du clic sur le bouton de suppression
    public void onDeleteProfileButtonClick(View view) {
        // Appel de la fonction de suppression
        deleteProfile();
    }

    // Fonction pour supprimer le profil
    private void deleteProfile() {
        // Obtenez l'email du profil à supprimer (remplacez-le par votre propre logique)
        String emailToDelete = "exemple@email.com";

        // Créer une boîte de dialogue de confirmation
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation de suppression");
        builder.setMessage("Êtes-vous sûr de vouloir supprimer votre profil ?");

        // Ajouter un bouton pour confirmer la suppression
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Appelez la fonction de suppression dans le helper de la base de données
                MaBaseDeDonneesHelper dbHelper = new MaBaseDeDonneesHelper(AfficheProfileActivity2.this);
                dbHelper.supprimerProfil(emailToDelete);

                // Affichez un message après la suppression
                Toast.makeText(AfficheProfileActivity2.this, "Profil supprimé", Toast.LENGTH_SHORT).show();

                // Redirigez l'utilisateur vers la page d'accueil
                Intent intent = new Intent(AfficheProfileActivity2.this, acceuilprofileActivity2.class);
                startActivity(intent);
                finish(); // Terminer cette activité pour ne pas revenir en arrière
            }
        });

        // Ajouter un bouton pour annuler la suppression
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Ne rien faire, l'utilisateur a annulé la suppression
            }
        });

        // Afficher la boîte de dialogue
        builder.show();
    }

    private void showPasswordVerificationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Vérification du mot de passe");
        builder.setMessage("Saisissez votre mot de passe:");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

        builder.setPositiveButton("Vérifier", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String enteredPassword = input.getText().toString();
                String actualPassword = getActualPasswordFromDatabase(displayedEmail);

                Log.d("PasswordDebug", "Entered Password: " + enteredPassword);
                Log.d("PasswordDebug", "Actual Password: " + actualPassword);

                if (enteredPassword.equals(actualPassword)) {
                    Log.d("PasswordDebug", "Passwords match. Redirecting to ModifyProfileActivity2.");
                    Intent intent = new Intent(AfficheProfileActivity2.this, ModifyProfileActivity2.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.d("PasswordDebug", "Passwords do not match. Showing toast message.");
                    Toast.makeText(AfficheProfileActivity2.this, "Mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirigez l'utilisateur vers l'activité de connexion
                Intent intent = new Intent(AfficheProfileActivity2.this, LoginActivity.class);

                // Effacez les autres activités de la pile et ajoutez l'intention pour démarrer une nouvelle tâche
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                // Terminez l'activité actuelle pour ne pas revenir en arrière
                finish();
            }
        });

        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private String getActualPasswordFromDatabase(String displayedEmail) {
        // Use the displayed email
        String email = displayedEmail;

        MaBaseDeDonneesHelper dbHelper = new MaBaseDeDonneesHelper(AfficheProfileActivity2.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                MaBaseDeDonneesHelper.TABLE_PROFILE,
                new String[]{"pwd"},
                "email = ?",
                new String[]{email},
                null,
                null,
                null
        );

        String actualPassword = "";

        if (cursor.moveToFirst()) {
            int passwordIndex = cursor.getColumnIndex("pwd");
            if (passwordIndex != -1) {
                actualPassword = cursor.getString(passwordIndex);
                Log.d("PasswordDebug", "Retrieved actual password from database: " + actualPassword);
            }
        }

        cursor.close();
        db.close();

        Log.d("PasswordDebug", "Returning actual password: " + actualPassword);
        return actualPassword;
    }}
