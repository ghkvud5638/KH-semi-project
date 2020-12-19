<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="admin.common.JDBCTemplate"%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- header.jsp 삽입하기 --%>
<%@ include file="../../header.jsp" %>
<%@ include file="../adminsidemenu.jsp" %>

<style type = "text/css">
 	.prodUpdate_page_wrap{ 
 	flex-wrap: wrap; 
	height: 765px; 
 	width: 700px; 
 	background-color: #ccc; 
 	margin: -256px 97px 0px 500px; 
 	opacity: 0.6; 
 	} 
 	.prodUpdate_page_wrap_content{ 
 	display: table-row-group; 
 	vertical-align: middle; 
 	} 

	.prodUpdate_page_wrap_content > tr{ 
	display: table-row; 
 	vertical-align: inherit; 
 	} 


 	.key{ 
	
 	padding-left: 30px; 
 	width: 60px; 
 	height:100px; 
 	color: #111; 
 	font-size: 14px; 
 	text-align: right; 
 	padding-right: 50px; 
	
	
 	} 

 	.td_input{ 
	
 	padding: 15px 30px 12px 30px; 
 	color: #888;  
	
 	} 
 	
 	.submitbox{ 
 	margin-top: 178px;
 	margin-left: 500px;
 	}
 	
 	.pre_img {
 	style="width:50px; height:50px;"
 	}
</style>

<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script type="text/javascript">



</script>

<section>
	<%
		String prod_name="", prod_content="", prod_num="", price="", picture="";
		int prod_id = Integer.parseInt(request.getParameter("prod_id"));
		
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "select * from TB_PRODUCT";
		sql += " WHERE PROD_ID = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, prod_id);

		rs = ps.executeQuery();
		
		while(rs.next()){
			prod_name = rs.getString("prod_name");
			prod_content = rs.getString("prod_content");
			prod_num = rs.getString("prod_num");
			price = rs.getString("price");
			picture = rs.getString("stored_name");
		}
		
	%>
<article>
    <div class="prodUpdate_page_wrap">
	 <form action="/admin/produpdate" method="get" enctype="multipart/form-data">
	  <table summary="상품 정보 입력 받는 표">
	  <colgroup>
	   <col width="100">
	   <col>
	  </colgroup>
	  <thead>
	   <tr>
	    <th scope="col"></th>
	   </tr>
	   <tr>
	    <th scope="col"></th>
	   </tr>
	  </thead>
	   <tbody class="prodInsert_page_wrap_content">
	   	<tr>
	   	<td class="key">
	   	상품 이름 : 
	   	</td>
		 <td>
		 	<div id="prod" class="input"><%=prod_name%>
		 </div>
		 </td>			
		</tr>
		<tr>
		
		 <td class="key">
		 </td>
		 <td><img src="<%=request.getContextPath()%>/upload/<%=picture %>">
		 </td>
		
		</tr>
		<tr>
		 <td class="key"> 상품 내용 :
		</td>
		 <td>
		 	<div id="content" style="margin-top: 30px;"><%=prod_content%>
		 	</div>
		 	</td>
		</tr>
		<tr>
		 <td class="key">
		  	금액 : 
		 	</td>
		 <td>
		 	<div id="price"class="input" ><%=price%>원
			</div>
		 </td>			

		 <td class="key">
		 	수량 :
		 </td>
		 <td>
		 	<div id="num" class="input"><%=prod_num%>개
		 </div>
		 </td>			
		</tr>
		
	   </tbody>
	  </table>

		
	  
	  
	  
	 <div class="submitbox">
    	<input type="submit" value="상품 변경" class="prodInsert_btn">
    	<a href="/admin/prodlist"><input type="button" value="목록으로" class="prodCancel_btn"></a>
    </div>
	 </form>
	 
	 
	  
	 
    </div>
</article>
</section>
<%-- footer.jsp 삽입하기 --%>
<%@ include file="../../footer.jsp" %>
