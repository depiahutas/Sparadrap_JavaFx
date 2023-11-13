package DAO.personne;

import DAO.DAO;
import classMetier.personne.Medecin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedecinDAO extends DAO<Medecin> {
    @Override
    public boolean create(Medecin obj) {

        StringBuilder sqlInsertMedecin = new StringBuilder();
        sqlInsertMedecin.append("insert into Medecin ");
        sqlInsertMedecin.append("(med_id,med_per,med_numAgr)");
        sqlInsertMedecin.append("values (null,?,?)");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlInsertMedecin.toString())) {
            preparedStatement.setInt(1,obj.getPersonne().getId());
            preparedStatement.setString(2,obj.getNumAgr());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return requetOK;
    }

    @Override
    public boolean delete(Medecin obj) {

        StringBuilder sqlDeleteMedecin = new StringBuilder();
        sqlDeleteMedecin.append("delete from medecin ");
        sqlDeleteMedecin.append("where med_id = ?");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlDeleteMedecin.toString())) {
            preparedStatement.setInt(1, obj.getId());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return requetOK;
    }

    @Override
    public boolean update(Medecin obj) {

        StringBuilder sqlUpdateMedecin = new StringBuilder();
        sqlUpdateMedecin.append("update medecin ");
        sqlUpdateMedecin.append("set med_numAgr = ?");
        sqlUpdateMedecin.append("where med_id=?");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlUpdateMedecin.toString())) {
            preparedStatement.setString(1, obj.getNumAgr());
            preparedStatement.setInt(2, obj.getId());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return requetOK;
    }

    @Override
    public Medecin find(Integer mID) {

        personneDAO personneDAO = new personneDAO();


        StringBuilder sqlFindMedecin = new StringBuilder();
        sqlFindMedecin.append("select * from medecin ");
        sqlFindMedecin.append("where med_id = ?");


        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindMedecin.toString())) {

            preparedStatement.setInt(1, mID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                return new Medecin(resultSet.getInt("med_id"),
                        personneDAO.find(resultSet.getInt("med_per")),
                        resultSet.getString("med_numAgr")
                );
            }

        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return null;
    }

    @Override
    public ArrayList<Medecin> findAll() {

        ArrayList<Medecin> listMedecin = new ArrayList<>();

        personneDAO personneDAO = new personneDAO();

        StringBuilder sqlFindAllMedecin = new StringBuilder();
        sqlFindAllMedecin.append("select * from medecin ");


        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindAllMedecin.toString())) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                listMedecin.add(new Medecin(resultSet.getInt("med_id"),
                        personneDAO.find(resultSet.getInt("med_per")),
                        resultSet.getString("med_numAgr")
                ));
            }

            return listMedecin;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return null;
    }

    public Medecin findMed(String nom){

        personneDAO personneDAO = new personneDAO();


        StringBuilder sqlFindMedecin = new StringBuilder();
        sqlFindMedecin.append("select * from medecin ");
        sqlFindMedecin.append("inner join personne on per_id=med_per ");
        sqlFindMedecin.append("where per_nom like ?");


        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindMedecin.toString())) {

            preparedStatement.setString(1, nom);
            ResultSet resultSet = preparedStatement.executeQuery();

            Medecin m = null;
            while (resultSet.next()) {

                m = new Medecin(resultSet.getInt("med_id"),
                        personneDAO.find(resultSet.getInt("med_per")),
                        resultSet.getString("med_numAgr")
                );
            }

            return m;

        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return null;
    }
}
