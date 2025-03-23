package com.hrd.homework002.repository;

import com.hrd.homework002.model.response.CourseResponse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentCoursesRepository {
    @Results(id = "mapper", value = {
            @Result(property = "courseId", column = "course_id"),
            @Result(property = "courseName", column = "course_name"),
            @Result(property = "instructor", column = "instructor_id", one = @One(select = "com.hrd.homework002.repository.InstructorRepository.findById"))
    })
    @Select("""
        SELECT * FROM student_courses SC
        JOIN courses C ON SC.course_id = C.course_id
        WHERE student_id = #{studentId}
    """)
    List<CourseResponse> getAllCoursesByStudentId(Long studentId);

    @Insert("""
        INSERT INTO student_courses VALUES (DEFAULT, #{studentId}, #{courseId});
    """)
    void saveStudentIdAndCourseId(Long studentId, Long courseId);

    @Delete("""
        DELETE FROM student_courses WHERE student_id = #{studentId};
    """)
    void deleteStudentCourses(Long studentId);
}
