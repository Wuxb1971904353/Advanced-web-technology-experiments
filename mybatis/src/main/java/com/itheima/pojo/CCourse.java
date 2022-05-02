package com.itheima.pojo;

public class CCourse {

  private int id;
  private String name;
  private String hours;
  private String school_name;

  public long getId() {
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


  public String getSchools() {
    return school_name;
  }

  public void setSchools(String schools) {
    this.school_name = schools;
  }

  @Override
  public String toString() {
    return "CCourse{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", hours='" + hours + '\'' +
            ", schools='" + school_name + '\'' +
            '}';
  }
}
