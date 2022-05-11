package com.itheima.Service;

import com.itheima.DAO.EditCourseMapper;
import com.itheima.DAO.MyBatisUtils;
import com.itheima.pojo.Course;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class EditCourseServiceImpl implements EditCourseService {

    @Override
    public List<Course> getIdCourse(){
        SqlSession sqlSession= MyBatisUtils.getSession();
        EditCourseMapper courseDao=sqlSession.getMapper(EditCourseMapper.class);
        List<Course> allCourse = courseDao.getAllCourse();
        Collections.sort(allCourse);
        return courseDao.getAllCourse();
    }

    @Override
    public boolean deleteCourse(int id) {
        SqlSession sqlSession= MyBatisUtils.getSession();
        EditCourseMapper courseDao=sqlSession.getMapper(EditCourseMapper.class);
        boolean state =  courseDao.deleteCourse(id);
        sqlSession.commit();
        return state;
    }

    @Override
    public boolean addCourse(int id, String name, String hours, int schools, String imgUrl) {
        SqlSession sqlSession= MyBatisUtils.getSession();
        EditCourseMapper courseDao=sqlSession.getMapper(EditCourseMapper.class);
        boolean state =  courseDao.addCourse(id,name,hours,schools,imgUrl);
        sqlSession.commit();
        return state;
    }

    @Override
    public boolean updateCourse(int id, String name, String hours, int schools, String imgUrl) {
        SqlSession sqlSession= MyBatisUtils.getSession();
        EditCourseMapper courseDao=sqlSession.getMapper(EditCourseMapper.class);
        boolean state =  courseDao.updateCourse(id,name,hours,schools,imgUrl);
        sqlSession.commit();
        return state;
    }

    @Override
    public String upload(MultipartFile file) {
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
