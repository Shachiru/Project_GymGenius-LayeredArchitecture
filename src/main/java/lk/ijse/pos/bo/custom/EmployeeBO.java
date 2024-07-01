package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO {

    public String generateNextIdEmployee() throws SQLException, ClassNotFoundException;

    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;

    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;

    public int countEmployee() throws SQLException, ClassNotFoundException;
}
