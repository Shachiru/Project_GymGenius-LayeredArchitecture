package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.SupplementDAO;
import lk.ijse.pos.dto.SupplementDTO;
import lk.ijse.pos.entity.Member;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.entity.Supplement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplementDAOImpl implements SupplementDAO {
    @Override
    public ResultSet generateNextId() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT ID from supplements order by ID desc limit 1");
    }

    @Override
    public ArrayList<Supplement> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Supplement> allSupplements = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplements");
        while (rst.next()){
            Supplement supplement = new Supplement(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4));
            allSupplements.add(supplement);
        }
        return allSupplements;
    }

    @Override
    public boolean save(Supplement entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO supplements VALUES(?,?,?,?)",
                entity.getId(),
                entity.getProductName(),
                entity.getUnitPrice(),
                entity.getQty());
    }

    @Override
    public boolean update(Supplement entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE supplements SET Description=?, UnitPrice=?, Qty=? WHERE ID=?",
                entity.getProductName(),
                entity.getUnitPrice(),
                entity.getQty(),
                entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE from supplements where ID = ?",id);
    }

    @Override
    public int count() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT count(ID) as sup_count from supplements");
        if (rst.next()){
            int supCount = Integer.parseInt(rst.getString(1));
            return supCount;
        }
        return Integer.parseInt(null);
    }

    @Override
    public List<String> getSupIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT ID from supplements");
        List<String> supIds = new ArrayList<>();
        while (rst.next()){
            supIds.add(rst.getString(1));
        }
        return supIds;
    }

    @Override
    public Supplement search(String supplementId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * from supplements where ID = ?",supplementId);
        Supplement supplement = null;
        if (rst.next()) {
            String id = rst.getString(1);
            String name = rst.getString(2);
            String unitPrice = rst.getString(3);
            String qty = rst.getString(4);
            supplement = new Supplement(id, name, unitPrice, qty);
        }
        return supplement;
    }

    @Override
    public boolean updateSupplementQty(List<OrderDetails> orderDetail) throws SQLException, ClassNotFoundException {
        for (OrderDetails od : orderDetail){
            if(!updateSupplementQty(od)){
                return false;
            }
        }
        return true;
    }

    private boolean updateSupplementQty(OrderDetails od) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE supplements set Qty = Qty - ? where ID = ? ",
                od.getQty(),
                od.getSupplement_id());
    }
}
