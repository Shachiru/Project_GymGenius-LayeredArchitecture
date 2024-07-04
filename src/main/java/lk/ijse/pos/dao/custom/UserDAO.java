package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {
    public ResultSet verifyCredentials(String username, String password) throws SQLException, ClassNotFoundException;
}
