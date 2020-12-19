package shopping.service.face;



import shopping.dto.TB_PAY;
import shopping.dto.TB_PROD;
import shopping.dto.TB_USER;

public interface PayService {

	

	/**
	 * prod정보 조회하기 
	 * prodid로 상품정보를 조회하고 객체로 반환한다
	 * 
	 * @param prod_id - 조회할 상품아이디
	 * @return 조회된 상품정보를 저장한 객체
	 */
	public TB_PROD selectByProdid(String prod_id);

	/**
	 * 
	 * user 정보 조회하기
	 * @param loginid - loginid로 조회한다
	 * @return 조회된 유저 정보를 저장한 객체
	 */
	public TB_USER selectByUserid(String loginid);
	
	
	//-------------------------------------------
	/**
	 * 신규 결제 입력
	 * 
	 * @param newPay - 등록할 신규 결제 정보 객체
	 */
	public void registerPay(TB_PAY newPay);

	
	
	//업데이트할 재고
	   public int updateProdNum(String prodno,int amount);

	   /**
	    * 재고 조회
	    * @param prodno
	    * @return
	    */
	   public int getProd_num(String prodno);
	
	



}
