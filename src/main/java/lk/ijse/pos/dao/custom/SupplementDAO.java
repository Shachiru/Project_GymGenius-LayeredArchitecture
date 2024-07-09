package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.dto.SupplementDTO;
import lk.ijse.pos.entity.Supplement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplementDAO extends CrudDAO<Supplement> {
    List<String> getSupIds() throws SQLException, ClassNotFoundException;

    Supplement search(String supplementId) throws SQLException, ClassNotFoundException;
}
