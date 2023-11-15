package classMetier.gestion;

import classMetier.sante.Medicament;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Panier {
    private int id;
    private ObservableList<Medicament> resumePanier;

    public Panier() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ObservableList<Medicament> getResumePanier() {
        return resumePanier;
    }

    public void setResumePanier(ObservableList<Medicament> resumePanier) {
        this.resumePanier = resumePanier;
    }

    public Panier(int id, ObservableList<Medicament> resumePanier) {
        setId(id);
        setResumePanier(resumePanier);
    }
}
