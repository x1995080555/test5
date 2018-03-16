package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Bean.UserBean;
import com.DBTool.DBUtil;
import com.DBTool.UserDao;



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
		String uname = request.getParameter("name"); //用于接收前段输入的ID值，此处参数须和input控件的name值一致  
        String upwd= request.getParameter("pwd");//用于接收前段输入的PW值，此处参数须和input控件的name值一致  
        boolean type=false;//用于判断账号和密码是否与数据库中查询结果一致  
        response.setContentType("text/html; charset=UTF-8");  
        PrintWriter out = response.getWriter();  
        UserBean userBean = new UserBean();
        userBean.setUname(uname);
        userBean.setUpwd(upwd);
        try  
        {   
        	UserDao userDao = new UserDao();
        	type = userDao.login(userBean);
        }
        catch(Exception ex)  
        {  
            ex.printStackTrace();  
        }  
        if(type) {
        	UserDao userDao = new UserDao();
        	int id = 0;
        	try {
				id = userDao.selid(userBean);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	HttpSession session = request.getSession();
        	session.setAttribute("Userid",id);
        	System.out.println(id);
//			String id1 = Integer.toString(id);
//			session.setAttribute("Suserid",id1);
        	out.print(type);  
        	out.flush();  
        	out.close();
        }
        else {
        	out.print(type);  
        	out.flush();  
        	out.close();
        }

	} 
}
