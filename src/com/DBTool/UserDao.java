package com.DBTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Bean.UserBean;


public class UserDao {
	DBUtil dbUtil = new DBUtil();
	
	
	public boolean login(UserBean userBean) throws Exception{
		Connection con = dbUtil.getConnection();
		UserBean user = new  UserBean();
		String sql = "select * from demotable where uid=? and pwd=?";
		ResultSet rs = null;
		PreparedStatement prest = dbUtil.getprep(con, sql);
		prest.setString(1, userBean.getUname());
		prest.setString(2, userBean.getUpwd());
		rs = prest.executeQuery();
		while(rs.next()){
			return true;
		}
		return false;
	}
	
	public boolean register(UserBean userBean) throws Exception{
		Connection con = dbUtil.getConnection();
		String sql = "insert into user(Uname,Upwd,Uphone) values (?,?,?)";
		PreparedStatement prest = dbUtil.getprep(con, sql);	
		prest.setString(1, userBean.getUname());
		prest.setString(2, userBean.getUpwd());
		prest.setString(3, userBean.getUphone());
		int i = prest.executeUpdate();
		if(i>0){
			return true;
		}
		return false;
	}
}
