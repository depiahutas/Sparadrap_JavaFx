package classMetier.personne;

import classMetier.Util.Regex;
import classMetier.sante.Mutuelle;

import java.security.PrivateKey;
import java.util.regex.Pattern;

public class Client{
    private int idClient;
    private Personne personne;
    private String dateNaiss;
    private Mutuelle mutuelle;
    private Medecin medecin;
    private String numSecu;


    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public int getIdClient() {

        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(String dateNaiss)throws IllegalArgumentException {
            if (Pattern.matches(Regex.getRegexDateNaiss(), dateNaiss)) {

                this.dateNaiss = dateNaiss;
            }
             else {
                 throw new IllegalArgumentException("date de naissance incorrecte");
            }
    }

    public String getNumSecu() {
        return numSecu;
    }

    public void setNumSecu(String numSecu)throws IllegalArgumentException {
            if (Pattern.matches(Regex.getRegexNumSecu(),numSecu)){
                this.numSecu = numSecu;
            }
            else{
                throw new IllegalArgumentException("Numéro de sécurité sociale incorrecte");
            }
    }

    public Mutuelle getMutuelle() {
        return mutuelle;
    }

    public void setMutuelle(Mutuelle mutuelle) {
            if (mutuelle == null) {
                throw new NullPointerException("la mutuelle ne peut etre null");
            } else {
                this.mutuelle = mutuelle;
            }
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        if (medecin==null){
            throw new NullPointerException("le medecin ne peut etre null");
        }
        else {
            this.medecin = medecin;
        }
    }

    /**
     * constructeur client
     * @param id id client
     * @param personne  Personne associé
     * @param dateNaiss String (Date au format jj/mm/aaaa, controle par REGEX)
     * @param medecin Medecin
     * @param mutuelle Mutuelle
     * @param numSecu String (controle REGEX)
     */
    public Client(int id,Personne personne, String dateNaiss, Medecin medecin, Mutuelle mutuelle, String numSecu) {
        setIdClient(id);
        setPersonne(personne);
        setDateNaiss(dateNaiss);
        setMedecin(medecin);
        setMutuelle(mutuelle);
        setNumSecu(numSecu);

    }

    public void updateClient(Personne personne,String dateNaiss, Medecin medecin, Mutuelle mutuelle, String numSecu){
    setPersonne(personne);
    setDateNaiss(dateNaiss);
    setMedecin(medecin);
    setMutuelle(mutuelle);
    setNumSecu(numSecu);
    }


    //
    public String getNom(){
        return personne.getNom();
    }

    public String getPrenom() {
        return personne.getPrenom();
    }

    public String getMail() {
        return personne.getMail();
    }

    public String getTel() {
        return personne.getTel();
    }

    public String getNomMut() {
        return mutuelle.getNom();
    }

    public String getNomMed(){
        return "Dr. "+medecin.getPersonne().getNom();
    }
}
