package tn.esprit.androidproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class acceuilprofileActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuilprofile2);



        List<String> options = new ArrayList<>();
        options.add("Créer Profil");
        options.add("Visualiser profil");
        options.add("Déposer Offre");
        options.add("Gérer mes annonces");
        options.add("Consulter les CV");


        OptionAdapter adapter = new OptionAdapter(this, R.layout.item_option, options);
        ListView listView = findViewById(R.id.optionsListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedOption = options.get(position);


                if (selectedOption.equals("Déposer Offre")) {
                    Intent intent = new Intent(acceuilprofileActivity2.this, AddAnnonceActivity2.class);
                    startActivity(intent);
                }
                if (selectedOption.equals("Créer Profil")) {
                    Intent intent = new Intent(acceuilprofileActivity2.this, AddProfileActivity2.class);
                    startActivity(intent);
                }
                if (selectedOption.equals("Visualiser profil")) {
                    Intent intent = new Intent(acceuilprofileActivity2.this, AfficheProfileActivity2.class);
                    startActivity(intent);
                }
                if (selectedOption.equals("Gérer mes annonces")) {
                    Intent intent = new Intent(acceuilprofileActivity2.this, MesOffreActivity2.class);
                    startActivity(intent);
                }
            }
        });
    }
}
