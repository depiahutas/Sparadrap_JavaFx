package DAO.gestion;

import DAO.DAO;
import DAO.sante.MedicamentDAO;
import classMetier.gestion.Panier;
import classMetier.sante.Medicament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PanierDAO extends DAO<Panier> {

    public boolean create(Panier obj,int idAchat) {

        MedicamentDAO medicamentDAO = new MedicamentDAO();

        StringBuilder sqlInsertPanier = new StringBuilder();
        sqlInsertPanier.append("insert into Panier ");
        sqlInsertPanier.append("(pan_id,pan_achat,pan_medic,pan_qte)");
        sqlInsertPanier.append("values (null,?,?,?)");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlInsertPanier.toString())) {

            for (Medicament medicament : obj.getResumePanier()) {
                preparedStatement.setInt(1, idAchat);
                preparedStatement.setInt(2, medicament.getId());
                preparedStatement.setInt(3, medicament.getQuantite());
                preparedStatement.executeUpdate();

                medicamentDAO.updateQte(medicament);
            }
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return requetOK;
    }

    //inutile
    @Override
    public boolean create(Panier obj) {
        return false;
    }

    @Override
    public boolean delete(Panier obj) {
        return false;
    }

    @Override
    public boolean update(Panier obj) throws SQLException {
        return false;
    }

    @Override
    public Panier find(Integer pID) {

        MedicamentDAO medicamentDAO = new MedicamentDAO();

        StringBuilder sqlFindPanier = new StringBuilder();
        sqlFindPanier.append("select * from Panier ");
        sqlFindPanier.append("where pan_achat = ?");


        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindPanier.toString())) {

            preparedStatement.setInt(1, pID);
            ResultSet resultSet = preparedStatement.executeQuery();

            ObservableList<Medicament> listMed = FXCollections.observableArrayList();


            int i = 0;
            while (resultSet.next()) {
                Medicament m = medicamentDAO.find(resultSet.getInt("pan_medic"));
                m.setQuantite(resultSet.getInt("pan_qte"));
                listMed.add(m);
                i = resultSet.getInt("pan_id");
            }
            return new Panier(i, listMed);
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return null;
    }

    //inutile
    @Override
    public ObservableList<Panier> findAll() {
        return null;
    }

}
