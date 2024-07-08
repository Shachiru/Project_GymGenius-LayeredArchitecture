package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.dto.SupplementDTO;
import lk.ijse.pos.entity.Supplement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface SupplementDAO extends CrudDAO<Supplement> {
    SupplementDTO search(String supplementId) throws SQLException;
}
