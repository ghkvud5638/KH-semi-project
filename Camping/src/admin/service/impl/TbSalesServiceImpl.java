package admin.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import admin.common.JDBCTemplate;
import admin.common.Paging;
import admin.dao.face.TbSalesDao;
import admin.dao.impl.TbSalesDaoImpl;
import admin.dto.TB_PAY;
import admin.dto.TB_PROD;
import admin.service.face.TbSalesService;

public class TbSalesServiceImpl implements TbSalesService {
	
	private TbSalesDao tbsales = new TbSalesDaoImpl();
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		//전달파라미터 curPage를 파싱한다
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param!=null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}
		
		//Board 테이블의 총 게시글 수를 조회한다 , 만약 검색어를 추가하여 조회할 경우 매개변수로 조회되는 기준을 넘겨주자
		int totalCount = tbsales.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging 객체 생성
		Paging paging = new Paging(totalCount, curPage); //총 게시글 수, 현재 페이지
		
		//계산된 Paging 객체 반환
		return paging;
	}

	
	
	@Override
	public List<HashMap<String, Object>> selectSalesList(Paging paging) {
		Connection conn = JDBCTemplate.getConnection();
		
		List<HashMap<String, Object>> b = tbsales.selectSalesList(conn, paging); 

		return b;
	}
	
	@Override
	public List<HashMap<String, Object>> SalesListDate(Paging paging, String start, String end) {
		Connection conn = JDBCTemplate.getConnection();
		
		List<HashMap<String, Object>> b = tbsales.SalesListDate(conn, paging, start, end); 

		return b;
	}
}
