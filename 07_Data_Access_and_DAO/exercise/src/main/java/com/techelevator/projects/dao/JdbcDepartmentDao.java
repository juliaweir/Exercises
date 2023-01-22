package com.techelevator.projects.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Department;

public class JdbcDepartmentDao implements DepartmentDao {

	private final JdbcTemplate jdbcTemplate;


	public JdbcDepartmentDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	//why would you use DataSource over using a DriverManager
	@Override
	public Department getDepartment(int id) {
		//call or instantiate a new department object
		Department department = new Department();
		//your sql code stored in a string
		String sql = "SELECT department_id, name "
				+ "FROM department " +
				"WHERE department_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id); //pass in id as parameter
		if (results.next()) { //check if any results were returned, account for null(can we do this on line 25?)
			department = mapRowToDepartment(results);
		} else {
			return null;
		}
		return department;
	}

	@Override
	public List<Department> getAllDepartments() {
		List<Department> departmentList = new ArrayList<>();
		String sql = "SELECT department_id, name " +
				"FROM department ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()){
			departmentList.add(mapRowToDepartment(results));
		}

		return departmentList;
	}

	@Override
	public void updateDepartment(Department updatedDepartment) { //void, doesn't need to return results
		//update the department name
		String sql = "UPDATE department " +
				"SET name = ? " +
				"WHERE department_id = ?;";
		jdbcTemplate.update(sql, updatedDepartment.getName(),
				updatedDepartment.getId());
		//check if rows were affected
		int rowsAffected = jdbcTemplate.update(sql, updatedDepartment.getName(),
				updatedDepartment.getId());
		if (rowsAffected > 0) {
			System.out.println("Department updated successfully");
		} else {
			System.out.println("Department update failed");
		}
	}
	private Department mapRowToDepartment(SqlRowSet rowSet) {
		Department department = new Department();
		department.setName(rowSet.getString("name"));
		department.setId(rowSet.getInt("department_id"));
		return department;
	}
}