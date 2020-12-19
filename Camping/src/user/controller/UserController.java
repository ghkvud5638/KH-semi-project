package user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import user.dto.TB_USER;
import user.service.face.UserService;
import user.service.impl.UserServiceImpl;

@WebServlet("/user/*") // myPage이하 모든 주소값들이 여기로 옴
public class UserController extends HttpServlet{
      private static final long serialVersionUID = 1L;
      UserService userservice = new UserServiceImpl();
      @Override
      protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         System.out.println("userController [GET] - 요청 성공");
         System.out.println("--------------------------------");
         doProcess(req, resp);
      }
      @Override
      protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         System.out.println("userController [POST] - 요청 성공");
         doProcess(req, resp);
      }
      private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String uri = req.getRequestURI();
            String path = uri.split("/user")[1];
            System.out.println(path);
            System.out.println(req.getMethod());
//            String method = req.getMethod();
            //회원가입 페이지 접속
            if ("/signup".equals(path)) {
            System.out.println("signup 회원가입 페이지 [GET]");
            req.getRequestDispatcher("/WEB-INF/views/user/signup.jsp").forward(req, resp);
            }
            //회원가입 버튼 누르기
            if ("/signupProcess".equals(path)) {
              System.out.println("signup 회원가입 처리 [POST]");
//              String birthday = req.getParameter("birth_yy")
//                     +req.getParameter("birth_mm")
//                     +req.getParameter("birth_dd");
//             System.out.println(birthday);
                req.setCharacterEncoding("UTF-8");
               //응답 데이터의 형식을 설정하기
                resp.setContentType("text/html;charset=utf-8");

              //회원가입폼에 입력한 데이터 가져와 TB_USER객체에 담아줌
                TB_USER up = new TB_USER();
              up.setUser_id(req.getParameter("id"));
              up.setPw(req.getParameter("password")); 
              up.setUser_name(req.getParameter("name"));
              up.setEmail(req.getParameter("mail1")+"@"+req.getParameter("mail2")); 
              up.setPhone(req.getParameter("phone"));
              up.setGender(req.getParameter("gender"));
              
//              boolean ch = userservice.insert(up); // 같은게 있어서 커밋안되면 false 출력
              //데이터 INSERT, ID 중복검사 필요 , AJAX로 구현
              userservice.insert(up);
//              System.out.println("결과 ch : "+ch);
              resp.sendRedirect("/user/login");
           }
            //로그인 페이지 접속
            if ("/login".equals(path)) {
               System.out.println("login 로그인 페이지 요청 [GET]");  
               req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req, resp);
            }            
            //로그인 버튼 클릭
            if ("/loginProcess".equals(path)) {
               System.out.println("login 로그인 요청 [POST]");  
               //로그인 폼 창에서 작성한 ID, PW 가져옴
               //login.jsp에서 ajax로 넘김
               String userid = req.getParameter("userId");
               String userpw = req.getParameter("userPw");
               System.out.println("id:"+userid+", pw:"+userpw);
              
               HttpSession session = req.getSession();
              int res = userservice.loginCheck(userid, userpw); //로그인 검증
              Map<String, Object> map = new HashMap<>(); //login.jsp로 넘겨줄 데이터를 담을 맵
              Gson gson = new Gson(); //login.jsp로 넘겨줄 데이터를 변환하는 gson

              if (res == 1 ) {
                  //로그인 성공
                  //url= "/WEB-INF/views/main.jsp";
                  //로그인 상태 정보 저장 - Session Scope , JSP로 전송
//                  session.setAttribute("login", true); //로그인 상태, login = true
                  
                    String grade = userservice.adminCheck(userid);
                    session.setAttribute("grade", grade);
                    session.setAttribute("loginid", userid); // 로그인 아이디, 세션 살리기
                  try {
                     PrintWriter out = resp.getWriter(); //jsp로 데이터넘기는 객체
                     map.put("msg", "ok"); //맵에 담을 키와 밸류
                     if("admin".equals(session.getAttribute("grade"))) {
                        map.put("redirectUrl", "/admin/main"); // 로그인 성공시 이동할 페이지 주소를 jsp로 넘기기
                     } else {
                        map.put("redirectUrl", "/myPage/main"); // 로그인 성공시 이동할 페이지 주소를 jsp로 넘기기
                     }
                        
                        
                     resp.setContentType("text/html; charset=utf-8"); 
                     String json = gson.toJson(map); // 넘길 데이터 jsonText로 변환
                     out.print(json); // 변환한 데이터 넘기기
                     out.flush();
                     out.close();
                     
                     return;
                  } catch(Exception e) {
                     e.printStackTrace();
                     return;
                  }
              } else if(res==0) {
                  //로그인 실패 , 비밀번호 불일치
                  PrintWriter out = resp.getWriter();
                  map.put("msg", "pwNotOk");
                  resp.setContentType("text/html; charset=utf-8"); 
                  String json = gson.toJson(map);
                  out.print(json);
                  out.flush();
                  out.close();
              } else {
                    //로그인 실패, 존재하지 않는 아이디
                  PrintWriter out = resp.getWriter();
                  map.put("msg", "3");
                  resp.setContentType("text/html; charset=utf-8"); 
                  String json = gson.toJson(map);
                  out.print(json);
                  out.flush();
                  out.close();
              }
              
              
              
            }
            
            if ("/logout".equals(path)) {
               System.out.println("loguot!");
             //로그아웃 버튼, 세션 죽이고 메인페이지로.
              //세션 객체 얻기
              HttpSession session = req.getSession();
              //세션 삭제
              session.invalidate();
              resp.sendRedirect("/myPage/main");
            }
            
            // 회원 가입시 아이디 중복 체크.
            // IdCheckForm.jsp에서 값 user_id 값 가져옴
		      if ("/IdCheckAction".equals(path)) {
		    	  System.out.println("/IdCheckAction [POST] 자식창에서 중복검사 버튼 클릭 완료");
		    	  
		    	  //에서 id 받아옴 , sign.jsp의 id 받아옴
		    	  String id = req.getParameter("id");
//		    	  System.out.println(id);
		    	  
		    	  //id값 매개로 전달해서 select쿼리 수행하는 메소드 실행
		    	  boolean  res = userservice.duplicateIdCheck(id);
		    	  
		    	  resp.setContentType("text/html;charset=utf-8");
		    	  
		    	  PrintWriter out = resp.getWriter();
		    	  if (res) {out.println("0");}
		    	  else	   {out.println("1");}
		    	  		    	  
		      }      
      }

}