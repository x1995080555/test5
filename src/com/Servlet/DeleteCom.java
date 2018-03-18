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

import com.Bean.CommentBean;
import com.DBTool.CommentDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class DeleteCom
 */
@WebServlet("/DeleteCom")
public class DeleteCom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCom() {
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
		HttpSession session = request.getSession();
		int uid = (int)session.getAttribute("Userid");
		int cid = (int)session.getAttribute("Cookid");
		CommentDao commentDao = new CommentDao();
		ArrayList<CommentBean> commentBeans = new ArrayList<CommentBean>();
		boolean flag = false;
		try {
			flag = commentDao.deletecom(uid, cid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag) {
			try {
				commentBeans = commentDao.selbycid(cid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("application/json;charset=utf-8");
        	PrintWriter out = response.getWriter(); 
        	JSONArray jsonArray = JSONArray.fromObject(commentBeans);
        	System.out.println(jsonArray.toString());
        	out.write(jsonArray.toString());  
        	out.flush();  
        	out.close();
		}else {
			response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();  
        	out.print(flag);  
        	out.flush();  
        	out.close();
        }
	}
}
