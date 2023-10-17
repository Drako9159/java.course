package drako.employees.service;


import drako.employees.model.Employee;

import java.util.List;

public interface IEmployeeService {

    public List<Employee> listEmployees();

    public Employee findEmployeeById(Integer idEmployee);

    public void addEmployee(Employee employee);

    public void deleteEmployee(Employee employee);

}
