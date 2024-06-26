package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.EmployeeDAO;
import lk.ijse.pos.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("Select ID from employee order by ID desc limit 1");
        if (rst.next()){
            String id = rst.getString("ID");
            int newEmployeeId = Integer.parseInt(id.replace("E00-","")) + 1;
            return String.format("E00-%03d", newEmployeeId);
        }else {
            return "E00-001";
        }
    }

    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployees = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee");
        while (rst.next()){
            Employee employee = new Employee(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address"),
                    rst.getString("mobile"),
                    rst.getString("empRole"),
                    rst.getString("userId"));
            allEmployees.add(employee);
        }
        return allEmployees;
    }

    @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO employee VALUES(?,?,?,?,?,?)",
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getMobile(),
                entity.getEmpRole(),
                entity.getUserId());
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employee SET EmpName = ?, Address = ?, Mobile = ?, EmpRole = ?, UserID = ? WHERE ID = ?",
                entity.getName(),
                entity.getAddress(),
                entity.getMobile(),
                entity.getMobile(),
                entity.getEmpRole(),
                entity.getUserId(),
                entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE from employee where ID = ?",id);
    }

    @Override
    public int count() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(ID) as employee_count FROM employee");
        if (rst.next()){
            int employeeCount = Integer.parseInt(rst.getString("employee_count"));
            return employeeCount;
        }
        return Integer.parseInt(null);
    }
}
