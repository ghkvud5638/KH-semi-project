package board.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import board.dao.face.BoardDao;
import board.dto.Board;
import board.dto.TB_BOARD_COMMENT;
import common.JDBCTemplate;
import common.Paging;

public class BoardDaoImpl implements BoardDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public int insertArticle(Connection conn, Board board) {
		System.out.println("BoardDao [insertArticle]");
		System.out.println("-------------------------");
		Calendar now = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(now.getTime());
		
		String sql="";	
		sql+="INSERT INTO board (boardno, title, content, user_id, board_cate,vid_url, Writtendate, hit)";
		sql+=" VALUES(board_seq.nextval, ?,?,?,?,?, ?, 0)";
		
//		board_seq.nextval
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setString(3, board.getUser_id());
			ps.setString(4, board.getBoardCate());
			ps.setString(5, board.getVid_url());
			ps.setString(6, today);
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return res;
	}
	
	
	
	@Override
	public int selectBoardCntAll(Connection connection, String category) {
		System.out.println("category : "+category);
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM board";
		sql += " WHERE BOARD_CATE=?";
		//결과 저장할 변수
		int totalCount = 0;		
		try {
			ps = connection.prepareStatement(sql); //SQL수행 객체
			ps.setString(1,category);	
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
	public List<Board> selectBoardList(Connection connection, Paging paging, String category) {
		System.out.println("paging : "+paging);
		System.out.println("category : "+category);
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";  //게시글번호가 아니라 rownum을 붙이는 이유는 중간에 만약에 게시글이 중간에 삭제 된 상태에서 조회를하면 그부분이 비어보인다.
		sql += "		SELECT";
		sql += "			boardno, title, user_id";
		sql += "			, content, hit, writer_date, board_cate";
		sql += "		FROM board WHERE board_cate=?";
		sql += "		ORDER BY TO_NUMBER(boardno) DESC";
		sql += "	) B";
		sql += " ) BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?"; // rownum을 기준으로 조회한다.
		
		//결과 저장할 List
		List<Board> boardList = new ArrayList<>();
		
		try {
			ps = connection.prepareStatement(sql); //SQL수행 객체
			ps.setString(1, category);
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			
			System.out.println(paging.getStartNo());
			System.out.println(paging.getEndNo());
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			//조회 결과 처리
			while(rs.next()) {
				Board b = new Board(); //결과값 저장 객체
				
				//결과값 한 행 처리
				b.setBoardno( rs.getString("boardno") );
				b.setTitle( rs.getString("title") );
				b.setUser_id( rs.getString("user_id") );
				b.setContent( rs.getString("content") );
				b.setHit( rs.getInt("hit") );
				b.setWrittendate( rs.getDate("writer_date") );
				b.setBoardCate( rs.getString("board_cate"));
				//리스트에 결과값 저장
				boardList.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}	
		//최종 결과 반환
		return boardList;
	}
	
	@Override
	public Board selectDetail(Connection connection, Board board) {
		
		String sql="";
		sql+="SELECT * FROM BOARD";
		sql+=" WHERE BOARDNO=?";
		Board detail = null;
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, board.getBoardno());
			rs = ps.executeQuery(); 
			while(rs.next()) {
				detail = new Board();
				detail.setTitle(rs.getString("TITLE"));
				detail.setContent(rs.getString("CONTENT"));
				detail.setHit(rs.getInt("HIT"));
				detail.setBoardCate(rs.getString("BOARD_CATE"));
				detail.setWrittendate(rs.getDate("WRITER_DATE"));
				detail.setUser_id(rs.getString("USER_ID"));
				detail.setBoardno(rs.getString("BOARDNO"));
				detail.setVid_url(rs.getString("VID_URL"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		return detail;
	}



	public int increaseHit(Connection connection, Board board) {
		
			String sql="";
			sql+="UPDATE BOARD SET HIT=HIT+1 WHERE BOARDNO=?";
			int res = 0;
			
			try {
				ps = connection.prepareStatement(sql);
				ps.setString(1, board.getBoardno());
				res = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(ps);
			}
			
			return res;
	}
	
	/**
	 * 게시글 삭제
	 */
	@Override
	public int deleteArticleByBoardno(Connection connection, int boardno) {
		PreparedStatement ps = null;
		
		String sql="";
		sql+= "DELETE FROM BOARD";
		sql+= " WHERE BOARDNO=?";
		int result = 0;
		System.out.println("dao boardno : "+boardno);
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, boardno);
			result = ps.executeUpdate();
			System.out.println("dao res : " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		return result;
	}
	
	/**
	 * 게시글 수정
	 */
	@Override
	public int updateArticle(Connection connection, Board board) {
		String sql="";
		sql+="UPDATE BOARD SET TITLE=?, CONTENT=?";
		sql+=" WHERE BOARDNO=?";
		int res = 0;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setString(3, board.getBoardno());
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return res;
	}
	
	
	@Override
	public Board selectBoardDetail(Connection connection, String boardno) {
		Board board = null;
		String sql="";
		sql+="SELECT * FROM BOARD";
		sql+=" WHERE BOARDNO=?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, boardno);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				board = new Board();
				board.setBoardCate(rs.getString("board_cate"));
				board.setBoardno(rs.getString("boardno"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setUser_id(rs.getString("user_id"));
				board.setHit(rs.getInt("hit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return board;
	}
	//
	@Override
	public int insertBoardComment(Connection connection, TB_BOARD_COMMENT comment) {
		String sql="";
		sql+="INSERT INTO TB_BOARD_COMMENT(COMMENT_ID,BOARDNO,USER_ID,CONTENT)";
		sql+=" VALUES(SEQ_BOARD_COMMENT.NEXTVAL,?,?,?)";
		int res = 0;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, comment.getBoardno());
			ps.setString(2, comment.getUser_id());
			ps.setString(3, comment.getContent());
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return res;
	}
	// 게시글 댓글 조회 
	@Override
	public List<TB_BOARD_COMMENT> selectCommentList(Connection connection, String boardno) {
		String sql="";
		sql="SELECT COMMENT_ID,BOARDNO,USER_ID,TO_CHAR(COMMENT_DATE,'YYYY-MM-DD HH24:MI:SS')COMMENT_DATE,CONTENT FROM TB_BOARD_COMMENT";
		sql+=" WHERE BOARDNO=?";
		sql+=" ORDER BY TO_NUMBER(COMMENT_ID) DESC";
		List<TB_BOARD_COMMENT> commentList = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1,boardno);
			rs = ps.executeQuery();
			while (rs.next()) {
				TB_BOARD_COMMENT comment = new TB_BOARD_COMMENT();
				comment.setBoardno(rs.getString("BOARDNO"));
				comment.setUser_id(rs.getString("USER_ID"));
				comment.setComment_date(rs.getString("COMMENT_DATE"));
				comment.setComment_id(rs.getString("COMMENT_ID"));
				comment.setContent(rs.getString("CONTENT"));
				commentList.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		return commentList;
	}
	//댓글 삭제
	@Override
	public int deleteComment(Connection connection, String commentId) {
		String sql="";
		sql+="DELETE FROM TB_BOARD_COMMENT";
		sql+=" WHERE COMMENT_ID=?";
		int result=0;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, commentId);
			result=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return result;
	}
	
	
	   @Override
	   public List<Board> selectAllBoardList(Connection connection, Paging paging) {
	      System.out.println("paging : "+paging);
	      //SQL 작성
	      String sql = "";
	      sql += "SELECT * FROM (";
	      sql += "   SELECT rownum rnum, B.* FROM (";  //게시글번호가 아니라 rownum을 붙이는 이유는 중간에 만약에 게시글이 중간에 삭제 된 상태에서 조회를하면 그부분이 비어보인다.
	      sql += "      SELECT";
	      sql += "         boardno, title, user_id";
	      sql += "         , content, hit, writtendate, board_cate";
	      sql += "      FROM board";
	      sql += "      ORDER BY TO_NUMBER(boardno) DESC";
	      sql += "   ) B";
	      sql += " ) BOARD";
	      sql += " WHERE rnum BETWEEN ? AND ?"; // rownum을 기준으로 조회한다.
	      
	      //결과 저장할 List
	      List<Board> boardList = new ArrayList<>();
	      
	      try {
	         ps = connection.prepareStatement(sql); //SQL수행 객체
	         ps.setInt(1, paging.getStartNo());
	         ps.setInt(2, paging.getEndNo());
	         
	         System.out.println(paging.getStartNo());
	         System.out.println(paging.getEndNo());
	         
	         rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
	         //조회 결과 처리
	         while(rs.next()) {
	            Board b = new Board(); //결과값 저장 객체
	            
	            //결과값 한 행 처리
	            b.setBoardno( rs.getString("boardno") );
	            b.setTitle( rs.getString("title") );
	            b.setUser_id( rs.getString("user_id") );
	            b.setContent( rs.getString("content") );
	            b.setHit( rs.getInt("hit") );
	            b.setWrittendate( rs.getDate("writtendate") );
	            b.setBoardCate( rs.getString("board_cate"));
	            //리스트에 결과값 저장
	            boardList.add(b);
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         //DB객체 닫기
	         JDBCTemplate.close(rs);
	         JDBCTemplate.close(ps);
	      }   
	      //최종 결과 반환
	      return boardList;
	   }
	   
	   @Override
	   public int selectAllBoardCntAll(Connection connection) {
	      //SQL 작성
	      String sql = "";
	      sql += "SELECT count(*) FROM board";
	      //결과 저장할 변수
	      int totalCount = 0;      
	      try {
	         ps = connection.prepareStatement(sql); //SQL수행 객체
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
	   public int selectSearchBoardCntAll(Connection connection, String category, String search) {
	      System.out.println("category : "+category);
	      //SQL 작성
	      String sql = "";
	      if("all".equals(category)) {
	         sql += "SELECT count(*) FROM board";
	         sql += " WHERE TITLE like ?";
	      }else {
	      sql += "SELECT count(*) FROM board";
	      sql += " WHERE BOARD_CATE=? AND TITLE like ?";
	      }
	      String se = "%" + search + "%";
	      //결과 저장할 변수
	      int totalCount = 0;      
	      try {
	         ps = connection.prepareStatement(sql); //SQL수행 객체
	         if("all".equals(category)) {
	            ps.setString(1,se);   
	         }else {
	         ps.setString(1,category);   
	         ps.setString(2,se);   
	         }
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
	   public List<Board> selectSearchBoardList(Connection connection, Paging paging, String category, String search) {
	      System.out.println("paging : "+paging);
	      System.out.println("category : "+category);
	      //SQL 작성
	      String sql = "";
	      if("all".equals(category)) {
	      sql += "SELECT * FROM (";
	      sql += "   SELECT rownum rnum, B.* FROM (";  //게시글번호가 아니라 rownum을 붙이는 이유는 중간에 만약에 게시글이 중간에 삭제 된 상태에서 조회를하면 그부분이 비어보인다.
	      sql += "      SELECT";
	      sql += "         boardno, title, user_id";
	      sql += "         , content, hit, writtendate, board_cate";
	      sql += "      FROM board WHERE TITLE like ?";
	      sql += "      ORDER BY TO_NUMBER(boardno) DESC";
	      sql += "   ) B";
	      sql += " ) BOARD";
	      sql += " WHERE rnum BETWEEN ? AND ?"; // rownum을 기준으로 조회한다.
	      } else {
	      sql += "SELECT * FROM (";
	      sql += "   SELECT rownum rnum, B.* FROM (";  //게시글번호가 아니라 rownum을 붙이는 이유는 중간에 만약에 게시글이 중간에 삭제 된 상태에서 조회를하면 그부분이 비어보인다.
	      sql += "      SELECT";
	      sql += "         boardno, title, user_id";
	      sql += "         , content, hit, writtendate, board_cate";
	      sql += "      FROM board WHERE board_cate=? AND TITLE like ?";
	      sql += "      ORDER BY TO_NUMBER(boardno) DESC";
	      sql += "   ) B";
	      sql += " ) BOARD";
	      sql += " WHERE rnum BETWEEN ? AND ?"; // rownum을 기준으로 조회한다.
	      }
	      //결과 저장할 List
	      List<Board> boardList = new ArrayList<>();
	      String se = "%" + search + "%";

	      try {
	         ps = connection.prepareStatement(sql); //SQL수행 객체
	         if("all".equals(category)) {
	         ps.setString(1, se);
	         ps.setInt(2, paging.getStartNo());
	         ps.setInt(3, paging.getEndNo());
	         } else {
	         ps.setString(1, category);
	         ps.setString(2, se);
	         ps.setInt(3, paging.getStartNo());
	         ps.setInt(4, paging.getEndNo());
	         }
	         System.out.println(paging.getStartNo());
	         System.out.println(paging.getEndNo());
	         
	         rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
	         //조회 결과 처리
	         while(rs.next()) {
	            Board b = new Board(); //결과값 저장 객체
	            
	            //결과값 한 행 처리
	            b.setBoardno( rs.getString("boardno") );
	            b.setTitle( rs.getString("title") );
	            b.setUser_id( rs.getString("user_id") );
	            b.setContent( rs.getString("content") );
	            b.setHit( rs.getInt("hit") );
	            b.setWrittendate( rs.getDate("writtendate") );
	            b.setBoardCate( rs.getString("board_cate"));
	            //리스트에 결과값 저장
	            boardList.add(b);
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         //DB객체 닫기
	         JDBCTemplate.close(rs);
	         JDBCTemplate.close(ps);
	      }   
	      //최종 결과 반환
	      return boardList;
	   }
	
	
	
	
}
