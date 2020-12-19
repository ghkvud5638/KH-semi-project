package board.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import board.dao.face.BoardDao;
import board.dao.impl.BoardDaoImpl;
import board.dto.Board;
import board.dto.TB_BOARD_COMMENT;
import board.service.face.BoardService;
import common.JDBCTemplate;
import common.Paging;

public class BoardServiceImpl implements BoardService{

	private BoardDao boarddao = new BoardDaoImpl();
	@Override
	public void applyArticle(Board board) {
		System.out.println("BoardService [applyArticle]");
		System.out.println("----------------------------");
		Connection conn = JDBCTemplate.getConnection();
		
		int res = boarddao.insertArticle(conn,board);
		
		if (res == 1) {
			System.out.println("커밋완료");
			JDBCTemplate.commit(conn);
		} else {
			System.out.println("커밋완료");
			JDBCTemplate.rollback(conn);
		}
	}
	
	
	@Override
	public Paging getBoardPaging(HttpServletRequest req, String category) {
		//전달파라미터 curPage를 파싱한다
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param!=null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}
		//Board 테이블의 총 게시글 수를 조회한다 , 만약 검색어를 추가하여 조회할 경우 매개변수로 조회되는 기준을 넘겨주자
		int totalCount = boarddao.selectBoardCntAll(JDBCTemplate.getConnection(), category);
		System.out.println("totalCount : "+totalCount);
		//Paging 객체 생성
		Paging paging = new Paging(totalCount, curPage); //총 게시글 수, 현재 페이지
		
		//계산된 Paging 객체 반환
		return paging;
	}
	
	
	@Override
	public List<Board> getBoardList(Paging paging, String category) {
		List<Board> b = boarddao.selectBoardList(JDBCTemplate.getConnection(), paging, category); 
		System.out.println("b : "+b);
		
		return b;
	}
	
	@Override
	public Board selectBoardDetail(Board board) {
		int res =  boarddao.increaseHit(JDBCTemplate.getConnection(), board);
		if (res==1) {
			System.out.println("조회수 커밋완료");
			JDBCTemplate.commit(JDBCTemplate.getConnection());
		} else {
			System.out.println("조회수 커밋실패");
			JDBCTemplate.rollback(JDBCTemplate.getConnection());
		}
		Board boardDetail = boarddao.selectDetail(JDBCTemplate.getConnection(), board);
		return boardDetail;
	}
	
	/**
	 * 게시글 삭제
	 */
	@Override
	public boolean deleteArticle(int boardno) {
		int res = boarddao.deleteArticleByBoardno(JDBCTemplate.getConnection(), boardno);
		System.out.println("service res : "+res);
		boolean result = false;
		if (res>0) {
			JDBCTemplate.commit(JDBCTemplate.getConnection());
			System.out.println("게시글 삭제 커밋성공");
			result = true;
		}else {
			JDBCTemplate.rollback(JDBCTemplate.getConnection());
			System.out.println("게시글 삭제 커밋실패");
			result = false;
		}
		return result;
	}
	
	/**
	 * 게시글 수정
	 */
	@Override
	public boolean modifyArticle(Board board) {
		boolean res = false;
		
		int result = boarddao.updateArticle(JDBCTemplate.getConnection(), board);
		if (result==1) {
			System.out.println("게시글 수정 커밋성공");
			JDBCTemplate.commit(JDBCTemplate.getConnection());
			res = true;
		}else {
			System.out.println("게시글 수정 커밋실패");
			JDBCTemplate.rollback(JDBCTemplate.getConnection());
			res = false;
		}
		return res;
	}
	
	@Override
	public Board selectArticle(String boardno) {
		Board board = new Board();
		
		board = boarddao.selectBoardDetail(JDBCTemplate.getConnection(),boardno);
		
		return board;
	}
	//
	@Override
	public boolean insertComment(TB_BOARD_COMMENT comment) {
		boolean result = false;
		int res = boarddao.insertBoardComment(JDBCTemplate.getConnection(),comment);
		if (res>0) {	
			System.out.println("게시글 댓글 작성 커밋성공");
			JDBCTemplate.commit(JDBCTemplate.getConnection());
			result = true;
		}else {
			System.out.println("게시글 댓글 작성 커밋실패");
			JDBCTemplate.rollback(JDBCTemplate.getConnection());
			result = false;
		}
		return result;
	}
	//댓글 조회
	@Override
	public List<TB_BOARD_COMMENT> getCommentList(String boardno) {
		List<TB_BOARD_COMMENT> list = boarddao.selectCommentList(JDBCTemplate.getConnection(), boardno);
		return list;
	}
	//댓글 삭제
	@Override
	public boolean deleteComment(String commentId) {
		int res = boarddao.deleteComment(JDBCTemplate.getConnection(),commentId);
		boolean result = false;
		if (res>0) {
			JDBCTemplate.commit(JDBCTemplate.getConnection());
			System.out.println("댓글 삭제 커밋 완료");
			result = true;
		} else {
			JDBCTemplate.rollback(JDBCTemplate.getConnection());
			System.out.println("댓글 삭제 커밋 실패");
		}		
		return result;
	}
	
	
	   @Override
	   public Paging getAllBoardPaging(HttpServletRequest req) {
	      //전달파라미터 curPage를 파싱한다
	      String param = req.getParameter("curPage");
	      int curPage = 0;
	      if( param!=null && !"".equals(param) ) {
	         curPage = Integer.parseInt(param);
	      }
	      //Board 테이블의 총 게시글 수를 조회한다 , 만약 검색어를 추가하여 조회할 경우 매개변수로 조회되는 기준을 넘겨주자
	      int totalCount = boarddao.selectAllBoardCntAll(JDBCTemplate.getConnection());
	      System.out.println("totalCount : "+totalCount);
	      //Paging 객체 생성
	      Paging paging = new Paging(totalCount, curPage); //총 게시글 수, 현재 페이지
	      
	      //계산된 Paging 객체 반환
	      return paging;
	   }
	   
	   @Override
	   public List<Board> getAllBoardList(Paging paging) {
	      List<Board> b = boarddao.selectAllBoardList(JDBCTemplate.getConnection(), paging); 
	      System.out.println("b : "+b);
	      
	      return b;
	   }
	   
	   @Override
	   public Paging getSearchBoardPaging(HttpServletRequest req, String category, String search) {
	      //전달파라미터 curPage를 파싱한다
	      String param = req.getParameter("curPage");
	      int curPage = 0;
	      if( param!=null && !"".equals(param) ) {
	         curPage = Integer.parseInt(param);
	      }
	      //Board 테이블의 총 게시글 수를 조회한다 , 만약 검색어를 추가하여 조회할 경우 매개변수로 조회되는 기준을 넘겨주자
	      int totalCount = boarddao.selectSearchBoardCntAll(JDBCTemplate.getConnection(), category, search);
	      System.out.println("totalCount : "+totalCount);
	      //Paging 객체 생성
	      Paging paging = new Paging(totalCount, curPage); //총 게시글 수, 현재 페이지
	      
	      //계산된 Paging 객체 반환
	      return paging;
	   }
	   
	   @Override
	   public List<Board> getSearchBoardList(Paging paging, String category, String search) {
	      List<Board> b = boarddao.selectSearchBoardList(JDBCTemplate.getConnection(), paging, category, search); 
	      System.out.println("b : "+b);
	      
	      return b;
	   }
	
	
	
	
}
