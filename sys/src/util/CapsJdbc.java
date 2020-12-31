package util;

import java.io.IOException;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author zhangys
 * Desc: JDBC功能的封装，门面模式
 */
public class CapsJdbc {
   static {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
   }

   /**
    * 执行 查询、修改、增加，删除的SQL语句功能
    * @param strSQL   SQL语句
    * @param params   绑定的参数值
    * @return   CapsRes 对象
    * @throws AppException
    */
   public static CapsRes execSQL(String strSQL,List<Object> params) throws AppException {
      CapsRes capsRes = new CapsRes();
      Matcher matcher = pattern.matcher(strSQL);
      boolean isPstmt = true;
      boolean isFind = false;
      if (matcher.find()) {
         isFind = true;
         if (params == null || params.size() == 0) {
            isPstmt = false;
            try (Statement stmt = getConn().createStatement();
                 ResultSet rs = stmt.executeQuery(strSQL)) {
               dealStmt(rs,capsRes);
            } catch (SQLException ex) {
               ex.printStackTrace();
               throw new AppException(ex.getMessage(), ex.getCause());
            }
         }
      }
      if (isPstmt) {
         try (PreparedStatement pstmt = getConn().prepareStatement(strSQL) ) {
            if (params != null && params.size() > 0) {
               int idx = 1;
               for (Object p : params) {
                  if (p instanceof Integer) {
                     pstmt.setInt(idx, (Integer) p);
                  } else if (p instanceof Date) {
                     java.sql.Date d = new java.sql.Date(((Date) p).getTime());
                     pstmt.setDate(idx, d);
                  } else {
                     pstmt.setString(idx, p.toString());
                  }
                  ++idx;
               }
            }
            if (isFind) {
               try (ResultSet rs = pstmt.executeQuery()) {
                  dealStmt(rs, capsRes);
               }
            } else {
               if (pstmt.execute()) {
                  try (ResultSet rs = pstmt.getResultSet()) {
                     dealStmt(rs, capsRes);
                  }
               } else {
                  capsRes.setType(0);
                  capsRes.setTtlNum(pstmt.getUpdateCount());
               }
            }
         } catch (SQLException ex) {
            ex.printStackTrace();
            throw new AppException(ex.getMessage(), ex.getCause());
         }
      }
      return capsRes;
   }


   public static List<Map<String,String>> getColInfo(String tableName) {
      List<Map<String,String>> result = new ArrayList<>();
      try (ResultSet rs = getConn().getMetaData().getColumns(null,null,tableName.toUpperCase(), "%")){
         while (rs.next()) {
            Map<String,String> map = new HashMap<>();
            String colName = rs.getString("COLUMN_NAME");
            map.put("code", colName);
            String remarks = rs.getString("REMARKS");
            if(remarks == null || remarks.equals("")){
               remarks = colName;
            }
            map.put("name",remarks);
            String dbType = rs.getString("TYPE_NAME");
            map.put("dbType",dbType);
//            map.put("valueType", changeDbType(dbType));
            result.add(map);
         }
         return result;
      } catch (SQLException ex) {
         ex.printStackTrace();
         throw new AppException(ex.getMessage(), ex.getCause());
      }
   }

   private static void dealStmt(ResultSet rs,CapsRes capsRes) throws SQLException{
      ResultSetMetaData rsm = rs.getMetaData();
      int fldCnt = rsm.getColumnCount();
      List<String> flds = new ArrayList<>(fldCnt);
      for (int i = 1; i <= fldCnt; i++) {
         flds.add(rsm.getColumnName(i));
      }
      capsRes.setFlds(flds);
      List<List<String>> recList = new ArrayList<>();
      while (rs.next()) {
         List<String> rec = new ArrayList<>(fldCnt);
         for (String fld : flds) {
            rec.add(rs.getString(fld));
         }
         recList.add(rec);
      }
      capsRes.setRs(recList);
      capsRes.setTtlNum(recList.size());
      capsRes.setType(1);
   }

   //其他数据库不需要这个方法 oracle和db2需要
//   private static String getSchema(Connection conn) throws Exception {
//      String schema;
//      schema = conn.getMetaData().getUserName();
//      if ((schema == null) || (schema.length() == 0)) {
//         throw new Exception("ORACLE数据库模式不允许为空");
//      }
//      return schema.toUpperCase().toString();
//
//   }


   private static Connection getConn() throws AppException {
      try {
         Properties props = new Properties();
//         System.out.println(CapsJdbc.class.getClassLoader().getResourceAsStream("/resources/appconf.properties"));
         props.load(CapsJdbc.class.getClassLoader().getResourceAsStream("resources/appconf.properties"));
         
         String uname = props.getProperty("uname");
         String upwd = props.getProperty("upwd");
         String dbhost = props.getProperty("dbhost");
         String dbport = props.getProperty("dbport");
         String db = props.getProperty("db");
         String url = String.format("jdbc:mysql://%s:%s/%s?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",dbhost,dbport,db);
         Connection conn =
                 DriverManager.getConnection(url, uname, upwd);
         return conn;
      } catch (IOException | SQLException ex) {
         ex.printStackTrace();
         throw new AppException(ex.getMessage(),ex.getCause());
      }
   }

   private static final Pattern pattern = Pattern.compile("^\\sSELECT",Pattern.CASE_INSENSITIVE);

   public static void main(String[] args) throws Exception {
      // TODO Auto-generated method stub
	   String sql = "select starttime,endtime from course_room where classroon_num='4-205' and course=0101 ;";
      CapsRes res = execSQL(sql, null);
      System.out.println(res);
      
//    System.out.println(execSQL("update c set ccredit=ccredit-1", null));
   }
}
