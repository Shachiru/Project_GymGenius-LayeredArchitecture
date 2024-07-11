package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.bo.custom.PlaceOrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dao.custom.SupplementDAO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.PlaceOrderDTO;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.entity.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOTypes.ORDER);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOTypes.ORDER_DETAILS);
    SupplementDAO supplementDAO = (SupplementDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOTypes.SUPPLEMENT);

    public  boolean orderPlaced(PlaceOrderDTO placeOrder) throws SQLException, ClassNotFoundException {

        Order order = new Order(placeOrder.getOrder().getOrderId(),
                placeOrder.getOrder().getDate(),
                placeOrder.getOrder().getMemberId());

        List<OrderDetails> orderDetails = new ArrayList<>(
                placeOrder.getOrderDetail().stream().map(detail -> new OrderDetails(detail.getOrderId(),detail.getSupplement_id(),
                        detail.getQty(),detail.getUnitPrice(),detail.getTotal())).toList(
                )
        );


        Connection connection = DBConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean isOrderSaved = orderDAO.save(order);
            if (isOrderSaved) {

                boolean isOrderDetailSaved = orderDetailsDAO.save(orderDetails);
                if (isOrderDetailSaved) {

                    boolean isSupplementUpdated =supplementDAO.updateSupplementQty(orderDetails);
                    if (isSupplementUpdated) {

                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        }catch (SQLException e) {
            connection.rollback();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
