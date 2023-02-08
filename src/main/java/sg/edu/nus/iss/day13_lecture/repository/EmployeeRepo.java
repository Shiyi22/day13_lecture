package sg.edu.nus.iss.day13_lecture.repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day13_lecture.model.Employee;

@Repository
public class EmployeeRepo {

    // set up model / data 
    private List<Employee> employees;

    public EmployeeRepo() throws ParseException {
        if (employees == null) {
            employees = new ArrayList<>(); 
        }
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        
        // create employee x2 and add data into employees list
        Date dt = df.parse("1980-10-15");
        Employee emp = new Employee("Derrick", "Tan", "derrick@gmail.com", "91234567", 7500, dt, "10 Ghim Moh", 272210); 
        employees.add(emp); 

        dt = df.parse("1979-02-18");
        emp = new Employee("Dennis", "Chew", "dennis@gmail.com", "92828282", 8500, dt, "28 Ghim Moh", 272228); 
        employees.add(emp); 
    }

    // write functions >> these methods will be called in service / controller
    // (1) to return a list of employees
    public List<Employee> findAll() {
        return employees;
    }

    // (2) to add/save employee to list 
    public Boolean save(Employee employee) {
        Boolean result = employees.add(employee);
        return result; 
    }

    // (3) to delete employee by index 
    public Boolean delete(Employee employee) {
        // Employee e = employees.stream().filter(emp -> emp.getEmail().equalsIgnoreCase(employee.getEmail())).findFirst().get(); 

        Boolean result = false; 
        int employeeIndex = employees.indexOf(employee); 
        if (employeeIndex >= 0) {
            employees.remove(employeeIndex);
            result = true; 
        }
        return result; 
    }    
}
