package gradle_study.dao;

import java.util.List;

import gradle_study.dto.Employee;

public interface EmployeeDao {
	List<Employee> selectEmployeeByAll();

	Employee selectEmployeeByNo(Employee employee);

	int insertEmployee(Employee employee);

	int updateEmployee(Employee employee);

	int deleteEmployee(int empNo);
}
