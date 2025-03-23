package com.hrd.homework002.repository;

import com.hrd.homework002.model.entity.Student;
import com.hrd.homework002.model.request.StudentRequest;
import jakarta.validation.Valid;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface StudentRepository {
    @Results(id = "studentMapper", value = {
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "studentName", column = "student_name"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "courses", column = "student_id",
                    many = @Many(select = "com.hrd.homework002.repository.StudentCoursesRepository.getAllCoursesByStudentId"))
    })
    @Select("""
        SELECT * FROM students OFFSET #{offset} LIMIT #{limit};
    """)
    List<Student> findAll(Integer offset, Integer limit);

    @ResultMap("studentMapper")
    @Select("""
        SELECT * FROM students WHERE student_id = #{studentId};
    """)
    Student findById(Long studentId);


    @ResultMap("studentMapper")
    @Select("""
        INSERT INTO students VALUES (DEFAULT, #{request.studentName}, #{request.email}, #{request.phoneNumber}) RETURNING *;
    """)
    Student save(@Param("request") @Valid StudentRequest request);

    @ResultMap("studentMapper")
    @Update("""
        UPDATE students SET student_name = #{request.studentName}, email = #{request.email}, phone_number = #{request.phoneNumber} WHERE student_id = #{studentId};
    """)
    int update(Long studentId, StudentRequest request);

    @Delete("""
        DELETE FROM students WHERE student_id = #{studentId};
    """)
    int delete(Long studentId);
}
