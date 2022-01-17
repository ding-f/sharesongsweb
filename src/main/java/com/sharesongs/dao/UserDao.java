package com.sharesongs.dao;

import com.sharesongs.entity.User;

import java.sql.SQLException;

public interface UserDao {
    public User SearchByYourName(String yourname) throws SQLException;
    public User SearchByPasswdAndYourName(String yourname,String passwd) throws SQLException;
}
