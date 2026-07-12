package com.ankur.mappers;

import java.util.ArrayList;
import java.util.List;

import com.ankur.dtos.CourseRequestDTO;
import com.ankur.dtos.CourseResponseDTO;
import com.ankur.models.CourseModel;

public final class CourseMapper
{
	private CourseMapper() {}
	
	public static CourseModel toModel(CourseRequestDTO dto)
	{
		return new CourseModel(dto.getName(), dto.getDurationInMonths(), dto.getFees());
	}
	
	public static CourseResponseDTO toResponseDTO(CourseModel course)
	{
		return new CourseResponseDTO(course.getId(), course.getName(), course.getDurationInMonths(), course.getFees());
	}
	
	public static List<CourseResponseDTO> toResponseDTOList(List<CourseModel> courseList)
	{
		List<CourseResponseDTO> courseResponseDTOList = new ArrayList<CourseResponseDTO>();
		
		for (var course : courseList)
		{
			courseResponseDTOList.add(toResponseDTO(course));
		}
		
		return courseResponseDTOList;
	}
}
