package classMetier.sante;

public class CategorieMedicament {
    private int id;
    private String libelle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public CategorieMedicament(int id, String libelle) {
        setId(id);
        setLibelle(libelle);
    }

    @Override
    public String toString() {
        return libelle;
    }
}
