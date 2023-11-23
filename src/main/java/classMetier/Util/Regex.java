package classMetier.Util;

/**
 * classe regroupant tous les REGEX de controle
 */
public class Regex {

    //regex Personne
    static final String regexDate = "^\\d{4}-\\d{2}-\\d{2}$";


    static final String regexNom="^[a-zA-ZÀ-ÖØ-öø-ÿ]+([-'\\s][a-zA-ZÀ-ÖØ-öø-ÿ]+)*$";
    static final String regexPrenom="^[a-zA-ZÀ-ÖØ-öø-ÿ]+$";

    static final String regexMail="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    static final String regexTel = "^((\\+|00)33|0)[0-9 .-]{9}$";
    public static String getRegexDate() {
        return regexDate;
    }



    public static String getRegexNom(){
        return regexNom;
    }
    public static String getRegexPrenom(){
        return regexPrenom;
    }

    public static String getRegexMail(){
        return regexMail;
    }

    public static String getRegexTel(){
        return regexTel;
    }


    //regex Adresse

    static final String regexNumeroAdresse = "^([0-9]{1,5}[a-zA-Z]{0,3})$";
    static final String regexNomAdresse = "^([a-zA-Z0-9\\-\\.\\,' ]+)\\S*$";

    static final String regexCodePostal = "^[0-9]{5}$";
    static final String regexVille="^\\p{Lu}\\p{L}*(?:[\\s-]\\p{Lu}\\p{L}*)*$";

    public static String getRegexNumeroAdresse(){
        return regexNumeroAdresse;
    }

    public static String getRegexNomAdresse(){
        return regexNomAdresse;
    }

    public static String getRegexCodePostal(){
        return regexCodePostal;
    }

    public static String getRegexVille(){
        return  regexVille;
    }

    //regex Client
    static final String regexDateNaiss="^\\d{4}-\\d{2}-\\d{2}$";
    static final String regexNumSecu = "^([1-2][0-9]{2})([0-1][0-9]|2[0-9]|9[1-7])([0-9]{2})([0-9]{3}(-[0-9]{2})?)$";

    public static String getRegexDateNaiss(){
        return regexDateNaiss;
    }
    public static String getRegexNumSecu(){
        return regexNumSecu;
    }

    //regex Medecin
    static final String regexAgr="^([A-Z]{2})([0-9]{8})$";
    public static String getRegexAgr(){
        return regexAgr;
    }

    static final String RegexMed="^[a-zA-ZÀ-ÖØ-öø-ÿ]+$";

    public static String getRegexNomMed() {
        return RegexMed;
    }
    static final String RegexPrix="d{1,3}(?:[.,]\\d{3})*(?:[.,]\\d{2})";
    public static String getRegexPrix() {
        return RegexPrix;
    }

    static final String RegexInt="^[0-9]*$";
    public static String getRegexInt() {
        return RegexInt;
    }
}
