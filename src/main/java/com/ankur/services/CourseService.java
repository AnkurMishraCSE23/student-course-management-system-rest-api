package com.ankur.services;

import java.util.List;

import com.ankur.exceptions.CourseNotFoundException;
import com.ankur.exceptions.DatabaseOperationException;
import com.ankur.exceptions.RepositoryException;
import com.ankur.exceptions.ValidationException;
import com.ankur.models.CourseModel;
import com.ankur.repositories.CourseRepository;

public class CourseService
{
	private final CourseRepository courseRepository = new CourseRepository();
	
	private void validateCourse(CourseModel course) throws ValidationException
	{
		if (course == null)
	    {
	        throw new ValidationException("Course cannot be null");
	    }

	    if (course.getName() == null || course.getName().isBlank())
	    {
	        throw new ValidationException("Name is required");
	    }

	    if (course.getDurationInMonths() == null)
	    {
	        throw new ValidationException("Duration is required");
	    }

	    if (course.getDurationInMonths() <= 0)
	    {
	        throw new ValidationException("Duration must be positive");
	    }

	    if (course.getFees() == null)
	    {
	        throw new ValidationException("Fees is required");
	    }

	    if (course.getFees() < 0)
	    {
	        throw new ValidationException("Fees cannot be negative");
	    }
	}
	
	public CourseModel createCourse(CourseModel course) throws DatabaseOperationException, ValidationException
	{
		try
		{
			validateCourse(course);
			
			return courseRepository.saveCourse(course);
		}
		catch (RepositoryException e)
		{
			throw new DatabaseOperationException("Registration failed due to db issue!", e);
		}
	}
	
	public List<CourseModel> getAllCourses() throws DatabaseOperationException
	{
		try
		{
			return courseRepository.getAllCourses();
		}
		catch (RepositoryException e)
		{
			throw new DatabaseOperationException("Retrieval failed due to db issue.", e);
		}
	}
	
	public CourseModel getCourseById(int id) throws CourseNotFoundException, DatabaseOperationException, ValidationException
	{
		if (id <= 0)
		{
			throw new ValidationException("Course ID must be positive.");
		}
		
		try
		{
			CourseModel course = courseRepository.getCourseById(id);
			
			if(course == null)
			{
				throw new CourseNotFoundException("Course not found!");
			}
			
			return course;
		}
		catch (RepositoryException e)
		{
			throw new DatabaseOperationException("Retrieval failed due to db issue.", e);
		}
	}
	
	public CourseModel updateCourseById(int id, CourseModel course) throws CourseNotFoundException, DatabaseOperationException, ValidationException
	{
		if (id <= 0)
		{
			throw new ValidationException("Course ID must be positive.");
		}
		
		validateCourse(course);
		
		try
		{
			CourseModel updatedCourse = courseRepository.updateCourseById(id, course);
			
			if (updatedCourse == null)
			{
				throw new CourseNotFoundException("Course not found!");
			}
			
			return updatedCourse;
		}
		catch (RepositoryException e)
		{
			throw new DatabaseOperationException("Update failed due to db issue!", e);
		}
	}
	
	public void deleteCourseById(int id) throws CourseNotFoundException, DatabaseOperationException, ValidationException
	{
		if (id <= 0)
		{
			throw new ValidationException("Course ID must be positive.");
		}
		
		try
		{
			if (courseRepository.deleteCourseById(id))
			{
				return;
			}
			
			throw new CourseNotFoundException("Course not found!");
		}
		catch (RepositoryException e)
		{
			throw new DatabaseOperationException("Deletion failed due to db issue!", e);
		} 
	}
}
