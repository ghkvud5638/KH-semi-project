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
import admin.service.face.TbProductService;
import admin.service.impl.TbProductServiceImpl;


@WebServlet("/admin/prodinsert")
public class TbProdInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	
		private TbProductService tbprod = new TbProductServiceImpl();
		
		@Override
 		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("tbProdInsertcontroller 접속 완료");
			HttpSession session = req.getSession();
		  	if(session.getAttribute("loginid") == null) {//로그인 되어있는지 확인
	 			resp.sendRedirect("/user/login");
		  		} else {
		  			String grade = (String)session.getAttribute("grade");//세션에서 등급 불러옴
		  			System.out.println(grade);
		  	  
		  		if(("admin").equals(grade)) {//등급이 관리자일 경우에만 관리자페이지 들어갈 수 있음
		  			req.getRequestDispatcher("/WEB-INF/views/admin/tbprod/tbprodinsert.jsp").forward(req, resp);
		  			} else {
		  			resp.sendRedirect("/myPage/main");
		  		}
		  	}

	    	  
		}

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			

		
			req.setCharacterEncoding("UTF-8");
	  		//응답 데이터의 형식을 설정하기
	  		resp.setContentType("text/html;charset=utf-8");
		
		System.out.println("/tbProdInsertcontroller [POST] 접속 완료");
		if (!ServletFileUpload.isMultipartContent(req)) { //form-enctype isMultipartContent 검사
			System.out.println("its not multipart");
			return;
		}	
		String saveDirectory = getServletContext().getRealPath("upload");
//		String saveDirectory = "C:\\Users\\김영재\\eclipse-web\\Camping\\WebContent\\upload\\qqq"; // 저장 경로 설정
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
		String mainPicture = mul.getFilesystemName("mainUpfile");
		String detailPicture = mul.getFilesystemName("detailUpfile");
		System.out.println("현재이름 : "+ mainPicture);
		System.out.println("현재이름 : "+ detailPicture);
		//	System.out.println("넘어온 값:"+mul.getParameter("id"));

		TB_PROD prodInfo = new TB_PROD();
			prodInfo.setCate_id(mul.getParameter("cate_id"));  
			prodInfo.setProd_name(mul.getParameter("prod_Name"));  
			prodInfo.setProd_price(Integer.parseInt(mul.getParameter("prod_Price")));
			prodInfo.setProd_num(Integer.parseInt(mul.getParameter("prod_Num")));
			prodInfo.setProd_picturetitle(mainPicture);
			prodInfo.setProd_picturedetail(detailPicture);
			tbprod.insertProd(prodInfo, "/upload/"); 
					resp.sendRedirect("/admin/prodlist");
	
			}
		
}
