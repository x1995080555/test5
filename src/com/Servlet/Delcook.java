package com.Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DBTool.CookDao;

/**
 * Servlet implementation class Delcook
 */
@WebServlet("/Delcook")
public class Delcook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delcook() {
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
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter(); 
		String img = request.getParameter("img");
		System.out.println(img);
		img = new String(img.getBytes("ISO-8859-1"),"UTF-8");
		String path = getServletContext().getRealPath("/images/"); 
		System.out.println(path);
		String filepath = path+File.separator+img;
		System.out.println(filepath);
		File deletefile = new File(filepath); 
		boolean flag = false;
		int cid = 0;
		if(deletefile.exists()) {
			flag = deletefile.delete();
			if(flag) {
        		CookDao cookDao = new CookDao();
        		boolean tag = false;
        		try {
					cid = cookDao.selectdelid(img);
					tag = cookDao.delete(cid);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		if(tag) {
            		boolean t = false;
            		try {
						t = cookDao.deletecom(cid);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		if(t) {
            			System.out.println("³É¹¦");
            			out.write("É¾³ý³É¹¦");  
            	        out.flush();  
            	        out.close();
            		}else {
            			System.out.println("Êý¾Ý¿âÉ¾³ýÆÀÂÛÊ§°Ü");
            			out.write("É¾³ýÆÀÂÛÏà¹ØÊ§°Ü");  
            	        out.flush();  
            	        out.close();
            		}
        		}else {
            		System.out.println("Êý¾Ý¿âÉ¾³ý×ÊÔ´Ê§°Ü");
            		out.write("É¾³ýÌû×ÓÊ§°Ü");  
        	        out.flush();  
        	        out.close();
        		}
        	}else {
        		System.out.println("Ê§°Ü");
        		out.write("É¾³ýÊ§°Ü");  
    	        out.flush();  
    	        out.close();
        	}
        }
	}
}
