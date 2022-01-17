package com.sharesongs.dao.imp;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sharesongs.dao.UserDao;
import com.sharesongs.entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    ComboPooledDataSource pr=new ComboPooledDataSource();
    QueryRunner qr=new QueryRunner(pr);
    User user=null;
    @Override
    public User SearchByYourName(String yourname) throws SQLException {
        String sql="select * from users where yourname=?;";     //用户名查重
        User user=qr.query(sql,new BeanHandler<>(User.class),yourname);
        return user;
    }

    @Override
    public User SearchByPasswdAndYourName(String yourname, String passwd) throws SQLException {
        String sql="select * from users where yourname=? and passwd=?;";        //查询用户名密码
        User user= qr.query(sql,new BeanHandler<>(User.class),yourname,passwd);

        return user;
    }


}
