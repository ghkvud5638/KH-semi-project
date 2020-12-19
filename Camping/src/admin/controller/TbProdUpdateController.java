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



@WebServlet("/admin/produpdate")
public class TbProdUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TbProductService tbprod = new TbProductServiceImpl();

	
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
	  			String prod_id = req.getParameter("prod_id");
	  			
	  			System.out.println("sadf"+prod_id);
	  			
	  			System.out.println("/produpdate [GET] 접속 완료");
	  			TB_PROD prodInfo = tbprod.selectProdInfo(prod_id); // 나중에는 selectUser에 ID값을 매개변수로 같이 넘겨야함
	  			req.setAttribute("prod_id", prod_id);
	  			req.setAttribute("cate_id", prodInfo.getCate_id());
	  			req.setAttribute("prod_name", prodInfo.getProd_name());
	  			req.setAttribute("prod_price", prodInfo.getProd_price());      		
	  			req.setAttribute("prod_num", prodInfo.getProd_num());
	  			req.setAttribute("prod_picturetitle", prodInfo.getProd_picturetitle());
	  			req.setAttribute("prod_picturedetail", prodInfo.getProd_picturedetail());
	  			
	  			req.getRequestDispatcher("/WEB-INF/views/admin/tbprod/tbprodupdate.jsp").forward(req, resp);
	  			} else {
	  			resp.sendRedirect("/myPage/main");
	  		}
	  	}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	int chk1 = 0;
	int chk2 = 0;

		req.setCharacterEncoding("UTF-8");
  		//응답 데이터의 형식을 설정하기
  		resp.setContentType("text/html;charset=utf-8");
		
		
  		System.out.println("/prodpage [POST] 접속 완료");
  	
		
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
	String mainPicture = mul.getFilesystemName("mainUpfile");
	String detailPicture = mul.getFilesystemName("detailUpfile");
	System.out.println("현재이름 : "+ mainPicture);
	System.out.println("현재이름 : "+ detailPicture);
	String prod_id = mul.getParameter("prod_id");
	String prod_name = mul.getParameter("prod_Name");
	
	if( mainPicture == null) {
		mainPicture = mul.getParameter("himain");
		chk1 = 1;
	}
	if( detailPicture == null) {
		detailPicture = mul.getParameter("hidetail");
		chk2 = 1;
	}
	System.out.println("sadf"+mainPicture);
	System.out.println("sadf"+detailPicture);
	
	System.out.println("sadf"+prod_id);
	System.out.println("sadf"+prod_name);

	TB_PROD prodInfo = new TB_PROD();
		prodInfo.setCate_id(mul.getParameter("cate_id"));  
		prodInfo.setProd_name(mul.getParameter("prod_Name"));  
		prodInfo.setProd_price(Integer.parseInt(mul.getParameter("prod_Price")));
		prodInfo.setProd_num(Integer.parseInt(mul.getParameter("prod_Num")));
		prodInfo.setProd_picturetitle(mainPicture);
		prodInfo.setProd_picturedetail(detailPicture);
		tbprod.updateProd(prodInfo, prod_id, "/upload/", chk1, chk2); 

		//값 전달		
		req.setAttribute("prod_id", prod_id);
		req.setAttribute("cate_id", prodInfo.getCate_id());
		req.setAttribute("prod_name", prodInfo.getProd_name());
		req.setAttribute("price", prodInfo.getProd_price());      		
		req.setAttribute("prod_num", prodInfo.getProd_num());
		req.setAttribute("prod_picturetitle", prodInfo.getProd_picturetitle());
		req.setAttribute("prod_picturedetail", prodInfo.getProd_picturedetail());

		resp.sendRedirect("/admin/prodpage?prod_id="+prod_id);
		

	}
 

}
