package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.MemberDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MemberBO extends SuperBO {

    public ResultSet generateNextIdMember() throws SQLException, ClassNotFoundException ;

    public ArrayList<MemberDTO> getAllMember() throws SQLException, ClassNotFoundException ;

    public boolean saveMember(MemberDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateMember(MemberDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean deleteMember(String id) throws SQLException, ClassNotFoundException;

    public int countMember() throws SQLException, ClassNotFoundException ;
}
