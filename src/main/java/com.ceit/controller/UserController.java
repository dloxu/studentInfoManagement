package com.ceit.controller;

import com.ceit.service.UserService;
import com.ceit.util.CaptchaUtil;
import com.ceit.util.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    JsonMapper nonNullBinder = JsonMapper.nonEmptyMapper();


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String demo1()
    {
        return "index";
    }
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String demo2()
    {
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    public String login(String account, String password,String code, HttpSession session,HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if(code.toLowerCase().equals(session.getAttribute("randomString").toString().toLowerCase()))
        {
            Map map=userService.judge(account,password);
            Map roleAndName=userService.selectRole_nameByAcc(account);
            session.setAttribute("roleAndName",roleAndName);
            session.setAttribute("isLogin",1);
            return  nonNullBinder.toJson(map);
        }else {
            Map map=new HashMap();
            map.put("fail",0);
            map.put("mess","验证码错误");
            return   nonNullBinder.toJson(map);
        }

    }
    @ResponseBody
    @RequestMapping(value = "/captcha",method = RequestMethod.GET)
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        CaptchaUtil.outputCaptcha(request,response);
    }

}
