package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public ResultSet generateNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(List<OrderDetails> orderDetailsList) throws SQLException, ClassNotFoundException {
//        List<OrderDetails> orderDetailsList
            for (OrderDetails od : orderDetailsList){
                if(!orderSave(od)){
                    return false;
                }
            }
            return true;
        }

    private  boolean orderSave(OrderDetails od) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("Insert into order_detail values (?,?,?,?,?)",
                od.getOrderId(),
                od.getSupplement_id(),
                od.getQty(),
                od.getUnitPrice(),
                od.getTotal());
    }

    @Override
    public boolean update(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
    @Override
    public int count() throws SQLException, ClassNotFoundException {
        return 0;
    }
}
