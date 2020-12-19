package mypage.dao.face;

import java.sql.Connection;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.AskPaging;
import common.CartPaging;
import common.Paging;
import mypage.dto.Board;
import mypage.dto.CAMP_INFO;
import mypage.dto.TB_ASK_BOARD;
import mypage.dto.TB_CART;
import mypage.dto.TB_COMMENT;
import mypage.dto.TB_PAY;
import mypage.dto.TB_RECOMMENT;
import mypage.dto.TB_REV;
import mypage.dto.TB_USER;

public interface myPageDao {


	/**
	 * 
	 * 회원정보 수정 페이지 열리면 회원 정보 조회
	 * 
	 * @param conn
	 * @param user_id
	 * @return
	 */
	public TB_USER selectUserByID(Connection conn, String user_id);
	
	public List<CAMP_INFO> selectJJcamp(Connection conn, String user_id);
	
	public int update(Connection conn , TB_USER up);
	
	public int insertFile(Connection connection, TB_USER up);

	public int deleteArticle(Connection conn, int boardno);
	
	
	
	//게시판 관련
	/**
	 * 게시글 전체 목록 조회
	 * @param connection - DB연결 객체
	 * @return 조회된 게시글 목록
	 */
//	public List<Board> selectList(Connection connection);

	/**
	 * 총 게시글 수 조회
	 * 
	 * @return 전체 게시글 수
	 */
	public int selectCntAll(Connection connection);

	/**
	 * 페이징 대상 게시글 목록 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return 조회된 게시글 목록
	 */
	public List<Board> selectList(Connection connection, Paging paging, String user_id);
	

	
	public int selectCartCntAll(Connection connection);
	public List<TB_CART> selectCartList(Connection connection, CartPaging paging, String user_id);

	public int deleteCartList(Connection conn, String cartId);

	public int deleteJJcamp(Connection conn, String campId);

	public int deleteMemberByUserId(Connection conn, String userId);

	public int insertAskArticle(Connection connection, TB_ASK_BOARD askBoard);

	/**
	 * 1:1문의 게시판 페이징
	 * @param connection
	 * @return
	 */
	public int selectAskBoardCntAll(Connection connection);

	/**
	 * 1:1문의 게시판 조회
	 * @param connection - DB 연결 객체
	 * @param askPaging  - 페이징 정보 
	 * @param user_id - 유저 정보
	 * @return
	 */
	public List<TB_ASK_BOARD> selectAskBoardList(Connection connection, AskPaging askPaging, String user_id);

	/**
	 * 1:1문의 게시판
	 * @param connection
	 * @param boardno
	 * @return
	 */
	public int deleteAskArticle(Connection connection, String boardno);

	/**
	 * 1:1문의 게시글 상세 페이지
	 * @param connection
	 * @param boardno
	 * @return
	 */
	public TB_ASK_BOARD selectAskArticleByBoardno(Connection connection, String boardno);

	/**
	 * 1:1문의 댓글 작성
	 * @param connection
	 * @param comment
	 * @return
	 */
	public int insertAskComment(Connection connection, TB_COMMENT comment);

	/**
	 * 댓글 리스트 조회
	 * @param connection
	 * @param boardno - 해당 게시글 번호
	 * @return
	 */
	public List<TB_COMMENT> selectAskCommentList(Connection connection, String boardno);

	/**
	 * 1:1문의 게시글 댓글 삭제
	 * @param connection
	 * @param boardno
	 * @return
	 */
	public int deleteAskComment(Connection connection, String commentId);

	/**
	 * 결제 내역 조회
	 * @param connection
	 * @param user_id
	 * @return
	 */
	public List<TB_PAY> selectPayList(Connection connection, String user_id);

	public List<TB_PAY> selectPayListByDate(Connection connection, String user_id, String SecondDate,String firstDate);

	/**
	 * 좋아요 상위 8개 캠핑장 메인에 뿌리기
	 * @return
	 */
	public List<CAMP_INFO> selectTopCamp(Connection connection);

	public List<Board> selectMainBoardList(Connection connection, String category);

	/**
	 * 캠핑장 결제 내역
	 * @param connection
	 * @param user_id
	 * @return
	 */
	public List<TB_REV> selectRevList(Connection connection, String user_id);

	
	/**
	 * 캠핑장 예약 내역 날짜별 조회
	 * @param connection
	 * @param user_id
	 * @param secondDate
	 * @param firstDate
	 * @return
	 */
	public List<TB_REV> selectRevListByDate(Connection connection, String user_id, String secondDate, String firstDate);
	
	
	
	
	public int updateComment(Connection connection, TB_COMMENT com, String commentId);

	   public int insertReComment(Connection connection, TB_RECOMMENT recomment);

	   public List<TB_RECOMMENT> selectReCommentList(Connection connection, String boardno);

	   public int updateReComment(Connection connection, TB_RECOMMENT recomment, String recommentId);

	   public int deleteReComment(Connection connection, String recommentId);
	
}


