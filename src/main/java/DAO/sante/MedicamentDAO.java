package DAO.sante;

import DAO.DAO;
import classMetier.sante.Medicament;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicamentDAO extends DAO<Medicament> {
    @Override
    public boolean create(Medicament obj) {
        StringBuilder sqlInsertMedicament = new StringBuilder();
        sqlInsertMedicament.append("insert into medicament ");
        sqlInsertMedicament.append("(medic_id,medic_nom,medic_prix,medic_dateMES,medic_qteStock)");
        sqlInsertMedicament.append("values (null,?,?,?,?)");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlInsertMedicament.toString())) {
            preparedStatement.setString(2, obj.getNom());
            preparedStatement.setFloat(3, obj.getPrix());
            preparedStatement.setString(4, obj.getDateMES());
            preparedStatement.setInt(5, obj.getQuantite());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return requetOK;
    }

    @Override
    public boolean delete(Medicament obj) {
        StringBuilder sqlDeleteMedicament = new StringBuilder();
        sqlDeleteMedicament.append("delete from medicament ");
        sqlDeleteMedicament.append("where medic_id = ?");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlDeleteMedicament.toString())) {
            preparedStatement.setInt(1, obj.getId());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }
        return requetOK;
    }

    @Override
    public boolean update(Medicament obj) {
        StringBuilder sqlUpdateMedicament = new StringBuilder();
        sqlUpdateMedicament.append("update medicament ");
        sqlUpdateMedicament.append("set medic_nom = ?, medic_prix=?, medic_categorie=?,medic_stock=?,medic_dateMES=?");
        sqlUpdateMedicament.append("where medic_id=?");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlUpdateMedicament.toString())) {
            preparedStatement.setString(1, obj.getNom());
            preparedStatement.setFloat(2, obj.getPrix());
            preparedStatement.setInt(3, obj.getCategorie().getId());
            preparedStatement.setInt(4, obj.getQuantite());
            preparedStatement.setString(5, obj.getDateMES());
            preparedStatement.setInt(6, obj.getId());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return requetOK;
    }

    @Override
    public Medicament find(Integer mID) {

        CategorieMedicamentDAO categorieMedicamentDAO = new CategorieMedicamentDAO();
        
        StringBuilder sqlFindMedicament = new StringBuilder();
        sqlFindMedicament.append("select * from medicament ");
        sqlFindMedicament.append("where medic_id = ?");


        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindMedicament.toString())) {

            preparedStatement.setInt(1, mID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                return new Medicament(resultSet.getInt("medic_id"),
                        resultSet.getString("medic_nom"),
                        resultSet.getFloat("medic_prix"),
                        resultSet.getString("medic_dateMES"),
                        resultSet.getInt("medic_qteStock"),
                        categorieMedicamentDAO.find(resultSet.getInt("medic_categorie"))
                );
            }

        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return null;
    }

    @Override
    public ArrayList<Medicament> findAll() {
        ArrayList<Medicament> listMedicament = new ArrayList<>();

        CategorieMedicamentDAO categorieMedicamentDAO = new CategorieMedicamentDAO();
        
        StringBuilder sqlFindAllMedicament = new StringBuilder();
        sqlFindAllMedicament.append("select * from medicament ");


        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindAllMedicament.toString())) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                listMedicament.add(new Medicament(resultSet.getInt("medic_id"),
                        resultSet.getString("medic_nom"),
                        resultSet.getFloat("medic_prix"),
                        resultSet.getString("medic_dateMES"),
                        resultSet.getInt("medic_qteStock"),
                        categorieMedicamentDAO.find(resultSet.getInt("medic_categorie"))
                        ));
            }

            return listMedicament;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return null;
    }


    public boolean updateQte(Medicament obj){

            StringBuilder sqlUpdateMedicament = new StringBuilder();
            sqlUpdateMedicament.append("update medicament ");
            sqlUpdateMedicament.append("set medic_qteStock= medic_qteStock - ? ");
            sqlUpdateMedicament.append("where medic_id= ?");

            boolean requetOK = false;

            try (PreparedStatement preparedStatement =
                         this.connection.prepareStatement(sqlUpdateMedicament.toString())) {
                preparedStatement.setInt(1, obj.getQuantite());
                preparedStatement.setInt(2, obj.getId());

                preparedStatement.executeUpdate();
                requetOK = true;
            } catch (SQLException e) {
                System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
            }

            return requetOK;
    }
}
