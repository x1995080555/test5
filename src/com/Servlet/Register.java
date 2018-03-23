package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Bean.UserBean;
import com.DBTool.UserDao;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");  
	    PrintWriter out = response.getWriter();  
		String uname = request.getParameter("uname"); //用于接收前段输入的ID值，此处参数须和input控件的name值一致  
        String upwd= request.getParameter("upwd");
        String uphone= request.getParameter("uphone");
        UserBean userBean = new UserBean();
        userBean.setUname(uname);
        userBean.setUpwd(upwd);
        userBean.setUphone(uphone);
        UserDao userDao = new UserDao();
        boolean flag = false;
        try {
			flag = userDao.register(userBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        out.print(flag);  
        out.flush();  
        out.close();  
	}

}
