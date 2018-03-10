package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DBTool.DBUtil;



/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ID = request.getParameter("ID"); //���ڽ���ǰ�������IDֵ���˴��������input�ؼ���nameֵһ��  
        String PW= request.getParameter("PW");//���ڽ���ǰ�������PWֵ���˴��������input�ؼ���nameֵһ��  
        boolean type=false;//�����ж��˺ź������Ƿ������ݿ��в�ѯ���һ��  
        response.setContentType("text/html; charset=UTF-8");  
        PrintWriter out = response.getWriter();  
        DBUtil dbUtil = new DBUtil();
        try  
        {  

     
//            Connection con=DBUtil.getConnection();  
        	Connection con = dbUtil.getConnection();
//            Statement stmt=con.createStatement();  
            
//        String sql="select * from demo.demotable where uid='"+ID+"' and pwd='"+PW+"'";  
            
  
          String sql="select * from demo.demotable where uid=? and pwd=?";
//                 PreparedStatement preparedStatement= DBUtil.getprep(con,sql); 
          PreparedStatement preparedStatement  = dbUtil.getprep(con, sql);
          preparedStatement.setString(1, ID);
          preparedStatement.setString(2, PW);
//        
          
          
//        ResultSet rs=stmt.executeQuery(sql);
          ResultSet rs=preparedStatement.executeQuery();
            while(rs.next())  
            {  
                type=true;  
            }  
        }  
        catch(Exception ex)  
        {  
            ex.printStackTrace();  
        }  
        finally  
        {  
            DBUtil.Close();  
            out.print(type);  
            out.flush();  
            out.close();  
        }  
	} 

}0    
