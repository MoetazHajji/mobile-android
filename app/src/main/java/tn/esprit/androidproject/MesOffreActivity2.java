package tn.esprit.androidproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

public class MesOffreActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_offre2);



        ImageView profileLogo = findViewById(R.id.profileLogo);

        profileLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MesOffreActivity2.this, AfficheProfileActivity2.class);
                startActivity(intent);
            }
        });

        ImageButton modifierButton = findViewById(R.id.modifie1);

        modifierButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MesOffreActivity2.this,ModifieOffreActivity2.class);
                    startActivity(intent);
                }
            });


            ImageButton deleteButton = findViewById(R.id.delete);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog();
            }
        });
        ImageView homephoto = findViewById(R.id.home);

        homephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MesOffreActivity2.this, acceuilprofileActivity2.class);
                startActivity(intent);
            }
        });


    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation de suppression");
        builder.setMessage("Voulez-vous vraiment supprimer cette offre ?");

        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean deletionSuccessful = deleteOffer();
                if (deletionSuccessful) {
                    Toast.makeText(MesOffreActivity2.this, "Offre supprimée avec succès", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MesOffreActivity2.this, "Échec de la suppression de l'offre", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean deleteOffer() {

        boolean deletionSuccessful = true;
        

        return deletionSuccessful;
    }

}
