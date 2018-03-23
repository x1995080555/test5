package com.Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.Bean.CookBean;
import com.DBTool.CookDao;


/**
 * Servlet implementation class Postservlet
 */
@WebServlet("/Postservlet")
public class Postservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Postservlet() {
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
		request.setCharacterEncoding("utf-8");  //设置编码  
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();  
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        ServletFileUpload upload = new ServletFileUpload(factory); 
        upload.setFileSizeMax(30*1024*1024);		//设置单个文件上传的大小  
        upload.setSizeMax(100*1024*1024);			//多文件上传时总大小限制  
        List<FileItem> list = null;
        boolean flag = false;
		try {
			list = (List<FileItem>)upload.parseRequest(request);
		}catch(FileUploadBase.FileSizeLimitExceededException e) {  
            out.write("上传的文件超出了30M");  
            return;  
        }catch(FileUploadBase.SizeLimitExceededException e){  
            out.write("总文件超出了100M");  
            return;  
        }catch (FileUploadException e) {  
            e.printStackTrace();  
            throw new RuntimeException("上传内容解析失败，请重新试一下");  
        }  
		if(list!=null) {
//	        String path = request.getRealPath("/upload");  
	        String path = getServletContext().getRealPath("/WEB-INF/img/");
	        File file=new File(path);
	        if(!file.exists()){
	            file.mkdirs();
	            System.out.println("make dir");
	        }
	        factory.setRepository(new File(path));  
	        factory.setSizeThreshold(1024*1024) ;  
	        try {      
	            for(FileItem item : list){  
	                //获取属性名字  
	                String name = item.getFieldName();  
	                System.out.println("name");
	                //如果获取的 表单信息是普通的 文本 信息  
	                if(item.isFormField()){                     
	                    //获取用户具体输入的字符串,因为表单提交过来的是 字符串类型的  
	                    String value = item.getString() ;  
	                    request.setAttribute(name, value);  
	                }else{  
	                    //获取路径名  
	                    String value = item.getName() ;  
	                    //索引到最后一个反斜杠  
	                    int start = value.lastIndexOf("\\");  
	                    //截取 上传文件的 字符串名字，加1是 去掉反斜杠，  
	                    String filename = value.substring(start+1);  
	                    request.setAttribute(name, filename);  
	                    //写到磁盘上  
	                    item.write( new File(path+File.separator+filename) );//第三方提供的  
	                    System.out.println("上传成功："+filename);
//	                    response.getWriter().print(filename);//将路径返回给客户端
	                    flag = true;
	                }  
	            }  
	        } catch (Exception e) {  
	            System.out.println("上传失败");
	            response.getWriter().print("上传失败："+e.getMessage());
	        }
	        if(flag) {
	        	CookBean cookBean = new CookBean();
	        	Date date = new Date();
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    		String posttime = sdf.format(date);
	    		int cview = 0;
	        	String title = (String) request.getAttribute("title");
	        	String img = (String) request.getAttribute("img");
	        	String text = (String) request.getAttribute("text");
	        	int sid = (int)request.getAttribute("sid");
	        	HttpSession session = request.getSession();
	        	int uid = (int)session.getAttribute("Userid");
	        	cookBean.setCimg(img);
	        	cookBean.setCtext(text);
	        	cookBean.setCtime(posttime);
	        	cookBean.setCview(cview);
	        	cookBean.setCtitle(title);
	        	cookBean.setSid(sid);
	        	CookDao cookDao = new CookDao();
	        	boolean tag = false;
	        	try {
	        		tag = cookDao.post(cookBean, uid);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	out.print(tag);  
	            out.flush();  
	            out.close();  
	        }
		}
	}
}
