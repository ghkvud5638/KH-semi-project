package shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shopping.dto.TB_PAY;
import shopping.dto.TB_PROD;
import shopping.service.face.PayService;
import shopping.service.impl.PayServiceImpl;


@WebServlet("/shopping/pay/success")
public class PaySuccessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PayService payService = new PayServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("--------------------");
		System.out.println("/shopping/pay/success : " + "[get] 요청");
		String amount=req.getParameter("amount");
		int amt=Integer.parseInt(amount);
		String total=req.getParameter("total");
		int totalprice=Integer.parseInt(total);
		//1. 상품의 정보를 셀렉트해오기
		String prodno =  req.getParameter("prodno");
		System.out.println("/shopping/pay/success - prodno : " + prodno);
		
		TB_PROD payProdInfo = payService.selectByProdid(prodno); 
		System.out.println("payProdInfo : " + payProdInfo);
		
		req.setAttribute("payProdInfo", payProdInfo);
		
		//2. 회원 아이디 세션 불러옴
		HttpSession session = req.getSession();
		//test
		
		//ID 가져오기
		String loginid = (String)session.getAttribute("loginid");
		System.out.println("/shopping/pay/success - loginid : " + loginid);
		
		//3. 상품의 수량 
		
		//3. 새로운 결제 정보 삽입
		TB_PAY newPay = new TB_PAY();
		newPay.setOrder_id(payProdInfo.getOrder_id());
		newPay.setUser_id(loginid);
		newPay.setProd_id(payProdInfo.getProd_id());
		newPay.setProd_cnt(amt);
		newPay.setTotalprice(totalprice);
		
		payService.registerPay(newPay);
		
		System.out.println("새로 입력할 정보 : " + newPay);
		
		   int res = payService.updateProdNum(prodno,amt); 
		      if (res==1) {
		         System.out.println("컨트롤러 재고 업데이트 성공");
		      }else {
		         System.out.println("컨트롤러 재고 업데이트 실패");
		      }
		      
		      req.getRequestDispatcher("/WEB-INF/views/shopping/paySuccess.jsp").forward(req, resp);
		      
		   }
	}