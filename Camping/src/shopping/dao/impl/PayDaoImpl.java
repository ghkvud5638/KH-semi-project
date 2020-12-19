package shopping.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import oracle.sql.DATE;
import shopping.dao.face.PayDao;
import shopping.dto.TB_PAY;
import shopping.dto.TB_PROD;
import shopping.dto.TB_USER;

public class PayDaoImpl implements PayDao{

	//sql 수행 객체, 조회 결과 객체
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public TB_PROD selectByProdid(Connection conn, String prod_id) {
	System.out.println("/shopping/pay [PayDaoImpl]");
		
		//sql 작성
		String sql = "";
		sql += "SELECT prod_id, prod_price, order_id, prod_name FROM tb_prod";
		sql += " WHERE prod_id = ?";
		
		//쿼리 수행 결과 저장 객체
		TB_PROD tb_prod = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, prod_id);
			
			//쿼리 수행
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				tb_prod = new TB_PROD();
				
				tb_prod.setOrder_id(rs.getString("order_id"));
				tb_prod.setProd_id(rs.getString("PROD_ID"));
				tb_prod.setProd_price(rs.getInt("PROD_PRICE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//자원 반납
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return tb_prod;
	}


	@Override
	public TB_USER selectByUser(Connection conn, String loginid) {
		//SQL 작성
		//2-1. 유저 아이디, 이름, 핸드폰, 주소, 우편번호 select해오기
		String sql = "";
		sql += "SELECT USER_ID, USER_NAME, PHONE, ADDR1, ADDR2 FROM TB_USER";
		sql += " WHERE USER_ID = ? ";
		
		//쿼리 수행 결과 저장 객체
		TB_USER tb_user = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginid);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				tb_user = new TB_USER();
				
				tb_user.setUser_id(rs.getString("USER_ID"));
				tb_user.setUser_name(rs.getString("USER_NAME"));
				tb_user.setPhone(rs.getString("PHONE"));
				tb_user.setAddr1(rs.getString("ADDR1"));
				tb_user.setAddr2(rs.getString("ADDR2"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return tb_user;
	}
	
	
//정보 삽입
	@Override
	public int insertPay(Connection conn, TB_PAY newPay) {
		System.out.println("payDaoImpl [insertPay]");
		
		
		//SQL 작성
		String sql = "";
		sql += "INSERT INTO TB_PAY(PAY_ID,ORDER_ID, USER_ID, PROD_ID, PAY_DATE, PROD_CNT, TOTALPRICE)";
		sql += " VALUES(pay_id_seq.nextval, ?, ?, ?, sysdate, ?, ?)";
		
		int result = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newPay.getOrder_id());
			ps.setString(2, newPay.getUser_id());
			ps.setString(3, newPay.getProd_id());
			ps.setInt(4, newPay.getProd_cnt());
			ps.setInt(5, newPay.getTotalprice());
			
			result = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//자원 해제
			JDBCTemplate.close(ps);
		}
		
		return result;
	}
	
	
	   //재고 업데이트 하기
	   //현재 수량 업데이트 하기
	   
	   public int updateProdNum(Connection conn, String prodno, int num) {
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      String sql = "";
	      sql += "UPDATE TB_PROD A SET ";
	      sql += " prod_num = prod_num - ?";
	      sql += " where prod_id = ?";
	      int result =0;
	      try {
	         ps = conn.prepareStatement(sql);
	         ps.setInt(1, num);
	         ps.setString(2, prodno);
	         
	         result = ps.executeUpdate(); 

	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         JDBCTemplate.close(ps);
	      }
	      return result;
	   }
	   //재고조회
	   @Override
	   public int selectProd_num(Connection connection, String prodno) {
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      String sql = "";
	      sql += "SELECT PROD_NUM FROM TB_PROD";
	      sql += " WHERE PROD_ID = ?";
	      int amt = 0;
	      System.out.println(prodno);
	      try {
	         ps = connection.prepareStatement(sql);
	         ps.setString(1, prodno);
	         
	         rs = ps.executeQuery();
	         while( rs.next() ) {
	         amt= rs.getInt("PROD_NUM");
	         System.out.println(amt);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         JDBCTemplate.close(rs);
	         JDBCTemplate.close(ps);
	      }
	      
	      return amt;
	   }
	}
	
	
	
	

