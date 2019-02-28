package com.ceit.dao;

import java.util.HashMap;

public interface UserMapper {
    public String judge_Password(String account);
    public int selectUserCount(String account);
    public HashMap selectRole_nameByAcc(String account);

}
