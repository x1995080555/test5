package com.DBTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Bean.UserBean;

import sun.security.util.PropertyExpander.ExpandException;


public class UserDao {
	DBUtil dbUtil = new DBUtil();
	
	public boolean login(UserBean userBean) throws Exception{
		Connection con = dbUtil.getConnection();
		UserBean user = new  UserBean();
		String sql = "select * from user where Uname=? and Upwd=?";
		ResultSet rs = null;
		PreparedStatement prest = dbUtil.getprep(con, sql);
		prest.setString(1, userBean.getUname());
		prest.setString(2, userBean.getUpwd());
		rs = prest.executeQuery();
		while(rs.next()){
			dbUtil.Close();
			return true;
		}
		dbUtil.Close();
		return false;
	}
	
	public UserBean selinfo(UserBean userBean) throws Exception{
		Connection con = dbUtil.getConnection();
		String sql = "select * from user where Uname=? and Upwd=?";
		ResultSet rs = null;
		PreparedStatement prest = dbUtil.getprep(con, sql);
		prest.setString(1, userBean.getUname());
		prest.setString(2, userBean.getUpwd());
		rs = prest.executeQuery();
		while(rs.next()){
			userBean.setUid(rs.getInt("Uid"));
			userBean.setUname(rs.getString("Uname"));
			userBean.setUpwd(rs.getString("Upwd"));
			userBean.setUphone(rs.getString("Uphone"));
		}
		dbUtil.Close();
		return userBean;
	}
	
	
	public UserBean selectuserinfo(int uid) throws Exception{
		Connection con = dbUtil.getConnection();
		String sql = "select * from user where Uid=?";
		ResultSet rs = null;
		PreparedStatement prest = dbUtil.getprep(con, sql);
		prest.setInt(1, uid);
		rs = prest.executeQuery();
		UserBean userBean = new UserBean();
		while(rs.next()){
			userBean.setUid(rs.getInt("Uid"));
			userBean.setUname(rs.getString("Uname"));
			userBean.setUpwd(rs.getString("Upwd"));
			userBean.setUphone(rs.getString("Uphone"));
		}
		dbUtil.Close();
		return userBean;
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
			dbUtil.Close();
			return true;
		}
		dbUtil.Close();
		return false;
	}
	
	public boolean changephone(UserBean userBean) throws Exception{
		Connection connection = dbUtil.getConnection();
		String sql = "update user set Uphone = ? where Uid = ?";
		PreparedStatement preparedStatement = dbUtil.getprep(connection, sql);
		preparedStatement.setString(1, userBean.getUphone());
		preparedStatement.setInt(2, userBean.getUid());
		int i = preparedStatement.executeUpdate();
		if(i>0) {
			dbUtil.Close();
			return true;
		}
		dbUtil.Close();
		return false;
	}
	
	public boolean changepwd(UserBean userBean)throws Exception{
		Connection connection = dbUtil.getConnection();
		String sql = "update user set Upwd = ? where Uid = ?";
		PreparedStatement preparedStatement = dbUtil.getprep(connection, sql);
		preparedStatement.setString(1, userBean.getUpwd());
		preparedStatement.setInt(2, userBean.getUid());
		int i = preparedStatement.executeUpdate();
		if(i >0) {
			dbUtil.Close();
			return true;
		}
		dbUtil.Close();
		return false;
	}
}
