package com.ankur.mappers;

import java.util.ArrayList;
import java.util.List;

import com.ankur.dtos.EnrollmentRequestDTO;
import com.ankur.dtos.EnrollmentResponseDTO;
import com.ankur.models.EnrollmentModel;

public final class EnrollmentMapper
{
	private EnrollmentMapper() {}
	
	public static EnrollmentModel toModel(EnrollmentRequestDTO dto)
	{
		return new EnrollmentModel(dto.getStudentId(), dto.getCourseId());
	}
	
	public static EnrollmentResponseDTO toResponseDTO(EnrollmentModel enrollment)
	{
		return new EnrollmentResponseDTO(enrollment.getStudentId(), enrollment.getCourseId());
	}
	
	public static List<EnrollmentResponseDTO> toResponseDTOList(List<EnrollmentModel> enrollmentList)
	{
		List<EnrollmentResponseDTO> enrollmentResponseDTOList = new ArrayList<EnrollmentResponseDTO>();
		
		for (var enrollment : enrollmentList)
		{
			enrollmentResponseDTOList.add(toResponseDTO(enrollment));
		}
		
		return enrollmentResponseDTOList;
	}
}
