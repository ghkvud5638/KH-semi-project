package user.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import common.JDBCTemplate;
import user.dao.face.UserDao;
import user.dto.TB_USER;

public class UserDaoImpl implements UserDao{

   @Override
   public int insertUser(Connection conn, TB_USER up) {
      PreparedStatement ps = null;
      Calendar now = Calendar.getInstance();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      String today = sdf.format(now.getTime());
      
      String sql ="";
      sql+="INSERT INTO TB_USER (USER_ID,USER_NAME,PW,PHONE,EMAIL, GENDER, GRADE, JOIN_DATE, PICTURE) ";
      sql+=" VALUES(?,?,?,?,?,?,?,?,?)";
      int result = 0;
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, up.getUser_id());
         ps.setString(2, up.getUser_name());
         ps.setString(3, up.getPw());
         ps.setString(4, up.getPhone());
         ps.setString(5, up.getEmail());
         ps.setString(6, up.getGender());
         ps.setString(7, "normal");
         ps.setString(8, today);
         ps.setString(9, "mi.jpg");
         result = ps.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return result;
   }
   
   @Override
   public TB_USER selectByID(Connection conn, String id, String pw) {
      
      return null;
   }
   
   
   
   
//   중복체크 이런식으로?
   @Override
   public boolean check(Connection conn, TB_USER up) {
      PreparedStatement ps = null;
      ResultSet rs = null;
      
      String sql="";
      sql+="SELECT USER_ID FROM TB_USER";
      
      boolean res =false;
      
      try {
         ps = conn.prepareStatement(sql);
         rs = ps.executeQuery();
         while (rs.next()) {
            if (rs.getString("USER_ID").equals(up.getUser_id())) { 
               res = true;
            }
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      System.out.println("res :"+res);
      return res;
   }
   
   @Override
   public boolean checkDuplicateId(Connection conn, String id) {
      
      System.out.println("userDaoImpl -  ckeckDuplicateId");
      PreparedStatement ps = null;
      ResultSet rs = null;
      boolean res = false;
      
      String sql="";
      sql+="SELECT USER_ID FROM TB_USER";
      sql+=" WHERE USER_ID=?";
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, id);
         rs = ps.executeQuery();
         if (rs.next()) {
            res = true; // 해당 아이디 존재
         }
               
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         JDBCTemplate.close(rs);
         JDBCTemplate.close(ps);
      }
      return res;
   }
   
   //로그인 검증, id, pw
   @Override
   public int checkLoginIdPw(Connection conn, String userid, String userpw) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String dbPW = "";
        int x = -1;
        String sql="";
        sql+="SELECT PW FROM TB_USER WHERE USER_ID=?";
        try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, userid);
         rs = ps.executeQuery();
         
         if (rs.next()) {
            dbPW = rs.getString("PW");
            if (dbPW.equals(userpw)) {
               x = 1; // 로그인 성공, 입력한 아이디로 조회한 결과 비밀번호와 입력한 비밀번호가 같다
            } else {
               x = 0; // 로그인 실패, 입력한 아이디로 조회한 결과 비밀번호가 있지만, 입력한 비밀번호와 동일하지 않다
            }
         }else {
            x = -1; //아무것도 안나옴, 입력한 아이디가 없음.
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         JDBCTemplate.close(rs);
         JDBCTemplate.close(ps);
      }
      return x;
   }
   
   
   

   @Override
   public String adminCheck(Connection conn, String userid) {
        PreparedStatement ps = null;
           ResultSet rs = null;
      //SQL 작성
      String sql="";
      String grade = null;
      sql += "SELECT grade FROM TB_USER";
      sql += " WHERE USER_ID = ?";
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, userid);
         rs = ps.executeQuery();
         while (rs.next()) {
            grade = rs.getString("GRADE");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         //자원반납
         JDBCTemplate.close(rs);
         JDBCTemplate.close(ps);
      }
      
         return grade;
   }
   
   
   
   
   //id 찾기
      @Override
      public TB_USER searchId(Connection conn, String name, String email, String phone) {
         
         PreparedStatement ps = null;
         ResultSet rs = null;
         
         String sql = "";
         sql += "SELECT * FROM TB_USER";
         sql += " WHERE USER_NAME=? and EMAIL=? and PHONE=?";
         
         TB_USER user = null;
         
         try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            rs = ps.executeQuery();
            
               while(rs.next()) {
                  user = new TB_USER();
                  user.setUser_id(rs.getString("user_id"));
                  user.setUser_name(rs.getString("user_name"));
                  user.setGender(rs.getString("gender"));
                  user.setPw(rs.getString("pw"));
                  user.setPhone(rs.getString("phone"));
                  user.setEmail(rs.getString("email"));
                  user.setAddr1(rs.getString("addr1"));
                  user.setAddr2(rs.getString("addr2"));
//                  user.setGrade(rs.getString("grade"));
//                  user.setNicname(rs.getString("nicname"));
//                  user.setJoin_date(rs.getString("join_date"));
//                  user.setPicture(rs.getString("picture"));
//                  user.setOrigin_name(rs.getString("origin_name"));
//                  user.setStored_name(rs.getString("stored_name"));
                  
               }
            
            
         } catch (SQLException e) {
            e.printStackTrace();
            
         } finally {
            JDBCTemplate.close(ps);
            JDBCTemplate.close(rs);
            
         }
         
         return user;
      }

      @Override
      public TB_USER searchPw(Connection conn, String id, String name, String email, String phone) {
      
         PreparedStatement ps = null;
         ResultSet rs = null;
         
         String sql = "";
         sql += "SELECT * FROM TB_USER";
         sql += " WHERE USER_ID=? and USER_NAME=? and EMAIL=? and PHONE=?";
         
         TB_USER user = null;
         
         try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, phone);
            rs = ps.executeQuery();
            
               while(rs.next()) {
                  user = new TB_USER();
                  user.setUser_id(rs.getString("user_id"));
                  user.setUser_name(rs.getString("user_name"));
                  user.setGender(rs.getString("gender"));
                  user.setPw(rs.getString("pw"));
                  user.setPhone(rs.getString("phone"));
                  user.setEmail(rs.getString("email"));
                  user.setAddr1(rs.getString("addr1"));
                  user.setAddr2(rs.getString("addr2"));
//                  user.setGrade(rs.getString("grade"));
//                  user.setNicname(rs.getString("nicname"));
//                  user.setJoin_date(rs.getString("join_date"));
//                  user.setPicture(rs.getString("picture"));
//                  user.setOrigin_name(rs.getString("origin_name"));
//                  user.setStored_name(rs.getString("stored_name"));
                  
               }
            
            
         } catch (SQLException e) {
            e.printStackTrace();
            
         } finally {
            JDBCTemplate.close(ps);
            JDBCTemplate.close(rs);
            
         }
         
         return user;
      
      }
   
   
}