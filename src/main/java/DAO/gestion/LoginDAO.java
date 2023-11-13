package DAO.gestion;

import DAO.DAO;
import classMetier.gestion.Login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginDAO extends DAO<Login> {
    @Override
    public boolean create(Login obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Login obj) {
        return false;
    }

    @Override
    public boolean update(Login obj) throws SQLException {
        return false;
    }

    @Override
    public Login find(Integer pID) {
        return null;
    }

    @Override
    public ArrayList<Login> findAll() {
        return null;
    }
    
    public boolean loginAdmin(String pword, String username){
        StringBuilder sqlLogin = new StringBuilder();
        sqlLogin.append("select * from login ");
        sqlLogin.append("where log_username = ? and log_password = ?");


        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlLogin.toString())) {

            preparedStatement.setString(1,username);
            preparedStatement.setString(2,pword);

            ResultSet resulset = preparedStatement.executeQuery();

            while(resulset.next()){
                return true;
            }

        } catch (SQLException e) {
            System.out.println("RelationWithDB erreur : " + e.getMessage() + " [SQL error code : " + e.getSQLState() + "]");
        }
        return false;
    }
    
}
