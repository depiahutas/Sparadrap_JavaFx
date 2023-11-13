package classMetier.gestion;

import classMetier.sante.Medicament;

import java.util.ArrayList;

public class Panier {
    private int id;
    private ArrayList<Medicament> resumePanier;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Medicament> getResumePanier() {
        return resumePanier;
    }

    public void setResumePanier(ArrayList<Medicament> resumePanier) {
        this.resumePanier = resumePanier;
    }

    public Panier(int id, ArrayList<Medicament> resumePanier) {
        setId(id);
        setResumePanier(resumePanier);
    }
}
