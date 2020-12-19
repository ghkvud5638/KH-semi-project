package mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.CartPaging;
import common.Paging;
import mypage.dto.TB_CART;
import mypage.dto.TB_USER;
import mypage.service.face.myPageService;
import mypage.service.impl.myPageServiceImpl;


@WebServlet("/myPage/cartlist")
public class CartListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private myPageService myPageService = new myPageServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("loginid")==null) {
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = resp.getWriter(); 
			writer.println("<script>alert('로그인 해주세요.'); location.href='/user/login';</script>"); 
			writer.close();
			resp.sendRedirect("/user/login");
			return;
		}else {
			System.out.println("CartListController [GET]");
			CartPaging paging = myPageService.getCartPaging(req); // 전체 게시글 수, 현재 페이지, 화면에 보이는 시작&끝 페이지 번호 등을 포함한 페이징 객체생성
			req.setAttribute("paging", paging); // paging.jsp로
			String user_id = session.getAttribute("loginid").toString(); //현재 세션에 로그인 되어있는 아이디
			List<TB_CART> cart = myPageService.getCartList(paging, user_id);
			System.out.println("cart : "+cart);
			TB_USER user = myPageService.selectUser(user_id);
			req.setAttribute("picture", user.getStored_name());
			req.setAttribute("list", cart); // 게시글 정보를 list에 담는다
			req.setAttribute("user_id", user_id); // 로그인 id 정보
			req.getRequestDispatcher("/WEB-INF/views/myPage/cart.jsp")
				.forward(req, resp);
		}
	}
}
