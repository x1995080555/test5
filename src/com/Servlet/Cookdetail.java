package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Bean.CommentBean;
import com.Bean.CookBean;
import com.DBTool.CommentDao;
import com.DBTool.CookDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Cookdetail
 */
@WebServlet("/Cookdetail")
public class Cookdetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cookdetail() {
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
		String cid1 = request.getParameter("Cookid");
		int cid = Integer.parseInt(cid1);
		CookBean cookBean = new CookBean();
		CookDao cookDao = new CookDao();
		try {
			cookBean = cookDao.selectmain(cid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CommentDao commentDao = new CommentDao();
		ArrayList<CommentBean> commentBeans = new ArrayList<CommentBean>();
		try {
			commentBeans = commentDao.selbycid(cid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter(); 
    	JSONObject jsonObject1 = JSONObject.fromObject(cookBean);
    	System.out.println(jsonObject1.toString());
    	
    	JSONArray jsonArray1 = JSONArray.fromObject(commentBeans);
    	System.out.println(jsonArray1.toString());
    	
    	JSONObject jObject = new JSONObject();
    	jObject.put("cook",jsonObject1);
    	jObject.put("comment", jsonArray1);
    	System.out.println(jObject.toString());
    	
    	out.write(jObject.toString());  
    	out.flush();  
    	out.close(); 
	}

}
