package com.ankur.services;

import java.util.List;

import com.ankur.exceptions.CourseNotFoundException;
import com.ankur.exceptions.DatabaseOperationException;
import com.ankur.exceptions.DuplicateEnrollmentException;
import com.ankur.exceptions.EnrollmentNotFoundException;
import com.ankur.exceptions.RepositoryException;
import com.ankur.exceptions.StudentNotFoundException;
import com.ankur.exceptions.ValidationException;
import com.ankur.models.EnrollmentModel;
import com.ankur.repositories.CourseRepository;
import com.ankur.repositories.EnrollmentRepository;
import com.ankur.repositories.StudentRepository;

public class EnrollmentService
{
	private final StudentRepository studentRepository = new StudentRepository();
	private final CourseRepository courseRepository = new CourseRepository();
	private final EnrollmentRepository enrollmentRepository = new EnrollmentRepository();
	
	public EnrollmentModel createEnrollment(EnrollmentModel enrollment) throws ValidationException, StudentNotFoundException, CourseNotFoundException, DuplicateEnrollmentException, DatabaseOperationException
	{
		try
		{
			validateEnrollment(enrollment);
			
			if (studentRepository.getStudentById(enrollment.getStudentId()) == null)
			{
				throw new StudentNotFoundException("Student not found!");
			}
			if (courseRepository.getCourseById(enrollment.getCourseId()) == null)
			{
				throw new CourseNotFoundException("Course not found!");
			}
			if (enrollmentRepository.existsEnrollment(enrollment.getStudentId(), enrollment.getCourseId()))
			{
				throw new DuplicateEnrollmentException("This student is already enrolled in this course!");
			}
			
			return enrollmentRepository.createEnrollment(enrollment);
		}
		catch (RepositoryException e)
		{
			throw new DatabaseOperationException("Enrollment failed due to db issue!", e);
		}
	}
	
	public List<EnrollmentModel> getAllEnrollments() throws DatabaseOperationException
	{
		try
		{
			return enrollmentRepository.getAllEnrollments();
		}
		catch (RepositoryException e)
		{
			throw new DatabaseOperationException("Retrieval failed due to db issue!", e);
		}
	}
	
	public List<EnrollmentModel> getEnrollmentsByStudentId(int studentId) throws StudentNotFoundException, ValidationException, DatabaseOperationException
	{
		try
		{
			validateStudentId(studentId);
			
			if (studentRepository.getStudentById(studentId) == null)
			{
				throw new StudentNotFoundException("Student not found!");
			}
			
			return enrollmentRepository.getEnrollmentsByStudentId(studentId);
		}
		catch (RepositoryException e)
		{
			throw new DatabaseOperationException("Retrieval failed due to db issue!", e);
		}
	}
	
	public List<EnrollmentModel> getEnrollmentsByCourseId(int courseId) throws CourseNotFoundException, DatabaseOperationException, ValidationException
	{
		try
		{
			validateCourseId(courseId);
			
			if (courseRepository.getCourseById(courseId) == null)
			{
				throw new CourseNotFoundException("Course not found!");
			}
			
			return enrollmentRepository.getEnrollmentsByCourseId(courseId);
		}
		catch (RepositoryException e)
		{
			throw new DatabaseOperationException("Retrieval failed due to db issue!", e);
		}
	}
	
	public void deleteEnrollment(EnrollmentModel enrollment) throws EnrollmentNotFoundException, DatabaseOperationException, ValidationException
	{
		try
		{
			validateEnrollment(enrollment);
			
			if (!enrollmentRepository.deleteEnrollment(enrollment.getStudentId(), enrollment.getCourseId()))
			{
				throw new EnrollmentNotFoundException("No such enrollment record found!");
			}
		}
		catch (RepositoryException e)
		{
			throw new DatabaseOperationException("Deletion failed due to db issue!", e);
		}
	}
	
	private void validateEnrollment(EnrollmentModel enrollment) throws ValidationException
	{
		if (enrollment == null)
		{
			throw new ValidationException("Enrollment can not be null!");
		}
		if(enrollment.getStudentId() == null)
		{
			throw new ValidationException("Student ID can not be empty!");
		}
		if(enrollment.getCourseId() == null)
		{
			throw new ValidationException("Course ID can not be empty!");
		}
		if(enrollment.getStudentId() <= 0)
		{
			throw new ValidationException("Student ID must be positive!");
		}
		if(enrollment.getCourseId() <= 0)
		{
			throw new ValidationException("Course ID must be positive!");
		}
	}
	
	private void validateStudentId(int studentId) throws ValidationException
	{
		if (studentId <= 0)
		{
			throw new ValidationException("Student ID must be positive!");
		}
	}
	
	private void validateCourseId(int courseId) throws ValidationException
	{
		if (courseId <= 0)
		{
			throw new ValidationException("Course ID must be positive!");
		}
	}
}
