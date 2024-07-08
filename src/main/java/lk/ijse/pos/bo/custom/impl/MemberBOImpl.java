package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.MemberBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.MemberDAO;
import lk.ijse.pos.dto.MemberDTO;
import lk.ijse.pos.entity.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberBOImpl implements MemberBO {

    MemberDAO memberDAO = (MemberDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOTypes.MEMBER);

    @Override
    public ResultSet generateNextIdMember() throws SQLException, ClassNotFoundException {
        return memberDAO.generateNextId();
    }

    @Override
    public ArrayList<MemberDTO> getAllMember() throws SQLException, ClassNotFoundException {
       ArrayList<MemberDTO> allMembers = new ArrayList<>();
       ArrayList<Member> all = memberDAO.getAll();
       for (Member m : all){
           allMembers.add(new MemberDTO(m.getId(),m.getName(),m.getAddress(),m.getMobile(),m.getDob(),m.getGender()));
       }
       return allMembers;
    }

    @Override
    public boolean saveMember(MemberDTO dto) throws SQLException, ClassNotFoundException {
        return memberDAO.save(new Member(dto.getId(),dto.getName(),dto.getAddress(),dto.getMobile(),dto.getDob(),dto.getGender()));
    }

    @Override
    public boolean updateMember(MemberDTO dto) throws SQLException, ClassNotFoundException {
        return memberDAO.update(new Member(dto.getId(),dto.getName(),dto.getAddress(),dto.getMobile(),dto.getDob(),dto.getGender()));
    }

    @Override
    public boolean deleteMember(String id) throws SQLException, ClassNotFoundException {
        return memberDAO.delete(id);
    }

    @Override
    public int countMember() throws SQLException, ClassNotFoundException {
        return memberDAO.count();
    }

    @Override
    public List<String> getMemIds() throws SQLException, ClassNotFoundException {
        return memberDAO.getMemIds();
    }

    @Override
    public MemberDTO searchMember(String memberId) throws SQLException, ClassNotFoundException {
        Member member = memberDAO.search(memberId);
        MemberDTO memberDTO = new MemberDTO(member.getId(),member.getName(),member.getAddress(),member.getMobile(),member.getDob(),member.getGender());
        return memberDTO;
    }
}
