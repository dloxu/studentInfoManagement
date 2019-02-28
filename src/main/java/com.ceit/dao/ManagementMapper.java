package com.ceit.dao;

import com.ceit.model.Grade;
import com.ceit.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ManagementMapper {
     ArrayList<Student> selectAllStudentByPage(@Param(value = "firstPage") int min,@Param(value = "rows") int rows);
     int allStudentCount();
     ArrayList<Grade> selectAllGradeByPage(@Param(value = "firstPage")int firstPage,@Param(value = "rows") int rows);
     int allGradeCount();
     ArrayList<HashMap> selectGradeIdAndName();
     void addStudent(Student student);
     void updateStudent(@Param(value = "student") Student student,@Param("stuId") int stuId);
     int selectMaxStuId();
     void addGrade(Grade grade);
     int selectMaxGradeId();
     void updateGrade(@Param(value = "gradeName")String gradeName,@Param(value = "gradeDesc")String gradeDesc,@Param(value = "id")int id);
     void deleteStudent(@Param(value = "idList") Integer idList []);
     void deleteGrade(@Param(value = "idList") Integer idList []);
     List<Student> selectStudentBySexAndGrade(@Param(value="sex") String sex,@Param(value="gradeId") int gradeId);
}
