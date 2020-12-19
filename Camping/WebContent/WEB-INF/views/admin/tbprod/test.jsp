<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="admin.dto.TB_PROD"%>
<%@page import="admin.dto.TB_USER"%>
<%@page import="admin.common.JDBCTemplate"%>

<%@page import="admin.service.impl.TbProductServiceImpl"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- header.jsp 삽입하기 --%>
<%@ include file="../../header.jsp" %>
<%@ include file="../adminsidemenu.jsp" %>
<style type = "text/css">

 	.prodlist_page_wrap{ 
 	flex-wrap: wrap; 
	height: 765px; 
 	width: 700px; 
 	background-color: #ccc; 
 	margin: -256px 97px 0px 500px; 
 	opacity: 0.6; 
 	} 
 	.prodlist_page_wrap_content{ 
  	display: table-row-group; 
  	vertical-align: middle; 
 	} 

	.prodlist_page_wrap_content > tr{ 
 	display: table-row;  
  	vertical-align: inherit;  
 	} 
 	 	.td_input{ 
	
 	padding: 15px 30px 12px 30px; 
 	color: #888;  
	
 	} 
 	
 	.submitbox{ 
 	margin-top: 300px;
 	margin-left: 500px;
 	}

 table, th, td {
 	
	 border: 1px solid black;
 	 text-align: center;
 	  
 }

</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script type="text/javascript">


</script>

<section>

<script>

		function chk() {
		//	alert("비밀번호 불일치");
		
		$.ajax({
			url : "/admin/loginprocess",
			method : "POST",
			data : {
				userId : $("#loginId").val(),
				userPw : $("#loginPw").val()
			},
			dataType : 'json',
			success : function(data) {
				// 성공
				if(data.msg == "ok") {
					location.href = data.redirectUrl;
				} else if(data.msg == "pwNotOk"){ // 실패, 비밀번호 불일치
					alert("비밀번호 불일치");
				} else{
					alert("존재하지 않는 아이디");
				}
				
			}
		});
		}
		chk();
</script>	
          <% if(session.getAttribute("loginid") == null) {%>
          <div class="prodlist_page_wrap">
   				관리자 아이디로 로그인 하셔야 볼 수 있습니다.
		</div>           
          <%} else {%>
          	<%	String chk=""; 
				String user_id = (String)session.getAttribute("loginid");
				
				
				Connection conn = JDBCTemplate.getConnection();
				PreparedStatement ps = null;
				ResultSet rs = null;
				
				String sql = "";
				sql += "select grade from TB_USER";
				sql += " WHERE USER_ID = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, user_id);

				rs = ps.executeQuery();
				
				if(rs.next()){
					chk = rs.getString("grade");		
          			}
 			%>

		<% if(chk.equals("admin")) { %>
			<%	List<TB_PROD> list = (List<TB_PROD>) request.getAttribute("list"); %>
			
			    <div class="prodlist_page_wrap">
			<form id="sub" name="sub" action="/admin/prodremove" method="get" enctype="multipart/form-data"  >
			<table>
			<tr>
			<!-- 	<th>글번호</th> -->
				<th width="50" >상품 사진</th>
				<th width="400">상품 이름 </th>
				<th width="100">상품 가격 </th>
				<th width="100">상품 수량</th>
				<th width="50" ><input type="checkbox" id="all_chk" name="all_chk"></th>
			</tr>
			<%	for(int i=0; i<list.size(); i++) { %>
			<tr>
				<td><img src="<%=request.getContextPath()%>/upload/<%=list.get(i).getProd_picturetitle() %>" width=100px; height=100px;></td>
				<td><a href="prodpage?prod_id=<%= list.get(i).getProd_id() %>"><%=list.get(i).getProd_name() %></a></td>
				<td><%=list.get(i).getProd_price() %>원</td>
				<td><%=list.get(i).getProd_num() %>개</td>
				<td><input type="checkbox" id="chk" name="prod_id" value="<%=list.get(i).getProd_id() %>"></td>
			</tr>
			<%	} %>
			</table>
			
				 <div class="submitbox">
			    	<a href="/admin/prodinsert"><input type="button" value="상품 등록" class="prodInsert_btn"></a>
			    	<input type="button" value="상품 삭제" class="prodRemove_btn" onclick="sel();">
			</div>
			</form>
			    </div>
			    
   
          <%} else { %>
            	<div class="prodlist_page_wrap">
   				해당 아이디는 관리자 권한이 없습니다.
			</div>   
          <%} %>
          <%} %>


</section>
<%-- footer.jsp 삽입하기 --%>
<%@ include file="../../footer.jsp" %>
