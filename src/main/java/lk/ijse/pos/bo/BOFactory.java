package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.MemberBO;
import lk.ijse.pos.bo.custom.impl.*;

import static lk.ijse.pos.dao.DAOFactory.DAOTypes.EMPLOYEE;
import static lk.ijse.pos.dao.DAOFactory.DAOTypes.USER;

public class BOFactory {
    public static BOFactory boFactory;

    public BOFactory() {

    }

    public static BOFactory getInstance(){
        return (boFactory==null)? boFactory = new BOFactory():boFactory;
    }

    public enum BOType{
        EMPLOYEE,MEMBER,ORDER,ORDER_DETAILS,SUPPLEMENT,USER

    }

    public SuperBO getBOType(BOType type){
        switch (type){
            case MEMBER:
                return new MemberBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case USER:
                return new UserBOImpl();
            case SUPPLEMENT:
                return new SupplementBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ORDER_DETAILS:
                return new OrderDetailsBOImpl();
            default:
                return null;
        }
    }
}
