package com.itheima.Service;

import com.itheima.POJO.Course;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EditCourseService {
    public List<Course> getIdCourse();
    public boolean deleteCourse(int id);
    public boolean addCourse(int id, String name, String hours, int schools, String imgUrl);
    public boolean updateCourse(int id, String name, String hours, int schools, String imgUrl);
    public String upload(MultipartFile file);
}
