package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.UserBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.UserDAO;
import lk.ijse.pos.dao.custom.impl.UserDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOTypes.USER);
    @Override
    public boolean verifyCredentials(String username, String password) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = userDAO.verifyCredentials(username,password);
        String pw = "";
        if (resultSet.next()){
            pw = resultSet.getString(1);
            if (pw.equals(password)){
                return true;
            }
        }
        return false;
    }
}
