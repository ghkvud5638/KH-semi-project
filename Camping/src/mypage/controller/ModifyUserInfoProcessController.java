package mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import mypage.dto.TB_USER;
import mypage.service.face.myPageService;
import mypage.service.impl.myPageServiceImpl;

/**
 * Servlet implementation class ModifyUserInfoProcessController
 */
@WebServlet("/myPage/modifyProcess")
public class ModifyUserInfoProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private myPageService myPageService = new myPageServiceImpl();

	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
  		//응답 데이터의 형식을 설정하기
  		resp.setContentType("text/html;charset=utf-8");
  		System.out.println("/modify [POST] 접속 완료");
  		if (!ServletFileUpload.isMultipartContent(req)) { //form-enctype isMultipartContent 검사
  			System.out.println("its not multipart");
  			return;
		}	
//		String saveDirectory = getServletContext().getRealPath("upload");
		String saveDirectory = "C:\\Users\\Lee\\eclipse-workspace\\Camping\\WebContent\\upload"; // 저장 경로 설정
		//이클립스 refresh 설정, general-workspace , refresh using native hooks or polling , save automatically before build 체크
		//업로드가 되어도 refresh에 약간의 텀이 생기긴 함.
		System.out.println("경로 : "+saveDirectory);
		//3.업로드 제한 크기
		int maxPostSize = 10 * 1024 * 1024; // 10 MB
		//4.한글 인코딩
		String encoding = "UTF-8";
		//5.중복된 파일 이름 처리
		FileRenamePolicy poicy = new DefaultFileRenamePolicy();
	
		// 파일이 업로드 됨.
		MultipartRequest mul = new MultipartRequest(req,saveDirectory,maxPostSize,encoding,poicy);
		String origin = mul.getOriginalFileName("upfile");
		String stored = mul.getFilesystemName("upfile");
		System.out.println("origin : "+ origin);
		System.out.println("stored : "+ stored);
//		System.out.println("넘어온 값:"+mul.getParameter("id"));
		
		TB_USER up = new TB_USER();
		up.setUser_id(mul.getParameter("id")); //사진 업로드 할 때 필요한 user_id, 사진의 경로를 객체변수 up에 담는다
		up.setOrigin_name(origin);
		up.setStored_name(stored);
//		System.out.println("up 확인 : "+up);    		
		myPageService.insertFile(up); //사진을 업로드한 파일명, user_id등을 db에 update
		
		TB_USER userInfo = new TB_USER();
		userInfo.setUser_id(mul.getParameter("id"));  
		userInfo.setUser_name(mul.getParameter("name"));
		userInfo.setNicname(mul.getParameter("nic"));
		userInfo.setEmail(mul.getParameter("email"));
		myPageService.updateUser(userInfo); // 작성한 user_id, user_name, nickname, Email 등을 DB에 UPDATE
		//리다이렉트, /modifyProcess가 아닌, modify로 돌아가기 
		resp.sendRedirect("/myPage/modify");
		
		}
	
}
