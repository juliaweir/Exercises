package com.techelevator.projects.dao;

import com.techelevator.projects.model.Department;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.SQLException;
import java.util.List;
//define
public interface  DepartmentDao {
	//possible to have a generic DAO then additional DAO for specific searches
	//DAO<T> interface, would include get int id, getAll,insert,update,delete
	//DAO Employee interface would have getFirst, getLast
	/**
	 * Get a department from the datastore that belongs to the given id.
	 * If the id is not found, return null.
	 *
	 * @param departmentId the department id to get from the datastore
	 * @return a filled out department object
	 */

	public Department getDepartment(int departmentId) throws SQLException;

	/**
	 * Get all departments from the datastore.
	 *
	 * @return all departments as Department objects in a List
	 */
	public List<Department> getAllDepartments();

	/**
	 * Update a department to the datastore. Only called on departments that
	 * are already in the datastore.
	 *
	 * @param updatedDepartment the department object to update
	 */
	public void updateDepartment(Department updatedDepartment);

}
