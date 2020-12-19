package mypage.service.impl;

import java.sql.Connection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import common.AskPaging;
import common.CartPaging;
import common.JDBCTemplate;
import common.Paging;
import mypage.dao.face.myPageDao;
import mypage.dao.impl.myPageDaoImpl;
import mypage.dto.Board;
import mypage.dto.CAMP_INFO;
import mypage.dto.TB_ASK_BOARD;
import mypage.dto.TB_CART;
import mypage.dto.TB_COMMENT;
import mypage.dto.TB_PAY;
import mypage.dto.TB_RECOMMENT;
import mypage.dto.TB_REV;
import mypage.dto.TB_USER;
import mypage.service.face.myPageService;

public class myPageServiceImpl implements myPageService {
	private myPageDao mypageDao = new myPageDaoImpl();
	@Override
	public CAMP_INFO getParam(HttpServletRequest req) {
		return null;
	}
	
	
	/**
	 * 1:1문의 게시글 상세 페이지
	 */
	@Override
	public TB_ASK_BOARD getAskArticle(String boardno) {
		TB_ASK_BOARD askBoard = mypageDao.selectAskArticleByBoardno(JDBCTemplate.getConnection(), boardno);
		return askBoard;
	}
	@Override
	public boolean insertComment(TB_COMMENT comment) {
		boolean result = false; 
		int res = mypageDao.insertAskComment(JDBCTemplate.getConnection(),comment);
		if (res>0) {
			JDBCTemplate.commit(JDBCTemplate.getConnection());
			System.out.println("ask댓글 작성 커밋 완료");
			result = true;
		} else {
			JDBCTemplate.rollback(JDBCTemplate.getConnection());
			System.out.println("ask댓글 작성 커밋 실패");
		}
		return result;
	}
	
	//댓글 조회
	@Override
	public List<TB_COMMENT> getCommentList(String boardno) {
		List<TB_COMMENT> commentList = mypageDao.selectAskCommentList(JDBCTemplate.getConnection(),boardno);
		return commentList;
	}
	@Override
	public boolean deleteComment(String commentId) {
		int res = mypageDao.deleteAskComment(JDBCTemplate.getConnection(),commentId);
		boolean result = false;
		if (res>0) {
			JDBCTemplate.commit(JDBCTemplate.getConnection());
			System.out.println("ask댓글 삭제 커밋 완료");
			result = true;
		} else {
			JDBCTemplate.rollback(JDBCTemplate.getConnection());
			System.out.println("ask댓글 삭제 커밋 실패");
		}		
		return result;
	}
	
	@Override
	public TB_USER selectUser(String user_id) {
		Connection conn = JDBCTemplate.getConnection();
		TB_USER user = mypageDao.selectUserByID(conn,user_id); 
		return user;
	}
	@Override
	public void updateUser(TB_USER user) {
		Connection conn = JDBCTemplate.getConnection();
		int res = mypageDao.update(conn, user); 
		if (res == 1) {
			System.out.println("커밋완료");
			JDBCTemplate.commit(conn);
		}else {
			System.out.println("커밋안됨");
			JDBCTemplate.rollback(conn);
		}
	}
	@Override
	public void insertFile(TB_USER up) {
		//DAO를 통해 insert수행
		int result = mypageDao.insertFile(JDBCTemplate.getConnection(), up);
		if( result > 0 ) {
			JDBCTemplate.commit(JDBCTemplate.getConnection());
			System.out.println("업로드 커밋 완료");
		} else {
			JDBCTemplate.rollback(JDBCTemplate.getConnection());
			System.out.println("업로드 커밋 실패");
		}
	}
	
	@Override
	public void insertAsk(TB_ASK_BOARD askBoard) {
		int result = mypageDao.insertAskArticle(JDBCTemplate.getConnection(), askBoard);
		if (result>0) {
			JDBCTemplate.commit(JDBCTemplate.getConnection());
			System.out.println("ask 게시글 등록 커밋 완료");
		} else {
			JDBCTemplate.rollback(JDBCTemplate.getConnection());
			System.out.println("ask 게시글 등록 커밋 실패");
		}
	}
	
	
	/**
	 * 찜한 컘핑장 리스트 조회
	 */
	@Override
	public List<CAMP_INFO> selectJJ(String user_id) {

		Connection conn = JDBCTemplate.getConnection();
		
		List<CAMP_INFO> list = mypageDao.selectJJcamp(conn, user_id);
		
		return list;
	}
	
	@Override
	public boolean deleteArticle(int boardno) {
		Connection conn = JDBCTemplate.getConnection();
		int res = mypageDao.deleteArticle(conn, boardno);
		boolean result=false;
		
		if( res > 0 ) {
			JDBCTemplate.commit(conn);
			System.out.println("업로드 커밋 완료");
			result=true;
		} else {
			JDBCTemplate.rollback(conn);
			System.out.println("업로드 커밋 실패");
			result=false;
		}
		
		return result;
	}
	
	@Override
	public boolean deleteMember(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		int res = mypageDao.deleteMemberByUserId(conn, userId);
		boolean result=false;
		if( res > 0 ) {
			JDBCTemplate.commit(conn);
			System.out.println("계정삭제 커밋 완료");
			result=true;
		} else {
			JDBCTemplate.rollback(conn);
			System.out.println("계정삭제 커밋 실패");
			result=false;
		}
		return result;
	}
	
	
	/**
	 * 찜한 캠핑장 삭제
	 */
	@Override
	public boolean deleteFavcamp(String campId) {
		
		Connection conn = JDBCTemplate.getConnection();
		int res = mypageDao.deleteJJcamp(conn, campId);
		boolean result=false;
		
		if( res > 0 ) {
			JDBCTemplate.commit(conn);
			System.out.println("삭제 커밋 완료");
			result=true;
		} else {
			JDBCTemplate.rollback(conn);
			System.out.println("삭제 커밋 실패");
			result=false;
		}
		
		return result;
	}

	
	
	// 게시판
//	@Override
//	public List<Board> getList() {
//		return mypageDao.selectList( JDBCTemplate.getConnection() );
//	}

	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		//전달파라미터 curPage를 파싱한다
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param!=null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}
		
		//Board 테이블의 총 게시글 수를 조회한다 , 만약 검색어를 추가하여 조회할 경우 매개변수로 조회되는 기준을 넘겨주자
		int totalCount = mypageDao.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging 객체 생성
		Paging paging = new Paging(totalCount, curPage); //총 게시글 수, 현재 페이지
		
		//계산된 Paging 객체 반환
		return paging;
	}

	@Override
	public AskPaging getAskPaging(HttpServletRequest req) {
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param!=null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}
		int totalCount = mypageDao.selectAskBoardCntAll(JDBCTemplate.getConnection());
		AskPaging askPaging = new AskPaging(totalCount, curPage);
		
		return askPaging;
	}
	
	@Override
	public List<Board> getList(Paging paging,String user_id) {
		
		System.out.println("boardService에서의 아이디"+user_id);
		List<Board> b = mypageDao.selectList(JDBCTemplate.getConnection(), paging, user_id); 
		System.out.println("b : "+b);
		
		return b;
	}

	/**
	 * 1:1문의 게시판 조회
	 */
	@Override
	public List<TB_ASK_BOARD> getAskBoardList(AskPaging askPaging, String user_id) {
		List<TB_ASK_BOARD> res = mypageDao.selectAskBoardList(JDBCTemplate.getConnection(), askPaging, user_id); 
		return res;
	}
	
	
	

	@Override
	public CartPaging getCartPaging(HttpServletRequest req) {
		//전달파라미터 curPage를 파싱한다
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param!=null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}
		
		//Board 테이블의 총 게시글 수를 조회한다 , 만약 검색어를 추가하여 조회할 경우 매개변수로 조회되는 기준을 넘겨주자
		int totalCount = mypageDao.selectCartCntAll(JDBCTemplate.getConnection());
		
		//Paging 객체 생성
		CartPaging paging = new CartPaging(totalCount, curPage); //총 게시글 수, 현재 페이지
		
		//계산된 Paging 객체 반환
		return paging;
	}
	
	
	
	@Override
	public List<TB_CART> getCartList(CartPaging paging, String user_id) {
		List<TB_CART> b = mypageDao.selectCartList(JDBCTemplate.getConnection(), paging, user_id); 
		System.out.println("b : "+b);
		
		return b;
	}
	
	
	@Override
	public boolean deleteCart(String cartId) {
		Connection conn = JDBCTemplate.getConnection();
		int res = mypageDao.deleteCartList(conn, cartId);
		boolean result=false;
		
		if( res > 0 ) {
			JDBCTemplate.commit(conn);
			System.out.println("업로드 커밋 완료");
			result=true;
		} else {
			JDBCTemplate.rollback(conn);
			System.out.println("업로드 커밋 실패");
			result=false;
		}
		
		return result;
	}
	
	@Override
	public boolean deleteAsk(String boardno) {
		int res = mypageDao.deleteAskArticle(JDBCTemplate.getConnection(), boardno);
		boolean result = false;
		if (res>0) {
			JDBCTemplate.commit(JDBCTemplate.getConnection());
			System.out.println("ask게시글 삭제 커밋 완료");
			result=true;
		} else {
			JDBCTemplate.rollback(JDBCTemplate.getConnection());
			System.out.println("ask게시글 삭제 커밋 실패");
			result=false;
		}
		return result;
	}
	//상위 8개 캠핑장 정보
	@Override
	public List<CAMP_INFO> getTopCamp() {
		List<CAMP_INFO> list = mypageDao.selectTopCamp(JDBCTemplate.getConnection());
		return list;
	}
	//메인에 게시글 뿌리기
	@Override
	public List<Board> getMainBoardList(String category) {
	      List<Board> list = mypageDao.selectMainBoardList(JDBCTemplate.getConnection(), category); 
		return list;
	}
	//결제 내역 조회
	@Override
	public List<TB_PAY> getPayList(String user_id) {
		List<TB_PAY> payList = mypageDao.selectPayList(JDBCTemplate.getConnection(),user_id); 
		return payList;
	}
	@Override
	public List<TB_PAY> getPayListByDate(String user_id,String secondDate, String firstDate) {
		List<TB_PAY> list = mypageDao.selectPayListByDate(JDBCTemplate.getConnection(),user_id,secondDate,firstDate);
		return list;
	}
	@Override
	public List<TB_REV> getRevList(String user_id) {
		List<TB_REV> list = mypageDao.selectRevList(JDBCTemplate.getConnection(),user_id);
		return list;
	}
	@Override
	public List<TB_REV> getRevListByDate(String user_id, String secondDate, String firstDate) {
		List<TB_REV> list = mypageDao.selectRevListByDate(JDBCTemplate.getConnection(),user_id,secondDate,firstDate);
		return list;
	}
	
	
	   @Override
	   public boolean updateComment(String commentId, TB_COMMENT com) {
	      int res = mypageDao.updateComment(JDBCTemplate.getConnection(), com, commentId);
	      boolean result = false;
	      if (res>0) {
	         JDBCTemplate.commit(JDBCTemplate.getConnection());
	         System.out.println("댓글 변경 커밋 완료");
	         result=true;
	      } else {
	         JDBCTemplate.rollback(JDBCTemplate.getConnection());
	         System.out.println("댓글 변경 커밋 실패");
	         result=false;
	      }
	      return result;
	   }
	   
	   @Override
	   public boolean insertReComment(TB_RECOMMENT recomment) {
	      boolean result = false; 
	      int res = mypageDao.insertReComment(JDBCTemplate.getConnection(),recomment);
	      if (res>0) {
	         JDBCTemplate.commit(JDBCTemplate.getConnection());
	         System.out.println("답글 작성 커밋 완료");
	         result = true;
	      } else {
	         JDBCTemplate.rollback(JDBCTemplate.getConnection());
	         System.out.println("답글글 작성 커밋 실패");
	      }
	      return result;
	   }
	   
	   @Override
	   public List<TB_RECOMMENT> getReCommentList(String boardno) {
	      List<TB_RECOMMENT> recommentList = mypageDao.selectReCommentList(JDBCTemplate.getConnection(),boardno);
	      return recommentList;
	   }
	   
	   @Override
	   public boolean updateReComment(String recommentId, TB_RECOMMENT recomment) {
	      int res = mypageDao.updateReComment(JDBCTemplate.getConnection(), recomment, recommentId);
	      boolean result = false;
	      if (res>0) {
	         JDBCTemplate.commit(JDBCTemplate.getConnection());
	         System.out.println("댓글 변경 커밋 완료");
	         result=true;
	      } else {
	         JDBCTemplate.rollback(JDBCTemplate.getConnection());
	         System.out.println("댓글 변경 커밋 실패");
	         result=false;
	      }
	      return result;
	   }
	   
	   @Override
	   public boolean deleteReComment(String recommentId) {
	      int res = mypageDao.deleteReComment(JDBCTemplate.getConnection(),recommentId);
	      boolean result = false;
	      if (res>0) {
	         JDBCTemplate.commit(JDBCTemplate.getConnection());
	         System.out.println("re댓글 삭제 커밋 완료");
	         result = true;
	      } else {
	         JDBCTemplate.rollback(JDBCTemplate.getConnection());
	         System.out.println("re댓글 삭제 커밋 실패");
	      }      
	      return result;
	   }
	
	
	
	
}
