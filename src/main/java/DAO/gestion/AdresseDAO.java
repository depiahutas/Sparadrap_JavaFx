package DAO.gestion;

import DAO.DAO;
import classMetier.gestion.Adresse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdresseDAO  extends DAO<Adresse> {
    @Override
    public boolean create(Adresse obj) {

        StringBuilder sqlInsertAdresse = new StringBuilder();
        sqlInsertAdresse.append("insert into adresse ");
        sqlInsertAdresse.append("(adr_id,adr_num,adr_rue,adr_cp,adr_ville)");
        sqlInsertAdresse.append("values (null,?,?,?,?)");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlInsertAdresse.toString())) {
            preparedStatement.setInt(1, obj.getNumero());
            preparedStatement.setString(2, obj.getRue());
            preparedStatement.setString(3, obj.getCodePostal());
            preparedStatement.setString(4, obj.getVille());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return requetOK;
    }

    @Override
    public boolean delete(Adresse obj) {
        StringBuilder sqlDeleteAdresse = new StringBuilder();
        sqlDeleteAdresse.append("delete from adresse ");
        sqlDeleteAdresse.append("where adr_id = ?");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlDeleteAdresse.toString())) {
            preparedStatement.setInt(1, obj.getID());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return requetOK;
    }

    @Override
    public boolean update(Adresse obj) {
        StringBuilder sqlUpdateAdresse = new StringBuilder();
        sqlUpdateAdresse.append("update adresse ");
        sqlUpdateAdresse.append("set adr_numero =?,adr_rue = ?,adr_cp=?,adr_ville=?)");
        sqlUpdateAdresse.append("where adr_id=?");

        boolean requetOK = false;

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlUpdateAdresse.toString())) {
            preparedStatement.setInt(1, obj.getNumero());
            preparedStatement.setString(2, obj.getRue());
            preparedStatement.setString(3, obj.getCodePostal());
            preparedStatement.setInt(5, obj.getID());
            preparedStatement.setString(6, obj.getVille());

            preparedStatement.executeUpdate();
            requetOK = true;
        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return requetOK;
    }

    @Override
    public Adresse find(Integer aID) {
        StringBuilder sqlFindAdresse = new StringBuilder();
        sqlFindAdresse.append("select * from adresse ");
        sqlFindAdresse.append("where adr_id = ?");


        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindAdresse.toString())) {

            preparedStatement.setInt(1, aID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                return new Adresse(resultSet.getInt("adr_id"),
                        resultSet.getInt("adr_num"),
                        resultSet.getString("adr_rue"),
                        resultSet.getString("adr_cp"),
                        resultSet.getString("adr_ville")
                        );
            }

        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return null;
    }

    public ArrayList<Adresse> findAll() {
        ArrayList<Adresse> listAdresse = new ArrayList<>();

        StringBuilder sqlFindAllAdresse = new StringBuilder();
        sqlFindAllAdresse.append("select * from adresse ");

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindAllAdresse.toString())) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                listAdresse.add(new Adresse(
                        resultSet.getInt("adr_id"),
                                resultSet.getInt("adr_num"),
                                resultSet.getString("adr_rue"),
                                resultSet.getString("adr_cp"),
                                resultSet.getString("adr_ville")
                        ));
            }
            return listAdresse;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Adresse verif_adr(Adresse adresse){

        StringBuilder sqlFindAdresse = new StringBuilder();
        sqlFindAdresse.append("select * from adresse ");
        sqlFindAdresse.append("where adr_rue like ? and adr_ville like ?");


        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindAdresse.toString())) {

            preparedStatement.setString(1, adresse.getRue());
            preparedStatement.setString(2, adresse.getVille());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                if( adresse.getNumero() == resultSet.getInt("adr_num") &&
                        adresse.getCodePostal().equals(resultSet.getString("adr_CP"))
                ) {
                    adresse.setId(resultSet.getInt("adr_id"));
                    return adresse;
                }
            }

            create(adresse);
            adresse.setId(count()+1);

            return adresse;

        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }

        return null;
    }


    public int count(){
        StringBuilder sqlCountAdresse = new StringBuilder();
        sqlCountAdresse.append("select count(*) as nb_adr from Adresse ");
        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlCountAdresse.toString())) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getInt("nb_adr");
            }

        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }
        return -1;
    }
}
