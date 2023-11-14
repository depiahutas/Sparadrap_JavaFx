package classMetier.sante;

public class Medicament {

    private int id;
    private String nom;
    private float prix;
    private String dateMES;
    private int quantite;
    private CategorieMedicament categorie;
    private String categorieMed;

    private String image;



    /**
     * constructeur médicament
     * @param nom String (controle REGEX)
     * @param prix double (2 chiffres apres la virgule)
     * @param dateMES String (date format jj//mm/aaaa)
     * @param quantite int (controle par REGEX)
     * @param categorie Categorie
     */
    public Medicament(int id,String nom, float prix, String dateMES, int quantite, CategorieMedicament categorie,String image) {
        setId(id);
        setNom(nom);
        setPrix(prix);
        setDateMES(dateMES);
        setQuantite(quantite);
        setCategorie(categorie);
        setCategorieMed(categorie.getLibelle());
        setImage(image);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom == null) {
            throw new NullPointerException("le nom ne peut etre null");
        } else {
            this.nom = nom;
        }
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
            this.prix = prix;

    }

    public String getDateMES() {
        return dateMES;
    }

    public void setDateMES(String dateMES) {
        if (dateMES==null){
            throw new NullPointerException("la date de mise en service ne peut etre null");
        }
        else {
            this.dateMES = dateMES;
        }
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public CategorieMedicament getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieMedicament categorie) {
        if (categorie==null){
            throw new NullPointerException("la categorie ne peut etre null");
        }
        else {
            this.categorie = categorie;
        }
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategorieMed() {
        return categorieMed;
    }

    public void setCategorieMed(String categorieMed) {
        this.categorieMed = categorieMed;
    }

    @Override
    public String toString() {
        return nom ;
    }
}
