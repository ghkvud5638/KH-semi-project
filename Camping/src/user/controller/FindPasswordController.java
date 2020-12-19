package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dto.TB_USER;
import user.service.face.UserService;
import user.service.impl.UserServiceImpl;

@WebServlet("/member/find/password")
public class FindPasswordController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   UserService userservice = new UserServiceImpl();

   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   
      System.out.println("FindIdAction [GET] - 요청 성공");
      System.out.println("--------------------------------");
   
      req.getRequestDispatcher("/WEB-INF/views/member/findIdPwForm.jsp").forward(req, resp);
   
   }
   
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      System.out.println("FindIdAction [POST] - 요청 성공");
      System.out.println("--------------------------------");

      req.setCharacterEncoding("UTF-8");
      
      //변수 저장
      String id = req.getParameter("user_id");
      String name = req.getParameter("user_name");
      String email = req.getParameter("email");
      String phone = req.getParameter("phone");
      
      System.out.println(id+","+name+","+email+","+phone);
      
      TB_USER user = userservice.searchPw(id, name, email, phone);
      
      if(user != null) {
         
         req.setAttribute("user", user);
         req.setAttribute("msg", "");
         req.setAttribute("loc", "/user/login");
         req.getRequestDispatcher("/WEB-INF/views/user/findPw.jsp")
            .forward(req, resp);
         
      } else {
         req.setAttribute("user", "false");
         req.setAttribute("msg", "잘못된 정보 입니다. 다시 입력해주세요.");
         req.setAttribute("loc", "/member/find/loginId");
         req.getRequestDispatcher("/WEB-INF/views/user/findPw.jsp")
            .forward(req, resp);
         }
   }

}