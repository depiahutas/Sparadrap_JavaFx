package DAO.gestion;

import DAO.DAO;
import DAO.sante.MedicamentDAO;
import DAO.sante.OrdonnanceDAO;
import classMetier.gestion.Compose;
import classMetier.sante.Medicament;
import classMetier.sante.Ordonnance;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComposeDAO extends DAO<Compose> {
    @Override
    public boolean create(Compose obj) {
        return false;
    }

    @Override
    public boolean delete(Compose obj) {
        return false;
    }

    @Override
    public boolean update(Compose obj) throws SQLException {
        return false;
    }

    @Override
    public Compose find(Integer pID) {

        OrdonnanceDAO ordonnanceDAO = new OrdonnanceDAO();
        MedicamentDAO medicamentDAO = new MedicamentDAO();

        StringBuilder sqlFindPanier = new StringBuilder();
        sqlFindPanier.append("select * from Compose ");
        sqlFindPanier.append("where com_ord = ?");


        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindPanier.toString())) {

            preparedStatement.setInt(1, pID);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Medicament> listMed = new ArrayList<Medicament>();


            int i = 0;
            Ordonnance ordonnance = null;
            while (resultSet.next()) {
                Medicament m = medicamentDAO.find(resultSet.getInt("com_medic"));
                m.setQuantite(resultSet.getInt("com_qte"));
                listMed.add(m);
                i = resultSet.getInt("com_id");
            }
            return new Compose(i, ordonnance, listMed);
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return null;
    }

    public ArrayList<Medicament> listMed(int pID){
        return null;
    }

    @Override
    public ObservableList<Compose> findAll() {
        return null;
    }
}
