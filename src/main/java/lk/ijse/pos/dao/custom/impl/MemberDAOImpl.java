package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.MemberDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAOImpl implements MemberDAO {
    @Override
    public Object search(String id) throws SQLException {
        return null;
    }

    @Override
    public String generateNextId() throws SQLException {
        return "";
    }

    @Override
    public String splitId(String id) {
        return "";
    }

    @Override
    public ArrayList getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Object dto) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Object dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public int count() throws SQLException {
        return 0;
    }
}
