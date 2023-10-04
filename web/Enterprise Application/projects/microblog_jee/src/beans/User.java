package beans;


public class User {
    private long id;
    private String email;
    private String motDePasse;
    private String nom;
    private String dateInscription;

    public User(long id, String email, String mdp, String nom, String dateInscription) {
        this.id = id;
        this.email = email;
        this.motDePasse = mdp;
        this.nom = nom;
        this.dateInscription = dateInscription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public String getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String toString() {
        return "id : " + this.id + ", name : " + this.nom + ", email : " + this.email + ", mdp : " + this.motDePasse + ", Date : " + this.dateInscription;
    }
}