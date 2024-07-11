package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.PlaceOrderDTO;
import lk.ijse.pos.entity.PlaceOrder;

import java.sql.SQLException;

public interface PlaceOrderBO extends SuperBO {
    public  boolean orderPlaced(PlaceOrderDTO placeOrder) throws SQLException, ClassNotFoundException;
}
