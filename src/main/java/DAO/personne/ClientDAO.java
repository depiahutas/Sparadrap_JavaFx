package DAO.personne;

import DAO.DAO;
import DAO.sante.MutuelleDAO;
import classMetier.personne.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientDAO extends DAO<Client> {
    @Override
    public boolean create(Client obj) {
        StringBuilder sqlInsertClient = new StringBuilder();
        sqlInsertClient.append("insert into Client ");
        sqlInsertClient.append("(cli_id,cli_per,cli_dateNaiss,cli_medecin,cli_mutuelle,cli_numSecu)");
        sqlInsertClient.append("values (null,?,?,?,?,?)");

        boolean requetOK = false;
        
        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlInsertClient.toString())) {
            preparedStatement.setInt(1,obj.getPersonne().getId());
            preparedStatement.setString(2,obj.getDateNaiss());
            preparedStatement.setInt(3,obj.getMedecin().getId());
            preparedStatement.setInt(4,obj.getMutuelle().getId());
            preparedStatement.setString(5,obj.getNumSecu());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }


        return requetOK;
    }

    @Override
    public boolean delete(Client obj) {
        StringBuilder sqlDeleteClient = new StringBuilder();
        sqlDeleteClient.append("delete from client ");
        sqlDeleteClient.append("where cli_id = ?");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlDeleteClient.toString())) {
            preparedStatement.setInt(1, obj.getIdClient());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return requetOK;
    }

    @Override
    public boolean update(Client obj) throws SQLException {
        StringBuilder sqlUpdateClient = new StringBuilder();
        sqlUpdateClient.append("update Client ");
        sqlUpdateClient.append("set cli_dateNaiss = ?,cli_medecin = ?,cli_mutuelle = ?,cli_numSecu = ? ");
        sqlUpdateClient.append("where cli_id = ?");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlUpdateClient.toString())) {
            preparedStatement.setString(1, obj.getDateNaiss());
            preparedStatement.setInt(2, obj.getMedecin().getId());
            preparedStatement.setInt(3, obj.getMutuelle().getId());
            preparedStatement.setString(4, obj.getNumSecu());
            preparedStatement.setInt(5, obj.getIdClient());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return requetOK;
    }

    @Override
    public Client find(Integer cID) {

        personneDAO personneDAO = new personneDAO();
        MedecinDAO medecinDAO = new MedecinDAO();
        MutuelleDAO mutuelleDAO = new MutuelleDAO();


        StringBuilder sqlFindClient = new StringBuilder();
        sqlFindClient.append("select * from client ");
        sqlFindClient.append("where cli_id = ?");


        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindClient.toString())) {

            preparedStatement.setInt(1, cID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                 return new Client(resultSet.getInt("cli_id"),
                        personneDAO.find(resultSet.getInt("cli_per")),
                        resultSet.getString("cli_dateNaiss"),
                        medecinDAO.find(resultSet.getInt("cli_medecin")),
                        mutuelleDAO.find(resultSet.getInt("cli_mutuelle")),
                        resultSet.getString("cli_numSecu")
                );

            }

        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return null;
    }

    @Override
    public ArrayList<Client> findAll(){
        ArrayList<Client> listClient = new ArrayList<>();

        personneDAO personneDAO = new personneDAO();
        MedecinDAO medecinDAO = new MedecinDAO();
        MutuelleDAO mutuelleDAO = new MutuelleDAO();


        StringBuilder sqlFindAllClient = new StringBuilder();
        sqlFindAllClient.append("select * from client ");


        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindAllClient.toString())) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                listClient.add(new Client(resultSet.getInt("cli_id"),
                        personneDAO.find(resultSet.getInt("cli_per")),
                        resultSet.getString("cli_dateNaiss"),
                        medecinDAO.find(resultSet.getInt("cli_medecin")),
                        mutuelleDAO.find(resultSet.getInt("cli_mutuelle")),
                        resultSet.getString("cli_numSecu")
                ));
            }

            return listClient;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return null;
    }

    public Client verif_client(Client client){

        StringBuilder sqlFindClient = new StringBuilder();
        sqlFindClient.append("select * from client ");
        sqlFindClient.append("where cli_dateNaiss = ? and cli_numSecu like ?");


        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindClient.toString())) {

            preparedStatement.setString(1, client.getDateNaiss());
            preparedStatement.setString(2, client.getNumSecu());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                if ((client.getDateNaiss().equals(resultSet.getString("cli_dateNaiss")) &&
                        client.getNumSecu().equals(resultSet.getString("cli_numSecu"))) ||
                        client.getPersonne().getId()==resultSet.getInt("cli_per")
                ) {
                    client.setIdClient(resultSet.getInt("cli_id"));
                    return client;
                }
            }

            if (create(client)) {
                int c=count();
                client.setIdClient(c);
                return client;
            }

        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return null;
    }



    public int count(){
        StringBuilder sqlCountClient = new StringBuilder();
        sqlCountClient.append("select count(*) as nb_cli from Client ");
        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlCountClient.toString())) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getInt("nb_cli");
            }

        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }
        return -1;
    }
}
