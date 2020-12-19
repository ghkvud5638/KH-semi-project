package shopping.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Paging;
import shopping.dto.Board;
import shopping.dto.TB_CART;
import shopping.dto.TB_PROD;

public interface shoppingService {

	/**
	 * 전체 상품 조회
	 * @return TB_PROD의 상품 정보들
	 */
	public List<TB_PROD> selectAll(Paging paging);

	/**
	 * prod_id로 상품 조회
	 * @param prod
	 * @return
	 */
	public TB_PROD selectDetail(TB_PROD prod);

	public boolean addCart(TB_CART cart);

	
	/**
	 * 전체 상품 리스트
	 * 페이징 객체 생성
	 * @param req
	 * @return
	 */
	public Paging getPaging(HttpServletRequest req);


	//-----------------------------------------------
	//1.
	public List<TB_PROD> tentList(Paging paging);
	public Paging getPaging1(HttpServletRequest req);

	//2.
	public List<TB_PROD> sleepingbagList(Paging paging2);
	public Paging getPaging2(HttpServletRequest req);

	//3.
	public List<TB_PROD> tableList(Paging paging3);
	public Paging getPaging3(HttpServletRequest req);
	
	//4.
	public List<TB_PROD> cookingList(Paging paging4);
	public Paging getPaging4(HttpServletRequest req);

	
	//리뷰 조회
	public List<Board> reviewListByid(String prodno);

	//qa조회
	public List<Board> qaListByid(String prodno);

	//정렬
	public List<TB_PROD> selectSort(Paging paging,String sort);

}
