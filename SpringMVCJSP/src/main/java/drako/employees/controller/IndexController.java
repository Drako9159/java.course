package drako.employees.controller;

import drako.employees.model.Employee;
import drako.employees.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String initialize(ModelMap modelMap) {
        List<Employee> employees = employeeService.listEmployees();
        employees.forEach((employee) -> logger.info(employees.toString()));
        modelMap.put("employees", employees);
        return "index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAdd() {
        return "adder";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String adder(@ModelAttribute("employeeForm") Employee employee, HttpServletRequest request) {
        logger.info("Employee to add: " + employee);
        employeeService.addEmployee(employee);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String showEdit(@RequestParam int idEmployee, ModelMap modelMap) {
        Employee employee = employeeService.findEmployeeById(idEmployee);
        logger.info("Employee to edit: " + employee);
        modelMap.put("employee", employee);
        return "edit";

    }

}
