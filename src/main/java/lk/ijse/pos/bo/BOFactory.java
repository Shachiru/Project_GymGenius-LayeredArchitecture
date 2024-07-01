package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.MemberBO;
import lk.ijse.pos.bo.custom.impl.MemberBOImpl;

public class BOFactory {
    public static BOFactory boFactory;

    public BOFactory() {

    }

    public enum BOType{
        MEMBER
    }

    public static BOFactory getInstance(){
        return (boFactory==null)? boFactory = new BOFactory():boFactory;
    }

    public SuperBO getBOType(BOType type){
        switch (type){
            case MEMBER:
                return new MemberBOImpl();
            default:
                return null;
        }
    }
}
