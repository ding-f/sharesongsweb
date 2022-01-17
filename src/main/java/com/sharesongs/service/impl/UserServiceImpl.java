package com.sharesongs.service.impl;

import com.sharesongs.dao.UserDao;
import com.sharesongs.dao.imp.UserDaoImpl;
import com.sharesongs.entity.User;
import com.sharesongs.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    UserDao ud = new UserDaoImpl();
    @Override
    public boolean checkYourName(String yourname) throws SQLException {

        User user=ud.SearchByYourName(yourname);
        boolean b=(user==null)?false:true;
        return b;
    }

    @Override
    public boolean checkYourNameAndPasswd(String yourname, String passwd) throws SQLException {
        User user=ud.SearchByPasswdAndYourName(yourname,passwd);
        boolean b=(user!=null)?true:false;
        return b;
    }

}
