package tn.esprit.androidproject;

public class Profile {


    private String nom;
    private String prenom;

    public Profile(String nom) {
        this.nom = nom;
    }

    private String email;
    private String tel;
    private String pwd;
    private String nom_entreprise;
    private String secteur_activité;
    private String region;
    private String datefondtion;
    private String adress;
    private String site;

    public String getEmail() {
        return email;
    }

    public String getNom_entreprise() {
        return nom_entreprise;
    }

    public String getSecteur_activité() {
        return secteur_activité;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDatefondtion() {
        return datefondtion;
    }

    public void setDatefondtion(String datefondtion) {
        this.datefondtion = datefondtion;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setSecteur_activité(String secteur_activité) {
        this.secteur_activité = secteur_activité;
    }

    public void setNom_entreprise(String nom_entreprise) {
        this.nom_entreprise = nom_entreprise;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getTel() {
        return tel;
    }
public int id;

    public int getId() {
        return id;
    }
    public Profile() {
        // Initialize default values if needed
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Profile(String prenom, String email, String tel, String pwd, String nom_entreprise, String secteur_activité, String datefondtion, String adress, String site, String region) {
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.pwd = pwd;
        this.nom_entreprise = nom_entreprise;
        this.secteur_activité = secteur_activité;
        this.datefondtion = datefondtion;
        this.adress = adress;
        this.site = site;
        this.region = region;
    }
}


