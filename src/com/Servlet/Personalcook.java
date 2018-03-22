package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Bean.CookBean;
import com.DBTool.CookDao;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class Personalcook
 */
@WebServlet("/Personalcook")
public class Personalcook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Personalcook() {
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
		HttpSession session = request.getSession();
		int uid = (int) session.getAttribute("Userid");
		ArrayList<CookBean> cookBeans = new ArrayList<CookBean>();
		CookDao cookDao = new CookDao();
		try {
			cookBeans = cookDao.selectbyuid(uid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter(); 
    	JSONArray jsonArray = JSONArray.fromObject(cookBeans);
    	System.out.println(jsonArray.toString());
    	out.write(jsonArray.toString());  
    	out.flush();  
    	out.close(); 
	}

}
