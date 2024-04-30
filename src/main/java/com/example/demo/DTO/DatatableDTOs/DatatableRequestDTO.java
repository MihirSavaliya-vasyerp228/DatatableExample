package com.example.demo.DTO.DatatableDTOs;

import lombok.Data;

@Data
public class DatatableRequestDTO {
    private String searchValue;
    private int length;
    private int start;
    private int draw;
}