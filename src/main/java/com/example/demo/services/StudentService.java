package com.example.demo.services;

import com.example.demo.DTO.DatatableDTOs.DatatableRequestDTO;
import com.example.demo.DTO.DatatableDTOs.DatatableStudentResponseDTO;
import com.example.demo.DTO.EditStudentDTO;
import com.example.demo.model.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    public void addStudent(Student student);

    public List<Student> getAllStudents();

    public String editStudent(long studentId, EditStudentDTO newData);

    public String deleteStudent(long studentId);

    DatatableStudentResponseDTO getStudentDatatableResponseData(DatatableRequestDTO datatableRequestDTO);
}
