package board.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import board.dto.Board;
import board.dto.TB_BOARD_COMMENT;
import common.Paging;

public interface BoardService {

	public void applyArticle(Board board);

	public Paging getBoardPaging(HttpServletRequest req, String category);

	public List<Board> getBoardList(Paging paging, String category);

	public Board selectBoardDetail(Board board);

	public boolean deleteArticle(int boardno);

	public boolean modifyArticle(Board board);

	/**
	 * 수정용, 
	 * 게시판 데이터 단순 불러오기
	 * 조회수 처리 안함
	 * @param boardno
	 * @return
	 */
	public Board selectArticle(String boardno);

	/**
	 * 게시글 댓글 작성
	 * @param comment
	 * @return
	 */
	public boolean insertComment(TB_BOARD_COMMENT comment);

	/**
	 * 게시글의 댓글 조회
	 * @param boardno
	 * @return
	 */
	public List<TB_BOARD_COMMENT> getCommentList(String boardno);

	/**
	 * 댓글 삭제
	 * @param commentId
	 * @return
	 */
	public boolean deleteComment(String commentId);
	
	
	
	public Paging getAllBoardPaging(HttpServletRequest req);

	   public List<Board> getAllBoardList(Paging paging);

	   public Paging getSearchBoardPaging(HttpServletRequest req, String category, String search);

	   public List<Board> getSearchBoardList(Paging paging, String category, String search);
	
	

}

