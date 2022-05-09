package com.itheima.pojo;

import org.springframework.stereotype.Component;

@Component
public class Course implements Comparable<Course> {
    private int id;
    private String name;
    private String hours;
    private String school_name;
    private String imgUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hours='" + hours + '\'' +
                ", school_name='" + school_name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
    @Override
    public int compareTo(Course c) {
        return this.getId() - c.getId();
    }
}
