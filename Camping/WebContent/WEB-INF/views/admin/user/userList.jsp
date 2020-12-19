<%@page import="admin.dto.TB_USER"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<%   List<TB_USER> list = (List<TB_USER>) request.getAttribute("list"); %>


<%@ include file="../../header.jsp" %>
<%@ include file="../adminsidemenu.jsp" %>

<style type="text/css">
.tableWrap{
   margin:0px 0px 0px 394px
}
.tableWrap > table{
   table-layout: fixed;
   width: 1004px;
}
.articleWrap > td{
   padding: 15px 71px 14px 84px;
/*    border: 1px solid #ccc; */
}
.text-center{
   margin:69px 0px 0px 600px;
   width: 600px;
}
caption{
    padding-bottom: 58px;
    margin: 0 0 -35px -895px;
    font-size: 19px;
    font-weight: bolder
}
.thWrap{
   border-top: 1px solid #ccc;

}
.thWrap > th{
   border-top: 1px solid #ccc;
   padding-top: 15px;
   padding-bottom: 15px;
      font-size: 15px;
   font-weight: bolder;
   text-align: center;
}

#allchk{
/*    border-top: 1px solid #ccc; */
   padding-top: 15px;
   padding-bottom: 15px;
      font-size: 15px;
   font-weight: bolder;
   text-align: center;
}

.articleWrap{
   border-top: 1px solid rgb(202 196 196 / 50%);;
   padding-top: 10px;
}

.articleWrap td{
 vertical-align: middle;
 text-align: center;
 font-size: 15px;
 }

.btn{
    width:100px;

    padding: 5px 0;
    text-align: center; 
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
   font-weight: bolder;
    border-radius: 4px;
    background-color: #f0e8df;
        
}
.search{
     margin: 200px 0px -200px 100px;   

}
#cate_id{
   width: 150px;
   height: 35px;
   font-size: 15px;
   font-weight: bolder;
   text-align: center;
   background-color: #f0e8df;
   cursor: pointer;
   
}
#prod_search {
   width: 450px;
   height: 35px;
   font-size: 15px;
/*    background-color: #f0e8df; */

}

#chk {
   width: 20px;
   height: 20px;
    margin: 0px 0px 0px -70px; 
}

.btnfloat{
    margin: 100px 0px 0px 800px; 

}

#allchk:hover{
   color: #ccc;
   cursor:pointer;
}

#prodPick{
   margin: 0px 0px 0px -55px;
}

</style>

<script type="text/javascript">
$(document).ready(function(){
   var chk = false;
   $('#allchk').on('click', function(){
      if(chk == false) { 
         chk = true;
         $("input[name='deleteChkBox']").prop('checked', true);
           }
        else {
         chk = false;
           $("input[name='deleteChkBox']").prop('checked', false);
        }   
   });
   
   $("#deleteBtn").on("click",function(){
      console.log("here")
      $.each($("input:checkbox[id='chk']:checked"), function(item) {
            console.log($(this).val());
            $.ajax({
               url:"/admin/userremove",
            type:"POST",
            data:{
               user_id : $(this).val() 
            },
            dataType:'json',
            success: function(data){
               if (data.msg=="ok") {
                  location.href = data.redirectUrl;
               }else {
                  alert("유저 정보 삭제 실패");
               }
            }
            });

      }); 
   })
})


</script>

<%@ include file="../adminUserManage.jsp" %>


<!-- <title> + 나의 게시글 목록 + </title> -->
<div class="tableWrap">
<table>
<caption>상품 목록</caption>
<tr class="thWrap">
   <th width="100px"><span id="allchk">전체 선택</span></th>
   <th width="150px">유저 ID</th>
   <th>유저 이름</th>
   <th>유저 닉네임</th>
   <th>등급</th>
</tr>
<%   for(int i=0; i<list.size(); i++) { %> <!-- size() 게시글 10개 -->
<tr class="articleWrap">
   <td width="100"><input id="chk" type="checkbox" name="deleteChkBox" value="<%=list.get(i).getUser_id() %>"></td>
   <td style="padding-left: 40px; text-align:left;"><%=list.get(i).getUser_id() %></td>
   <td><a href="userdetail?user_id=<%= list.get(i).getUser_id() %>">
   <span style="font-size: 15px;"><%=list.get(i).getUser_name() %></span></a></td>
   <td ><%= list.get(i).getNicname() %></td>
   <td ><%= list.get(i).getGrade() %></td>
</tr>
<%   } %>
</table>
<div class="btnfloat">
<button type="button" id="deleteBtn" class="btn">삭제</button>
</div>


</div>

<%@ include file="../../footer.jsp" %>