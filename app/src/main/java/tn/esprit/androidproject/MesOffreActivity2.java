package tn.esprit.androidproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import androidx.cardview.widget.CardView;

public class MesOffreActivity2 extends AppCompatActivity {

    private MaBaseDeDonneesHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_offre2);

        dbHelper = new MaBaseDeDonneesHelper(this);

        List<Offre> offresList = dbHelper.getAllOffres();

        ListView listView = findViewById(R.id.listView);

        ArrayAdapter<Offre> adapter = new ArrayAdapter<Offre>(this, android.R.layout.simple_list_item_1, offresList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = view.findViewById(android.R.id.text1);

                Offre offre = getItem(position);
                if (offre != null) {
                    textView.setText(offre.getDisplayText());
                }

                return view;
            }
        };

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Offre selectedOffre = offresList.get(position);


                showOptionsDialog(selectedOffre);
            }
        });
    }

    private void showOptionsDialog(final Offre selectedOffre) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Options d'Offre");
        builder.setMessage("Sélectionnez une option :");

        builder.setPositiveButton("Modifier", (dialog, which) -> {
            Intent intent = new Intent(MesOffreActivity2.this, ModifieOffreActivity2.class);
             getIntent().getParcelableExtra("selectedOffre");

            startActivity(intent);
            dialog.dismiss();
        });

        builder.setNegativeButton("Supprimer", (dialog, which) -> {

            showConfirmationDialog(selectedOffre);
            dialog.dismiss();
        });

        builder.setNeutralButton("Annuler", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }

    private void showConfirmationDialog(final Offre selectedOffre) {
        AlertDialog.Builder confirmationBuilder = new AlertDialog.Builder(this);
        confirmationBuilder.setTitle("Confirmation");
        confirmationBuilder.setMessage("Voulez-vous vraiment supprimer cette offre?");

        confirmationBuilder.setPositiveButton("Oui", (dialog, which) -> {

            dbHelper.deleteOffre(selectedOffre);
            refreshList();
            Toast.makeText(getApplicationContext(), "Supprimer", Toast.LENGTH_SHORT).show();
        });

        confirmationBuilder.setNegativeButton("Non", (dialog, which) -> {

            Toast.makeText(getApplicationContext(), "Opération annulée", Toast.LENGTH_SHORT).show();
        });

        confirmationBuilder.create().show();
    }
    private void refreshList() {
        Log.d("MesOffreActivity2", "Refreshing list...");
        List<Offre> updatedOffresList = dbHelper.getAllOffres();
        ArrayAdapter<Offre> updatedAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, updatedOffresList);
        ListView updatedListView = findViewById(R.id.listView);
        updatedListView.setAdapter(updatedAdapter);
    }
}