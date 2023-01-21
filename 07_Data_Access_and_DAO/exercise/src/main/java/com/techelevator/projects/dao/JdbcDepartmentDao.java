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

	@Override
	public Department getDepartment(int id) {
		//call or instantiate a new department object
		Department department = null; //null accounts for null pointer exception
		//your sql code stored in a string
		String sql = "SELECT department_id, name "
				+ "FROM department " +
				"WHERE name = ?;";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		System.out.println(results);
		if (results.next()) {
			department = mapRowToDepartment(results);
		}
		return department;
	}

	private Department mapRowToDepartment(SqlRowSet rowSet) {
		Department department = new Department();
		department.setName(rowSet.getString("name"));
		department.setId(rowSet.getInt("department_id"));
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
	public void updateDepartment(Department updatedDepartment) {


	}
}