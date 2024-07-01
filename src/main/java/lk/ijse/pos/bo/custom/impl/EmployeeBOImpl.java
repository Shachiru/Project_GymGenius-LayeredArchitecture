package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.EmployeeBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.EmployeeDAO;
import lk.ijse.pos.dto.EmployeeDTO;
import lk.ijse.pos.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public String generateNextIdEmployee() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateNextId();
    }

    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> allEmployees = new ArrayList<>();
        ArrayList<Employee> all = employeeDAO.getAll();
        for (Employee e:all){
            allEmployees.add(new EmployeeDTO(e.getId(),e.getName(),e.getAddress(),e.getMobile(),e.getEmpRole(),e.getUserId()));
        }
        return allEmployees;
    }

    @Override
    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(dto.getId(),dto.getName(),dto.getAddress(),dto.getMobile(),dto.getEmpRole(),dto.getUserId()));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getId(),dto.getName(),dto.getAddress(),dto.getMobile(),dto.getEmpRole(),dto.getUserId()));
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public int countEmployee() throws SQLException, ClassNotFoundException {
        return employeeDAO.count();
    }
}
