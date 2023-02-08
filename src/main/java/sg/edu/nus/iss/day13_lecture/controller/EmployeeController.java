package sg.edu.nus.iss.day13_lecture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.day13_lecture.model.Employee;
import sg.edu.nus.iss.day13_lecture.repository.EmployeeRepo;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    
    @Autowired
    EmployeeRepo empRepo; 

    // main directory page 
    @GetMapping("/home")
    public String employeeListPage(Model model) {
        
        List<Employee> employees = empRepo.findAll(); 
        model.addAttribute("employees", employees); 
        return "employeeslist"; 
    }

    // to create a form to add employee
    @GetMapping("/addnew")
    public String addPage(Model model) {
        Employee emp = new Employee(); 
        model.addAttribute("employee", emp); 
        return "employeeadd"; 
    }

    // create a post mapping for the form 
    @PostMapping("/saveEmployee")
    public String addEmployee(@Valid @ModelAttribute("employee") Employee employeeForm, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "employeeadd"; 
        }

        Boolean bResult = false; 
        bResult = empRepo.save(employeeForm); 

        return "redirect:/employees/home"; 
    }

    // create a mapping for delete
    @GetMapping("/deleteEmployee/{email}")
    public String delEmployee(@PathVariable("email") String email, Model model) {

        Employee emp = empRepo.findByEmailId(email);
        Boolean bResult = empRepo.delete(emp); 

        return "redirect:/employees/home"; 
    }

    // create a mapping to update employee

}
