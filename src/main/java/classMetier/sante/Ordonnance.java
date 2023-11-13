package classMetier.sante;

import classMetier.gestion.Compose;
import classMetier.personne.Client;
import classMetier.personne.Medecin;

public class Ordonnance {
    private int id;
    private Medecin medecin;
    private Client client;
    private Compose compose;
    private String date;

    /**
     * constructeur Ordonnance
     * @param medecin voir classe Medecin
     * @param client voir classe Client
     * @param date String (date format jj/mm/aaaa)
     * @param id int (id ordonnance cache de l'utilisateur)
     */
    public Ordonnance(Medecin medecin, Client client, Compose compose, String date, int id) {
        setId(id);
        setMedecin(medecin);
        setClient(client);
        setCompose(compose);
        setDate(date);
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        if(medecin==null){
            throw new NullPointerException("le medecin ne peut etre null");
        }
        else {
            this.medecin = medecin;
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        if(client==null){
            throw new NullPointerException("le nom ne peut etre null");
        }
        else {
            this.client = client;
        }
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if(date==null){
            throw new NullPointerException("le nom ne peut etre null");
        }
        else {
            this.date = date;
        }
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Compose getCompose() {
        return compose;
    }

    public void setCompose(Compose compose) {
        this.compose = compose;
    }
}
