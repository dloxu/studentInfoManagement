package com.ceit.service;

import com.ceit.dao.ManagementMapper;
import com.ceit.model.Grade;
import com.ceit.model.PageBean;
import com.ceit.model.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ManagementService {
    @Autowired
    private ManagementMapper managementMapper;

    public ArrayList<Student> selectAllStudentByPage(int page,int rows)
    {
        int firstPage=(page-1)*rows;
        return managementMapper.selectAllStudentByPage( firstPage, rows);
    }
    public int allStudentCount()
    {
        return managementMapper.allStudentCount();
    }
    public ArrayList<Grade> selectAllGradeByPage(int page,int rows)
    {
        int firstPage=(page-1)*rows;
        return managementMapper.selectAllGradeByPage(firstPage,rows);
    }
    public int allGradeCount()
    {
        return managementMapper.allGradeCount();
    }
    public ArrayList<HashMap> selectGradeIdAndName()
    {
        return managementMapper.selectGradeIdAndName();
    }
    public void addStudent(Student student)
    {
        managementMapper.addStudent(student);
    }
    public void updateStudent(Student student)
    {
        int stuId=student.getStuId();
        managementMapper.updateStudent(student,stuId);
    }
    public int selectMaxStuId()
    {
        return managementMapper.selectMaxStuId();
    }
    public void addGrade(Grade grade)
    {
        managementMapper.addGrade(grade);
    }
    public int selectMaxGradeId()
    {
        return managementMapper.selectMaxGradeId();
    }
    public void updateGrade(Grade grade)
    {
        int id=grade.getId();
        String gradeName=grade.getGradeName();
        String gradeDesc=grade.getGradeDesc();
        managementMapper.updateGrade(gradeName,gradeDesc,id);
    }
    public void deleteStudent(Integer idList [])
    {
        managementMapper.deleteStudent(idList);
    }
    public  void deleteGrade (Integer idList [])
    {
        managementMapper.deleteGrade(idList);
    }
    public List<Student> selectStudentBySexAndGrade(String sex,int gradeId)
    {
//        int firstPage=(page1-1)*rows1;
        return managementMapper.selectStudentBySexAndGrade(sex,gradeId);
    }




}
