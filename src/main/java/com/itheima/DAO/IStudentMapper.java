package com.itheima.DAO;

import com.itheima.pojo.CCourse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IStudentMapper {
    /*
    * 查询id = 2的课程信息
    * */
    @Select("select c_course.id,c_course.name,c_course.hours,c_course.schools from c_course where id = 2")
    CCourse getIdCourse();

    /*
    * 查询出所有计算机学院开设的课程信息
    * */
    @Select("select c_course.id,c_course.name,c_course.hours,c_course.schools" +
            " from c_course where schools=1")
    List<CCourse> getSchoolsCourse();

    /*
    * 将 id=4 这⻔课程的课时数修改为 32+8=40
     * */
    @Update("update c_course set hours = '40' where id = 4")
    boolean updateHours();
    /*
    * 插⼊⼀条新的课程记录： names=”⼤数据存储“，hours=32，schools =1；
    * */
    @Insert("insert into c_course(id,name,hours,schools) values(5,'⼤数据存储','32','1') ")
    boolean insertCourse();
    /*
    * 输出所有的学院开设的课程信息。
    * */
    @Select("select c_course.id,c_course.name,c_course.hours,s_school.school_name" +
            " from c_course,s_school where c_course.schools=s_school.id")
    List<CCourse> getAllSchools();
}
