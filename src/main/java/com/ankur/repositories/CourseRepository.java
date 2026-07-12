package com.ankur.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ankur.exceptions.RepositoryException;
import com.ankur.models.CourseModel;
import com.ankur.utils.JDBCUtil;

public class CourseRepository
{
	public CourseModel saveCourse(CourseModel course) throws RepositoryException
	{
		final String sql = "INSERT INTO courses(name, duration, fees) VALUES(?, ?, ?);";
		
		try (Connection connection = JDBCUtil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
		{
			preparedStatement.setString(1, course.getName());
			preparedStatement.setInt(2, course.getDurationInMonths());
			preparedStatement.setDouble(3, course.getFees());
			
			if (preparedStatement.executeUpdate() == 0)
			{
				throw new RepositoryException("Database error!");
			}
			
			try(ResultSet keys = preparedStatement.getGeneratedKeys())
			{
				if(keys.next())
				{
					course.setId(keys.getInt(1));
				}
				else
				{
					throw new RepositoryException("Course inserted but id not generated!");
				}
			}
			
			return course;
		}
		catch (SQLException e)
		{
			throw new RepositoryException("Database error!", e);
		}
	}
	
	public List<CourseModel> getAllCourses() throws RepositoryException
	{
		final String sql = "SELECT * FROM courses;";
		List<CourseModel> courseList = new ArrayList<CourseModel>();
		
		try (Connection connection = JDBCUtil.getConnection();
			 Statement statement = connection.createStatement())
		{
			ResultSet courses = statement.executeQuery(sql);
			
			while (courses.next())
			{
				CourseModel course = new CourseModel(courses.getString("name"), courses.getInt("duration"), courses.getDouble("fees"));
				course.setId(courses.getInt("id"));
				
				courseList.add(course);
			}
			
			return courseList;
		}
		catch (SQLException e)
		{
			throw new RepositoryException("Database error!", e);
		}
	}
	
	public CourseModel getCourseById(int id) throws RepositoryException
	{
		final String sql = "SELECT * FROM courses WHERE id=?;";
		
		try (Connection connection = JDBCUtil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next())
			{
				return null;
			}
			
			CourseModel course = new CourseModel(resultSet.getString("name"), resultSet.getInt("duration"), resultSet.getDouble("fees"));
			course.setId(resultSet.getInt("id"));
			
			return course;
		}
		catch (SQLException e)
		{
			throw new RepositoryException("Database error!", e);
		}
	}
	
	public CourseModel updateCourseById(int id, CourseModel course) throws RepositoryException
	{
		final String sql = "UPDATE courses SET name=?, duration=?, fees=? WHERE id=?;";
		
		try (Connection connection = JDBCUtil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, course.getName());
			preparedStatement.setInt(2, course.getDurationInMonths());
			preparedStatement.setDouble(3, course.getFees());
			preparedStatement.setInt(4, id);
			
			if(preparedStatement.executeUpdate() == 0)
			{
				return null;
			}
			
			return getCourseById(id);
		}
		catch (SQLException e)
		{
			throw new RepositoryException("Database error!", e);
		}
	}
	
	public boolean deleteCourseById(int id) throws RepositoryException
	{
		final String sql = "DELETE FROM courses WHERE id=?;";
		
		try (Connection connection = JDBCUtil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setInt(1, id);
			
			int rowsAffected = preparedStatement.executeUpdate();
			
			return rowsAffected == 1;
		}
		catch (SQLException e)
		{
			throw new RepositoryException("Data Base error!", e);
		}
	}
}
