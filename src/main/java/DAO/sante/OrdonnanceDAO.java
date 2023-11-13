package DAO.sante;

import DAO.DAO;
import DAO.gestion.ComposeDAO;
import DAO.personne.ClientDAO;
import DAO.personne.MedecinDAO;
import classMetier.sante.Ordonnance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdonnanceDAO extends DAO<Ordonnance> {
    @Override
    public boolean create(Ordonnance obj) {
        StringBuilder sqlInsertOrdonnance = new StringBuilder();
        sqlInsertOrdonnance.append("insert into Ordonnance ");
        sqlInsertOrdonnance.append("(ord_id,ord_med,ord_cli,ord_date) ");
        sqlInsertOrdonnance.append("values (null,?,?,?)");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlInsertOrdonnance.toString())) {
            preparedStatement.setInt(2, obj.getMedecin().getId());
            preparedStatement.setInt(3, obj.getClient().getIdClient());
            preparedStatement.setString(4, obj.getDate());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return requetOK;
    }

    @Override
    public boolean delete(Ordonnance obj) {
        StringBuilder sqlDeleteOrdonnance = new StringBuilder();
        sqlDeleteOrdonnance.append("delete from Ordonnance ");
        sqlDeleteOrdonnance.append("where ord_id = ?");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlDeleteOrdonnance.toString())) {
            preparedStatement.setInt(1, obj.getId());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return requetOK;
    }

    @Override
    public boolean update(Ordonnance obj) throws SQLException {
        StringBuilder sqlUpdateOrdonnance = new StringBuilder();
        sqlUpdateOrdonnance.append("update Ordonnance ");
        sqlUpdateOrdonnance.append("set ord_med=?,ord_cli=?,ord_date=?)");
        sqlUpdateOrdonnance.append("where per_id=?");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlUpdateOrdonnance.toString())) {
            preparedStatement.setInt(1, obj.getMedecin().getId());
            preparedStatement.setInt(2, obj.getClient().getIdClient());
            preparedStatement.setString(3, obj.getDate());
            preparedStatement.setInt(4, obj.getId());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return requetOK;
    }

    @Override
    public Ordonnance find(Integer pID) {


        MedecinDAO medecinDAO = new MedecinDAO();
        ClientDAO clientDAO =  new ClientDAO();
        ComposeDAO composeDAO = new ComposeDAO();
        
        StringBuilder sqlFindOrdonnance = new StringBuilder();
        sqlFindOrdonnance.append("select * from Ordonnance ");
        sqlFindOrdonnance.append("where ord_id = ?");


        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindOrdonnance.toString())) {

            preparedStatement.setInt(1, pID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                return new Ordonnance(
                        medecinDAO.find(resultSet.getInt("ord_med")),
                        clientDAO.find(resultSet.getInt("ord_cli")),
                        composeDAO.find(resultSet.getInt("ord_id")),
                        resultSet.getString("ord_date"),
                        resultSet.getInt("ord_id")
                );
            }

        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return null;
    }

    @Override
    public ArrayList<Ordonnance> findAll() {
        ArrayList<Ordonnance> listOrdonnace = new ArrayList<>();

        MedecinDAO medecinDAO = new MedecinDAO();
        ClientDAO clientDAO =  new ClientDAO();
        ComposeDAO composeDAO = new ComposeDAO();

        StringBuilder sqlFindOrdonnance = new StringBuilder();
        sqlFindOrdonnance.append("select * from Ordonnance ");


        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindOrdonnance.toString())) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                listOrdonnace.add(new Ordonnance(
                        medecinDAO.find(resultSet.getInt("ord_med")),
                        clientDAO.find(resultSet.getInt("ord_cli")),
                        composeDAO.find(resultSet.getInt("ord_id")),
                        resultSet.getString("ord_date"),
                        resultSet.getInt("ord_id")
                ));
            }

            return listOrdonnace;

        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return null;
    }
}
