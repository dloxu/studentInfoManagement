package com.ceit.model;

import org.apache.ibatis.type.Alias;


public class Grade {

    private Integer id;
    private String gradeName;
    private String gradeDesc;


    public Grade() {
        super();
        // TODO Auto-generated constructor stub
    }


    public Grade(String gradeName, String gradeDesc) {
        super();
        this.gradeName = gradeName;
        this.gradeDesc = gradeDesc;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getGradeName() {
        return gradeName;
    }
    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
    public String getGradeDesc() {
        return gradeDesc;
    }
    public void setGradeDesc(String gradeDesc) {
        this.gradeDesc = gradeDesc;
    }


}

