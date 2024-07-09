package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dto.SupplementDTO;
import lk.ijse.pos.entity.Supplement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplementBO extends SuperBO {
    ResultSet generateNextIdSupplement() throws SQLException, ClassNotFoundException;

    ArrayList<SupplementDTO> getAllSupplement() throws SQLException, ClassNotFoundException;

    boolean saveSupplement(SupplementDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateSupplement(SupplementDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteSupplement(String id) throws SQLException, ClassNotFoundException;

    int countSupplement() throws SQLException, ClassNotFoundException;

    List<String> getSupIds() throws SQLException, ClassNotFoundException;

    SupplementDTO searchSupplement(String supplementId) throws SQLException, ClassNotFoundException;
}
