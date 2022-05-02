package com.itheima.controller;

import com.itheima.dao.IStudentMapper;
import com.itheima.dao.MyBatisUtils;
import com.itheima.pojo.CCourse;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/*
 * 高级web技术实验一
 * */
public class Mybatis_test01 {
    public CCourse getIdCourse(){
        SqlSession sqlSession= MyBatisUtils.getSession();
        IStudentMapper courseDao=sqlSession.getMapper(IStudentMapper.class);
        CCourse course=courseDao.getIdCourse();
        sqlSession.close();
        return course;
    }
    public List<CCourse> getSchoolsCourse(){
        SqlSession sqlSession= MyBatisUtils.getSession();
        IStudentMapper courseDao=sqlSession.getMapper(IStudentMapper.class);
        List<CCourse> allCourse=courseDao.getSchoolsCourse();
        sqlSession.close();
        return allCourse;
    }
    public boolean updateHours(){
        SqlSession sqlSession= MyBatisUtils.getSession();
        IStudentMapper courseDao=sqlSession.getMapper(IStudentMapper.class);
        boolean updateState=courseDao.updateHours();
        sqlSession.commit();
        sqlSession.close();
        return updateState;
    }
    public boolean insertCourse(){
        SqlSession sqlSession= MyBatisUtils.getSession();
        IStudentMapper courseDao=sqlSession.getMapper(IStudentMapper.class);
        boolean insertState=courseDao.insertCourse();
        sqlSession.commit();
        sqlSession.close();
        return insertState;
    }

    public List<CCourse> getAllSchool(){
        SqlSession sqlSession= MyBatisUtils.getSession();
        IStudentMapper courseDao=sqlSession.getMapper(IStudentMapper.class);
        List<CCourse> course=courseDao.getAllSchools();
        sqlSession.close();
        return course;
    }

    public static void main(String[] args) {
        Mybatis_test01 g = new Mybatis_test01();
        System.out.println("查询 id=2 的课程信息:");
        System.out.println(g.getIdCourse().toString());
        System.out.println("查询出所有计算机学院开设的课程信息:");
        for (int i = 0; i < g.getSchoolsCourse().size(); i++) {
            System.out.println(g.getSchoolsCourse().get(i).toString());
        }
//        System.out.println("将 id=4 这⻔课程的课时数修改为 32+8=40");
//        System.out.println(g.updateHours());
//        System.out.println("插⼊⼀条新的课程记录： names='⼤数据存储'，hours=32，schools =1");
//        System.out.println(g.insertCourse());
        System.out.println("输出所有的学院开设的课程信息:");
        for (int i = 0; i < g.getAllSchool().size(); i++) {
            System.out.println(g.getAllSchool().get(i).toString());
        }
    }
}
