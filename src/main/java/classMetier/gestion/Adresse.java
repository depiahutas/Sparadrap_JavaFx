package classMetier.gestion;

import classMetier.Util.Regex;

import java.util.regex.Pattern;

public class Adresse {
    private int numero;
    private String rue;
    private String codePostal;
    private String ville;
    private int id;



    /**
     * constructeur Adresse
     * @param numero int
     * @param rue String (controle par REGEX)
     * @param codePostal String (controle par REGEX)
     * @param ville String (controle par REGEX)
     */
    public Adresse(int id,int numero, String rue, String codePostal, String ville) {
        setId(id);
        setNumero(numero);
        setRue(rue);
        setVille(ville);
        setCodePostal(codePostal);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero)throws IllegalArgumentException {
            if (Pattern.matches(Regex.getRegexNumeroAdresse(),""+numero)){
                this.numero = numero;
            }else {
                throw new IllegalArgumentException("Numéro adresse incorrecte");
            }
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue){
            if (Pattern.matches(Regex.getRegexNomAdresse(),rue)){
                this.rue = rue;
            }
            else{
                throw new IllegalArgumentException("Rue incorrecte");
            }
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal)throws IllegalArgumentException {

            if (Pattern.matches(Regex.getRegexCodePostal(),codePostal)) {
                this.codePostal = codePostal;
            }
            else {
                throw new IllegalArgumentException("code postal incorrecte");
            }
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville)throws IllegalArgumentException {

            if (Pattern.matches(Regex.getRegexVille(),ville))
            {
                this.ville = ville;
            }
            else {
                throw new IllegalArgumentException("ville incorrecte");
            }
    }

    public int getID() {
        return this.id;
    }
}
