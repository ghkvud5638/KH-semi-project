package shopping.service.impl;

import java.sql.Connection;

import common.JDBCTemplate;
import shopping.dao.face.PayDao;
import shopping.dao.impl.PayDaoImpl;
import shopping.dto.TB_PAY;
import shopping.dto.TB_PROD;
import shopping.dto.TB_USER;
import shopping.service.face.PayService;

public class PayServiceImpl implements PayService{

	private PayDao payDao = new PayDaoImpl();
	

	@Override
	public TB_PROD selectByProdid(String prod_id) {
		//connection 객체
		Connection conn = JDBCTemplate.getConnection();
		
		TB_PROD info = payDao.selectByProdid(conn, prod_id);
		
		return info;
	
	}


	//유저 정보 가져오기
	@Override
	public TB_USER selectByUserid(String loginid) {
		//connection 객체
		Connection conn = JDBCTemplate.getConnection();
		
		TB_USER userInfo = payDao.selectByUser(conn, loginid);
		return userInfo;
	}

	@Override
	public void registerPay(TB_PAY newPay) {
		
		//connection 객체
		Connection conn = JDBCTemplate.getConnection();
		
		int res = payDao.insertPay(conn, newPay);
		
		if( res>0 ) {
			JDBCTemplate.commit(conn); //커밋
		} else {
			JDBCTemplate.rollback(conn); //롤백
		}
	}
	
	
	@Override
	   public int updateProdNum(String prodno, int amount) {
	      //Connection 객체
	      Connection conn = JDBCTemplate.getConnection();
	      int result = payDao.updateProdNum(conn, prodno,amount);
	      if( result >0 ) {
	         System.out.println("재고 업뎃 커밋 성공");
	         JDBCTemplate.commit(conn);
	      } else {
	         System.out.println("재고 업뎃 커밋 실패");
	         JDBCTemplate.rollback(conn);
	      }
	      return result;
	   }
	   @Override
	   public int getProd_num(String prodno) {
	      int amt = payDao.selectProd_num(JDBCTemplate.getConnection(), prodno);
	      return amt;
	   }

	   
	}
	
	
	
	

