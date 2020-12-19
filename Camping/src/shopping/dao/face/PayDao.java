package shopping.dao.face;

import java.sql.Connection;

import shopping.dto.TB_PAY;
import shopping.dto.TB_PROD;
import shopping.dto.TB_USER;

public interface PayDao {

	/**
	 * 주어진 prodid로 해당 상품의 정보를 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @param prod_id - 조회할 상품 ID
	 * @return 조회된 상품의 정보 객체
	 */
	public TB_PROD selectByProdid(Connection conn, String prod_id);


	/**
	 * userid를 통해 해당 유저의 정보를 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @param loginid - 조회할 로그인 ID
	 * @return 조회된 유저 정보 객체
	 */
	public TB_USER selectByUser(Connection conn, String loginid);

	//--------------------------------------------------
	/**
	 * 전달받은 객체를 통해 결제 정보를 삽입한다
	 * 
	 * @param conn - DB연결 객체
	 * @param newPay - 새로 삽입할 결제 정보 객체
	 * @return
	 */
	public int insertPay(Connection conn, TB_PAY newPay);
	
	
	public int updateProdNum(Connection conn, String prodno, int amount);

	   /**
	    * 재고조회
	    * @param connection
	    * @param prodno
	    * @return
	    */
	   public int selectProd_num(Connection connection, String prodno);
	}
	
	
	
	

