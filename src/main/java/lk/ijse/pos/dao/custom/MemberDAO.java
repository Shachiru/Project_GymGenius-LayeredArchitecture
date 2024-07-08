package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.dto.MemberDTO;
import lk.ijse.pos.entity.Member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MemberDAO extends CrudDAO<Member> {
    List<String> getMemIds() throws SQLException, ClassNotFoundException;

    Member search(String memberId) throws SQLException, ClassNotFoundException;
}
