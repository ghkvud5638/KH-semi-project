package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.dto.TB_PROD;
import admin.service.face.TbProductService;
import admin.service.impl.TbProductServiceImpl;

@WebServlet("/admin/prodpage")
public class TbProdPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TbProductService tbprod = new TbProductServiceImpl();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
	  	if(session.getAttribute("loginid") == null) {//로그인 되어있는지 확인
 			resp.sendRedirect("/user/login");
	  		} else {
	  			String grade = (String)session.getAttribute("grade");//세션에서 등급 불러옴
	  			System.out.println(grade);
	  	  
	  		if(("admin").equals(grade)) {//등급이 관리자일 경우에만 관리자페이지 들어갈 수 있음
	  			req.setCharacterEncoding("UTF-8");
	  			//응답 데이터의 형식을 설정하기
	  			resp.setContentType("text/html;charset=utf-8");
	  			String prod_id = req.getParameter("prod_id");
	  			
	  			
	  			System.out.println(prod_id);
	  			
	  			System.out.println("/prodpage [GET] 접속 완료");
	  			TB_PROD prodInfo = tbprod.selectProdInfo(prod_id); // 나중에는 selectUser에 ID값을 매개변수로 같이 넘겨야함
	  			req.setAttribute("prod_id", prod_id);
	  			req.setAttribute("cate_id", prodInfo.getCate_id());
	  			req.setAttribute("prod_name", prodInfo.getProd_name());
	  			req.setAttribute("prod_price", prodInfo.getProd_price());      		
	  			req.setAttribute("prod_num", prodInfo.getProd_num());
	  			req.setAttribute("prod_picturetitle", prodInfo.getProd_picturetitle());
	  			req.setAttribute("prod_picturedetail", prodInfo.getProd_picturedetail());
	  			
	  			req.getRequestDispatcher("/WEB-INF/views/admin/tbprod/tbprodpage.jsp").forward(req, resp);
	  			
	  			} else {
	  			resp.sendRedirect("/myPage/main");
	  		}
	  	}


		
	}

}
