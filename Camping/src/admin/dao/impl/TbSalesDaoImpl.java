package admin.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import admin.common.JDBCTemplate;
import admin.common.Paging;
import admin.dao.face.TbSalesDao;

public class TbSalesDaoImpl implements TbSalesDao {
   
   PreparedStatement ps = null;
   ResultSet rs = null;
   
   @Override
   public int selectCntAll(Connection conn) {
      //SQL 작성
            String sql = "";
            sql += "SELECT count(*) FROM TB_PAY";
            
            //결과 저장할 변수
            int totalCount = 0;
            
            try {
               ps = conn.prepareStatement(sql); //SQL수행 객체
               
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

   /*
    * 현재 쓰이지 않으며 나중에 전체 조회 할때만 쓰일 예정
    */
   @Override
   public List<HashMap<String, Object>> selectSalesList(Connection conn, Paging paging) {
      String sql = "";
      sql += "SELECT * FROM (";
      sql += "   SELECT rownum rnum, B.* FROM (";  //게시글번호가 아니라 rownum을 붙이는 이유는 중간에 만약에 게시글이 중간에 삭제 된 상태에서 조회를하면 그부분이 비어보인다.
      sql += "      SELECT PR.PROD_NAME,SUM(PROD_CNT) PROD_CNT, TO_CHAR(PA.PAY_DATE,'YYYY/MM/DD') PAY_DATE, SUM(TOTALPRICE) TOTALPRICE, PROD_NUM, PR.PROD_ID ";
      sql += "      FROM TB_PAY PA";
      sql += "      INNER JOIN TB_PROD PR ON PA.prod_id = PR.prod_id";
      sql += "      GROUP BY PR.PROD_NAME,TO_CHAR(PA.PAY_DATE,'YYYY/MM/DD'), PROD_NUM, PR.PROD_ID";
      sql += "      ORDER BY PAY_date";
      sql += "   ) B";
      sql += " ) TB_PAY";
      sql += " WHERE rnum BETWEEN ? AND ?"; // rownum을 기준으로 조회한다.
      System.out.println("작동작동");
      List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
      try {
         ps = conn.prepareStatement(sql);
         ps.setInt(1, paging.getStartNo());
         ps.setInt(2, paging.getEndNo());
         
         rs = ps.executeQuery();
         while(rs.next()) {
            HashMap<String, Object> joinmap = new HashMap<String, Object>();
            
            
//            joinmap.put("pay_id", rs.getString("pay_id"));
//            joinmap.put("Order_id", rs.getString("order_id"));
//            joinmap.put("User_id", rs.getString("user_id"));
            joinmap.put("Prod_id", rs.getString("prod_id"));
            joinmap.put("Pay_date", rs.getString("PAY_DATE"));
            joinmap.put("Prod_cnt", rs.getInt("PROD_CNT"));
            joinmap.put("Totalprice", rs.getInt("TOTALPRICE"));
            joinmap.put("Prod_name", rs.getString("PROD_NAME"));
            joinmap.put("Prod_num", rs.getString("PROD_NUM"));

            
            list.add(joinmap);
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
   public List<HashMap<String, Object>> SalesListDate(Connection conn, Paging paging, String start, String end) {
      String sql = "";
      sql += "SELECT * FROM (";
      sql += "   SELECT rownum rnum, B.* FROM (";  //게시글번호가 아니라 rownum을 붙이는 이유는 중간에 만약에 게시글이 중간에 삭제 된 상태에서 조회를하면 그부분이 비어보인다.
      sql += "      SELECT PR.PROD_NAME,SUM(PROD_CNT) PROD_CNT, TO_CHAR(PA.PAY_DATE,'YYYY/MM/DD') PAY_DATE, SUM(TOTALPRICE) TOTALPRICE, PROD_NUM, PR.PROD_ID ";
      sql += "      FROM TB_PAY PA";
      sql += "      INNER JOIN TB_PROD PR ON PA.prod_id = PR.prod_id";
      sql += "       WHERE TO_CHAR(PA.PAY_DATE, 'YYYY/MM/DD') BETWEEN ? and ?";
      sql += "      GROUP BY PR.PROD_NAME,TO_CHAR(PA.PAY_DATE,'YYYY/MM/DD'), PROD_NUM, PR.PROD_ID";
      sql += "      ORDER BY PAY_date";
      sql += "   ) B";
      sql += " ) TB_PAY";
      sql += " WHERE rnum BETWEEN ? AND ?"; // rownum을 기준으로 조회한다.
      
      //조회 결과를 저장할 List
      List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, start);
         ps.setString(2, end);
         ps.setInt(3, paging.getStartNo());
         ps.setInt(4, paging.getEndNo());
         
         rs = ps.executeQuery();
         while(rs.next()) {
            HashMap<String, Object> joinmap = new HashMap<String, Object>();

            
//            joinmap.put("pay_id", rs.getString("pay_id"));
//            joinmap.put("Order_id", rs.getString("order_id"));
//            joinmap.put("User_id", rs.getString("user_id"));
            joinmap.put("Prod_id", rs.getString("prod_id"));
            joinmap.put("Pay_date", rs.getString("PAY_DATE"));
            joinmap.put("Prod_cnt", rs.getInt("PROD_CNT"));
            joinmap.put("Totalprice", rs.getInt("TOTALPRICE"));
            joinmap.put("Prod_name", rs.getString("PROD_NAME"));
            joinmap.put("Prod_num", rs.getString("PROD_NUM"));
            

            list.add(joinmap);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         JDBCTemplate.close(rs);
         JDBCTemplate.close(ps);
      }
      
      return list;
   }
   
}