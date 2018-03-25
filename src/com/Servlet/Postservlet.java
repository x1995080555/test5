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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		CookBean cookBean = new CookBean();
        PrintWriter out = response.getWriter();  
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        ServletFileUpload upload = new ServletFileUpload(factory); 
        upload.setFileSizeMax(30*1024*1024);		//���õ����ļ��ϴ��Ĵ�С  
        upload.setSizeMax(100*1024*1024);			//���ļ��ϴ�ʱ�ܴ�С����  
        List<FileItem> list = null;
        boolean flag = false;
		try {
			list = (List<FileItem>)upload.parseRequest(request);
		}catch(FileUploadBase.FileSizeLimitExceededException e) {  
            out.write("�ϴ����ļ�������30M");  
            return;  
        }catch(FileUploadBase.SizeLimitExceededException e){  
            out.write("���ļ�������100M");  
            return;  
        }catch (FileUploadException e) {  
            e.printStackTrace();  
            throw new RuntimeException("�ϴ����ݽ���ʧ�ܣ���������һ��");  
        }  
		if(list!=null) {
//	        String path = request.getRealPath("/upload");  
	        String path = getServletContext().getRealPath("/images/");
//	        System.out.println(path);
	        File file=new File(path);
	        if(!file.exists()){
	            file.mkdirs();
	            System.out.println("make dir");
	        }
	        factory.setRepository(new File(path));  
	        factory.setSizeThreshold(1024*1024) ;  
	        try {      
	            for(FileItem item : list){  
	                //��ȡ��������  
	                String name = item.getFieldName();  
	                System.out.println("Field:"+name);
	                //�����ȡ�� ����Ϣ����ͨ�� �ı� ��Ϣ  
	                if(item.isFormField()){                     
	                    //��ȡ�û�����������ַ���,��Ϊ���ύ�������� �ַ������͵�  
	                    String value = item.getString() ; 
	                    String aString = new String(value.getBytes("ISO-8859-1"),"UTF-8");
	                    System.out.println("value:"+aString);
	                    request.setAttribute(name, value);  
	                }else{  
	                    //��ȡ·����  
	                    String value = item.getName() ;
	                    System.out.println("filename:"+value);
	                    //���������һ����б��  
	                    int start = value.lastIndexOf("\\");  
	                    //��ȡ �ϴ��ļ��� �ַ������֣���1�� ȥ����б�ܣ�  
	                    String filename = value.substring(start+1); 
	                    cookBean.setCimg(filename);
	                    request.setAttribute(name, filename);  
	                    //д��������  
	                    item.write( new File(path+File.separator+filename) );//�������ṩ��  
	                  
	                    flag = true;
	                }  
	            }  
	        } catch (Exception e) {  
	            System.out.println("�ϴ�ʧ��");
	            response.getWriter().print("�ϴ�ʧ�ܣ�"+e.getMessage());
	        }
	        if(flag) {
	        	Date date = new Date();
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		String posttime = sdf.format(date);
	    		int cview = 0;
	        	String title = (String) request.getAttribute("title");
	        	String ctitle = new String(title.getBytes("ISO-8859-1"),"UTF-8");
	        	
	        	String text = (String) request.getAttribute("text");
	        	String ctext = new String(text.getBytes("ISO-8859-1"),"UTF-8");
	        	
	        	String sort = (String) request.getAttribute("sort");
	        	sort = sort.replaceAll("\r|\n", "");
	        	int sid = Integer.parseInt(sort);
	        	System.out.println("test3:"+sid);
	        	
	        	String id = (String) request.getAttribute("uid");
	        	id = id.replaceAll("\r|\n", "");
	        	int uid = Integer.parseInt(id);
	        	System.out.println("test4:"+uid);
	        	
	        	cookBean.setCtext(ctext);
	        	cookBean.setCtime(posttime);
	        	cookBean.setCview(cview);
	        	cookBean.setCtitle(ctitle);
	        	cookBean.setSid(sid);
	        	CookDao cookDao = new CookDao();
	        	boolean tag = false;
	        	try {
	        		tag = cookDao.post(cookBean, uid);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	if(tag) {
	        		  String filename = cookBean.getCimg();
	        		  System.out.println("�ϴ��ɹ���"+filename);
	                  response.getWriter().print(filename);//��·�����ظ��ͻ���
	        	}
	        }
		}
	}
}
