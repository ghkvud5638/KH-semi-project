package shopping.dao.face;

import java.sql.Connection;

import shopping.dto.Board;


public interface BoardDao {

	public int insertReview(Connection conn, Board board);

	public int insertQA(Connection conn, Board board);

	
	   /**
	    * 
	    * @param conn - DB연결 객체
	    * @param boardno - 게시판 번호로 리뷰게시글 조회
	    * @return
	    */
	   public Board selectByBoardno(Connection conn, String boardno);

	   public Board selectByQABoardno(Connection conn, String boardno);
	
	
	
}
