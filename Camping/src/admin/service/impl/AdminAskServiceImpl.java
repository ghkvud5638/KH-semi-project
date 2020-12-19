package admin.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import admin.common.JDBCTemplate;
import admin.dao.face.AdminAskDao;
import admin.dao.impl.AdminAskDaoImpl;
import admin.dto.TB_ASK_BOARD;
import admin.dto.TB_PROD;
import admin.service.face.AdminAskService;
import common.Paging;

public class AdminAskServiceImpl implements AdminAskService {

	private AdminAskDao ask = new AdminAskDaoImpl();
	
	@Override
	public Paging getPaging(HttpServletRequest req) {

		//전달파라미터 curPage를 파싱한다
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param!=null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}
		
		//Board 테이블의 총 게시글 수를 조회한다 , 만약 검색어를 추가하여 조회할 경우 매개변수로 조회되는 기준을 넘겨주자
		int totalCount = ask.paging(JDBCTemplate.getConnection());
		
		//Paging 객체 생성
		Paging paging = new Paging(totalCount, curPage); //총 게시글 수, 현재 페이지
		
		//계산된 Paging 객체 반환
		return paging;
	}
	
	@Override
	public List<TB_ASK_BOARD> askBoardList(Paging paging) {
		System.out.println("askboardlist 서비스 작동");
		Connection conn = JDBCTemplate.getConnection();
		
		List<TB_ASK_BOARD> b = ask.askBoardList(conn, paging); 

		return b;
	}
	
}
