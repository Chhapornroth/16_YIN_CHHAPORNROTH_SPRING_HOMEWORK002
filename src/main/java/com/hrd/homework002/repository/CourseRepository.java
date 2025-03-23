package com.hrd.homework002.repository;

import com.hrd.homework002.model.entity.Course;
import com.hrd.homework002.model.request.CourseRequest;
import jakarta.validation.Valid;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseRepository {
    @Results(id = "courseMapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "instructor", column = "instructor_id", one = @One(select = "com.hrd.homework002.repository.InstructorRepository.findById"))
    })
    @Select("""
        SELECT * FROM courses OFFSET #{offset} LIMIT #{limit};
    """)
    List<Course> findAll(Integer offset, Integer limit);

    @ResultMap("courseMapper")
    @Select("""
        SELECT * FROM courses WHERE course_id = #{courseId};
    """)
    Course findById(Long courseId);

    @ResultMap("courseMapper")
    @Select("""
        INSERT INTO courses VALUES (DEFAULT, #{request.courseName}, #{request.description}, #{request.instructorId}) RETURNING *;
    """)
    Course save(@Param("request") @Valid CourseRequest request);

    @ResultMap("courseMapper")
    @Select("""
        UPDATE courses SET course_name = #{request.courseName}, description = #{request.description}, instructor_id = #{request.instructorId} WHERE course_id = #{courseId} RETURNING *;
    """)
    Course update(Long courseId, @Valid CourseRequest request);

    @ResultMap("courseMapper")
    @Delete("""
        DELETE FROM courses WHERE course_id = #{courseId};
    """)
    int delete(Long courseId);
}
