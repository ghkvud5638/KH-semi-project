<%@page import="shopping.dto.Board"%>
<%@page import="java.util.List"%>
<%@page import="shopping.dto.TB_PROD"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<% TB_PROD detail = (TB_PROD)request.getAttribute("detail"); %>
<% List<Board> reviewList = (List<Board>) request.getAttribute("reviewList"); %>
<% List<Board> qaList = (List<Board>) request.getAttribute("qaList"); %>
<style type="text/css"> 

.wrap{
   margin: 450px 0 0 600px;
}

.trtr> td{
    padding: 30px 0px 0 0px;
    width: 145px;
}

.tableWrap{
   width: 1200px;
   margin: 73px 0 0 -320px;
}

.prodDetail{
   table-layout: fixed;
    width: 400px;
    margin: -306px 0px 100px 351px;
    padding: 47px 19px 10px 16px;
    position: absolute;
}

.pic{
   size: 300px;
    margin: 80px 0 0 -1px;
    padding: 50px;
    float: left;
}

.QAapplyWrap{
}

.QAwrap{
/*    margin: 0px 0 0 600px; */
    width: 420px;
    float:right; 
}

.QAwrap > table{
   table-layout: fixed;
   width:700px;
}

.articleWrap > td{
   padding: 15px 5px 20px 58px;
}

.thWrap{
   border-top: 1px solid #ccc;
}

.thWrap > th{
   border-top: 1px solid #ccc;
   padding-top: 15px;
   padding-bottom: 15px;
}

.articleWrap{
   border-top: 1px solid rgb(202 196 196 / 50%);;
   padding-top: 10px;
}

.reviewWrap{
   width: 350px;
   float:left;
}

.reviewWrap > table{
   table-layout: fixed;
   width:700px;
}

.prodDetailTitle{
   table-layout: fixed;
   width:500px;
   border-bottom: 1px solid #ccc;
   margin: 0px 0px 60px 135px;
   text-align: center;
   height: 40px;
}
   
   /* 버튼 메뉴 */
   
#detail {
   margin-right: -4px;
   border-top-left-radius: 5px;
   border-bottom-left-radius: 5px;

}

#review {
   margin-right: -3px;

}

#QAtitle {
   margin-right: -3px;

}

#delivery {
   border-top-right-radius: 5px;
   border-bottom-right-radius: 5px;
   margin-left: 0px;

}

.btn_group  button  {
   border: 1px solid #F2F0E6; 
   background-color: rgba(0,0,0,0); 
   color: black; 
   padding: 5px;
   font-size: 16px;
   width: 110px;
   height: 55px;
}


.btn_group button:hover{
   color:white;
   background-color: #084034;
   cursor: pointer;
   
}

   

.prodDetailTitle tbody tr td a {
   
}

.prodDetailContent{
   table-layout: fixed;
   width:600px;
   
}

.contenttext{
   width:500px;
}

.prodDetailContentTR > td{
   padding: 40px 15px 0px 0px;
}


#pay-btn {
   width: 115px;
   height: 45px;
   font-family: 'Roboto', sans-serif;
   font-size: 15px;
   text-transform: uppercase;
   letter-spacing: 2.5px;
   font-weight: 500;
   color: #fff;
   background-color: #084034;
   border: none;
   border-radius: 10px;
   box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
   transition: all 0.3s ease 0s;
   cursor: pointer;
   outline: none;
}

#addCart{
   width: 115px;
   height: 45px;
   font-family: 'Roboto', sans-serif;
   font-size: 15px;
   text-transform: uppercase;
   letter-spacing: 2.5px;
   font-weight: 500;
   color: #fff;
   background-color: #084034;
   border: none;
   border-radius: 10px;
   box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
   transition: all 0.3s ease 0s;
   cursor: pointer;
   outline: none;
}


#applyReview{

   width: 115px;
   height: 40px;
   font-family: 'Roboto', sans-serif;
   font-size: 15px;
   text-transform: uppercase;
   letter-spacing: 2.5px;
   font-weight: 500;
   color: #fff;
   background-color: #084034;
   border: none;
   border-radius: 10px;
   box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
   transition: all 0.3s ease 0s;
   cursor: pointer;
   outline: none;
   margin-left: 580px;

}


#applyQA {

   width: 115px;
   height: 40px;
   font-family: 'Roboto', sans-serif;
   font-size: 15px;
   text-transform: uppercase;
   letter-spacing: 2.5px;
   font-weight: 500;
   color: #fff;
   background-color: #084034;
   border: none;
   border-radius: 10px;
   box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
   transition: all 0.3s ease 0s;
   cursor: pointer;
   outline: none;
   margin-left: 580px;
   
}


</style>
<script type="text/javascript">
$(document).ready(function(){
   $("#addCart").on("click", function(){
      console.log("here")
      $.ajax({
         url:"",
         type:"POST",
         data:{
            prodId : $("#prodId").text(),
            prodName : $("#prodName").text(),
            price : $("#price").text(),
            cnt : $("#cnt").val()
         },
         dataType:"json",
         success: function(data){
            if (data.msg=="ok") {
               alert("장바구니 담기 완료");
//                location.href = data.redirectUrl;
            } else{
               alert("장바구니 담기 실패");
            }
         }
      });
   });

     //Review 하단
      $("#applyReview").on("click", function(){
         $(".Reviewresult").load("/shopping/review","prodid=<%=detail.getProd_id()%>");
          $(this).next().toggle();
            $(this).next().next().toggle();
      })
      //QA 하단
      $("#applyQA").on("click", function(){
         $(".QAresult").load("/shopping/QA","prodid=<%=detail.getProd_id()%>");
          $(this).next().toggle();
            $(this).next().next().toggle();
      })
})
</script>

<div class="wrap">
<form action="/shopping/pay" method="get">
<img class="pic" src="<%=detail.getProd_picturetitle() %>" style="display: flex;position: absolute;size:200px;margin: 15px;padding: 50px;margin-top: -280px; width:200px; height:200px;">
<table class="prodDetail">
   
   <!-- 상품 상세정보 -->
   <tr class="trtr">
<%--       <td colspan="2" id="prod_id" name="prodId">상품id  <%=detail.getProd_id() %></td> --%>
      <td><input type="hidden" id="prodno" name="prodno" value= <%=detail.getProd_id() %> ></td>
   </tr>
   <tr class="trtr">
      <td colspan="2" id="prodName">상품명  <%=detail.getProd_name() %></td>
   </tr>
   <tr class="trtr">
      <td colspan="2" name="price">가격  <fmt:formatNumber value="<%=detail.getProd_price()%>" pattern="#,###"/>원</td>
   </tr>
   <tr class="trtr">
      <td colspan="2">수량<input type="number" id="cnt" min="1" placeholder="1" name="amount" value="1"/></td>
   </tr>
   
   <tr class="trtr">
      <td><input type="submit" name="pay-btn" id="pay-btn" value="결제">
      <!--  <td colspan="1"><a href="/shopping/pay?prodno=<%=detail.getProd_id() %>&amount=amount">카카오페이로 결제</a></td>-->
      <td colspan="1"><button type="button" id="addCart">ADDCART</button></td>
   </tr>
</table>
</form>

<!-- 탭 -->
<table class="prodDetailTitle">
<tr>
   <td class="btn_group">
      <button type="button"  onclick="location.href='#detail'">제품 상세</button>
      <button type="button"  onclick="location.href='#review'">리뷰</button>
      <button type="button"  onclick="location.href='#QAtitle'"> 질문</button>
      <button type="button"  onclick="location.href='#delivery'">배송 및 교환</button>
   </td>

<!--    <td><a href="#detail">제품 상세</a></td>
   <td><a href="#review">리뷰</a></td>
   <td><a href="#QAtitle">Q&A</a></td>
   <td><a href="#delivery">배송 및 교환안내</a></td> -->
</tr>
</table>

<div>
   <img src="<%=detail.getProd_picturedetail()%>" id="detail">
</div>

<!-- 배송, A/S -->
<div>
<strong id="delivery">배송 및 교환안내</strong>
<table class="prodDetailContent" border="1">
   <tr class="prodDetailContentTR">
      <td>
         <h3>배송안내</h3>
      </td>
      <td class="contenttext">
         -평균 배송일은 주문, 입금 확인 후 다음날 입니다<br>
         -물품 수급 상황에 따라 일부 제품은 평균 배송일과 차이가 날 수 있습니다(3~5일)<br>
         -배송일이 5일 이상 소요되는 경우, 고객님께 별도로 전화를 드립니다<br>
         -주말 및 공휴일, 명절기간등은 배송이 이루어지지 않습니다
      </td>
   </tr>
   <tr class="prodDetailContentTR">
      <td>
         <h3>교환안내</h3>
      </td>
      <td class="contenttext">
         -상품 청약 철회기간은 상품 수령일로부터 5일 이내입니다<br>
         -환불은 반품한 제품 수령후 3일 이내에 처리됩니다<br>
         -상품 택(tag)제거 또는 개봉으로 상품가치 훼손 시에는 반품일 이내라도 교환 및 반품이 불가능합니다<br>
      </td>
   </tr>
</table>
</div>


<div class="tableWrap">
<!-- 리뷰 -->
<div class="reviewWrap">
   <h3 id="review">리뷰</h3>
   <table>
      <tr class="thWrap">
         <th>번호</th>
         <th>날짜</th>
         <th>제목</th>
         <th>내용</th>
         <th>id</th>
<%   for(int i=0; i<reviewList.size(); i++) { %>
      <tr class="articleWrap">
        <td id="rvboardno"><%=reviewList.get(i).getBoardno() %></td>
         <td><%=reviewList.get(i).getWrittendate() %></td>
         <td><a href="/shopping/reviewDetail?boardno=<%=reviewList.get(i).getBoardno() %>"><%=reviewList.get(i).getTitle() %></a></td>
         <td><a href="/shopping/reviewDetail?boardno=<%=reviewList.get(i).getBoardno() %>"><%=reviewList.get(i).getContent() %></a></td>
         <td><%=reviewList.get(i).getUser_id() %></td>
      </tr>
<%   } %>
   </table>
   <div class="applyReviewWrap"></div>
      <input id="applyReview" type="button" value="작성하기"/>
   <div class="Reviewresult"></div>
   
</div>

<!-- Q&A -->
<div class="QAwrap">
<strong id="QAtitle">Q&A</strong>
<table>
   <tr class="thWrap">
      <th>번호</th>
      <th>날짜</th>
      <th>질문제목</th>
      <th>질문내용</th>
      <th>id</th>
<%   for(int i=0; i<qaList.size(); i++) { %>
   </tr>
   <tr class="articleWrap">
      <td id="qaboardno"><%=qaList.get(i).getBoardno() %></td>
      <td><%=qaList.get(i).getWrittendate() %></td>
      <td><a href="/shopping/QAdetail?boardno=<%=qaList.get(i).getBoardno() %>"><%=qaList.get(i).getTitle() %></a></td>
      <td><a href="/shopping/QAdetail?boardno=<%=qaList.get(i).getBoardno() %>"><%=qaList.get(i).getContent() %></a></td>
      <td><%=qaList.get(i).getUser_id() %></td>
   </tr>
<%   } %>
</table>
<div class="QAapplyWrap"></div>
<input id="applyQA" type="button" value="작성하기"/>
<div class="QAresult"></div>
</div>
</div>



</div>