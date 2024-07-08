package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dto.MemberDTO;
import lk.ijse.pos.entity.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MemberBO extends SuperBO {
    ResultSet generateNextIdMember() throws SQLException, ClassNotFoundException ;

    ArrayList<MemberDTO> getAllMember() throws SQLException, ClassNotFoundException ;

    boolean saveMember(MemberDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateMember(MemberDTO dto) throws SQLException, ClassNotFoundException ;

    boolean deleteMember(String id) throws SQLException, ClassNotFoundException;

    int countMember() throws SQLException, ClassNotFoundException ;

    List<String> getMemIds() throws SQLException, ClassNotFoundException;

    MemberDTO searchMember(String memberId) throws SQLException, ClassNotFoundException;
}