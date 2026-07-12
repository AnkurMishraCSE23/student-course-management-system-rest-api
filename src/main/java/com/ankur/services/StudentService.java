package com.ankur.services;

import java.util.List;

import com.ankur.exceptions.DatabaseOperationException;
import com.ankur.exceptions.RepositoryException;
import com.ankur.exceptions.StudentNotFoundException;
import com.ankur.exceptions.ValidationException;
import com.ankur.models.StudentModel;
import com.ankur.repositories.StudentRepository;

public class StudentService
{
	private final StudentRepository studentRepository = new StudentRepository();
	
	public StudentModel registerStudent(StudentModel student) throws DatabaseOperationException, ValidationException
	{
		try
		{
			validateStudent(student);
			
			return studentRepository.saveStudent(student);
		}
		catch (RepositoryException e)
		{
			throw new DatabaseOperationException("Registration failed due to db issue!", e);
		}
	}
	
	private void validateStudent(StudentModel student) throws ValidationException
	{
		if(student == null)
        {
            throw new ValidationException(
                    "Student cannot be null");
        }

        if(student.getName() == null ||
           student.getName().isBlank())
        {
            throw new ValidationException(
                    "Name is required");
        }

        if(student.getEmail() == null ||
           student.getEmail().isBlank())
        {
            throw new ValidationException(
                    "Email is required");
        }
	}
	
	public List<StudentModel> retrieveAllStudents() throws DatabaseOperationException
	{
		try
		{
			List<StudentModel> studentList = studentRepository.getAllStudents();
			return studentList;
		}
		catch (RepositoryException e)
		{
			throw new DatabaseOperationException("Retrieval failed due to db issue!", e);
		}
	}
	
	public StudentModel retrieveStudentById(int id) throws StudentNotFoundException, DatabaseOperationException, ValidationException
	{
		if(id <= 0)
		{
			throw new ValidationException("Student ID must be positive.");
		}
		
		try
		{
			StudentModel student = studentRepository.getStudentById(id);
			
			if(student == null)
			{
				throw new StudentNotFoundException("Student not found!");
			}
			
			return student;
		}
		catch (RepositoryException e)
		{
			throw new DatabaseOperationException("Student could not be fetched!", e);
		}
	}
	
	public StudentModel updateStudentById(int id, StudentModel student) throws StudentNotFoundException, DatabaseOperationException, ValidationException
	{
		if (id <= 0)
		{
			throw new ValidationException("Student ID must be positive.");
		}
		
		validateStudent(student);
		
		try
		{
			StudentModel updatedStudent = studentRepository.updateStudentById(id, student);
			
			if (updatedStudent == null)
			{
				throw new StudentNotFoundException("Student not found!");
			}
			
			return updatedStudent;
		}
		catch (RepositoryException e)
		{
			throw new DatabaseOperationException("Student could not be updated due to db issue!", e);
		}
	}
	
	public void deleteStudentById(int id) throws StudentNotFoundException, DatabaseOperationException, ValidationException
	{
		if (id <= 0)
		{
			throw new ValidationException("Student ID must be positive.");
		}
		
		try
		{
			if (!studentRepository.deleteStudentById(id))
			{
				throw new StudentNotFoundException("Student not found!");
			}
		}
		catch (RepositoryException e)
		{
			throw new DatabaseOperationException("Student could not be deleted due to db issue!", e);
		}
	}
}
