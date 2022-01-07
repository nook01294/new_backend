package th.go.customs.example.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import th.go.customs.example.app.model.Employee;




@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
