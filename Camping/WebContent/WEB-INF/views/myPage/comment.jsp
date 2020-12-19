<%@page import="mypage.dto.TB_RECOMMENT"%>
<%@page import="mypage.dto.TB_COMMENT"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<% List<TB_COMMENT> list = (List<TB_COMMENT>) request.getAttribute("commentList"); %>
<% List<TB_RECOMMENT> relist = (List<TB_RECOMMENT>) request.getAttribute("recommentList"); %>


<style type="text/css">
.commenttext{
   display:none;
}
.commentup{
   display:none;
      border: none;
   background-color: #ffffff;
   cursor:pointer;
   font-size: 14px;
}
.recoContent {
   display:none;
}
.recommentup {
display:none;
      border: none;
   background-color: #eceef6;
   cursor:pointer;
   font-size: 14px;
}
.recoApply{
   display:none;
      border: none;
   background-color: #eceef6;
   cursor:pointer;
   font-size: 14px;
}

.commentDiv{
    margin: 50px 0 0 515px;
    width: 800px;
}
.tdUserId{
   padding: 0px 0 8px 0;
   width: 100px;
   font-size: 18px;
   
}
.tdDate{
   padding: 3px 0px 0 12px;
    opacity: .5;
    font-size: 12px;
}

.tdContent{
   margin-top: -3px;
    padding: 5px 0px 17px 15px;
    border-bottom: 1px solid #EFEFEF;

}

#deletBtn{
    border: 0;
    outline: 0;
    cursor:pointer;
 
}
.allBtn {
    margin: -20px 0px 0 215px;
    
}
.reco {
   border: none;
   background-color: #eceef6;
   cursor:pointer;
   font-size: 16px;
}
.update {
   border: none;
   background-color: #eceef6;
   cursor:pointer;
   font-size: 16px;
   }
.deletBtn{
   margin-left: -10px;
   border: none;
   background-color: #eceef6;
   cursor:pointer;
   font-size: 16px;
   }
.recoupdate {
   border: none;
   background-color: #eceef6;
   cursor:pointer;
   font-size: 16px;
   }
.recodeletBtn{
   margin-left: -10px;
   border: none;
   background-color: #eceef6;
   cursor:pointer;
   font-size: 16px;
   }

</style>
<script type="text/javascript">
var upchk = [];
var reupchk = [];
var rechk = [];


$('.update').on('click', function(){ 

   if(upchk[$(this).val()]==false){
      upchk[$(this).val()] = true;
      $('#content'+$(this).val()).css('display','block');
      $('#comment'+$(this).val()).css('display','none');
      $('#updateBtn'+$(this).val()).css('display','none');
   } else{
      upchk[$(this).val()] = false;
      $('#content'+$(this).val()).css('display','none');
      $('#comment'+$(this).val()).css('display','block');
      $('#updateBtn'+$(this).val()).css('display','block');
      
      
   }
})
$('.recoupdate').on('click', function(){ 

   if(reupchk[$(this).val()]==false){
      reupchk[$(this).val()] = true;
      $('#recontent'+$(this).val()).css('display','block');
      $('#recomment'+$(this).val()).css('display','none');
      $('#reupdateBtn'+$(this).val()).css('display','none');
   } else{
      reupchk[$(this).val()] = false;
      $('#recontent'+$(this).val()).css('display','none');
      $('#recomment'+$(this).val()).css('display','block');
      $('#reupdateBtn'+$(this).val()).css('display','block');
      
      
   }
})
      
$('.reco').on('click',function() {
   
   if(rechk[$(this).val()]==false){
      rechk[$(this).val()] = true;
      $('#recoContent'+$(this).val()).css('display','none');
      $('#recoApply'+$(this).val()).css('display','none');

   } else{
      rechk[$(this).val()] = false;
      $('#recoContent'+$(this).val()).css('display','block');
      $('#recoApply'+$(this).val()).css('display','block');
}




});
   
$('.commentup').on('click', (e) => { 
      console.log(e.target.value); 
      $.ajax({
         url:"/myPage/comment",
         type:"POST",
         data:{
            commentId : e.target.value
            ,boardno : $("#cmtId").attr("value")
            ,up : true
            ,comment : $('#comment'+e.target.value).attr("value")
         },
         dataType:'json',
         success: function(data){
            if (data.msg=="ok") {
               $(".result").load("/myPage/comment?boardno="+$("#boardNo").attr("value"))
            }else {
               alert("댓글 수정 실패");
            }
         }
         });
      });

$('.recommentup').on('click', (e) => { 
      console.log(e.target.value); 
      $.ajax({
         url:"/myPage/recomment",
         type:"POST",
         data:{
            recommentId : e.target.value
            ,up : "update"
            ,recomment : $('#recomment'+e.target.value).attr("value")
            ,boardno : $("#cmtId").attr("value")
         },
         dataType:'json',
         success: function(data){
            if (data.msg=="ok") {
               $(".result").load("/myPage/comment?boardno="+$("#boardNo").attr("value"))

            }else {
               alert("댓글 수정 실패");
            }
         }
         });
      });
      
$('.recodeletBtn').on('click', (e) => { 

   console.log(e.target.value); 
   $.ajax({
      url:"/myPage/recomment",
      type:"POST",
      data:{
         recommentId : e.target.value
         ,up : "remove"
         ,boardno : $("#cmtId").attr("value")
      },
      dataType:'json',
      success: function(data){
         if (data.msg=="ok") {
            $(".result").load("/myPage/comment?boardno="+$("#boardNo").attr("value"))
         }else {
            alert("댓글 삭제 실패");
         }
      }
      });
   });

$('.recoApply').on('click', (e) => { 
      console.log(e.target.value); 
      $.ajax({
         url:"/myPage/recomment",
         type:"POST",
         data:{
            recommentId : e.target.value
            ,boardno : $("#boardNo").attr("value")
            ,recomment : $('#recoContent'+e.target.value).attr("value")
         },
         dataType:'json',
         success: function(data){
            if (data.msg=="ok") {
               $(".result").load("/myPage/comment?boardno="+$("#boardNo").attr("value"))
            }else {
               alert("댓글 수정 실패");
            }
         }
         });
      });

   
$('.deletBtn').on('click', (e) => { 

   console.log(e.target.value); 
   $.ajax({
      url:"/myPage/comment",
      type:"POST",
      data:{
         commentId : e.target.value
         ,boardno : $("#cmtId").attr("value")
      },
      dataType:'json',
      success: function(data){
         if (data.msg=="ok") {
            $(".result").load("/myPage/comment?boardno="+$("#boardNo").attr("value"))
         }else {
            alert("댓글 삭제 실패");
         }
      }
      });
   });
   
   
</script>
<div class="commentDiv">
<%if(list != null){ %>
<%   for(int i=0; i<(int)request.getAttribute("cmtsize"); i++) { %>
<div class="tdWrap">
   <strong class="tdUserId"><%=list.get(i).getUser_id() %></strong>
   <span class="tdDate"><%=list.get(i).getComment_date() %></span>
   <div class="allBtn">
   <button class="reco" type="button" id="reco"  value="<%=list.get(i).getComment_id()%>">답글</button>
   <%if("admin".equals(session.getAttribute("grade")) || session.getAttribute("loginid").equals(list.get(i).getUser_id())    ){ %>
   <%if(session.getAttribute("loginid").equals(list.get(i).getUser_id())){ %>
   <button class="update" type="button" id="update"  value="<%=list.get(i).getComment_id()%>">수정</button>
   <%} %>
   <button class="deletBtn" type="button" id="deletBtn"  value="<%=list.get(i).getComment_id()%>">삭제</button>
   <%} %>
   </div>
   <input id="boardNo" class="boardno" type="hidden" value="<%=list.get(i).getBoardno()%>">
   
</div>
<div class="allas">
<div>
   <input id="cmtid" class="cmtid" type="hidden" value="<%=list.get(i).getComment_id()%>">
   <p class="tdContent"><span class="comment" id="content<%=list.get(i).getComment_id()%>" >
   <%=list.get(i).getContent() %>

   </span>
   <input type="text" class="commenttext" id="comment<%=list.get(i).getComment_id()%>" name="coup"  value=<%=list.get(i).getContent() %>>
   <button class="commentup" type="button" id="updateBtn<%=list.get(i).getComment_id()%>"  value="<%=list.get(i).getComment_id()%>">등록</button><br>
   <textarea id="recoContent<%=list.get(i).getComment_id()%>" class="recoContent" cols="100" rows="6" name="CommentContent"  style="resize:none;"></textarea>
   <button class="recoApply" type="button" id="recoApply<%=list.get(i).getComment_id()%>"  value="<%=list.get(i).getComment_id()%>">답글 등록</button>
   </p>   
</div>
<%if(relist != null){ %>
<div class="recoContentAll">
<%   for(int j=0; j<relist.size(); j++) { %>
<% System.out.println("작동"); %>
<% System.out.println(list.get(i).getComment_id()); %>
<% System.out.println(relist.get(j).getComment_parent() ); %>
<%if( list.get(i).getComment_id().equals(relist.get(j).getComment_parent()) ) { %>

<div class="retdWrap" style="margin-left: 50px;">
   <strong class="tdUserId"><%=relist.get(j).getUser_id() %></strong>
   <span class="tdDate"><%=relist.get(j).getComment_date() %></span>
   <div class="allBtn">
   <%if("admin".equals(session.getAttribute("grade")) || session.getAttribute("loginid").equals(relist.get(j).getUser_id())){ %>
   		<%if(session.getAttribute("loginid").equals(relist.get(j).getUser_id())){ %>
   		<button class="recoupdate" type="button" id="update"  value="<%=relist.get(j).getComment_id()%>">수정</button>
   		<%} %>
   <button class="recodeletBtn" type="button" id="deletBtn"  value="<%=relist.get(j).getComment_id()%>">삭제</button>
   <%} %>
   
   </div>
</div>
<div style="margin-left: 50px;">
   <p class="tdContent"><span class="comment" id="recontent<%=relist.get(j).getComment_id()%>"><%=relist.get(j).getContent() %></span>
   <input type="text" class="commenttext" id="recomment<%=relist.get(j).getComment_id()%>" name="coup"  value=<%=relist.get(j).getContent() %>>
   <button class="recommentup" type="button" id="reupdateBtn<%=relist.get(j).getComment_id()%>"  value="<%=relist.get(j).getComment_id()%>">등록</button><br>
   
   </p>   
</div>
<%} %>
<%} %>
</div>
</div>
<%} %>

<%} %>
<%} %>


</div>