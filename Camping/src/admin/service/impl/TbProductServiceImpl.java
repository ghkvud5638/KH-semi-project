package admin.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import admin.common.JDBCTemplate;
import admin.dao.face.TbProductDao;
import admin.dao.impl.TbProductDaoImpl;
import admin.dto.TB_PROD;
import admin.service.face.TbProductService;
import common.Paging;
import mypage.dto.Board;


public class TbProductServiceImpl implements TbProductService {

	private TbProductDao prodDao = new TbProductDaoImpl();
	
	

	
	@Override
	public void insertProd(TB_PROD prodInfo, String saveDirectory) {
		Connection conn = JDBCTemplate.getConnection();

		int res = prodDao.insertProd(conn, prodInfo, saveDirectory); 
		if (res == 1) {
			System.out.println("커밋완료");
			JDBCTemplate.commit(conn);
		}else {
			System.out.println("커밋안됨");
			JDBCTemplate.rollback(conn);
		}
		
	}
	
	@Override
	public void updateProd(TB_PROD prodInfo, String prod_id,String save, int chk1, int chk2) {
		Connection conn = JDBCTemplate.getConnection();

		int res = prodDao.updateProd(conn, prodInfo, prod_id, save, chk1, chk2); 
		if (res == 1) {
			System.out.println("커밋완료");
			JDBCTemplate.commit(conn);
		}else {
			System.out.println("커밋안됨");
			JDBCTemplate.rollback(conn);
		}		
	}
	
	@Override
	public boolean removeProd(String prod_id) {
		Connection conn = JDBCTemplate.getConnection();
		boolean result=false;

		int res = prodDao.removeProd(conn, prod_id); 
		if (res > 0) {
			System.out.println("커밋완료");
			JDBCTemplate.commit(conn);
			result=true;

		}else {
			System.out.println("커밋안됨");
			JDBCTemplate.rollback(conn);
			result=false;

		}
		
		return result;
	}
	
	@Override
	public List<TB_PROD> selectProdBoardList(Paging paging) {
		Connection conn = JDBCTemplate.getConnection();
		
		List<TB_PROD> b = prodDao.selectProdBoardList(conn, paging); 

		return b;

	}
	
	@Override
	public List<TB_PROD> searchProd(Paging paging, String cate_id, String search ) {
		Connection conn = JDBCTemplate.getConnection();
		
		List<TB_PROD> b = prodDao.searchProd(conn, paging, cate_id, search); 

		return b;
	}

	
	@Override
	public TB_PROD selectProdInfo(String prod_id) {
		Connection conn = JDBCTemplate.getConnection();

		TB_PROD prodInfo = prodDao.selectProdByID(conn, prod_id); 
		
		return prodInfo;
	}
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		//전달파라미터 curPage를 파싱한다
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param!=null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}
		
		//Board 테이블의 총 게시글 수를 조회한다 , 만약 검색어를 추가하여 조회할 경우 매개변수로 조회되는 기준을 넘겨주자
		int totalCount = prodDao.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging 객체 생성
		Paging paging = new Paging(totalCount, curPage); //총 게시글 수, 현재 페이지
		
		//계산된 Paging 객체 반환
		return paging;
	}
	
	@Override
	public Paging getSearchPaging(HttpServletRequest req, String cate_id, String search) {
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param!=null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}
		
		//Board 테이블의 총 게시글 수를 조회한다 , 만약 검색어를 추가하여 조회할 경우 매개변수로 조회되는 기준을 넘겨주자
		int totalCount = prodDao.selectSearchAll(JDBCTemplate.getConnection(), cate_id, search);
		
		//Paging 객체 생성
		Paging paging = new Paging(totalCount, curPage); //총 게시글 수, 현재 페이지
		
		//계산된 Paging 객체 반환
		return paging;
	}
	
	

}
