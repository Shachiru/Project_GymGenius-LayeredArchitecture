package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SqlUtil;
import lk.ijse.pos.dao.custom.MemberDAO;
import lk.ijse.pos.entity.Member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {

    @Override
    public Member search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("Select * from member where ID = ?",id + "");

        /*PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, memberId);

        ResultSet resultSet = pstm.executeQuery();
        Member member = null;*/

        rst.next();
        return new Member(id + "",
                rst.getString("name"),
                rst.getString("address"),
                rst.getString("mobile"),
                rst.getString("dob"),
                rst.getString("gender"));
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("Select ID from member order by ID desc limit 1");
        if (rst.next()){
            String id = rst.getString("ID");
            int newMemId = Integer.parseInt(id.replace("M ","")) + 1;
            return String.format("M ", newMemId);
        }else {
            return "M 1";
        }
    }

    @Override
    public ArrayList getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Member> allMember = new ArrayList<>();
        ResultSet rst = SqlUtil.execute("SELECT * FROM member");
        while (rst.next()) {
            Member member = new Member(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address"),
                    rst.getString("mobile"),
                    rst.getString("dob"),
                    rst.getString("gender"));
            allMember.add(member);
        }
        return allMember;
    }

    @Override
    public boolean save(Member entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO member (id,name,address,mobile,dob,gender) VALUES (?, ?, ?, ?, ?, ?)",
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getMobile(),
                entity.getDob(),
                entity.getGender());
    }

    @Override
    public boolean update(Member entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("Update member set name=?, address=?, mobile=?, dob=?, gender=? where id=?",
                entity.getName(),
                entity.getAddress(),
                entity.getMobile(),
                entity.getDob(),
                entity.getGender(),
                entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE from member where id = ?", id);
    }

    @Override
    public int count() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT COUNT(ID) FROM member");
        if (rst.next()){
            int idd = Integer.parseInt(rst.getString(1));
            return idd;
        }
        return Integer.parseInt(null); //////////////  method eka sure na
    }
}
