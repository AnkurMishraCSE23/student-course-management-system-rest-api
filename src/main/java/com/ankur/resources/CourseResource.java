package com.ankur.resources;

import java.util.List;

import com.ankur.exceptions.CourseNotFoundException;
import com.ankur.exceptions.DatabaseOperationException;
import com.ankur.exceptions.ValidationException;
import com.ankur.models.CourseModel;
import com.ankur.services.CourseService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/courses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CourseResource
{
	private final CourseService courseService = new CourseService();
	
	@POST
	public Response createCourse(CourseModel course)
	{
		try
		{
			CourseModel createdCourse = courseService.createCourse(course);
			
			return Response
					.status(Response.Status.CREATED)
					.entity(createdCourse)
					.build();
		}
		catch (ValidationException e)
		{
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(e.getMessage())
					.build();
		}
		catch (DatabaseOperationException e)
		{
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage())
					.build();
		}
	}
	
	@GET
	public Response getAllCourses()
	{
		try
		{
			List<CourseModel> courseList = courseService.getAllCourses();
			
			return Response
					.ok(courseList)
					.build();
		}
		catch (DatabaseOperationException e)
		{
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage())
					.build();
		}
	}
	
	@GET
	@Path("/{id}")
	public Response getCourseById(@PathParam("id") int id)
	{
		try
		{
			CourseModel course = courseService.getCourseById(id);
			
			return Response
					.ok(course)
					.build();
		}
		catch (ValidationException e)
		{
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(e.getMessage())
					.build();
		}
		catch (CourseNotFoundException e)
		{
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(e.getMessage())
					.build();
		}
		catch (DatabaseOperationException e)
		{
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage())
					.build();
		}
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCourseById(@PathParam("id") int id, CourseModel course)
	{
		try
		{
			CourseModel updatedCourse = courseService.updateCourseById(id, course);
			
			return Response
					.ok(updatedCourse)
					.build();
		}
		catch (ValidationException e)
		{
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(e.getMessage())
					.build();
		}
		catch (CourseNotFoundException e)
		{
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(e.getMessage())
					.build();
		}
		catch (DatabaseOperationException e)
		{
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage())
					.build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteCourseById(@PathParam("id") int id)
	{
		try
		{
			courseService.deleteCourseById(id);
			
			return Response
					.ok("Course deleted successfully!")
					.build();
		}
		catch (ValidationException e)
		{
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(e.getMessage())
					.build();
		}
		catch (CourseNotFoundException e)
		{
			return Response
					.status(Response.Status.NOT_FOUND)
					.entity(e.getMessage())
					.build();
		}
		catch (DatabaseOperationException e)
		{
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage())
					.build();
		}
	}
}
