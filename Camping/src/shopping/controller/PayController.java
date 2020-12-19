package shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;


import shopping.dto.TB_PAY;
import shopping.dto.TB_PROD;
import shopping.dto.TB_USER;
import shopping.service.face.PayService;
import shopping.service.impl.PayServiceImpl;


@WebServlet("/shopping/pay")
public class PayController extends HttpServlet {
   private static final long serialVersionUID = 1L;

   private PayService payService = new PayServiceImpl();
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      System.out.println("--------------------");
      System.out.println("/shopping/pay [get] 요청");
      //2. 회원 아이디 세션으로 불러옴
      HttpSession session = req.getSession();
      
      
      //ID 가져오기
      String loginid = (String)session.getAttribute("loginid");
      System.out.println("loginid : " + loginid);
      
      
      //2-1. 유저 아이디, 이름, 핸드폰, 주소, 우편번호 select해오기
      TB_USER payUserInfo = payService.selectByUserid(loginid);
   
      
      //1. 상품의 정보를 셀렉트해오기
      String prodno =  req.getParameter("prodno");
      System.out.println("prodno : " + prodno);
      String amount=req.getParameter("amount");
      int amt = Integer.parseInt(amount);
      //int i=Integer.parseInt(amount);
      System.out.println(amount);
      TB_PROD payProdInfo = payService.selectByProdid(prodno); 
      System.out.println("payProdInfo : " + payProdInfo);
      
      req.setAttribute("payProdInfo", payProdInfo);
      
      
      
       
       
       req.setAttribute("payUserInfo", payUserInfo);
       req.setAttribute("amount",amount);
       
       if(loginid == null) { //로그인되지 않았다면 //로그인 창으로 돌아가기
          req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req, resp);
       }
       
       System.out.println("payUserInfo : " + payUserInfo);
       
      
       
       int prod_num = payService.getProd_num(prodno);
       System.out.println("prod_num : " + prod_num);
       
       if (prod_num < amt) {
          System.out.println("결제 실패, 재고 없음");
          resp.setCharacterEncoding("UTF-8"); 
          resp.setContentType("text/html; charset=UTF-8");
 
          PrintWriter writer = resp.getWriter();
          writer.println("<script>alert('재고 없음'); history.go(-1);</script>");

      }else{
         req.getRequestDispatcher("/WEB-INF/views/shopping/payForm.jsp").forward(req, resp);
       }

   }
}