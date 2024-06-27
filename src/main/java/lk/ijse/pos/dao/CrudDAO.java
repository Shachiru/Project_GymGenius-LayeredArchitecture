package lk.ijse.pos.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    public String generateNextId() throws SQLException;

    public String splitId(String id);

    public ArrayList<T> getAll() throws SQLException;

    public boolean save(T entity) throws SQLException;

    public boolean update(T entity) throws SQLException;

    public boolean delete(String id) throws SQLException;

    public int count() throws SQLException;
}
