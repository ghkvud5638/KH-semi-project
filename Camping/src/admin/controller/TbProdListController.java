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




@WebServlet("/admin/prodlist")
public class TbProdListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TbProductService tbprod = new TbProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	  	HttpSession session = req.getSession();
	  	if(session.getAttribute("loginid") == null) {
 			resp.sendRedirect("/user/login");

	  	}else {
	  		String grade = (String)session.getAttribute("grade");
	  		System.out.println(grade);
	  	  
	  		if(("admin").equals(grade)) {
	  	  		System.out.println(req.getParameter("cate"));
	  	  		
	  	  		if(req.getParameter("cate") == null) {
		  	  		Paging paging = tbprod.getPaging(req);
		 			req.setAttribute("paging", paging);
		 			
		 			List<TB_PROD> list = tbprod.selectProdBoardList(paging);
		 				
		 			req.setAttribute("list", list);
		 		
		 			req.getRequestDispatcher("/WEB-INF/views/admin/tbprod/tbprod.jsp").forward(req, resp);
	  	  	
	  	  		} else {
	  	  		
	  	  			String cate_id = (String)req.getParameter("cate");
	  	  			String search = (String)req.getParameter("search");
  	  			
	 				System.out.println("세션값은 ca : " + cate_id);
	 				System.out.println("세션값은 se : " + search);
	 				
	 				Paging paging = tbprod.getSearchPaging(req, cate_id, search);
	 				req.setAttribute("paging", paging);
	 				
	 				List<TB_PROD> list = tbprod.searchProd(paging, cate_id, search);
	 				
	 				req.setAttribute("cate", cate_id);
	 				req.setAttribute("search", search);
	 				req.setAttribute("list", list);
	 				
	 				req.getRequestDispatcher("/WEB-INF/views/admin/tbprod/tbprodsearch.jsp").forward(req, resp);
				
	  	  		} 
	
	 		} else {
		        
	 			resp.sendRedirect("/myPage/main");
			
	 			}
	  	}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
