package com.ankur.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ankur.exceptions.RepositoryException;
import com.ankur.models.EnrollmentModel;
import com.ankur.utils.JDBCUtil;

public class EnrollmentRepository
{
	public EnrollmentModel createEnrollment(EnrollmentModel enrollment) throws RepositoryException
	{
		final String sql = "INSERT INTO enrollments(student_id, course_id) VALUES(?, ?);";
		
		try (Connection connection = JDBCUtil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setInt(1, enrollment.getStudentId());
			preparedStatement.setInt(2, enrollment.getCourseId());
			
			if (preparedStatement.executeUpdate() == 0)
			{
				throw new RepositoryException("Database error!");
			}
			
			return enrollment;
		}
		catch (SQLException e)
		{
			throw new RepositoryException("Database error!", e);
		}
	}
	
	public boolean existsEnrollment(int studentId, int courseId) throws RepositoryException
	{
		final String sql = "SELECT 1 FROM enrollments WHERE student_id=? AND course_id=?;";
		
		try (Connection connection = JDBCUtil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setInt(1, studentId);
			preparedStatement.setInt(2, courseId);
			
			return preparedStatement.executeQuery().next();
		}
		catch (SQLException e)
		{
			throw new RepositoryException("Database error!", e);
		}
	}
	
	public List<EnrollmentModel> getAllEnrollments() throws RepositoryException
	{
		final String sql = "SELECT * FROM enrollments;";
		List<EnrollmentModel> enrollmentList = new ArrayList<EnrollmentModel>();
		
		try (Connection connection = JDBCUtil.getConnection();
			 Statement statement = connection.createStatement())
		{
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next())
			{
				enrollmentList.add(mapRow(resultSet));
			}
			
			return enrollmentList;
		}
		catch (SQLException e)
		{
			throw new RepositoryException("Database error!", e);
		}
	}
	
	public List<EnrollmentModel> getEnrollmentsByStudentId(int studentId) throws RepositoryException
	{
		final String sql = "SELECT * FROM enrollments WHERE student_id=?;";
		List<EnrollmentModel> enrollmentListById = new ArrayList<EnrollmentModel>();
		
		try (Connection connection = JDBCUtil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setInt(1, studentId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next())
			{
				enrollmentListById.add(mapRow(resultSet));
			}
			
			return enrollmentListById;
		}
		catch (SQLException e)
		{
			throw new RepositoryException("Database error!", e);
		}
	}
	
	public List<EnrollmentModel> getEnrollmentsByCourseId(int courseId) throws RepositoryException
	{
		final String sql = "SELECT * FROM enrollments WHERE course_id=?;";
		List<EnrollmentModel> enrollmentListById = new ArrayList<EnrollmentModel>();
		
		try (Connection connection = JDBCUtil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setInt(1, courseId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next())
			{
				enrollmentListById.add(mapRow(resultSet));
			}
			
			return enrollmentListById;
		}
		catch (SQLException e)
		{
			throw new RepositoryException("Database error!", e);
		}
	}
	
	public boolean deleteEnrollment(int studentId, int courseId) throws RepositoryException
	{
		final String sql = "DELETE FROM enrollments WHERE student_id=? AND course_id=?;";
		
		try (Connection connection = JDBCUtil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setInt(1, studentId);
			preparedStatement.setInt(2, courseId);
			
			return preparedStatement.executeUpdate() == 1;
		}
		catch (SQLException e)
		{
			throw new RepositoryException("Database error!", e);
		}
	}
	
	private EnrollmentModel mapRow(ResultSet resultSet) throws SQLException
	{
		return new EnrollmentModel(resultSet.getInt("student_id"), resultSet.getInt("course_id"));
	}
}
