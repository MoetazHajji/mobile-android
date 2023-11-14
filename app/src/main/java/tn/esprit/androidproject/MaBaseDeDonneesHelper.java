package tn.esprit.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MaBaseDeDonneesHelper extends SQLiteOpenHelper {

    private static final String NOM_BASE_DE_DONNEES = "OffreBD";
    private static final int VERSION_BASE_DE_DONNEES = 3;

    // Requête de création de la table
    static final String CREER_TABLE_OFFRE =
            "CREATE TABLE IF NOT EXISTS MaTable (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "titre_offre TEXT," +
                    "nom_entreprise TEXT," +
                    "secteur_activite TEXT," +
                    "region TEXT," +  // Nouvelle colonne
                    "type_contrat TEXT," +  // Nouvelle colonne
                    "delai_expiration TEXT," +
                    "Type_Offre TEXT," +
                    "etat_Offre TEXT," +
                    "description TEXT" +

                    ");";


    static final String CREER_TABLE_PROFILE =
            "CREATE TABLE IF NOT EXISTS ProfileTable (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "prenom TEXT," +
                    "email TEXT," +
                    "tel TEXT," +
                    "pwd TEXT," +
                    "nom_entreprise TEXT," +
                    "secteur_activite TEXT," +
                    "region TEXT," +
                    "datefondtion TEXT," +
                    "adress TEXT," +
                    "site TEXT" +
                    ");";



    private Context context;


    public MaBaseDeDonneesHelper(Context context) {
        super(context, NOM_BASE_DE_DONNEES, null, VERSION_BASE_DE_DONNEES);
        this.context = context;


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Création de la table
        sqLiteDatabase.execSQL(CREER_TABLE_OFFRE);
        sqLiteDatabase.execSQL(CREER_TABLE_PROFILE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public void deleteOffre(Offre offre) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete(TABLE_OFFRE, "titre_offre = ?", new String[]{offre.getTitreOffre()});
        db.close();

        if (deletedRows > 0) {
            Log.d("MaApplication", "Offre supprimée avec succès!");
            Toast.makeText(context, "Offre supprimée avec succès!", Toast.LENGTH_SHORT).show();
        } else {
            Log.e("MaApplication", "Erreur lors de la suppression de l'offre.");
            Toast.makeText(context, "Erreur lors de la suppression de l'offre.", Toast.LENGTH_SHORT).show();
        }
    }
    public void supprimerProfil(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete(TABLE_PROFILE, "email = ?", new String[]{email});
        db.close();

        if (deletedRows > 0) {
            Log.d("MaApplication", "Profile supprimé avec succès!");
            Toast.makeText(context, "Profile supprimé avec succès!", Toast.LENGTH_SHORT).show();
        } else {
            Log.e("MaApplication", "Erreur lors de la suppression du profile.");
            Toast.makeText(context, "Erreur lors de la suppression du profile.", Toast.LENGTH_SHORT).show();
        }
    }

    public static final String TABLE_OFFRE = "MaTable";
    public static final String TABLE_PROFILE = "ProfileTable";
    public List<Offre> getAllOffres() {
        List<Offre> offresList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + MaBaseDeDonneesHelper.TABLE_OFFRE, null);

        if (cursor.moveToFirst()) {
            do {
                Offre offre = new Offre();

                int titreIndex = cursor.getColumnIndex("titre_offre");
                if (titreIndex != -1) {
                    offre.setTitreOffre(cursor.getString(titreIndex));
                }

                int descriptionIndex = cursor.getColumnIndex("description");
                if (descriptionIndex != -1) {
                    offre.setDescription(cursor.getString(descriptionIndex));
                }

                int typeContratIndex = cursor.getColumnIndex("type_contrat");
                if (typeContratIndex != -1) {
                    offre.setTypeContrat(cursor.getString(typeContratIndex));
                }

                int delaiExpirationIndex = cursor.getColumnIndex("delai_expiration");
                if (delaiExpirationIndex != -1) {
                    offre.setDelaiExpiration(cursor.getString(delaiExpirationIndex));
                }

                int etatOffreIndex = cursor.getColumnIndex("etat_Offre");
                if (etatOffreIndex != -1) {
                    offre.setEtatoffre(cursor.getString(etatOffreIndex));
                }

                int typeOffreIndex = cursor.getColumnIndex("Type_Offre");
                if (typeOffreIndex != -1) {
                    offre.setTypeOffre(cursor.getString(typeOffreIndex));
                }

                int regionIndex = cursor.getColumnIndex("region");
                if (regionIndex != -1) {
                    offre.setRegion(cursor.getString(regionIndex));
                }

                int nameIndex = cursor.getColumnIndex("nom_entreprise");
                if (nameIndex != -1) {
                    offre.setNomEntreprise(cursor.getString(nameIndex));
                }

                int SecteurIndex = cursor.getColumnIndex("secteur_activite");
                if (SecteurIndex != -1) {
                    offre.setSecteurActivite(cursor.getString(SecteurIndex));
                }



                offresList.add(offre);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return offresList;







    }


    public void updateOffre(Offre offre) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("titre_offre", offre.getTitreOffre());
        values.put("nom_entreprise", offre.getNomEntreprise());
        values.put("secteur_activite", offre.getSecteurActivite());
        values.put("region", offre.getRegion());
        values.put("type_contrat", offre.getTypeContrat());
        values.put("delai_expiration", offre.getDelaiExpiration());
        values.put("Type_Offre", offre.getTypeOffre());
        values.put("etat_Offre", offre.getEtatoffre());
        values.put("description", offre.getDescription());

        try {
            // Mise à jour de la base de données
            db.update("MaTable", values, "id = ?", new String[]{String.valueOf(offre.getId())});
            Log.d("MaBaseDeDonneesHelper", "Offre mise à jour dans la base de données avec succès.");
        } catch (Exception e) {
            Log.e("MaBaseDeDonneesHelper", "Erreur lors de la mise à jour de l'offre dans la base de données.", e);
            Toast.makeText(context, "Erreur lors de la mise à jour de l'offre", Toast.LENGTH_SHORT).show();
        } finally {
            db.close();
        }
    }

    public boolean  modifierProfil(Profile profile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Ajout du code pour mettre à jour chaque colonne dans la base de données
        values.put("prenom", profile.getPrenom());
        values.put("email", profile.getEmail());
        values.put("tel", profile.getTel());
        values.put("pwd", profile.getPwd());
        values.put("nom_entreprise", profile.getNom_entreprise());
        values.put("secteur_activite", profile.getSecteur_activité());
        values.put("region", profile.getRegion());
        values.put("datefondtion", profile.getDatefondtion());
        values.put("adress", profile.getAdress());
        values.put("site", profile.getSite());

        try {
            // Mise à jour de la base de données
            db.update("ProfileTable", values, "pwd = ?", new String[]{String.valueOf(profile.getPwd())});
            Log.d("MaBaseDeDonneesHelper", "Profil mis à jour dans la base de données avec succès.");
            Toast.makeText(context, "Profil mis à jour avec succès", Toast.LENGTH_SHORT).show();
            return true;  // Succès
        } catch (Exception e) {
            Log.e("MaBaseDeDonneesHelper", "Erreur lors de la mise à jour du profil dans la base de données.", e);
            Toast.makeText(context, "Erreur lors de la mise à jour du profil", Toast.LENGTH_SHORT).show();
            return false;  // Échec
        } finally {
            db.close();
        }
    }
    public Profile getProfileByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_PROFILE,
                null,
                "email = ?",
                new String[]{email},
                null,
                null,
                null
        );

        Profile profile = null;

        if (cursor != null && cursor.moveToFirst()) {
            // Construct the Profile object from cursor data
            profile = new Profile();

            int prenomIndex = cursor.getColumnIndex("prenom");
            if (prenomIndex != -1) {
                profile.setPrenom(cursor.getString(prenomIndex));
            }

            int emailIndex = cursor.getColumnIndex("email");
            if (emailIndex != -1) {
                profile.setEmail(cursor.getString(emailIndex));
            }

            int telIndex = cursor.getColumnIndex("tel");
            if (telIndex != -1) {
                profile.setTel(cursor.getString(telIndex));
            }

            int pwdIndex = cursor.getColumnIndex("pwd");
            if (pwdIndex != -1) {
                profile.setPwd(cursor.getString(pwdIndex));
            }

            int nomEntrepriseIndex = cursor.getColumnIndex("nom_entreprise");
            if (nomEntrepriseIndex != -1) {
                profile.setNom_entreprise(cursor.getString(nomEntrepriseIndex));
            }

            int secteurActiviteIndex = cursor.getColumnIndex("secteur_activite");
            if (secteurActiviteIndex != -1) {
                profile.setSecteur_activité(cursor.getString(secteurActiviteIndex));
            }

            int regionIndex = cursor.getColumnIndex("region");
            if (regionIndex != -1) {
                profile.setRegion(cursor.getString(regionIndex));
            }

            int dateFondationIndex = cursor.getColumnIndex("datefondtion");
            if (dateFondationIndex != -1) {
                profile.setDatefondtion(cursor.getString(dateFondationIndex));
            }

            int addressIndex = cursor.getColumnIndex("adress");
            if (addressIndex != -1) {
                profile.setAdress(cursor.getString(addressIndex));
            }

            int siteIndex = cursor.getColumnIndex("site");
            if (siteIndex != -1) {
                profile.setSite(cursor.getString(siteIndex));
            }
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();

        return profile;
    }}