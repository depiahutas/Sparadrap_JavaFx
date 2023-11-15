package DAO.personne;

import DAO.DAO;
import DAO.gestion.AdresseDAO;
import classMetier.personne.Personne;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class personneDAO extends DAO<Personne> {

    /*
        Creation d'une personne dans la bdd a partir d'un objet Personne
     */
    @Override
    public boolean create(Personne obj) {
        StringBuilder sqlInsertPersonne = new StringBuilder();
        sqlInsertPersonne.append("insert into personne ");
        sqlInsertPersonne.append("(per_id,per_nom,per_prenom,per_mail,per_tel,per_adresse)");
        sqlInsertPersonne.append("values (null,?,?,?,?,?)");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlInsertPersonne.toString())) {
            preparedStatement.setString(1, obj.getNom());
            preparedStatement.setString(2, obj.getPrenom());
            preparedStatement.setString(3, obj.getMail());
            preparedStatement.setString(4, obj.getTel());
            preparedStatement.setInt(5, obj.getAdresse().getID());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return requetOK;
    }

    @Override
    public boolean delete(Personne obj) {
        StringBuilder sqlDeletePersonne = new StringBuilder();
        sqlDeletePersonne.append("delete from personne ");
        sqlDeletePersonne.append("where per_id = ?");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlDeletePersonne.toString())) {
            preparedStatement.setInt(1, obj.getId());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return requetOK;
    }

    @Override
    public boolean update(Personne obj) {
        StringBuilder sqlUpdatePersonne = new StringBuilder();
        sqlUpdatePersonne.append("update personne ");
        sqlUpdatePersonne.append("set per_nom = ?,per_prenom = ?,per_mail = ?,per_tel = ?,per_adresse = ? ");
        sqlUpdatePersonne.append("where per_id = ?");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlUpdatePersonne.toString())) {
            preparedStatement.setString(1, obj.getNom());
            preparedStatement.setString(2, obj.getPrenom());
            preparedStatement.setString(3, obj.getMail());
            preparedStatement.setString(4, obj.getTel());
            preparedStatement.setInt(5, obj.getAdresse().getID());
            preparedStatement.setInt(6, obj.getId());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return requetOK;
    }

    @Override
    public Personne find(Integer pID) {

        AdresseDAO adresseDAO = new AdresseDAO();

        StringBuilder sqlFindPersonne = new StringBuilder();
        sqlFindPersonne.append("select * from personne ");
        sqlFindPersonne.append("where per_id = ?");


        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindPersonne.toString())) {

            preparedStatement.setInt(1, pID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                return new Personne(resultSet.getInt("per_id"),
                        resultSet.getString("per_nom"),
                        resultSet.getString("per_prenom"),
                        resultSet.getString("per_mail"),
                        resultSet.getString("per_tel"),
                        adresseDAO.find(resultSet.getInt("per_adresse"))
                );
            }

        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return null;
    }

    @Override
    public ObservableList<Personne> findAll() {
        ObservableList<Personne> listPersonne = FXCollections.observableArrayList();

        AdresseDAO adresseDAO = new AdresseDAO();

        StringBuilder sqlFindAllPersonne = new StringBuilder();
        sqlFindAllPersonne.append("select * from personne ");

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindAllPersonne.toString())) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                listPersonne.add(new Personne(resultSet.getInt("per_id"),
                        resultSet.getString("per_nom"),
                        resultSet.getString("per_prenom"),
                        resultSet.getString("per_mail"),
                        resultSet.getString("per_tel"),
                        adresseDAO.find(resultSet.getInt("per_Adresse"))
                ));
            }
            return listPersonne;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Personne verif_per(Personne personne) {

        StringBuilder sqlFindPersonne = new StringBuilder();
        sqlFindPersonne.append("select * from Personne ");
        sqlFindPersonne.append("where per_nom like ? and per_prenom like ?");


        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindPersonne.toString())) {

            preparedStatement.setString(1, personne.getNom());
            preparedStatement.setString(2, personne.getPrenom());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                if (personne.getMail().equals(resultSet.getString("per_mail")) ||
                        personne.getTel().equals(resultSet.getString("per_tel"))
                    ) {
                    personne.setId(resultSet.getInt("per_id"));
                    return personne;
                }
            }

            if (create(personne)) {
                int c=count();
                personne.setId(c);
                return personne;
            }

        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return null;
    }

    public int count() {
        StringBuilder sqlCountPersonne = new StringBuilder();
        sqlCountPersonne.append("select count(*) as nb_per from Personne ");
        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlCountPersonne.toString())) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                    return resultSet.getInt("nb_per");
            }

        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }
        return -1;
    }

}