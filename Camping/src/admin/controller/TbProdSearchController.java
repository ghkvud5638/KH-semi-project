package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.dto.TB_PROD;
import admin.service.face.TbProductService;
import admin.service.impl.TbProductServiceImpl;
import common.Paging;

@WebServlet("/admin/prodsearch")//사용 안합니다
public class TbProdSearchController extends HttpServlet {
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
	  			String cate_id = req.getParameter("cate");
	  			String search = req.getParameter("search");
	  			
	  			System.out.println(cate_id);
	  			System.out.println(search);
	  			
	  			System.out.println("세션값은 ca : " + cate_id);
	  			System.out.println("세션값은 se : " + search);
	  			
	  			Paging paging = tbprod.getSearchPaging(req, cate_id, search);
	  			req.setAttribute("paging", paging);
	  			
	  			List<TB_PROD> list = tbprod.searchProd(paging, cate_id, search);
	  			
	  			req.setAttribute("cate", cate_id);
	  			req.setAttribute("search", search);
	  			req.setAttribute("list", list);
	  			
	  			req.getRequestDispatcher("/WEB-INF/views/admin/tbprod/tbprodsearch.jsp").forward(req, resp);
	  			} else {
	  			resp.sendRedirect("/myPage/main");
	  		}
	  	}
	}
	
}
