package shopping.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.CartPaging;
import common.JDBCTemplate;
import common.Paging;
import mypage.dto.CAMP_INFO;
import shopping.dao.face.shoppingDao;
import shopping.dao.impl.shoppingDaoImpl;
import shopping.dto.Board;
import shopping.dto.TB_CART;
import shopping.dto.TB_PROD;
import shopping.service.face.shoppingService;

public class shoppingServiceImpl implements shoppingService {
	
	private shoppingDao shoppingdao = new shoppingDaoImpl();
	
	@Override
	public List<TB_PROD> selectAll(Paging paging) {
		System.out.println("shoppingServiceImpl - selectAll() 호출");
		Connection conn = JDBCTemplate.getConnection();
		//Emp테이블 전체 조회하여 List에 넣어줌 - EmpDao객체 이용
		List<TB_PROD> prodlist = shoppingdao.selectAllProd(conn, paging);
		System.out.println("prodlist"+prodlist);
		return prodlist;
	}
	
	
	@Override
	public TB_PROD selectDetail(TB_PROD prod) {
		Connection conn = JDBCTemplate.getConnection();
		TB_PROD detail = shoppingdao.selectByProdId(conn,prod);
		return detail;
	}
	
	@Override
	public boolean addCart(TB_CART cart) {
		Connection conn = JDBCTemplate.getConnection();
		boolean res = false;
		int result = shoppingdao.insertCart(conn,cart);
		if (result>0) {
			System.out.println("커밋완료");
			res = true;
			JDBCTemplate.commit(conn);
		}else {
			System.out.println("커밋실패");
			res = false;
			JDBCTemplate.rollback(conn);
		}
		return res;
	}
	
	
	
	/**
	 * 전체 상품 리스트 페이징
	 */
	@Override
	public Paging getPaging(HttpServletRequest req) {
		//전달파라미터 curPage를 파싱한다
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param!=null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}

		int totalCount = shoppingdao.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging 객체 생성
		Paging paging = new Paging(totalCount, curPage); //총 게시글 수, 현재 페이지
		//계산된 Paging 객체 반환
		return paging;

	}
	
	
	//-----------------------------------------------
		//사이드메뉴

			//1. 텐트타프
			@Override
			public List<TB_PROD> tentList(Paging paging1) {

				Connection conn = JDBCTemplate.getConnection();

				List<TB_PROD> tentList = new ArrayList<TB_PROD>();
				tentList = shoppingdao.tentList(conn, paging1);
				
				return tentList;
			}


			@Override
			public Paging getPaging1(HttpServletRequest req) {

				Connection conn = JDBCTemplate.getConnection();
				
				//전달 파리미터 curPage를 파싱한다
				String param = req.getParameter("curPage");
				int curPage1 = 0;
				
				//파싱된값이 null아니고 아무것도 없는것도 아닐때
				if(param!=null &&!"".equals(param))  {
					curPage1 = Integer.parseInt(param);
				}
				
				//Board 테이블의 총 게시글 수를 조회
				//메소드 호출
				int totalCount1 = shoppingdao.selectCntAll1(conn);
				
				//Paging 객체 생성
				Paging paging1 = new Paging(totalCount1, curPage1);
				
				//계산된 Paging 객체 반환
				return paging1;
			}

			//2. 침낭
			@Override
			public List<TB_PROD> sleepingbagList(Paging paging2) {

				Connection conn = JDBCTemplate.getConnection();
				
				List<TB_PROD> sleepingbagList = new ArrayList<TB_PROD>();
				sleepingbagList = shoppingdao.sleepingbagList(conn, paging2);
				
				return sleepingbagList;
			}


			@Override
			public Paging getPaging2(HttpServletRequest req) {

				Connection conn = JDBCTemplate.getConnection();
				
				//전달 파리미터 curPage를 파싱한다
				String param = req.getParameter("curPage");
				int curPage2 = 0;
				
				//파싱된값이 null아니고 아무것도 없는것도 아닐때
				if(param!=null &&!"".equals(param))  {
					curPage2 = Integer.parseInt(param);
				}
				
				//Board 테이블의 총 게시글 수를 조회
				//메소드 호출
				int totalCount2 = shoppingdao.selectCntAll2(conn);
				
				//Paging 객체 생성
				Paging paging2 = new Paging(totalCount2, curPage2);
				
				//계산된 Paging 객체 반환
				return paging2;
			}

			//3. 테이블 의자
			@Override
			public List<TB_PROD> tableList(Paging paging3) {

				Connection conn = JDBCTemplate.getConnection();
				
				List<TB_PROD> tableList = new ArrayList<TB_PROD>();
				tableList = shoppingdao.tableList(conn, paging3);
				
				return tableList;
			}


			@Override
			public Paging getPaging3(HttpServletRequest req) {

				Connection conn = JDBCTemplate.getConnection();
				
				//전달 파리미터 curPage를 파싱한다
				String param = req.getParameter("curPage");
				int curPage3 = 0;
				
				//파싱된값이 null아니고 아무것도 없는것도 아닐때
				if(param!=null &&!"".equals(param))  {
					curPage3 = Integer.parseInt(param);
				}
				
				//Board 테이블의 총 게시글 수를 조회
				//메소드 호출
				int totalCount3 = shoppingdao.selectCntAll3(conn);
				
				//Paging 객체 생성
				Paging paging3 = new Paging(totalCount3, curPage3);
				
				//계산된 Paging 객체 반환
				return paging3;
			}

			//4. 취사용품
			@Override
			public List<TB_PROD> cookingList(Paging paging4) {

				Connection conn = JDBCTemplate.getConnection();
				
				List<TB_PROD> cookingList = new ArrayList<TB_PROD>();
				cookingList = shoppingdao.cookingList(conn, paging4);
				
				return cookingList;
			}


			@Override
			public Paging getPaging4(HttpServletRequest req) {

				Connection conn = JDBCTemplate.getConnection();
				
				//전달 파리미터 curPage를 파싱한다
				String param = req.getParameter("curPage");
				int curPage4 = 0;
				
				//파싱된값이 null아니고 아무것도 없는것도 아닐때
				if(param!=null &&!"".equals(param))  {
					curPage4 = Integer.parseInt(param);
				}
						
				//Board 테이블의 총 게시글 수를 조회
				//메소드 호출
				int totalCount4 = shoppingdao.selectCntAll4(conn);
				
				//Paging 객체 생성
				Paging paging4 = new Paging(totalCount4, curPage4);
				
				//계산된 Paging 객체 반환
				return paging4;
			}


			@Override
			public List<Board> reviewListByid(String prodno) {
				Connection conn = JDBCTemplate.getConnection();
				List<Board> reviewInfo = shoppingdao.selectReview(conn, prodno);
				
				return reviewInfo;			
			}


			@Override
			public List<Board> qaListByid(String prodno) {
				Connection conn = JDBCTemplate.getConnection();
				List<Board> qaInfo = shoppingdao.selectQa(conn, prodno);
				
				return qaInfo;			
			}
	
			@Override
			public List<TB_PROD> selectSort(Paging paging, String sort) {
				Connection conn = JDBCTemplate.getConnection();
				List<TB_PROD> list = shoppingdao.selectProdListBySort(conn, paging, sort);
				
				return list;
			}
	
	
}
