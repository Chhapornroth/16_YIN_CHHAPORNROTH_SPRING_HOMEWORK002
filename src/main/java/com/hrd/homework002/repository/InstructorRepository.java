package com.hrd.homework002.repository;

import com.hrd.homework002.model.entity.Instructor;
import com.hrd.homework002.model.request.InstructorRequest;
import jakarta.validation.Valid;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InstructorRepository {

    @Results(id = "instructorMapper", value = {
            @Result(property = "instructorId", column = "instructor_id"),
            @Result(property = "instructorName", column = "instructor_name"),
    })
    @Select("""
        SELECT * FROM instructors OFFSET #{offset} LIMIT #{limit};
    """)
    List<Instructor> findAll(Integer offset, Integer limit);

    @ResultMap("instructorMapper")
    @Select("""
        SELECT * FROM instructors WHERE instructor_id = #{instructorId};
    """)
    Instructor findById(Long instructorId);

    @ResultMap("instructorMapper")
    @Select("""
        INSERT INTO instructors VALUES (DEFAULT, #{request.instructorName}, #{request.email}) RETURNING *;
    """)
    Instructor save(@Valid @Param("request") InstructorRequest request);

    @ResultMap("instructorMapper")
    @Select("""
        UPDATE instructors SET instructor_name = #{request.instructorName}, email = #{request.email} WHERE instructor_id = #{instructorId} RETURNING *;
    """)
    Instructor update(Long instructorId, @Valid @Param("request") InstructorRequest request);

    @ResultMap("instructorMapper")
    @Delete("""
        DELETE FROM instructors WHERE instructor_id = #{instructorId};
    """)
    int delete(Long instructorId);
}
