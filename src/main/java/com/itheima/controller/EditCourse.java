package com.itheima.controller;

import com.itheima.dao.EditCourseMapper;
import com.itheima.dao.MyBatisUtils;
import com.itheima.pojo.Course;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
* 高级web技术实验二
* */
@RestController
@CrossOrigin(allowCredentials="true",allowedHeaders = "*",methods = {})
public class EditCourse {
    @RequestMapping("/getAllCourse")
    public List<Course> getIdCourse(){
        SqlSession sqlSession= MyBatisUtils.getSession();
        EditCourseMapper courseDao=sqlSession.getMapper(EditCourseMapper.class);
        List<Course> allCourse = courseDao.getAllCourse();
        Collections.sort(allCourse);
        for (int i = 0; i < allCourse.size(); i++) {
            System.out.println(allCourse.get(i).toString());
        }
        return courseDao.getAllCourse();
    }

    @RequestMapping("/deleteCourse")
    public boolean deleteCourse(@RequestParam("id") int id){
        SqlSession sqlSession= MyBatisUtils.getSession();
        EditCourseMapper courseDao=sqlSession.getMapper(EditCourseMapper.class);
        boolean state =  courseDao.deleteCourse(id);
        sqlSession.commit();
        return state;
    }

    @RequestMapping("/addCourse")
    public boolean addCourse(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("hours") String hours,
            @RequestParam("schools") int schools,
            @RequestParam("imgUrl") String imgUrl
    ){
        SqlSession sqlSession= MyBatisUtils.getSession();
        EditCourseMapper courseDao=sqlSession.getMapper(EditCourseMapper.class);
        boolean state =  courseDao.addCourse(id,name,hours,schools,imgUrl);
        sqlSession.commit();
        return state;
    }

    @RequestMapping("updateCourse")
    public boolean updateCourse(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("hours") String hours,
            @RequestParam("schools") int schools,
            @RequestParam("imgUrl") String imgUrl
    ){
        SqlSession sqlSession= MyBatisUtils.getSession();
        EditCourseMapper courseDao=sqlSession.getMapper(EditCourseMapper.class);
        boolean state =  courseDao.updateCourse(id,name,hours,schools,imgUrl);
        sqlSession.commit();
        return state;
    }
/*
* 高级web技术实验三
* */
    @PostMapping("/file")
    public String upload(@RequestParam("file") MultipartFile file){
        Random random = new Random();
        String ImagesPath = "B:\\mybatis\\src\\main\\resources\\test03_imagePath";
        String fileName=random.nextInt()+file.getOriginalFilename();
        String filename = ImagesPath+"\\"+fileName;
        System.out.println(filename);
//        .substring(5)
        try {
            file.transferTo(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
