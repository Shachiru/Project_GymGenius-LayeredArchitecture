package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.MemberDAO;
import lk.ijse.pos.entity.Member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {
    @Override
    public ResultSet generateNextId() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT ID from member order by ID desc limit 1");
    }

    @Override
    public ArrayList<Member> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Member> allMembers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM member");
        while (rst.next()){
            Member member = new Member(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6));
            allMembers.add(member);
        }
        return allMembers;
    }

    @Override
    public boolean save(Member entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO member VALUES (?, ?, ?, ?, ?, ?)",
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getMobile(),
                entity.getDob(),
                entity.getGender());
    }

    @Override
    public boolean update(Member entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE member set name=?, address=?, mobile=?, dob=?, gender=? where id=?",
                entity.getName(),
                entity.getAddress(),
                entity.getMobile(),
                entity.getDob(),
                entity.getGender(),
                entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE from member where id = ?",id);
    }

    @Override
    public int count() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(ID) FROM member");
        if (rst.next()) {
            int memberCount = Integer.parseInt(rst.getString(1));
            return memberCount;
        }
        return Integer.parseInt(null);
    }


    @Override
    public List<String> getMemIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT ID from member");
        List<String> memIds = new ArrayList<>();
        while (rst.next()) {
            memIds.add(rst.getString(1));
        }
        return memIds;          // Get member id but not complete yet..
    }


    public Member search(String memberId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * from member where ID = ?",memberId);
        Member member = null;
        if (rst.next()) {
            String id = rst.getString(1);
            String name = rst.getString(2);
            String address = rst.getString(3);
            String mobile = rst.getString(4);
            String dob = rst.getString(5);
            String gender = rst.getString(6);
            member = new Member(id, name, address, mobile, dob, gender);
        }
        return member;
    }
}
