package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Bean.CookBean;
import com.DBTool.CookDao;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class Sortselect
 */
@WebServlet("/Sortselect")
public class Sortselect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sortselect() {
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
		request.setCharacterEncoding("utf-8");
		String sort = request.getParameter("sort");
		ArrayList<CookBean> cookBeans = new ArrayList<CookBean>();
		CookDao cookDao = new CookDao();
		try {
			cookBeans = cookDao.selectbysort(sort);
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
