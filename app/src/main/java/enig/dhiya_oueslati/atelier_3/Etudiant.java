package enig.dhiya_oueslati.atelier_3;

public class Etudiant {
    String id, prenom, nom, groupe, email;

    public Etudiant() {
    }

    public String getemail() {
        return email;
    }

    public String getnom() {
        return nom;
    }

    public String getprenom() {
        return prenom;
    }

    public String getId() {
        return id;
    }

    public String getgroupe() {
        return groupe;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public void setLastName(String nom) {
        this.nom = nom;
    }

    public void setprenom(String prenom) {
        this.prenom = prenom;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setgroupe(String groupe) {
        this.groupe = groupe;
    }
}