package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class db {
	ResultSet rs=null;
	public PreparedStatement connection(String SQL){
	    //定义要读取的字段
	    String driver=null;
	    String url=null;
	    String username=null;
	    String password=null;
	    Connection conn=null;
	    PreparedStatement ps= null;
	    try {
	        //从配置文件中读取配置
	        Properties properties=new Properties();
	        //获取配置文件的路径
	        String path=Thread.currentThread().getContextClassLoader().getResource("jdbc.properties").getPath();
	        FileInputStream in=null;
	        in=new FileInputStream(path);
	        properties.load(in);
	        System.out.println("path:"+path);
	        //获取配置文件中的信息，放入到对应的变量中
	        driver=properties.getProperty("driver");
	        url=properties.getProperty("url");
	        username=properties.getProperty("username");
	        password=properties.getProperty("password");
	        System.out.println("获取到的URL："+url+"\t username:"+username+"\tpassword:"+password);
	        in.close();
	        //加载驱动
	        Class.forName(driver);
	        System.out.println("开始连接数据库！");
	        conn = DriverManager.getConnection(url, username, password);//连接数据库
	        if (!(conn==null)){
	            System.out.println("连接数据库成功");
	        }else {
	            System.out.println("连接数据库失败！");
	        }
	        ps = conn.prepareStatement(SQL);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return ps;
	}
//	public ResultSet add(String SQL,) {
//		connection(SQL);
//		return rs;
//	}
}
