package com.itheima.Controller;

import com.itheima.POJO.Course;
import com.itheima.Service.EditCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

/*
* 高级web技术实验二
* */
@RestController
@CrossOrigin(allowCredentials="true",allowedHeaders = "*",methods = {})
public class EditCourse {
    @Autowired
    EditCourseService editCourseService;

    @RequestMapping("/getAllCourse")
    public List<Course> getIdCourse(){
        List<Course> allCourse = editCourseService.getIdCourse();
        Collections.sort(allCourse);
        return allCourse;
    }

    @RequestMapping("/deleteCourse")
    public boolean deleteCourse(@RequestParam("id") int id){
        return editCourseService.deleteCourse(id);
    }

    @RequestMapping("/addCourse")
    public boolean addCourse(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("hours") String hours,
            @RequestParam("schools") int schools,
            @RequestParam("imgUrl") String imgUrl
    ){
        return editCourseService.addCourse(id,name,hours,schools,imgUrl);
    }

    @RequestMapping("updateCourse")
    public boolean updateCourse(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("hours") String hours,
            @RequestParam("schools") int schools,
            @RequestParam("imgUrl") String imgUrl
    ){
        return editCourseService.updateCourse(id,name,hours,schools,imgUrl);
    }
/*
* 高级web技术实验三
* */
    @PostMapping("/file")
    public String upload(@RequestParam("file") MultipartFile file){
        return editCourseService.upload(file);
    }
}
