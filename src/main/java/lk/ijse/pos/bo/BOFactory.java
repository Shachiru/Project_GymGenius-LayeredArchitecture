package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.MemberBO;
import lk.ijse.pos.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.pos.bo.custom.impl.MemberBOImpl;
import lk.ijse.pos.bo.custom.impl.SupplementBOImpl;
import lk.ijse.pos.bo.custom.impl.UserBOImpl;

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
            default:
                return null;
        }
    }
}
