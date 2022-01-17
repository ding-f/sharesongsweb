package com.sharesongs.service;

import java.sql.SQLException;

public interface UserService {
public boolean checkYourName(String yourname) throws SQLException;
public boolean checkYourNameAndPasswd(String yourname,String passwd ) throws SQLException;

}
