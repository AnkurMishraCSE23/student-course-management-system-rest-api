package com.ankur.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ankur.exceptions.RepositoryException;
import com.ankur.models.StudentModel;
import com.ankur.utils.JDBCUtil;
import java.util.*;

public class StudentRepository
{
	public StudentModel saveStudent(StudentModel student) throws RepositoryException
	{
		final String sql = "INSERT INTO students(name, email) VALUES(?, ?)";
		
		try (Connection connection = JDBCUtil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
		{
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getEmail());
			
			int rowsAffected = preparedStatement.executeUpdate();
			
			if(rowsAffected == 0)
			{
				throw new RepositoryException("Student could not be created!");
			}
			
			try(ResultSet keys = preparedStatement.getGeneratedKeys())
			{
				if(keys.next())
				{
					student.setId(keys.getInt(1));
				}
				else
				{
					throw new RepositoryException("Student inserted but id not generated!");
				}
			}
			
			return student;
		}
		catch (SQLException e)
		{
			throw new RepositoryException("Data Base error!", e);
		}
	}
	
	public List<StudentModel> getAllStudents() throws RepositoryException
	{
		final String sql = "SELECT * FROM students;";
		List<StudentModel> studentList = new ArrayList<StudentModel>();
		
		try (Connection connection = JDBCUtil.getConnection();
			 Statement statement = connection.createStatement())
		{
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next())
			{
				StudentModel student = new StudentModel(resultSet.getString("name"), resultSet.getString("email"));
				student.setId(resultSet.getInt("id"));
					
				studentList.add(student);
			}
			
			return studentList;
		}
		catch (SQLException e)
		{
			throw new RepositoryException("Data Base error!", e);
		}
	}
	
	public StudentModel getStudentById(int id) throws RepositoryException
	{
		final String sql = "SELECT * FROM students WHERE id=?";
		
		try (Connection connection = JDBCUtil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next())
			{
				return null;
			}
			
			StudentModel student = new StudentModel(resultSet.getString("name"), resultSet.getString("email"));
			student.setId(resultSet.getInt("id"));
			
			return student;
		}
		catch (SQLException e)
		{
			throw new RepositoryException("Data Base error!", e);
		}
	}
	
	public StudentModel updateStudentById(int id, StudentModel student) throws RepositoryException
	{
		final String sql = "UPDATE students SET name=?, email=? WHERE id=?;";
		
		try (Connection connection = JDBCUtil.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setInt(3, id);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getEmail());
			
			int rowsAffected = preparedStatement.executeUpdate();
			
			if(rowsAffected == 0)
			{
				return null;
			}
			
			return getStudentById(id);
		}
		catch (SQLException e)
		{
			throw new RepositoryException("Data Base error!", e);
		}
	}
	
	public boolean deleteStudentById(int id) throws RepositoryException
	{
		final String sql = "DELETE FROM students WHERE id=?;";
		
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
