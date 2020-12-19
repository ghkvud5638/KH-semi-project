package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import admin.dto.TB_PROD;
import admin.dto.TB_USER;
import admin.service.face.UserManageService;
import admin.service.impl.UserManageServiceImpl;




@WebServlet("/admin/usermodify")
public class UserModifyManageController extends HttpServlet {
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
              
              TB_USER user = userService.selectUserInfo(user_id); // 나중에는 selectUser에 ID값을 매개변수로 같이 넘겨야함
              req.setAttribute("id", user.getUser_id()); // 조회된 데이터 jsp로 넘기기
              req.setAttribute("name", user.getUser_name());
              req.setAttribute("nicname", user.getNicname());
              req.setAttribute("email", user.getEmail());            
              req.setAttribute("picture", user.getPicture());
              req.setAttribute("gender", user.getGender());
              req.setAttribute("phone", user.getPhone());
              req.setAttribute("addr1", user.getAddr1());
              req.setAttribute("addr2", user.getAddr2());
              req.setAttribute("grade", user.getGrade());
              req.setAttribute("join_date", user.getJoin_date());
              System.out.println("사진ㅇ ㅣ름 : "+user.getPicture());
              req.getRequestDispatcher("/WEB-INF/views/admin/user/usermodify.jsp").forward(req, resp);
              } else {
              resp.sendRedirect("/myPage/main");
           }
        }
   
   }
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      int chk1 = 0;

         req.setCharacterEncoding("UTF-8");
           //응답 데이터의 형식을 설정하기
           resp.setContentType("text/html;charset=utf-8");
         
         
           System.out.println("/ModifiyManage [POST] 접속 완료");
        
         
         if (!ServletFileUpload.isMultipartContent(req)) { //form-enctype isMultipartContent 검사
         System.out.println("its not multipart");
         return;
         }   
         String saveDirectory = getServletContext().getRealPath("upload");
            System.out.println("경로 : "+saveDirectory);
            int maxPostSize = 10 * 1024 * 1024; // 10 MB
            String encoding = "UTF-8";
            FileRenamePolicy poicy = new DefaultFileRenamePolicy();

      // 파일이 업로드 됨.
      MultipartRequest mul = new MultipartRequest(req,saveDirectory,maxPostSize,encoding, poicy);
      String Picture = mul.getFilesystemName("upfile");
      System.out.println("현재이름 : "+ Picture);
      String user_id = mul.getParameter("id");
      
      if( Picture == null) {
         Picture = mul.getParameter("hiprofile");
         chk1 = 1;
      }
   
      System.out.println("sadf"+Picture);
      
      System.out.println("sadf"+user_id);

      TB_USER userInfo = new TB_USER();
         userInfo.setUser_id(user_id);  
         userInfo.setPhone(mul.getParameter("phone"));
         userInfo.setEmail(mul.getParameter("email"));
         userInfo.setAddr1(mul.getParameter("addr1"));
         userInfo.setAddr2(mul.getParameter("addr2"));
         userInfo.setNicname(mul.getParameter("nicname"));
         userInfo.setPicture(Picture);
         userService.modifyUser(userInfo, user_id, "/upload/", chk1); 

         //값 전달      
         req.setAttribute("id", user_id);
         req.setAttribute("phone", userInfo.getPhone());
         req.setAttribute("email", userInfo.getEmail());
         req.setAttribute("addr1", userInfo.getAddr1());
         req.setAttribute("addr2", userInfo.getAddr2());
         req.setAttribute("nicname", userInfo.getNicname());
         req.setAttribute("picture", userInfo.getPicture());

         resp.sendRedirect("/admin/userdetail?user_id="+user_id);
         

   }

}