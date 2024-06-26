package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.SupplementDAO;
import lk.ijse.pos.entity.Supplement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplementDAOImpl implements SupplementDAO {
    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("Select ID from supplements order by ID desc limit 1");
        if (rst.next()){
            String id = rst.getString("ID");
            int newSupId = Integer.parseInt(id.replace("Sup00-","")) + 1;
            return String.format("Sup00-%03d", newSupId);
        }else {
            return "Sup00-001";
        }
    }

    @Override
    public ArrayList<Supplement> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Supplement> allSupplements = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplements");
        while (rst.next()){
            Supplement supplement = new Supplement(
                    rst.getString("id"),
                    rst.getString("productName"),
                    rst.getString("unitPrice"),
                    rst.getString("qty"));
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
        ResultSet rst = SQLUtil.execute("Select count(ID) as sup_count from supplements");
        if (rst.next()){
            int supCount = Integer.parseInt(rst.getString("supplement_count"));
            return supCount;
        }
        return Integer.parseInt(null);
    }
    ///////////  thawa updateQTY method ekak ithurui    ////////////
}
