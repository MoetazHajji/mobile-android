package tn.esprit.androidproject;

public class Offre {
    private String titre;
    private String etat;
    private String dateCreation;
    private String dateExpiration;

    public Offre(String titre, String etat, String dateCreation, String dateExpiration) {
        this.titre = titre;
        this.etat = etat;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
    }
}
