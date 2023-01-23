package com.techelevator.projects.dao;

import com.techelevator.projects.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcEmployeeDao implements EmployeeDao {

	private final JdbcTemplate jdbcTemplate;

	public JdbcEmployeeDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Employee> getAllEmployees() {
		//create a new list for all employees
		List<Employee> allEmployees = new ArrayList<>();
		//sql query to find all employees
		String sql = "SELECT employee_id, department_id, first_name, last_name, " +
				"birth_date, hire_date " +
				"FROM employee ";
		//capture results
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		//add them to a list
		while (results.next()) {
			allEmployees.add(mapToEmployee(results));
		}
		return allEmployees;
	}

	@Override
	public List<Employee> searchEmployeesByName(String firstNameSearch, String lastNameSearch) {
		List<Employee> employees = new ArrayList<>();
		String sql = "SELECT employee_id, department_id," +
				"first_name, last_name, birth_date, hire_date " +
				"FROM employee " +
				"WHERE first_name ILIKE ? and last_name ILIKE ?;" ; //use like operator along with wildcard
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + firstNameSearch + "%",
				"%" + lastNameSearch + "%");
		while (results.next()) {
			employees.add(mapToEmployee(results)); //add to a list, call mapToRow method,
		}
		if (employees.isEmpty()){
			System.out.println("No matches found");
		}
		return employees;
	}


	@Override
	public List<Employee> getEmployeesByProjectId(int projectId) {
		List<Employee> employees = new ArrayList<>();
		String sql = "SELECT e.employee_id, e.department_id, e.first_name, e.last_name, e.birth_date, e.hire_date "+
				"FROM employee e " +
				"JOIN project_employee pe ON e.employee_id = pe.employee_id " +
				"WHERE pe.project_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, projectId);
		while(results.next()){
			employees.add(mapToEmployee(results));
		}
		return employees;
	}

	@Override
	public void addEmployeeToProject(int projectId, int employeeId) {
		String sql = "INSERT INTO project_employee (project_id, employee_id) " +
				"VALUES (?, ?)";
		jdbcTemplate.update(sql, projectId, employeeId);
	}

	@Override
	public void removeEmployeeFromProject(int projectId, int employeeId) {
		String sql = "DELETE FROM project_employee " +
				"WHERE project_id = ? AND employee_id = ?";
		jdbcTemplate.update(sql, projectId, employeeId);
	}

	@Override
	public List<Employee> getEmployeesWithoutProjects() {
		List<Employee> listEmployeeWithoutProject = new ArrayList<>();
		String sql = "SELECT * " +
				"FROM employee " +
				"LEFT OUTER JOIN project_employee pe ON pe.employee_id = employee.employee_id " +
				"WHERE pe.project_id is null;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()){
			listEmployeeWithoutProject.add(mapToEmployee(results));
		}
		return listEmployeeWithoutProject;
	}

	private Employee mapToEmployee(SqlRowSet rowSet) {
		Employee employee = new Employee();
		employee.setId(rowSet.getInt("employee_id"));
		employee.setDepartmentId(rowSet.getInt("department_id"));
		employee.setFirstName(rowSet.getString("first_name"));
		employee.setLastName(rowSet.getString("last_name"));
		employee.setBirthDate(rowSet.getDate("birth_date").toLocalDate());     //is parse or this preferred
		employee.setHireDate(LocalDate.parse(rowSet.getString("hire_date")));
		return employee;
	}
}
