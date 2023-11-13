package classMetier.gestion;

import classMetier.sante.Medicament;
import classMetier.sante.Ordonnance;

import java.util.ArrayList;

public class Compose {
    private int id;

    private ArrayList<Medicament> listMedic;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public ArrayList<Medicament> getListMedic() {
        return listMedic;
    }

    public void setListMedic(ArrayList<Medicament> listMedic) {
        if(listMedic==null){
            throw new NullPointerException("une ordonnance doit avoir au moins un medicament");
        }
        else {
            this.listMedic = listMedic;
        }
    }

    public Compose(int id, Ordonnance ordonnance, ArrayList<Medicament> listMedic) {
        setId(id);
        setListMedic(listMedic);
    }

    public String getListMedToString() {
        StringBuilder b= new StringBuilder();
        for (Medicament medicament:getListMedic()){
            b.append(" | ").append(medicament.getNom());
        }

        return b.toString();
    }
}
