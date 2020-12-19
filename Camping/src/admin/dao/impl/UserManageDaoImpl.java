package admin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.common.JDBCTemplate;
import admin.dao.face.UserManageDao;
import admin.dto.TB_USER;
import common.Paging;

public class UserManageDaoImpl implements UserManageDao {
   PreparedStatement ps = null;
   ResultSet rs = null;
   
   
   @Override
   public int selectCntAll(Connection connection) {
   
      //SQL 작성
      String sql = "";
      sql += "SELECT count(*) FROM TB_USER WHERE NOT GRADE = ?";
      
      //결과 저장할 변수
      int totalCount = 0;
      
      try {
         ps = connection.prepareStatement(sql); //SQL수행 객체
         ps.setString(1, "admin");
         
         rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
         //조회 결과 처리
         while(rs.next()) {
            totalCount = rs.getInt(1);
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         //DB객체 닫기
         JDBCTemplate.close(rs);
         JDBCTemplate.close(ps);
      }
      
      //최종 결과 반환
      return totalCount;
   }
   
   @Override
   public List<TB_USER> userList(Connection conn, Paging paging) {
      String sql = "";
      sql += "SELECT * FROM (";
      sql += "   SELECT rownum rnum, B.* FROM (";  //게시글번호가 아니라 rownum을 붙이는 이유는 중간에 만약에 게시글이 중간에 삭제 된 상태에서 조회를하면 그부분이 비어보인다.
      sql += "      SELECT * ";
      sql += "      FROM TB_USER";
      sql += "       WHERE NOT GRADE = ?";
      sql += "      ORDER BY JOIN_DATE DESC";
      sql += "   ) B";
      sql += " ) TB_USER";
      sql += " WHERE rnum BETWEEN ? AND ?"; // rownum을 기준으로 조회한다.
      
      //조회 결과를 저장할 List
      List<TB_USER> list = new ArrayList<>();
      
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, "admin");

         ps.setInt(2, paging.getStartNo());
         ps.setInt(3, paging.getEndNo());
         
         rs = ps.executeQuery();
         while(rs.next()) {
            TB_USER b = new TB_USER();
            
            b.setUser_id( rs.getString("user_id") );
            b.setUser_name( rs.getString("user_name") );
            b.setGender( rs.getString("gender") );
            b.setPhone( rs.getString("phone") );
            b.setEmail( rs.getString("email") );
            b.setAddr1( rs.getString("addr1") );
            b.setAddr2( rs.getString("addr2") );
            b.setGrade( rs.getString("grade") );
            b.setNicname( rs.getString("nicname") );
            b.setJoin_date( rs.getDate("join_date") );
            b.setStored_name( rs.getString("stored_name") );
            b.setPicture(rs.getString("PICTURE"));
            list.add(b);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         JDBCTemplate.close(rs);
         JDBCTemplate.close(ps);
      }
      
      return list;
   }
   
   @Override
   public TB_USER selectUserByID(Connection conn, String user_id) {
      PreparedStatement ps = null; //SQL 수행 객체
      ResultSet rs = null; //조회 결과 객체
      //SQL 작성
      String sql="";
      sql += "SELECT * FROM TB_USER";
      sql += " WHERE USER_ID = ?";
      TB_USER user = null;
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, user_id);
         rs = ps.executeQuery();
         while (rs.next()) {
            user = new TB_USER();
            user.setPhone(rs.getString("PHONE"));
            user.setGender(rs.getString("GENDER"));
            user.setGrade(rs.getString("GRADE"));
            user.setAddr1(rs.getString("Addr1"));
            user.setAddr2(rs.getString("Addr2"));
            user.setJoin_date(rs.getDate("JOIN_DATE"));
            user.setUser_id(rs.getString("USER_ID"));
            user.setUser_name(rs.getString("USER_NAME"));
            user.setNicname(rs.getString("NICNAME"));
            user.setEmail(rs.getString("EMAIL"));
            user.setPicture(rs.getString("PICTURE"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         //자원반납
         JDBCTemplate.close(rs);
         JDBCTemplate.close(ps);
      }
      
         return user;
   }
   
   @Override
   public int removeUser(Connection conn, String user_id) {
   int result = 0;
      
      try {
         String sql="";
         sql += "SELECT * FROM TB_USER";
         sql += " WHERE USER_ID = ?";
         ps = conn.prepareStatement(sql);
         ps.setString(1, user_id);
         rs = ps.executeQuery();

         while(rs.next()) {
            sql = "";
            sql += "DELETE FROM TB_USER";
            sql += " WHERE USER_ID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user_id);
            result = ps.executeUpdate();
         
         System.out.println(rs);
         
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         JDBCTemplate.close(rs);
         JDBCTemplate.close(ps);
      }
      return result;
   }

   @Override
   public int modifyUser(Connection conn, TB_USER userInfo, String user_id, String save, int chk1) {
      String sql="";
      sql += "UPDATE TB_USER SET";
      sql += " NICNAME=?, PHONE=?, EMAIL=?, ADDR1=?, ADDR2=?, PICTURE=?";
      sql += " WHERE USER_ID = ?";
      int res = 0;
      System.out.println(userInfo.getPicture());
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, userInfo.getNicname());
         ps.setString(2, userInfo.getPhone());
         ps.setString(3, userInfo.getEmail());
         ps.setString(4, userInfo.getAddr1());
         ps.setString(5, userInfo.getAddr2());
//         if( chk1 == 0) {
//            ps.setString(6, save+userInfo.getPicture());
//         } else {
//            ps.setString(6, userInfo.getPicture());
//         }
         ps.setString(6, userInfo.getPicture());
         ps.setString(7, user_id);
         res = ps.executeUpdate();
         
         System.out.println(res);
         
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         JDBCTemplate.close(ps);
      }
      return res;
   }
   
}