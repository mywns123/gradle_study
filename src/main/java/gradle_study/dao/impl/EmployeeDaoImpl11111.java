package gradle_study.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gradle_study.dao.EmployeeDao;
import gradle_study.dto.Department;
import gradle_study.dto.Employee;
import gradle_study.dto.Title;
import gradle_study.util.jdbcUtil;

public class EmployeeDaoImpl11111 implements EmployeeDao {

	private static EmployeeDaoImpl11111 instance = new EmployeeDaoImpl11111();

	private EmployeeDaoImpl11111() {
	}

	public static EmployeeDaoImpl11111 getInstance() {
		if(instance == null) {
			instance = new EmployeeDaoImpl11111();
		}
		return instance;
	}

	@Override
	public List<Employee> selectEmployeeByAll() {
		String sql = "select empNo,empName,title,manager,salary,dept from employee";
		try (Connection con = jdbcUtil.getConnection();
				PreparedStatement std = con.prepareStatement(sql);
				ResultSet rs = std.executeQuery()) {
			if (rs.next()) {
				List<Employee> list = new ArrayList<>();
				do {
					list.add(getEmployee(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("empno");
		String empName = rs.getString("empname");
		Title title = new Title(rs.getInt("title"));
		Employee manager = new Employee(rs.getInt("MANAGER"));
		int salary = rs.getInt("salary");
		Department dept = new Department(rs.getInt("dept"));
		return new Employee(empNo, empName, title, manager, salary, dept);
	}

	@Override
	public Employee selectEmployeeByNo(Employee employee) {
		String sql = "select empNo,empName,title,manager,salary,dept from employee where empNo =?";
		try (Connection con = jdbcUtil.getConnection(); PreparedStatement std = con.prepareStatement(sql);) {
			std.setInt(1, employee.getEmpNo());
			try (ResultSet rs = std.executeQuery()) {
				if (rs.next()) {
					return getEmployee(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertEmployee(Employee employee) {
		String sql = "insert into employee values(?,?,?,?,?,?)";
		try (Connection con = jdbcUtil.getConnection(); PreparedStatement std = con.prepareStatement(sql);) {
			std.setInt(1, employee.getEmpNo());
			std.setString(2, employee.getEmpName());
			std.setInt(3, employee.getTitle().gettNo());
			std.setInt(4, employee.getManager().getEmpNo());
			std.setInt(5, employee.getSalary());
			std.setInt(6, employee.getDept().getDeptno());
			return std.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateEmployee(Employee employee) {
		String sql = "update employee set empName = ? where empNo = ?";
		try (Connection con = jdbcUtil.getConnection(); PreparedStatement std = con.prepareStatement(sql)) {
			std.setString(1, employee.getEmpName());
			std.setInt(2, employee.getEmpNo());
			return std.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteEmployee(int empNo) {
		String sql = "delete  from employee  where empNo =?";
		try (Connection con = jdbcUtil.getConnection(); PreparedStatement std = con.prepareStatement(sql)) {
			std.setInt(1, empNo);
			return std.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
