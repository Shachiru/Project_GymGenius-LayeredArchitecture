package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserBO extends SuperBO {
    boolean verifyCredentials(String username, String password) throws SQLException, ClassNotFoundException;
}
