package com.example.demo.DTO.DatatableDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatatableResponseDTO {
    public Integer recordsFiltered;
    public Integer recordsTotal;
    public Integer draw;
    public String error;
    public DatatableMetaDTO datatableMetaDTO;
}
