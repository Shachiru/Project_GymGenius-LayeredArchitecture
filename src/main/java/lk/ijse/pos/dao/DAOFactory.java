package lk.ijse.pos.dao;

import lk.ijse.pos.dao.custom.EmployeeDAO;
import lk.ijse.pos.dao.custom.MemberDAO;
import lk.ijse.pos.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.pos.dao.custom.impl.MemberDAOImpl;
import lk.ijse.pos.dao.custom.impl.SupplementDAOImpl;
import lk.ijse.pos.dao.custom.impl.UserDAOImpl;

import java.security.PublicKey;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        EMPLOYEE,MEMBER,ORDER,ORDER_DETAILS,SUPPLEMENT,USER
    }

public SuperDAO getDAOType(DAOTypes type) {
    switch (type) {
        case MEMBER:
            return new MemberDAOImpl();
        case EMPLOYEE:
            return new EmployeeDAOImpl();
        case USER:
            return new UserDAOImpl();
        case SUPPLEMENT:
            return new SupplementDAOImpl();
        default:
            return null;
    }
}
}
