package com.example.demo.DTO.DatatableDTOs;

import lombok.Data;

import java.util.List;

@Data
public class DatatableStudentResponseDTO extends DatatableResponseDTO {
    private List<AllStudentsDatatableDTO> data;
}
