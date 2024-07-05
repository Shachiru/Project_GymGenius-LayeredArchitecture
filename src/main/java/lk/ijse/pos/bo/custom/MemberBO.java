package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.MemberDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MemberBO extends SuperBO {
    ResultSet generateNextIdMember() throws SQLException, ClassNotFoundException ;

    ArrayList<MemberDTO> getAllMember() throws SQLException, ClassNotFoundException ;

    boolean saveMember(MemberDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateMember(MemberDTO dto) throws SQLException, ClassNotFoundException ;

    boolean deleteMember(String id) throws SQLException, ClassNotFoundException;

    int countMember() throws SQLException, ClassNotFoundException ;
}