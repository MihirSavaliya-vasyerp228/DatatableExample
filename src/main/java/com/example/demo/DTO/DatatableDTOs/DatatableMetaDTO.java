package com.example.demo.DTO.DatatableDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DatatableMetaDTO {
    private Integer page;
    private Integer pages;
    private Integer perpage;
    private Integer total;
}
