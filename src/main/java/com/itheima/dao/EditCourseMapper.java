package com.itheima.dao;

import com.itheima.pojo.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EditCourseMapper {
    @Select("select c_course.id,c_course.imgUrl,c_course.name,c_course.hours,s_school.school_name " +
            "from c_course,s_school where c_course.schools=s_school.id")
    List<Course> getAllCourse();

    @Delete("delete from c_course where id=#{ id }")
    boolean deleteCourse(int id);

    @Insert("insert into c_course(id,name,hours,schools,imgUrl) values(#{id},#{name},#{hours},#{schools},#{imgUrl})")
    boolean addCourse(@Param("id") int id,@Param("name") String name,@Param("hours") String hours,@Param("schools") int schools,@Param("imgUrl") String imgUrl);

    @Update("update c_course set name=#{name},hours=#{hours},schools=#{schools},imgUrl=#{imgUrl} where id=#{id}")
    boolean updateCourse(@Param("id") int id,@Param("name") String name,@Param("hours") String hours,@Param("schools") int schools,@Param("imgUrl") String imgUrl);
}
