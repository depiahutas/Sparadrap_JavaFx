package DAO.sante;

import DAO.DAO;
import DAO.gestion.AdresseDAO;
import classMetier.sante.Mutuelle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MutuelleDAO extends DAO<classMetier.sante.Mutuelle> {

    @Override
    public boolean create(Mutuelle obj) {
        AdresseDAO adresseDAO = new AdresseDAO();
        
        StringBuilder sqlInsertMutuelle = new StringBuilder();
        sqlInsertMutuelle.append("insert into mutuelle ");
        sqlInsertMutuelle.append("(mut_id,mut_nom,mut_adresse,mut_tel,mut_mail,mut_txPECR)");
        sqlInsertMutuelle.append("values (null,?,?,?,?,?)");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlInsertMutuelle.toString())) {
            preparedStatement.setString(1,obj.getNom());
            preparedStatement.setInt(2,obj.getAdresse().getID());
            preparedStatement.setString(3,obj.getTel());
            preparedStatement.setString(4,obj.getMail());
            preparedStatement.setInt(5,obj.getTxPECR());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return requetOK;
    }

    @Override
    public boolean delete(Mutuelle obj) {
        StringBuilder sqlDeleteMutuelle = new StringBuilder();
        sqlDeleteMutuelle.append("delete from mutuelle ");
        sqlDeleteMutuelle.append("where mut_id = ?");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlDeleteMutuelle.toString())) {
            preparedStatement.setInt(1, obj.getId());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }
        return requetOK;
    }

    @Override
    public boolean update(Mutuelle obj) {
        StringBuilder sqlUpdateMutuelle = new StringBuilder();
        sqlUpdateMutuelle.append("update mutuelle ");
        sqlUpdateMutuelle.append("set mut_nom=?, mut_adresse=?, mut_tel = ?, mut_mail=?,mut_txPECR=?");
        sqlUpdateMutuelle.append("where mut_id=?");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlUpdateMutuelle.toString())) {
            preparedStatement.setString(1, obj.getNom());
            preparedStatement.setInt(2, obj.getAdresse().getID());
            preparedStatement.setString(3, obj.getTel());
            preparedStatement.setString(4, obj.getMail());
            preparedStatement.setInt(5, obj.getTxPECR());
            preparedStatement.setInt(6, obj.getId());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return requetOK;
    }

    @Override
    public Mutuelle find(Integer mID) {
        AdresseDAO adresseDAO = new AdresseDAO();


        StringBuilder sqlFindMutuelle = new StringBuilder();
        sqlFindMutuelle.append("select * from mutuelle ");
        sqlFindMutuelle.append("where mut_id = ?");


        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindMutuelle.toString())) {

            preparedStatement.setInt(1, mID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                return new Mutuelle(resultSet.getInt("mut_id"),
                        adresseDAO.find(resultSet.getInt("mut_adresse")),
                        resultSet.getString("mut_nom"),
                        resultSet.getString("mut_tel"),
                        resultSet.getString("mut_mail"),
                        resultSet.getInt("mut_txPECR")
                );
            }

        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return null;
    }

    @Override
    public ObservableList<Mutuelle> findAll() {
        ObservableList<Mutuelle> listMutuelle = FXCollections.observableArrayList();

        AdresseDAO adresseDAO = new AdresseDAO();

        StringBuilder sqlFindAllMutuelle = new StringBuilder();
        sqlFindAllMutuelle.append("select * from mutuelle ");


        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindAllMutuelle.toString())) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                listMutuelle.add(new Mutuelle(resultSet.getInt("mut_id"),
                        adresseDAO.find(resultSet.getInt("mut_adresse")),
                        resultSet.getString("mut_nom"),
                        resultSet.getString("mut_tel"),
                        resultSet.getString("mut_mail"),
                        resultSet.getInt("mut_txPECR")
                ));
            }

            return listMutuelle;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return null;
    }

    public Mutuelle findMut(String string) {

        AdresseDAO adresseDAO = new AdresseDAO();


        StringBuilder sqlFindMutuelle = new StringBuilder();
        sqlFindMutuelle.append("select * from mutuelle ");
        sqlFindMutuelle.append("where mut_nom = ?");


        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindMutuelle.toString())) {

            preparedStatement.setString(1, string);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                return new Mutuelle(resultSet.getInt("mut_id"),
                        adresseDAO.find(resultSet.getInt("mut_adresse")),
                        resultSet.getString("mut_nom"),
                        resultSet.getString("mut_tel"),
                        resultSet.getString("mut_mail"),
                        resultSet.getInt("mut_txPECR")
                );
            }

        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return null;
    }
}
