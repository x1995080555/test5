package com.DBTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.Bean.CommentBean;



public class CommentDao {
	DBUtil dbUtil = new DBUtil();
	
	public boolean commentcook(CommentBean commentBean,int uid,int cid) throws Exception{
		Connection conn = dbUtil.getConnection();
		String sql = "insert into reply values (?,?,?,?)";
		PreparedStatement prest = dbUtil.getprep(conn, sql);	
		prest.setString(1, commentBean.getRtext());
		prest.setString(2, commentBean.getRtime());
		prest.setInt(3, uid);
		prest.setInt(4, cid);
		int i = prest.executeUpdate();
		if(i>0){
			return true;
		}
		return false;
	}
	
	public boolean deletecom(int uid,int cid) throws Exception{
		Connection conn = dbUtil.getConnection();
		String sql = "delete from reply where Uid = ? and Cid = ? ";
		PreparedStatement prest = dbUtil.getprep(conn, sql);	
		prest.setInt(1, uid);
		prest.setInt(2, cid);
		int i = prest.executeUpdate();
		if(i>0){
			return true;
		}
		return false;
	}
	
	public boolean deletebyid(int rid) throws Exception{
		Connection conn = dbUtil.getConnection();
		String sql = "delete from reply where Rid = ? ";
		PreparedStatement prest = dbUtil.getprep(conn, sql);	
		prest.setInt(1, rid);
		int i = prest.executeUpdate();
		if(i>0){
			return true;
		}
		return false;
	}
	
	public ArrayList<CommentBean> selbycid(int cid) throws Exception{
		ArrayList<CommentBean> commentinfo = new ArrayList<CommentBean>();
		Connection connection  = dbUtil.getConnection();
		String sql = "select Rid,Rtext,Rtime,reply.Uid,Uname from cook,user,reply"
				+ "where user.Uid = reply.Uid and reply.Cid = cook.Cid and cook.Cid =? ";				                                                                                                             
		PreparedStatement prest =  null;
		ResultSet rs = null;
		prest = dbUtil.getprep(connection, sql);
		prest.setInt(1, cid);
		rs = prest.executeQuery();
		while (rs.next()) {
			CommentBean commentBean = new CommentBean();
			commentBean.setRid(rs.getInt("Rid"));
			commentBean.setRtext(rs.getString("Rtext"));
			commentBean.setRtime(rs.getString("Rtime"));
			commentBean.setUid(rs.getInt("Uid"));
			commentBean.setUname(rs.getString("Uname"));
			commentinfo.add(commentBean);
		}
		return commentinfo;
	} 
	
	public ArrayList<CommentBean> selectallcom() throws Exception{
		ArrayList<CommentBean> commentinfo = new ArrayList<CommentBean>();
		Connection connection  = dbUtil.getConnection();
		PreparedStatement prest =  null;
		ResultSet rs = null;
		String sql = "select Rid,Rtext,Rtime,reply.Uid,Uname,Ctitle from cook,user,reply"
				+ "where reply.Uid = user.Uid and reply.Cid = cook.Cid";
		prest = dbUtil.getprep(connection, sql);
		rs = prest.executeQuery();
		while (rs.next()) {
			CommentBean commentBean = new CommentBean();
			commentBean.setRid(rs.getInt("Rid"));
			commentBean.setRtext(rs.getString("Rtext"));
			commentBean.setRtime(rs.getString("Rtime"));
			commentBean.setUid(rs.getInt("Uid"));
			commentBean.setUname(rs.getString("Uname"));
			commentBean.setCtitle(rs.getString("Ctitle"));
			commentinfo.add(commentBean);
		}
		return commentinfo;
	} 

}
