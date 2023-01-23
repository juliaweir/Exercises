package com.techelevator.projects.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Project;

public class JdbcProjectDao implements ProjectDao {

	private final JdbcTemplate jdbcTemplate;

	public JdbcProjectDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Project getProject(int projectId) {
		Project project = new Project();
		String sql = "SELECT project_id, name, to_date, from_date " +
				"FROM project " +
				"WHERE project_id = ?;" ;
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, projectId);
		if (results.next()) {
			project = mapRowToProject(results);
		}
		else {
			System.out.println("No projects found");
			return  null;
		}
		return project;
	}

	@Override
	public List<Project> getAllProjects() {
		List<Project> allProjects = new ArrayList<>();
		String sql = "SELECT project_id, name, from_date, to_date " +
				"FROM project;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			allProjects.add(mapRowToProject(results));
		}
		return allProjects;
	}

	@Override
	public Project createProject(Project newProject) {
		String sql = "INSERT INTO project(project_id, name, from_date, to_date) " +
				"VALUES (DEFAULT, ? , ? , ?)"; //will autogenerate next key in proj_id sequence
		int id = jdbcTemplate.update(sql, newProject.getName(),
						newProject.getFromDate(),
						newProject.getToDate());
		newProject.setId(id);
		return newProject;
	}

	@Override
	public void deleteProject(int projectId) {
		//instead of two deletes here, you could set cascading deletes in the data
		String deleteDependants = "DELETE FROM project_employee " +
				"WHERE project_id = ?;";
		jdbcTemplate.update(deleteDependants, projectId);

		String deleteProject = "DELETE FROM project " +
				"WHERE project_id =?;";
		jdbcTemplate.update(deleteProject, projectId);
	}

	private Project mapRowToProject(SqlRowSet rowSet) {
		Project project = new Project();
		project.setId(rowSet.getInt("project_id"));
		project.setName(rowSet.getString("name"));

		if(rowSet.getDate("from_date") != null)
			project.setFromDate(rowSet.getDate("from_date").toLocalDate());

		if(rowSet.getDate("to_date") != null)
			project.setToDate(rowSet.getDate("to_date").toLocalDate());

		return project;
		}
}
