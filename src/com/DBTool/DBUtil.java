package com.DBTool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtil {  
    private static String url="jdbc:mysql://localhost:3306/demo?&useSSL=true";  
    private static String driverClass="com.mysql.jdbc.Driver";  
    private static String username="root";  
    private static String password="123456";  
    private static Connection conn;  
    
    private PreparedStatement s;
    
    
    
    public PreparedStatement getprep(Connection conn, String sql){
        try {
            s = conn.prepareStatement(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return s;
    }
    
    
    

    
    
    //装载驱动  
    static{  
        try{  
            Class.forName(driverClass);  
        }  
        catch(ClassNotFoundException e){  
            e.printStackTrace();  
        }  
    }  
    
    
    
    
    //获取数据库连接  
    public static Connection getConnection(){  
        try{  
            conn=DriverManager.getConnection(url,username,password);  
        }  
        catch(SQLException e){  
            e.printStackTrace();  
        }  
        return conn;  
    }  
    //建立数据库连接  
    public static void main(String[] args){  
    	
    	
    	DBUtil dbUtil=new DBUtil();
    	
        Connection conn=DBUtil.getConnection();  
        
        
        if(conn==null){  
            System.out.println("数据库连接失败！");  
        }  
        }  
    //关闭数据库连接  
    public static void Close(){  
        if(conn!=null){  
            try{  
                conn.close();  
            }  
            catch(SQLException e){  
                e.printStackTrace();  
            }  
        }  
    }  
    }  