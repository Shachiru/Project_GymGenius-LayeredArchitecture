package lk.ijse.pos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO <T> extends SuperDAO{
    T search(String id) throws SQLException;

    public String generateNextId() throws SQLException;

    String splitId(String id);

    ArrayList<T> getAll() throws SQLException;

    boolean save(T dto) throws SQLException;

    boolean update(T dto) throws SQLException;

    boolean delete(String id) throws SQLException;

    int count() throws SQLException;
}
