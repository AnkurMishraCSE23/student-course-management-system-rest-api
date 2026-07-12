package com.ankur.mappers;

import java.util.ArrayList;
import java.util.List;

import com.ankur.dtos.StudentRequestDTO;
import com.ankur.dtos.StudentResponseDTO;
import com.ankur.models.StudentModel;

public final class StudentMapper //final bcoz no extention
{
	private StudentMapper() {} //stops anyone from instantiating it
	
	public static StudentModel toModel(StudentRequestDTO dto)
	{
		return new StudentModel(dto.getName(), dto.getEmail());
	}
	
	public static StudentResponseDTO toResponseDTO(StudentModel model)
	{
		return new StudentResponseDTO(model.getId(), model.getName(), model.getEmail());
	}
	
	public static List<StudentResponseDTO> toResponseDTOList(List<StudentModel> studentList)
	{
		List<StudentResponseDTO> studentResponseDTOList = new ArrayList<StudentResponseDTO>();
		
		for (var student : studentList)
		{
			studentResponseDTOList.add(toResponseDTO(student));
		}
		
		return studentResponseDTOList;
	}
}
