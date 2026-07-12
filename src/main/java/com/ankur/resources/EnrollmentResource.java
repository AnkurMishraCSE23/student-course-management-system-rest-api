package com.ankur.resources;

import java.util.List;

import com.ankur.exceptions.CourseNotFoundException;
import com.ankur.exceptions.DatabaseOperationException;
import com.ankur.exceptions.DuplicateEnrollmentException;
import com.ankur.exceptions.EnrollmentNotFoundException;
import com.ankur.exceptions.StudentNotFoundException;
import com.ankur.exceptions.ValidationException;
import com.ankur.models.EnrollmentModel;
import com.ankur.services.EnrollmentService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/enrollments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EnrollmentResource
{
	private final EnrollmentService enrollmentService = new EnrollmentService();
	
	@POST
	public Response createEnrollment(EnrollmentModel enrollment)
	{
		try
		{
			EnrollmentModel createdEnrollment = enrollmentService.createEnrollment(enrollment);
			
			return Response
					.status(Response.Status.CREATED)
					.entity(createdEnrollment)
					.build();
		}
		catch (ValidationException e)
		{
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(e.getMessage())
					.build();
		}
		catch (StudentNotFoundException e)
		{
			return Response
					.status(Response.Status.NOT_FOUND)
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
		catch (DuplicateEnrollmentException e)
		{
			return Response
					.status(Response.Status.CONFLICT)
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
	public Response getAllEnrollments()
	{
		try
		{
			List<EnrollmentModel> enrollmentList = enrollmentService.getAllEnrollments();
			
			return Response
					.ok(enrollmentList)
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
	@Path("/student/{studentId}")
	public Response getEnrollmentsByStudentId(@PathParam("studentId") int studentId)
	{
		try
		{
			List<EnrollmentModel> enrollmentList = enrollmentService.getEnrollmentsByStudentId(studentId);
			
			return Response
					.ok(enrollmentList)
					.build();
		}
		catch (ValidationException e)
		{
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(e.getMessage())
					.build();
		}
		catch (StudentNotFoundException e)
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
	
	@GET
	@Path("/course/{courseId}")
	public Response getEnrollmentsByCourseId(@PathParam("courseId") int courseId)
	{
		try
		{
			List<EnrollmentModel> enrollmentList = enrollmentService.getEnrollmentsByCourseId(courseId);
			
			return Response
					.ok(enrollmentList)
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
	@Path("/student/{studentId}/course/{courseId}")
	public Response deleteEnrollment(@PathParam("studentId") int studentId, @PathParam("courseId") int courseId)
	{
		try
		{
			enrollmentService.deleteEnrollment(new EnrollmentModel(studentId, courseId));
			
			return Response
					.ok("Enrollment record deleted successfully!")
					.build();
		}
		catch (ValidationException e)
		{
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(e.getMessage())
					.build();
		}
		catch (EnrollmentNotFoundException e)
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
