package shopping.dao.face;

import java.sql.Connection;
import java.util.List;

import common.CartPaging;
import common.Paging;
import shopping.dto.Board;
import shopping.dto.TB_CART;
import shopping.dto.TB_PROD;

public interface shoppingDao {

	public List<TB_PROD> selectAllProd(Connection conn , Paging paging);

	public TB_PROD selectByProdId(Connection conn, TB_PROD prod);

	public int insertCart(Connection conn, TB_CART cart);

	public int selectCntAll(Connection connection);
	//-------------------------------------------
		//---사이드 메뉴
		//1. 텐트/타프
		public List<TB_PROD> tentList(Connection conn, Paging paging1);
		public int selectCntAll1(Connection conn);
		
		//2. 침낭
		public List<TB_PROD> sleepingbagList(Connection conn, Paging paging2);
		public int selectCntAll2(Connection conn);
		
		//3. 테이블의자
		public List<TB_PROD> tableList(Connection conn, Paging paging3);
		public int selectCntAll3(Connection conn);

		//4. 취사용품
		public List<TB_PROD> cookingList(Connection conn, Paging paging4);
		public int selectCntAll4(Connection conn);

		//리뷰
		public List<Board> selectReview(Connection conn, String prodno);

		//qa
		public List<Board> selectQa(Connection conn, String prodno);

		//상품 리스트 정렬 조회
		public List<TB_PROD> selectProdListBySort(Connection conn,Paging paging ,String sort);
}
