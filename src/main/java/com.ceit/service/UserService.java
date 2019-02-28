package com.ceit.service;

import com.ceit.dao.UserMapper;
import com.ceit.util.EncoderMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    EncoderMD5 encoderMD5=new EncoderMD5();

    public Map judge(String account, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map map=new HashMap();
        String password_1=userMapper.judge_Password(account);
        String password_2=encoderMD5.EncoderByMd5(password);
        int count=userMapper.selectUserCount(account);
        if(password_2.equals(password_1)&&count==1)
        {
            map.put("one",1);
        }else {
            map.put("zero",0);
        }
        return map;

    }
    public HashMap selectRole_nameByAcc(String account)
    {
        HashMap roleAndName=userMapper.selectRole_nameByAcc(account);
        return roleAndName;
    }

}
