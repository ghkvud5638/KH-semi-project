package shopping.controller;

import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Paging;
import shopping.dto.TB_PROD;
import shopping.service.face.shoppingService;
import shopping.service.impl.shoppingServiceImpl;

@WebServlet("/shopping/prodlist") //shopping 이하 모든 주소값들이 여기로 옴
public class shoppingController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private shoppingService shoppingservice = new shoppingServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	          System.out.println("shoppingController [GET]");
    		  System.out.println("sort null select");
    		  Paging paging = shoppingservice.getPaging(req); // 전체 게시글 수, 현재 페이지, 화면에 보이는 시작&끝 페이지 번호 등을 포함한 페이징 객체생성
    		  //Paging처리 결과 MODEL값 전달
    		  req.setAttribute("prodlistPaging", paging); // paging.jsp로
    		  
    		  System.out.println(paging);
    		  List<TB_PROD> prodList = shoppingservice.selectAll(paging); //리스트(배열), TB_PROD형태로 prodList변수 안에 담긴다
    		  System.out.println("상품 전체 : "+prodList);
    		  
    		  req.setAttribute("prodlist", prodList);
    		  req.getRequestDispatcher("/WEB-INF/views/shopping/shopping.jsp").forward(req, resp);
	}
}
