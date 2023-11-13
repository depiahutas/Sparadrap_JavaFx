package DAO.sante;

import DAO.DAO;
import classMetier.sante.CategorieMedicament;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategorieMedicamentDAO extends DAO<CategorieMedicament> {
    @Override
    public boolean create(CategorieMedicament obj) {
        return false;
    }

    @Override
    public boolean delete(CategorieMedicament obj) {
        return false;
    }

    @Override
    public boolean update(CategorieMedicament obj) throws SQLException {
        return false;
    }

    @Override
    public CategorieMedicament find(Integer pID) {
        StringBuilder sqlFindCatMedic = new StringBuilder();
        sqlFindCatMedic.append("select * from categorie_medic where cat_id= ? ");

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindCatMedic.toString())) {

            preparedStatement.setInt(1,pID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                       return new CategorieMedicament(resultSet.getInt("cat_id"),
                        resultSet.getString("cat_libelle")
                       );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<CategorieMedicament> findAll() {
        ArrayList<CategorieMedicament> listCategorie = new ArrayList<>();

        StringBuilder sqlFindAllCatMedic = new StringBuilder();
        sqlFindAllCatMedic.append("select * from categorie_medic ");

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindAllCatMedic.toString())) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                listCategorie.add(new CategorieMedicament(
                        resultSet.getInt("cat_id"),
                        resultSet.getString("cat_libelle")
                ));
            }
            return listCategorie;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CategorieMedicament findLibelle(String libelle) {

        StringBuilder sqlFindCatMedic = new StringBuilder();
        sqlFindCatMedic.append("select * from categorie_medic where cat_libelle= ? ");

        try (PreparedStatement preparedStatement =
                     this.connection.prepareStatement(sqlFindCatMedic.toString())) {

            preparedStatement.setString(1,libelle);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                return new CategorieMedicament(resultSet.getInt("cat_id"),
                        resultSet.getString("cat_libelle")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
