package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EditStudentDTO {
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String email;
    private String phone;
    private String city;
}
