package shopping.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import shopping.dao.face.BoardDao;
import shopping.dto.Board;

public class BoardDaoImpl implements BoardDao{
	//sql객체
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public int insertReview(Connection conn, Board board) {
		System.out.println("BoardDaoImpl [insertReview] ");
		
		//SQL문 작성
		String sql = "";
		sql += "INSERT INTO board(boardno, title, user_id, id, content, board_cate, hit, WRITTENDATE)";
		sql += " VALUES(board_seq.nextval, ?, ?, ?, ?, ?, 0, sysdate)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getUser_id());
			ps.setString(3, board.getId());
			ps.setString(4, board.getContent());
			ps.setString(5, board.getBoardCate());
			
			res = ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		
		return res;
	}

	@Override
	public int insertQA(Connection conn, Board board) {
System.out.println("BoardDaoImpl [insertQA] ");
		
		//SQL문 작성
		String sql = "";
		sql += "INSERT INTO board(boardno, title, user_id, id, content, board_cate, hit, WRITTENDATE)";
		sql += " VALUES(board_seq.nextval, ?, ?, ?, ?, ?, 0, sysdate)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getUser_id());
			ps.setString(3, board.getId());
			ps.setString(4, board.getContent());
			ps.setString(5, board.getBoardCate());
			
			res = ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		
		return res;
	}
	
	
	   @Override
	   public Board selectByBoardno(Connection conn, String boardno) {
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      
	      String sql = "";
	      sql += "SELECT * FROM board";
	      sql += " WHERE board_cate = 'review' and boardno = ?";
	      
	      Board reviewInfo = null;
	      
	      try {
	         ps = conn.prepareStatement(sql);
	         ps.setString(1, boardno);
	         
	         rs = ps.executeQuery();
	         while(rs.next()) {
	            reviewInfo = new Board();
	            
	            reviewInfo.setBoardno(rs.getString("boardno"));
	            reviewInfo.setBoardCate(rs.getString("board_cate"));
	            reviewInfo.setUser_id(rs.getString("user_id"));
	            reviewInfo.setTitle(rs.getString("title"));
	            reviewInfo.setWrittendate(rs.getDate("writtendate"));
	            reviewInfo.setHit(rs.getInt("hit"));
	            reviewInfo.setContent(rs.getString("content"));
	            
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         JDBCTemplate.close(rs);
	         JDBCTemplate.close(ps);
	      }
	      
	      return reviewInfo;
	   }

	   @Override
	   public Board selectByQABoardno(Connection conn, String boardno) {
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      
	      String sql = "";
	      sql += "SELECT * FROM board";
	      sql += " WHERE board_cate like 'Q%' and boardno = ?";
	      
	      Board reviewInfo = null;
	      
	      try {
	         ps = conn.prepareStatement(sql);
	         ps.setString(1, boardno);
	         
	         rs = ps.executeQuery();
	         while(rs.next()) {
	            reviewInfo = new Board();
	            
	            reviewInfo.setBoardno(rs.getString("boardno"));
	            reviewInfo.setBoardCate(rs.getString("board_cate"));
	            reviewInfo.setUser_id(rs.getString("user_id"));
	            reviewInfo.setTitle(rs.getString("title"));
	            reviewInfo.setWrittendate(rs.getDate("writtendate"));
	            reviewInfo.setHit(rs.getInt("hit"));
	            reviewInfo.setContent(rs.getString("content"));
	            
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         JDBCTemplate.close(rs);
	         JDBCTemplate.close(ps);
	      }
	      
	      return reviewInfo;
	   }



	   
	}



	
