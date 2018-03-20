package com.DBTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.Bean.CookBean;

public class CookDao {
	DBUtil dbUtil = new DBUtil();
	public boolean post(CookBean cookBean,int uid) throws Exception{
		Connection conn = dbUtil.getConnection();
		String sql = "insert into cook(Ctitle,Ctime,Cview,Cimg,Ctext,Csort,Uid) values (?,?,?,?,?,?,?)";
		PreparedStatement prest = dbUtil.getprep(conn, sql);	
		prest.setString(1,cookBean.getCtitle());
		prest.setString(2, cookBean.getCtime());
		prest.setInt(3, cookBean.getCview());
		prest.setString(4, cookBean.getCimg());
		prest.setString(5, cookBean.getCtext());
		prest.setInt(6, cookBean.getSort());
		prest.setInt(7, uid);
		int i = prest.executeUpdate();
		if(i>0){
			return true;
		}
		return false;	
	}
	
	public ArrayList<CookBean> select() throws Exception{
		ArrayList<CookBean> Cookinfo = new ArrayList<CookBean>();
		Connection connection  = dbUtil.getConnection();
		PreparedStatement prest =  null;
		ResultSet rs = null;
		String sql = "select Cid,Ctitle,Ctime,Cview,Cimg,Csort,"
				+ "Uname from cook,user"
				+ "where cook.Uid = user.Uid order by Ctime desc";
		prest = dbUtil.getprep(connection, sql);
		rs = prest.executeQuery();
		while (rs.next()) {
			CookBean cookBean = new CookBean();
			cookBean.setCid(rs.getInt("Cid"));
			cookBean.setCtitle(rs.getString("Ctitle"));
			cookBean.setCtime(rs.getString("Ctime"));
			cookBean.setCview(rs.getInt("Cview"));
			cookBean.setCimg(rs.getString("Cimg"));
			cookBean.setSort(rs.getInt("Csort"));
			cookBean.setUname(rs.getString("Uname"));
			Cookinfo.add(cookBean);
		}
		return Cookinfo;
	} 
	
	
	public ArrayList<CookBean> selectbyview() throws Exception{
		ArrayList<CookBean> Cookinfo = new ArrayList<CookBean>();
		Connection connection  = dbUtil.getConnection();
		PreparedStatement prest =  null;
		ResultSet rs = null;
		String sql = "select Cid,Ctitle,Ctime,Cview,Cimg,Csort,"
				+ "Uname from cook,user"
				+ "where cook.Uid = user.Uid order by Cview desc";
		prest = dbUtil.getprep(connection, sql);
		rs = prest.executeQuery();
		while (rs.next()) {
			CookBean cookBean = new CookBean();
			cookBean.setCid(rs.getInt("Cid"));
			cookBean.setCtitle(rs.getString("Ctitle"));
			cookBean.setCtime(rs.getString("Ctime"));
			cookBean.setCview(rs.getInt("Cview"));
			cookBean.setCimg(rs.getString("Cimg"));
			cookBean.setSort(rs.getInt("Csort"));
			cookBean.setUname(rs.getString("Uname"));
			Cookinfo.add(cookBean);
		}
		return Cookinfo;
	} 
	
	public ArrayList<CookBean> selectbysort(String sort) throws Exception{
		ArrayList<CookBean> Cookinfo = new ArrayList<CookBean>();
		Connection connection  = dbUtil.getConnection();
		PreparedStatement prest =  null;
		ResultSet rs = null;
		String sql = "select Cid,Ctitle,Ctime,Cview,Cimg,Csort,"
				+ "Uname from cook,user"
				+ "where cook.Uid = user.Uid and Csort = ? order by Ctime desc";
		prest = dbUtil.getprep(connection, sql);
		prest.setString(1, sort);
		rs = prest.executeQuery();
		while (rs.next()) {
			CookBean cookBean = new CookBean();
			cookBean.setCid(rs.getInt("Cid"));
			cookBean.setCtitle(rs.getString("Ctitle"));
			cookBean.setCtime(rs.getString("Ctime"));
			cookBean.setCview(rs.getInt("Cview"));
			cookBean.setCimg(rs.getString("Cimg"));
			cookBean.setSort(rs.getInt("Csort"));
			cookBean.setUname(rs.getString("Uname"));
			Cookinfo.add(cookBean);
		}
		return Cookinfo;
	} 
	
	public ArrayList<CookBean> selectbyuid(int uid) throws Exception{
		ArrayList<CookBean> Cookinfo = new ArrayList<CookBean>();
		Connection connection  = dbUtil.getConnection();
		PreparedStatement prest =  null;
		ResultSet rs = null;
		String sql = "select Cid,Ctitle,Ctime,Cview,Cimg,Csort,"
				+ "Uname from cook,user"
				+ "where cook.Uid = user.Uid and user.Uid = ? order by Ctime desc";
		prest = dbUtil.getprep(connection, sql);
		prest.setInt(1, uid);
		rs = prest.executeQuery();
		while (rs.next()) {
			CookBean cookBean = new CookBean();
			cookBean.setCid(rs.getInt("Cid"));
			cookBean.setCtitle(rs.getString("Ctitle"));
			cookBean.setCtime(rs.getString("Ctime"));
			cookBean.setCview(rs.getInt("Cview"));
			cookBean.setCimg(rs.getString("Cimg"));
			cookBean.setSort(rs.getInt("Csort"));
			cookBean.setUname(rs.getString("Uname"));
			Cookinfo.add(cookBean);
		}
		return Cookinfo;
	} 
	
	public ArrayList<CookBean> search(String search) throws Exception{
		ArrayList<CookBean> Cookinfo = new ArrayList<CookBean>();
		Connection connection  = dbUtil.getConnection();
		PreparedStatement prest =  null;
		ResultSet rs = null;
		String sql = "select Cid,Ctitle,Ctime,Cview,Cimg,Csort,"
				+ "Uname from cook,user"
				+ "where cook.Uid = user.Uid and "
				+ "(Csort like ? or Ctitle like ?) order by Ctime desc";
		prest = dbUtil.getprep(connection, sql);
		prest.setString(1, "%"+search+"%");
		prest.setString(2, "%"+search+"%");
		rs = prest.executeQuery();
		while (rs.next()) {
			CookBean cookBean = new CookBean();
			cookBean.setCid(rs.getInt("Cid"));
			cookBean.setCtitle(rs.getString("Ctitle"));
			cookBean.setCtime(rs.getString("Ctime"));
			cookBean.setCview(rs.getInt("Cview"));
			cookBean.setCimg(rs.getString("Cimg"));
			cookBean.setSort(rs.getInt("Csort"));
			cookBean.setUname(rs.getString("Uname"));
			Cookinfo.add(cookBean);
		}
		return Cookinfo;
	} 
	
	public ArrayList<CookBean> selectmain(int cid) throws Exception{
		ArrayList<CookBean> Cookinfo = new ArrayList<CookBean>();
		Connection connection  = dbUtil.getConnection();
		PreparedStatement prest =  null;
		ResultSet rs = null;
		String sql = "select Cid,Ctitle,Ctime,Cview,Cimg,Csort,"
				+ "Ctext,Uname from cook,user"
				+ "where cook.Uid = user.Uid and Cid = ?";
		prest = dbUtil.getprep(connection, sql);
		prest.setInt(1, cid);
		rs = prest.executeQuery();
		while (rs.next()) {
			CookBean cookBean = new CookBean();
			cookBean.setCid(rs.getInt("Cid"));
			cookBean.setCtitle(rs.getString("Ctitle"));
			cookBean.setCtime(rs.getString("Ctime"));
			cookBean.setCview(rs.getInt("Cview"));
			cookBean.setCimg(rs.getString("Cimg"));
			cookBean.setSort(rs.getInt("Csort"));
			cookBean.setUname(rs.getString("Uname"));
			cookBean.setCtext(rs.getString("Ctext"));
			Cookinfo.add(cookBean);
		}
		return Cookinfo;
	} 
	
	public boolean updateview(int cid,int count) throws Exception{
		Connection connection  = dbUtil.getConnection();
		PreparedStatement prest =  null;
		String sql = "update cook set Cview=? where Cid = ?";
		prest = dbUtil.getprep(connection, sql);
		prest.setInt(1,count);
		prest.setInt(2,cid);
		int i = prest.executeUpdate();
		if(i>0){
			return true;
		}
		return false;	
	} 
	
	public boolean delete(int cid) throws Exception{
		Connection conn = dbUtil.getConnection();
		String sql = "delete from cook where Cid= ?";
		PreparedStatement prest = dbUtil.getprep(conn, sql);	
		prest.setInt(1, cid);
		int i = prest.executeUpdate();
		if(i>0){
			return true;
		}
		return false;
	}
	
	public boolean deletecom(int id) throws Exception{
		Connection conn = dbUtil.getConnection();
		String sql = "delete from reply where Cid= ?";
		PreparedStatement prest = dbUtil.getprep(conn, sql);	
		prest.setInt(1, id);
		int i = prest.executeUpdate();
		if(i>0){
			return true;
		}
		return false;
	}
}
