package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.SupplementBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.SupplementDAO;
import lk.ijse.pos.dto.SupplementDTO;
import lk.ijse.pos.entity.Supplement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplementBOImpl implements SupplementBO {
    SupplementDAO supplementDAO = (SupplementDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOTypes.SUPPLEMENT);

    @Override
    public ResultSet generateNextIdSupplement() throws SQLException, ClassNotFoundException {
        return supplementDAO.generateNextId();
    }

    @Override
    public ArrayList<SupplementDTO> getAllSupplement() throws SQLException, ClassNotFoundException {
        ArrayList<SupplementDTO> allSupplements = new ArrayList<>();
        ArrayList<Supplement> all = supplementDAO.getAll();
        for (Supplement s: all){
            allSupplements.add(new SupplementDTO(s.getId(),s.getProductName(),s.getUnitPrice(),s.getQty()));
        }
        return allSupplements;
    }

    @Override
    public boolean saveSupplement(SupplementDTO dto) throws SQLException, ClassNotFoundException {
        return supplementDAO.save(new Supplement(dto.getId(),dto.getProductName(),dto.getUnitPrice(),dto.getQty()));
    }

    @Override
    public boolean updateSupplement(SupplementDTO dto) throws SQLException, ClassNotFoundException {
        return supplementDAO.update(new Supplement(dto.getId(),dto.getProductName(),dto.getUnitPrice(),dto.getQty()));
    }

    @Override
    public boolean deleteSupplement(String id) throws SQLException, ClassNotFoundException {
        return supplementDAO.delete(id);
    }

    @Override
    public int countSupplement() throws SQLException, ClassNotFoundException {
        return supplementDAO.count();
    }
}
