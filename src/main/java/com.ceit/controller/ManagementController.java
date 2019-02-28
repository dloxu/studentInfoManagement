package com.ceit.controller;

import com.ceit.model.Grade;
import com.ceit.model.Student;
import com.ceit.service.ManagementService;
import com.ceit.util.CaptchaUtil;
import com.ceit.util.JsonMapper;
import com.ceit.util.PageBean;
import com.ceit.util.ResponseUtil;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.apache.http.HttpResponse;
import org.apache.xmlbeans.impl.common.ValidatorListener;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.DocFlavor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/management")
public class ManagementController {
    @Autowired
    private ManagementService managementService;
    JsonMapper nonNullBinder = JsonMapper.nonEmptyMapper();

    @RequestMapping(value = "/loginSuccess",method =RequestMethod.GET)
    public String loginSuccess(HttpSession session, Model model)
    {
        Map roleAndName= (Map) session.getAttribute("roleAndName");
        model.addAttribute("roleAndName", roleAndName);
        String role_1=roleAndName.get("role").toString();
        Integer role_2=Integer.parseInt(role_1);
        if (role_2==1) {
            return "manager_main";
        }
        else {
            return "teacher_main";

        }

    }
    @RequestMapping(value = "/loginFail",method = RequestMethod.GET)
    public String loginFail()
    {
        return "index";
    }

    @RequestMapping(value = "/gradeInfoManage",method = RequestMethod.GET)
    public String gradeInfoManage(HttpSession session, Model model)
    {
        Map roleAndName= (Map) session.getAttribute("roleAndName");
        String role_name= roleAndName.get("role_name").toString();
        model.addAttribute("role_name",role_name);
        return "gradeInfoManage";
    }

    @RequestMapping(value = "/studentInfoManage",method = RequestMethod.GET)
    public String studentInfoManage(HttpSession session, Model model)
    {
        return "studentInfoManage";
    }


    @ResponseBody
    @RequestMapping(value = "/studentList",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    public String studentList(int page,int rows) throws Exception {
        int count=managementService.allStudentCount();
        List<Student> list=managementService.selectAllStudentByPage( page, rows);
        Map<String,Object> json=new HashMap<>();
        json.put("total",count);
        json.put("rows",list);
        return nonNullBinder.toJson(json);
    }
    @ResponseBody
    @RequestMapping(value = "/gradeList",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    public String gradeList(int page,int rows)
    {
        List<Grade> list=managementService.selectAllGradeByPage(page,rows);
        Map<String,Object> json=new HashMap<>();
        int count=managementService.allGradeCount();
        json.put("total",count);
        json.put("rows",list);
        return nonNullBinder.toJson(json);
    }
    @ResponseBody
    @RequestMapping(value = "/addStudent",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
    public String addStudent(Student student)
    {
        Map map=new HashMap();
//        int maxStuId=managementService.selectMaxStuId();
//        student.setStuId(maxStuId+1);
        if (student!=null) {
            managementService.addStudent(student);
            map.put("addsuccess",true);
        }else {
            map.put("addfail",false);
        }
        return nonNullBinder.toJson(map);
    }
    @ResponseBody
    @RequestMapping(value = "/gradeComboList",method = RequestMethod.POST,produces ="application/json;charset=utf-8")
    public String gradeComboList()
    {
        List<HashMap> jsonlist=managementService.selectGradeIdAndName();
        return nonNullBinder.toJson(jsonlist);
    }
    @ResponseBody
    @RequestMapping(value = "/updateStudent",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
    public String updateStudent(Student student,int stuId)
    {
//        int stuId=student.getStuId();
        Map map=new HashMap();
        if(student!=null)
        {
            managementService.updateStudent(student);
            map.put("updatesuccess",true);
        }else {
            map.put("updatefail",false);
        }
        return nonNullBinder.toJson(map);
    }
    @ResponseBody
    @RequestMapping(value = "/addGrade",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
    public String addGrade(Grade grade)
    {
        Map map=new HashMap();
//        int gid=managementService.selectMaxGradeId();
//        grade.setId(gid);
        if (grade!=null)
        {
            managementService.addGrade(grade);
            map.put("addsuccess",true);
        }else {
            map.put("addfail",false);
        }
        return nonNullBinder.toJson(map);
    }
    @ResponseBody
    @RequestMapping(value = "/updateGrade",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
    public String updateGrade(Grade grade)
    {
        Map map=new HashMap();
        if(grade!=null)
        {
            managementService.updateGrade(grade);
            map.put("updatesuccess",true);
        }else {
            map.put("updatefail",false);
        }
        return nonNullBinder.toJson(map);
    }
    @ResponseBody
    @RequestMapping(value = "/deleteStudent",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
    public  String deleteStudent(String delIds)
    {

        String [] ids=delIds.split(",");
        List<Integer> list=new ArrayList<>();
        Integer idList []=new Integer[ids.length];
        for (int i=0;i<ids.length;i++)
        {
            idList[i]=Integer.parseInt(ids[i]);
            list.add(idList[i]);
        }
        Map map=new HashMap();
        if (delIds.length()>=1)
        {
            managementService.deleteStudent(idList);
            map.put("deletesuccess",true);
            map.put("deletecount",list.size());
        }else {
            map.put("deletefail",false);
        }
        return nonNullBinder.toJson(map);
    }
    @ResponseBody
    @RequestMapping(value = "/deleteGrade",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
    public String deleteGrade(String delIds)
    {
        String [] idArray=delIds.split(",");
        Integer idList []=new Integer[idArray.length];
        for (int i=0;i<idArray.length;i++)
        {
            idList[i]=Integer.parseInt(idArray[i]);
        }
        Map map=new HashMap();
        if (idArray.length>=1)
        {
            managementService.deleteGrade(idList);
            map.put("deletesuccess",true);
            map.put("deletecount",idArray.length);
        }else {
            map.put("deletefail",false);
        }
        return nonNullBinder.toJson(map);
    }


    @ResponseBody
    @RequestMapping(value = "/searchStudent",method = RequestMethod.POST,produces ="application/json;charset=utf-8")
    public String searchStudent(String sex,Integer gradeId)
    {
        List<Student> list=managementService.selectStudentBySexAndGrade(sex,gradeId);
        Map<String ,Object> map=new HashMap();
        int count=list.size();
        if(list!=null&&count!=0)
        {
            map.put("rows",list);
            map.put("total",count);
        }else {
            map.put("error",0);
        }
        return nonNullBinder.toJson(map);
    }

}
