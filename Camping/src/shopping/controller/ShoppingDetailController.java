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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import shopping.dto.Board;
import shopping.dto.TB_CART;
import shopping.dto.TB_PROD;
import shopping.service.face.shoppingService;
import shopping.service.impl.shoppingServiceImpl;

/**
 * Servlet implementation class ShoppingDetailController
 */
@WebServlet("/shopping/detail")
public class ShoppingDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	shoppingService shoppingservice = new shoppingServiceImpl();  
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("ShoppingDetailController [GET]");
			
			String param = req.getParameter("prodno");
			//파라미터값 저장할 변수
			String prodno = null;
			
			//param의 값이 널값이 아니면서 빈 문자열이 아닐 때
			if( param != null && !"".equals(param)) {
				prodno = param; //형변환
			}
			System.out.println("prodno :"+prodno);
			TB_PROD prod = new TB_PROD();
			prod.setProd_id(prodno);
			
			TB_PROD detail = shoppingservice.selectDetail(prod);
			//-----select ( prodid를 이용해서 )
			List<Board> reviewList = shoppingservice.reviewListByid(prodno);
			System.out.println("reviewList : "+reviewList);
//			for(Board b : reviewList ) {
//				System.out.println("reviewList : " + reviewList);
//			}
			//-----select ( prodid를 이용해서 )
			List<Board> qaList = shoppingservice.qaListByid(prodno);
			System.out.println("qaList : "+qaList);

			req.setAttribute("reviewList", reviewList);
			req.setAttribute("qaList", qaList);
			req.setAttribute("detail", detail);
			req.getRequestDispatcher("/WEB-INF/views/shopping/shoppingDetail.jsp")
			.forward(req, resp);
		}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ShoppingDetailController [POST]");
		
		 HttpSession session = req.getSession();
	     String user_id = session.getAttribute("loginid").toString();
	     String prodId = req.getParameter("prodno");
	     String prodName = req.getParameter("prodName");
	     String pirce = req.getParameter("price");
	     int cnt = Integer.parseInt(req.getParameter("cnt"));
//	     System.out.println("prodName : "+prodName);
//	     System.out.println("pirce : "+pirce);
//	     System.out.println("cnt : "+cnt);
//	     System.out.println("user_id : "+ user_id);
	     TB_CART cart = new TB_CART();
	     cart.setUser_id(user_id);
	     cart.setProd_id(prodId);
	     cart.setCnt(cnt);
	     
		 Map<String, Object> map = new HashMap<>();
		 Gson gson = new Gson();

	     boolean res = shoppingservice.addCart(cart);
	     if (res) {
	    	 try {
			     PrintWriter out = resp.getWriter();
			     map.put("msg", "ok");
//				 map.put("redirectUrl", "/shopping/detail");
				 resp.setContentType("text/html; charset=utf-8"); 
				 String json = gson.toJson(map);
				 out.print(json); // 변환한 데이터 넘기기
				 out.flush();
				 out.close();
				 return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			PrintWriter out = resp.getWriter();
			map.put("msg", "NotOk");
			resp.setContentType("text/html; charset=utf-8"); 
			String json = gson.toJson(map);
			out.print(json);
			out.flush();
			out.close();

		}
	}
	
	
}
