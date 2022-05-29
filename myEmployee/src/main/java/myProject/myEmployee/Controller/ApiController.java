package myProject.myEmployee.Controller;

import myProject.myEmployee.EmployeeRepo;
import myProject.myEmployee.Models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome!";
    }

    @GetMapping(value = "/employees")
    public List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }

    @PostMapping(value = "/save")
    public String saveEmployee(@RequestBody Employee employee) {
        employeeRepo.save(employee);
        return "Saved...";
    }

    @PutMapping(value = "update/{id}")
    public String updateEmployee(@PathVariable long id, @RequestBody Employee employee) {
        Employee newEmployee = employeeRepo.findById(id).get();
        newEmployee.setName(employee.getName());
        newEmployee.setAge(employee.getAge());
        newEmployee.setOccupation(employee.getOccupation());
        employeeRepo.save(newEmployee);
        return "Updated...";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteEmployee(@PathVariable long id) {
        Employee deleteEmployee = employeeRepo.findById(id).get();
        employeeRepo.delete(deleteEmployee);
        return "Delete id = " + id;
    }

}
