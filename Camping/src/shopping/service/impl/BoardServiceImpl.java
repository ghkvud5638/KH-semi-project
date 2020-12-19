package shopping.service.impl;

import java.sql.Connection;

import common.JDBCTemplate;
import shopping.dao.face.BoardDao;
import shopping.dao.impl.BoardDaoImpl;
import shopping.dto.Board;
import shopping.service.face.BoardService;

public class BoardServiceImpl implements BoardService{

	BoardDao boardDao = new BoardDaoImpl();
	
	@Override
	public void applyReview(Board board) {
	System.out.println("boardService [applyReview]");
		//DB연결 객체
	Connection conn = JDBCTemplate.getConnection();
	
	int res = boardDao.insertReview(conn, board);
	
	if(res == 1) { //커밋
		JDBCTemplate.commit(conn);
	} else { //롤백
		JDBCTemplate.rollback(conn);
	}
	
	
	}

	//QA 삽입
	@Override
	public void applyQA(Board board) {
		//DB연결 객체
	Connection conn = JDBCTemplate.getConnection();
		
	int res = boardDao.insertQA(conn, board);
		
		if(res == 1) { //커밋
			JDBCTemplate.commit(conn);
		} else { //롤백
			JDBCTemplate.rollback(conn);
		}

	}
	   @Override
	   public Board selectByBoardno(String boardno) {
	      Connection conn = JDBCTemplate.getConnection();
	      Board revInfo = boardDao.selectByBoardno(conn, boardno);
	      
	      return revInfo;
	   }

	   @Override
	   public Board selectByQABoardno(String boardno) {
	      Connection conn = JDBCTemplate.getConnection();
	      Board revInfo = boardDao.selectByQABoardno(conn, boardno);
	      
	      return revInfo;
	   }
}
