package shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import common.Paging;
import shopping.dto.TB_PROD;
import shopping.service.face.shoppingService;
import shopping.service.impl.shoppingServiceImpl;

/**
 * Servlet implementation class ProdListSortSearchController
 */
@WebServlet("/shopping/sortSearch")
public class ProdListSortSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private shoppingService shoppingservice = new shoppingServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("get...? 여기...? 먹통?>?");
		resp.setContentType("text/html; charset=utf-8"); 
		System.out.println("shoppingController [GET]");
		Paging paging = shoppingservice.getPaging(req); // 전체 게시글 수, 현재 페이지, 화면에 보이는 시작&끝 페이지 번호 등을 포함한 페이징 객체생성
		req.setAttribute("prodSortedListPaging", paging); 

		String sort = req.getParameter("sort"); 
		System.out.println("sort : "+sort);
		List<TB_PROD> prodList = shoppingservice.selectSort(paging,sort);
		System.out.println("controller prodList : "+prodList);
		req.setAttribute("prodlist", prodList);
		req.setAttribute("sorted", sort);
		req.getRequestDispatcher("/WEB-INF/views/shopping/shopping.jsp").forward(req, resp);
		  
	}
	
}