package mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import mypage.dto.TB_USER;
import mypage.service.face.myPageService;
import mypage.service.impl.myPageServiceImpl;

@WebServlet("/myPage/modify")
public class ModifyUserInfoController extends HttpServlet {
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
			System.out.println("/modify [GET] 접속 완료");
			String user_id = session.getAttribute("loginid").toString(); //현재 세션에 로그인 되어있는 아이디
	  		TB_USER user = myPageService.selectUser(user_id); // 나중에는 selectUser에 ID값을 매개변수로 같이 넘겨야함
	  		req.setAttribute("id", user.getUser_id()); // 조회된 데이터 jsp로 넘기기
	  		req.setAttribute("name", user.getUser_name());
	  		req.setAttribute("nickname", user.getNicname());
	  		req.setAttribute("email", user.getEmail());      		
			req.setAttribute("picture", user.getStored_name());
			System.out.println("사진ㅇ ㅣ름 : "+user.getStored_name());
		   	req.getRequestDispatcher("/WEB-INF/views/myPage/modify.jsp").forward(req, resp);
		}
	}
}
