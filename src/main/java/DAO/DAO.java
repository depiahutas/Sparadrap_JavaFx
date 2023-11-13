package DAO;

import classMetier.Util.Singleton;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class DAO<T> {

    protected Connection connection = Singleton.getInstanceDB();

    public abstract boolean create(T obj) throws SQLException;

    public abstract boolean delete(T obj);

    public abstract boolean update(T obj) throws SQLException;

    public abstract T find(Integer pID);

    public abstract ArrayList<T> findAll();
}
