package com.example.demo.DTO.DatatableDTOs;

import java.util.List;

import lombok.Data;

@Data
public class DatatableStudentResponseDTO{
    private List<AllStudentsDatatableDTO> data;
    public Integer recordsFiltered;
    public Integer recordsTotal;
    public Integer draw;
    public String error;
    public DatatableMetaDTO datatableMetaDTO;
}
