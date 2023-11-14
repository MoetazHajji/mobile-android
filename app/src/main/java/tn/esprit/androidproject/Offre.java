package tn.esprit.androidproject;

public class Offre {
    private String titreOffre;
    private String nomEntreprise;
    private String secteurActivite;
    private String region;
    private String typeContrat;
    private String delaiExpiration;
private String typeOffre;
private String etatoffre;
    private int id; // Assuming that id is an integer

    // Other methods...

    public int getId() {
        return id;
    }

    public String getTypeOffre() {
        return typeOffre;
    }

    public String getEtatoffre() {
        return etatoffre;
    }

    public void setEtatoffre(String etatoffre) {
        this.etatoffre = etatoffre;
    }

    public void setTypeOffre(String typeOffre) {
        this.typeOffre = typeOffre;
    }

    private String description;
    private String imagePath;



    public Offre(String titreOffre, String nomEntreprise ,String secteurActivite,String region,String typeContrat ,String delaiExpiration,String description,String typeOffre, String etatoffre) {
        this.titreOffre = titreOffre;
        this.nomEntreprise = nomEntreprise;
        this.secteurActivite= secteurActivite;
        this.region = region;
        this.typeContrat= typeContrat;
        this.delaiExpiration= delaiExpiration;
        this.description=description;
        this.typeOffre =typeOffre;
        this.etatoffre =etatoffre;

    }

    public Offre(String region) {
        this.region = region;
    }

    public Offre() {

    }
    public String getDisplayText() {

        StringBuilder displayText = new StringBuilder();
        displayText.append("Titre : ").append(titreOffre).append("\n");
        displayText.append("Nom_Entreprise: ").append(nomEntreprise).append("\n");
        displayText.append("Secteur_Activite : ").append(secteurActivite).append("\n");
        displayText.append("Région : ").append(region).append("\n");
        displayText.append("Déscription : ").append(description).append("\n");
        displayText.append("Etat_offre : ").append(etatoffre).append("\n");
        displayText.append("type_Offre : ").append(typeOffre).append("\n");

        return displayText.toString();
    }

    public String getTitreOffre() {
        return titreOffre;
    }


    public String getRegion() {
        return region;
    }

    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDelaiExpiration() {
        return delaiExpiration;
    }

    public void setDelaiExpiration(String delaiExpiration) {
        this.delaiExpiration = delaiExpiration;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getSecteurActivite() {
        return secteurActivite;
    }

    public void setSecteurActivite(String secteurActivite) {
        this.secteurActivite = secteurActivite;
    }

    public void setTitreOffre(String titreOffre) {
        this.titreOffre = titreOffre;
    }


}
