package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.dto.TB_USER;
import admin.service.face.UserManageService;
import admin.service.impl.UserManageServiceImpl;


@WebServlet("/admin/userdetail")
public class UserDetailManagerController extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
   private UserManageService userService = new UserManageServiceImpl();
   
   
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
              System.out.println("/modify [GET] 접속 완료");
              
              String user_id = req.getParameter("user_id"); //현재 세션에 로그인 되어있는 아이디
              
              System.out.println(user_id);
              
              TB_USER user = userService.selectUserInfo(user_id); // 나중에는 selectUser에 ID값을 매개변수로 같이 넘겨야함
              req.setAttribute("id", user.getUser_id()); // 조회된 데이터 jsp로 넘기기
              req.setAttribute("name", user.getUser_name());
              req.setAttribute("nickname", user.getNicname());
              req.setAttribute("email", user.getEmail());            
              req.setAttribute("picture", user.getPicture());
              req.setAttribute("gender", user.getGender());
              req.setAttribute("phone", user.getPhone());
              req.setAttribute("addr1", user.getAddr1());
              req.setAttribute("addr2", user.getAddr2());
              req.setAttribute("grade", user.getGrade());
              req.setAttribute("join_date", user.getJoin_date());
              System.out.println("사진ㅇ ㅣ름 : "+user.getStored_name());
              req.getRequestDispatcher("/WEB-INF/views/admin/user/userdetail.jsp").forward(req, resp);
              } else {
              resp.sendRedirect("/myPage/main");
           }
        }
      


   }

}