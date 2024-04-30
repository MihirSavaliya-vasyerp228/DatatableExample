package com.example.demo.repository;

import com.example.demo.DTO.DatatableDTOs.AllStudentsDatatableDTO;
import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudRepo extends JpaRepository<Student, Long> {

    @Query(value = "select * from student where is_deleted = 0",nativeQuery = true)
    public List<Student> findAllStudent();

    @Query(value = "SELECT COUNT(*) from student WHERE CASE WHEN '' != :searchValue\n" +
            "    THEN (LOWER(student.first_name) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR\n" +
            "          LOWER(student.email) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR\n" +
            "          LOWER(student.last_name) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR\n" +
            "          LOWER(student.city) LIKE LOWER(CONCAT('%',:searchValue,'%')) OR\n" +
            "          LOWER(student.gender) LIKE LOWER(CONCAT('%',:searchValue,'%')) OR\n" +
            "          LOWER(student.phone) LIKE LOWER(CONCAT('%',:searchValue,'%'))) ELSE 1 = 1 END", nativeQuery = true)
    int countStudentData(@Param("searchValue") String searchValue);

    @Query(value = "SELECT\n" +
            "    student.first_name as firstName,\n" +
            "    student.last_name as lastName,\n" +
            "    student.email as email,\n" +
            "    student.gender as gender,\n" +
            "    student.phone as phone,\n" +
            "    student.city as city\n" +
            "FROM student\n" +
            "WHERE CASE WHEN '' != :searchValue\n" +
            "    THEN (LOWER(student.first_name) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR\n" +
            "          LOWER(student.email) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR\n" +
            "          LOWER(student.last_name) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR\n" +
            "          LOWER(student.city) LIKE LOWER(CONCAT('%',:searchValue,'%')) OR\n" +
            "          LOWER(student.gender) LIKE LOWER(CONCAT('%',:searchValue,'%')) OR\n" +
            "          LOWER(student.phone) LIKE LOWER(CONCAT('%',:searchValue,'%'))) ELSE 1 = 1 END\n" +
            "LIMIT :length OFFSET :offset", nativeQuery = true)
    List<AllStudentsDatatableDTO> findStudentDatatableData(@Param("searchValue") String searchValue, @Param("length") int length, @Param("offset") int offset);
}
