package com.example.demo.controller;

import com.example.demo.DTO.ApiResponse;
import com.example.demo.DTO.DatatableDTOs.DatatableRequestDTO;
import com.example.demo.DTO.DatatableDTOs.DatatableStudentResponseDTO;
import com.example.demo.DTO.EditStudentDTO;
import com.example.demo.model.Student;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    StudentService studentService;

    @GetMapping("/allStudent")
    public ResponseEntity<ApiResponse> allStudent() {
        return ResponseEntity.ok(new ApiResponse(true,"Success",studentService.getAllStudents()));
    }

    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView m1 = new ModelAndView("students");
        System.err.println("Entered in index");
        return m1;
    }

    @PostMapping("/datatable")
    @ResponseBody
    public ResponseEntity<ApiResponse> allStudentDatatable(@RequestBody DatatableRequestDTO datatableRequestDTO) {
        DatatableStudentResponseDTO dto = studentService.getStudentDatatableResponseData(datatableRequestDTO);
        if(dto!=null){
            return ResponseEntity.ok(new ApiResponse(true,"Success",dto));
        }else {
            return ResponseEntity.ok(new ApiResponse(false,"Error","No data found"));
        }
    }

    @PostMapping("/addStudent")
    public ResponseEntity<ApiResponse> addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return ResponseEntity.ok(new ApiResponse(true,"Success",studentService.getAllStudents()));
    }

    @PostMapping("/editStudent")
    public ResponseEntity<ApiResponse> editStudent(@RequestParam("studId") long studId,@RequestBody EditStudentDTO editStudent) {
        return ResponseEntity.ok(new ApiResponse(true,"Success",studentService.editStudent(studId,editStudent)));
    }

    @PostMapping("/deleteStudent")
    public ResponseEntity<ApiResponse> deleteStudent(@RequestParam("studId") long studId) {
        studentService.deleteStudent(studId);
        return ResponseEntity.ok(new ApiResponse(true,"Success",studentService.getAllStudents()));
    }
}
