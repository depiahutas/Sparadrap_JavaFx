package DAO.gestion;

import DAO.DAO;
import DAO.personne.ClientDAO;
import DAO.sante.OrdonnanceDAO;
import classMetier.gestion.Achat;
import classMetier.sante.Ordonnance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AchatDAO extends DAO<Achat> {
    @Override
    public boolean create(Achat obj){

        PanierDAO panierDAO = new PanierDAO();

        StringBuilder sqlInsertAchat = new StringBuilder();
        if (obj.getOrdonnance()!=null) {

        sqlInsertAchat.append("insert into Achat ");
        sqlInsertAchat.append("(ach_id,ach_cli,ach_date,ach_prixTot,ach_ord)");
        sqlInsertAchat.append("values (null,?,?,?,?)");
        }
        else {
            sqlInsertAchat.append("insert into Achat ");
            sqlInsertAchat.append("(ach_id,ach_cli,ach_date,ach_prixTot,ach_ord)");
            sqlInsertAchat.append("values (null,?,?,?,null)");
        }
        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlInsertAchat.toString())) {
            preparedStatement.setInt(1, obj.getClient().getIdClient());
            preparedStatement.setString(2, obj.getDate());
            preparedStatement.setFloat(3, obj.getPrix());
            if (obj.getOrdonnance()!=null) {
                preparedStatement.setInt(4, obj.getOrdonnance().getId());
            }



            preparedStatement.executeUpdate();

            panierDAO.create(obj.getPanier(),count());

            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return requetOK;
    }

    @Override
    public boolean delete(Achat obj) {
        StringBuilder sqlDeleteAchat = new StringBuilder();
        sqlDeleteAchat.append("delete from Achat ");
        sqlDeleteAchat.append("where ach_id = ?");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlDeleteAchat.toString())) {
            preparedStatement.setInt(1, obj.getId());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return requetOK;
    }

    @Override
    public boolean update(Achat obj) throws SQLException {

        StringBuilder sqlUpdateAchat = new StringBuilder();
        sqlUpdateAchat.append("update Achat ");
        sqlUpdateAchat.append("set ach_cli=?,ach_prixTot=?,ach_date=?,ach_ord=?)");
        sqlUpdateAchat.append("where ach_id=?");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlUpdateAchat.toString())) {
            preparedStatement.setInt(1, obj.getClient().getIdClient());
            preparedStatement.setFloat(2, obj.getPrix());
            preparedStatement.setString(3, obj.getDate());
            preparedStatement.setInt(4, obj.getOrdonnance().getId());
            preparedStatement.setInt(5, obj.getId());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return requetOK;
    }

    @Override
    public Achat find(Integer pID) {
        ClientDAO clientDAO = new ClientDAO();
        OrdonnanceDAO ordonnanceDAO = new OrdonnanceDAO();
        PanierDAO panierDAO = new PanierDAO();

        StringBuilder sqlFindAchat = new StringBuilder();
        sqlFindAchat.append("select * from achat ");
        sqlFindAchat.append("where ach_id = ? ");


        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindAchat.toString())) {

            preparedStatement.setInt(1, pID);
            ResultSet resultSet = preparedStatement.executeQuery();

            Ordonnance ord=ordonnanceDAO.find(resultSet.getInt("ach_ord"));


            while (resultSet.next()) {

               return new Achat(resultSet.getInt("ach_id"),
                        clientDAO.find(resultSet.getInt("ach_cli")),
                        panierDAO.find(resultSet.getInt("ach_id")),
                        resultSet.getFloat("ach_prixTot"),
                        resultSet.getString("ach_date"),
                        ord

                );
            }

            return null;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return null;
    }

    @Override
    public ObservableList<Achat> findAll() {
        ObservableList<Achat> listAchat = FXCollections.observableArrayList();

        ClientDAO clientDAO = new ClientDAO();
        OrdonnanceDAO ordonnanceDAO = new OrdonnanceDAO();
        PanierDAO panierDAO = new PanierDAO();

        StringBuilder sqlFindAllAchat = new StringBuilder();
        sqlFindAllAchat.append("select * from achat ");


        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindAllAchat.toString())) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                listAchat.add(new Achat(resultSet.getInt("ach_id"),
                        clientDAO.find(resultSet.getInt("ach_cli")),
                        panierDAO.find(resultSet.getInt("ach_id")),
                        resultSet.getFloat("ach_prixTot"),
                        resultSet.getString("ach_date"),
                        ordonnanceDAO.find(resultSet.getInt("ach_ord"))
                ));
            }

            return listAchat;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return null;
    }

    public int count(){

        StringBuilder sqlCountClient = new StringBuilder();
        sqlCountClient.append("select count(*) as nb_ach from Achat ");
        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlCountClient.toString())) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getInt("nb_ach");
            }

        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }
        return -1;
    }
}
