package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    ResultSet generateNextIdEmployee() throws SQLException, ClassNotFoundException;

    ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;

    boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;

    int countEmployee() throws SQLException, ClassNotFoundException;

}