package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails> {
    public boolean save(List<OrderDetails> orderDetailsList) throws SQLException, ClassNotFoundException;
}
