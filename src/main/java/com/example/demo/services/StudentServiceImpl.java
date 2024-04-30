package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.EditStudentDTO;
import com.example.demo.DTO.DatatableDTOs.AllStudentsDatatableDTO;
import com.example.demo.DTO.DatatableDTOs.DatatableMetaDTO;
import com.example.demo.DTO.DatatableDTOs.DatatableRequestDTO;
import com.example.demo.DTO.DatatableDTOs.DatatableStudentResponseDTO;
import com.example.demo.model.Student;
import com.example.demo.repository.StudRepo;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudRepo studentRepo;

	@Override
	public void addStudent(Student student) {
		studentRepo.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepo.findAllStudent();
	}

	@Override
	public String editStudent(long studentId, EditStudentDTO newData) {
		Optional<Student> oldData = studentRepo.findById(studentId);
		if (oldData.isPresent()) {
			Student old = oldData.get();
			if (newData.getFirstName() != null) {
				old.setFirstName(newData.getFirstName());
			}
			if (newData.getLastName() != null) {
				old.setLastName(newData.getLastName());
			}
			if (newData.getEmail() != null) {
				old.setEmail(newData.getEmail());
			}
			if (newData.getAge() != 0) {
				old.setAge(newData.getAge());
			}
			if (newData.getGender() != null) {
				old.setGender(newData.getGender());
			}
			if (newData.getCity() != null) {
				old.setCity(newData.getCity());
			}
			studentRepo.save(old);
			return "Successfully edited student";
		} else {
			return "Student not found";
		}
	}

	@Override
	public String deleteStudent(long studentId) {
		Optional<Student> student = studentRepo.findById(studentId);
		if (student.isPresent()) {
			Student stud = student.get();
			stud.setIsDelete(1);
			return "Student Deleted Successfully";
		} else {
			return "Student not deleted";
		}
	}

	@Override
	public DatatableStudentResponseDTO getStudentDatatableResponseData(DatatableRequestDTO datatableRequestDTO) {
		String searchValue = StringUtils.defaultString(datatableRequestDTO.getSearchValue(), "");
		int start = Integer.parseInt(StringUtils.defaultIfBlank(String.valueOf(datatableRequestDTO.getStart()), "0"));
		int length = Integer
				.parseInt(StringUtils.defaultIfBlank(String.valueOf(datatableRequestDTO.getLength()), "10"));
		int draw = Integer.parseInt(StringUtils.defaultIfBlank(String.valueOf(datatableRequestDTO.getDraw()), "0"));

		int totalLength = studentRepo.countStudentData(searchValue);
		if (totalLength > 0) {
			List<AllStudentsDatatableDTO> data = studentRepo.findStudentDatatableData(searchValue, length, start);

			DatatableStudentResponseDTO dto = new DatatableStudentResponseDTO();
			dto.setData(data);
			dto.setDraw(draw);
			dto.setError(null);
			dto.setRecordsFiltered(totalLength);
			dto.setRecordsTotal(totalLength);
			dto.setDatatableMetaDTO(new DatatableMetaDTO(start / length, (int) Math.ceil(totalLength / (double) length),
					length, totalLength));
			return dto;
		} else {
			return null;
		}
	}

	@Override
	public JSONObject getStudentDatatableResponseDataShivam(DatatableRequestDTO datatableRequestDTO) {
		// TODO Auto-generated method stub
		int start = Integer.parseInt(StringUtils.defaultIfBlank(String.valueOf(datatableRequestDTO.getStart()), "0"));
		int length = Integer
				.parseInt(StringUtils.defaultIfBlank(String.valueOf(datatableRequestDTO.getLength()), "10"));
		int draw = Integer.parseInt(StringUtils.defaultIfBlank(String.valueOf(datatableRequestDTO.getDraw()), "0"));
		int totalLength = studentRepo.findCountOfTotalStudentDatatableShivam();
		if (totalLength > 0) {
			List<AllStudentsDatatableDTO> data = studentRepo.findStudentDatatableDataShivam(length, start);

//			DatatableStudentResponseDTO dto = new DatatableStudentResponseDTO();
//			dto.setData(data);
//			dto.setDraw(draw);
//			dto.setError(null);
//			dto.setRecordsFiltered(totalLength);
//			dto.setRecordsTotal(totalLength);
//			dto.setDatatableMetaDTO(new DatatableMetaDTO(start / length, (int) Math.ceil(totalLength / (double) length),
//					length, totalLength));
//			System.err.println(dto);
			JSONObject jsonMainObject = new JSONObject();
			JSONObject jsonMataObject = new JSONObject();
			double totalRecords = totalLength;
//			jsonMainObject.put("draw", Integer.parseInt(allRequestParams.get("draw")));
			jsonMainObject.put("recordsFiltered", totalRecords);
			jsonMainObject.put("recordsTotal", totalRecords);
			jsonMainObject.put("data", data);

//			System.err.println(list);
			int page = start / length;
			jsonMataObject.put("page", page);
			jsonMataObject.put("pages", Math.ceil((totalRecords) / length));
			jsonMataObject.put("perpage", length);
			jsonMataObject.put("total", totalRecords);

			jsonMainObject.put("meta", jsonMataObject);

			return jsonMainObject;
		} else {
			return null;
		}

	}
}
