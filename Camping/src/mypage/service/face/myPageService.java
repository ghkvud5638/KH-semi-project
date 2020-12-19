package mypage.service.face;

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

public interface myPageService {
	/**
	 * 찜한 캠핑장 정보를 테이블에서 조회
	 * @param user_id 로그인한 유저
	 * @return 찜한캠핑장들의 리스트
	 */
	public List<CAMP_INFO> selectJJ(String user_id);
		
	public CAMP_INFO getParam(HttpServletRequest req);

	/**
	 * 로그인한 회원의 개인정보 불러오기
	 * 
	 * @param user_id
	 * @return
	 */
	public TB_USER selectUser(String user_id);


	/**
	 * 회원 정보 수정
	 * @param user
	 */
	public void updateUser(TB_USER user);
	
	/**
	 * 사진 업로드
	 * @param up
	 */
	public void insertFile(TB_USER up);

	
	/**
	 * 내가 쓴 게시글 , 삭제 버튼
	 * @param boardno - 게시판 번호
	 * @return 커밋 결과 번호
	 */
	public boolean deleteArticle(int boardno);
	
//	게시판 관련
	
	/**
	 * 게시글 전체 조회
	 * @return 조회된 게시글 목록
	 */
//	public List<Board> getList();

	/**
	 * 회원이 쓴 게시글 페이징 객체 생성
	 * 
	 * @param req - 요청 정보 객체
	 * @return 페이징 계산이 완료된 객체
	 */
	public Paging getPaging(HttpServletRequest req);

	/**
	 * 회원이 쓴 게시글 페이징 처리하여 보여질 게시글 목록만 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return 페이징 게시글 조회 결과 리스트
	 */
	public List<Board> getList(Paging paging, String user_id);

	
	
//	마이페이지 장바구니
	public CartPaging getCartPaging(HttpServletRequest req);
	public List<TB_CART> getCartList(CartPaging paging, String user_id);

	
	public boolean deleteCart(String cartId);

	public boolean deleteFavcamp(String campId);

	public boolean deleteMember(String userId);

	public void insertAsk(TB_ASK_BOARD askBoard);

	/**
	 * 1:1문의 게시판 페이징
	 * 
	 * @param req
	 * @return
	 */
	public AskPaging getAskPaging(HttpServletRequest req);

	/**
	 * 1:1문의 게시판 조회
	 * @param askPaging 페이징 정보
	 * @param user_id 작성자 정보
	 * @return
	 */
	public List<TB_ASK_BOARD> getAskBoardList(AskPaging askPaging, String user_id);

	/**
	 * 1:1문의 게시글 삭제
	 * @param boardno
	 * @return
	 */
	public boolean deleteAsk(String boardno);

	/**
	 * 1:1문의  상세 게시글
	 * @param boardno
	 * @return
	 */
	public TB_ASK_BOARD getAskArticle(String boardno);

	/**
	 * 1:1문의 상세페이지에서 댓글 작성
	 * @param comment
	 * @return
	 */
	public boolean insertComment(TB_COMMENT comment);

	/**
	 * 댓글 리스트 조회
	 * @param boardno - 해당 게시글 번호
	 * @return
	 */
	public List<TB_COMMENT> getCommentList(String boardno);

	/**
	 * 댓글 삭제
	 * @param boardno
	 * @return
	 */
	public boolean deleteComment(String commentId);
	
	/**
	 * 상위 8개 캠핑장 정보
	 * @return
	 */
	public List<CAMP_INFO> getTopCamp();

	/**
	 * 메인에 게시글 뿌리기
	 * @param communityCategory
	 * @return
	 */
	public List<Board> getMainBoardList(String category);
	
	
	/**
	 * 결제 내역 조회
	 * @param user_id
	 * @return
	 */
	public List<TB_PAY> getPayList(String user_id);
	
	/**
	 * 날짜로 결제내역 조회
	 * @param secondDate
	 * @param firstDate
	 * @return
	 */
	public List<TB_PAY> getPayListByDate(String user_id, String secondDate, String firstDate);
	
	/**
	 * 캠핑장 예약 내역
	 * @param user_id
	 * @return
	 */
	public List<TB_REV> getRevList(String user_id);

	/**
	 * 갬핑장 예약 내역 날짜 별 조회
	 * @param user_id
	 * @param secondDate
	 * @param firstDate
	 * @return
	 */
	public List<TB_REV> getRevListByDate(String user_id, String secondDate, String firstDate);
	   public boolean updateComment(String commentId, TB_COMMENT com);

	   public boolean insertReComment(TB_RECOMMENT recomment);

	   public List<TB_RECOMMENT> getReCommentList(String boardno);

	   public boolean updateReComment(String recommentId, TB_RECOMMENT recomment);

	   public boolean deleteReComment(String recommentId);

}
