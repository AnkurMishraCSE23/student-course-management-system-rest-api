package com.ankur.resources;

import java.util.List;

import com.ankur.exceptions.DatabaseOperationException;
import com.ankur.exceptions.StudentNotFoundException;
import com.ankur.models.StudentModel;
import com.ankur.services.StudentService;

import jakarta.validation.ValidationException;
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


@Path("/students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentResource
{
	private final StudentService studentService = new StudentService();
	
	@POST
	public Response registerStudent(StudentModel student)
	{
//		StudentModel student = new StudentModel(name, email);
		
		try
		{
			StudentModel registeredStudent = studentService.registerStudent(student);
			
			return Response
					.status(Response.Status.CREATED)
					.entity(registeredStudent)
					.build();
		}
		catch (ValidationException e)
		{
//			e.printStackTrace();
			
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
	public Response getAllStudents()
	{
		try
		{
			List<StudentModel> studentList = studentService.retrieveAllStudents();
			
			return Response
					.ok(studentList)
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
	public Response getStudentById(@PathParam("id") int id)
	{
		try
		{
			StudentModel student = studentService.retrieveStudentById(id);
			
			return Response
					.ok(student)
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
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateStudentById(@PathParam("id") int id, StudentModel student)
	{
		try
		{
			StudentModel updatedStudent = studentService.updateStudentById(id, student);
			
			return Response
					.ok(updatedStudent)
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
	
	@DELETE
	@Path("/{id}")
	public Response deleteStudentById(@PathParam("id") int id)
	{
		try
		{
			studentService.deleteStudentById(id);
			
			return Response
					.ok("Student deleted successfully!")
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
}
