package board.dao.face;

import java.sql.Connection;
import java.util.List;

import board.dto.Board;
import board.dto.TB_BOARD_COMMENT;
import common.Paging;

public interface BoardDao {

	public int insertArticle(Connection conn, Board board);

	public int selectBoardCntAll(Connection connection, String category);

	public List<Board> selectBoardList(Connection connection, Paging paging, String category);

	public Board selectDetail(Connection connection, Board board);

	public int increaseHit(Connection connection, Board board);

	public int deleteArticleByBoardno(Connection connection, int boardno);

	public int updateArticle(Connection connection, Board board);

	public Board selectBoardDetail(Connection connection, String boardno);

	/**
	 * 게시글 댓글 작성
	 * @param connection
	 * @param comment
	 * @return
	 */
	public int insertBoardComment(Connection connection, TB_BOARD_COMMENT comment);

	public List<TB_BOARD_COMMENT> selectCommentList(Connection connection, String boardno);
	/**
	 * 댓글 삭제
	 * @param connection
	 * @param commentId
	 * @return
	 */
	public int deleteComment(Connection connection, String commentId);
	
	
	
	
	   public int selectAllBoardCntAll(Connection connection);

	   public List<Board> selectAllBoardList(Connection connection, Paging paging);

	   public int selectSearchBoardCntAll(Connection connection, String category, String search);

	   public List<Board> selectSearchBoardList(Connection connection, Paging paging, String category, String search);
	
	
	

}
