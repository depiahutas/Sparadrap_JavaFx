package classMetier.personne;

import classMetier.Util.Regex;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Medecin{
    private int id;
    private String numAgr;
    private Personne personne;
    private ArrayList<Client> listPatient;

    public String getNumAgr() {
        return numAgr;
    }

    public void setNumAgr(String numAgr)throws IllegalArgumentException {
        if(Pattern.matches(Regex.getRegexAgr(),numAgr)){
            this.numAgr = numAgr;
        }
        else {
            throw new IllegalArgumentException("Numéro d'agrément incorrecte");
        }
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Client> getListPatient() {
        return listPatient;
    }

    public void setListPatient(ArrayList<Client> listPatient) {
        this.listPatient = listPatient;
    }

    /**
     *
     * @param personne
     * @param numAgr String ( controle par REGEX)
     */
    public Medecin(int id,Personne personne, String numAgr) {
        setId(id);
        setPersonne(personne);
        setNumAgr(numAgr);
    }
}
