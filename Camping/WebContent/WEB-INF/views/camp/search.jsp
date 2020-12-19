<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="camping.dto.Camp"%>
<%@ include file="../header.jsp" %>

<%
   List<Camp> camp = (List) request.getAttribute("list");

%>
<%
   List<String> geo = (List) request.getAttribute("geolist");
   
%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
   src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script>
$(document).ready(function(){
   $("#list-view").load("/camp/search2?addr=null");
   
   $("#list-view").on("click", ".pagination a", function(e) {
      e.preventDefault();
      
      console.log($(this).attr("href"))
      $("#list-view").load($(this).attr("href"));
      
      return false;
   });
});
   
</script>
<script>
   function page_move(id,j){
      location.href='/camp/detail?camp_id='+id+'&j='+j;
   }
   
   function select() {
      $.ajax({
         url : "/camp/search2",
         type : "GET",
         data : {
            addr : $("#addr").val()
         },
         dataType : 'html',
         success : function(data) {
//             $("#list-view").remove()
//             $("#new-list").html(data)
            $("#list-view").html(data)
         }
      })
   }
   function list_select(){
      $.ajax({
         url : "/camp/search3",
         type : "POST",
         data : { 
            local : $("#select-addr").val(),
            induty : $("#select-ind").val(),
            keyword : $("#select-text").val()
         },
         dataType : 'html',
         success : function(d) {
            $(".select-all-list-view").remove();
            $(".select-item-new").html(d);
         }
      })
   }
   
</script>
<style>

@font-face {
   font-family: "BMHANNAAIR";
   src: url(/font/BMHANNAAIR_TTF.TTF) format("truetype");
   font-style: normal;
   font-weight: normal;
}

html{
    position: relative;
    min-height: 100%;
    margin: 0;
}


.wrap {
   position: absolute;
   left: 0;
   bottom: 40px;
   width: 288px;
   height: 132px;
   margin-left: -144px;
   text-align: left;
   overflow: hidden;
   font-size: 12px;
   font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;
   line-height: 1.5;
}

.wrap * {
   padding: 0;
   margin: 0;
}

.wrap .info {
   width: 286px;
   height: 95px;
   border-radius: 5px;
   border-bottom: 2px solid #ccc;
   border-right: 1px solid #ccc;
   overflow: hidden;
   background: #fff;
}

.wrap .info:nth-child(1) {
   border: 0;
   box-shadow: 0px 1px 2px #888;
}

.info .title {
   padding: 5px 0 0 10px;
   height: 30px;
   background: #eee;
   border-bottom: 1px solid #ddd;
   font-size: 18px;
   font-weight: bold;
}

.info .body {
   position: relative;
   overflow: hidden;
}

.info .desc {
   position: relative;
   height: 75px;
}

.desc .ellipsis {
   overflow: hidden;
   text-overflow: ellipsis;
   white-space: nowrap;
}

#list-all {
   position: relative;
   width: auto;
   height: 500px;
   
}

#select-list {
   position: absolute;
   left: 0;
   width: 30%;
   height: 100%;
   padding-left:10px;
   overflow: auto;
}

#map {
   position: absolute;
   right: 0;
   width: 68%;
   height: 100%;
   overflow: auto;
}

.item {
   height: 110px;
}

.box1 {
   float: left;
}

.box2 {
   float: left;
   width: 150px;
   padding: 10px;
}

.box2>a {
   text-overflow: ellipsis;
   white-space: nowrap;
   overflow: hidden;
}

.box2>p {
   text-overflow: ellipsis;
   white-space: nowrap;
   overflow: hidden;
}

.box3 {
   float: right;
   padding: 30px 0;
}

.box4 {
   float: right;
   padding: 30px 0;
}

.rev-btn {
   vertical-align: middle;
}

.rev-btn-2 {
   vertical-align: middle;
}
.select-all-list{
   position:relative;
   width:auto;
   height:600px;
   
}
.select-selectbox{
   position:absolute;
   align-content:center;
   width: 500px;
   height: 50px;
   left:50%;
   transform:translate(-50%,0);
}
.select-all-list-view{
   position: absolute;
   top: 60px;
   width: 100%;
   height:700px;

   overflow: scroll;
}

.select-item {
   height: 110px;
}
/* .select-item-new{ */
/*    position: absolute; */
/*    top: 60px; */
/*    width: 100%; */
/*    height: 500px; */
/*    overflow: auto; */
/* } */

.select-box1 {
   float: left;
}

.select-box2 {
   float: left;
   width: 300px;
   padding: 10px;
   
}

.title-1 {
   text-overflow: ellipsis;
   white-space: nowrap;
   overflow: hidden;
   font-size: 18px;
   font: bold;
   	font-family: "한컴바탕";
	font-weight: bolder;
}
.title-2{
text-overflow: ellipsis;
   white-space: nowrap;
   overflow: hidden;
   
   font-size:7px;
   font:italic;
}
.ADDRRR {
   text-overflow: ellipsis;
   white-space: nowrap;
   padding-top:2px;
   overflow: hidden;
   font-size:12px;
}
.tel{
   font-size:10;
}

.select-box3{
   float:left;
   width:800px;
   
}
.select-box-sub{
   
}
.intro-1{
   width:700px;
   text-overflow: ellipsis;
   overflow: hidden;
   white-space:nowrap;
   	font-family: "한컴바탕";
	font-weight: bolder;
	margin-top: 5px;
   
   
   
}
.facil{
   padding-left: 10px;
   height:15px;
}
.select-box4 {
   float: right;
   padding: 30px 0;
}


.rev-btn {
   vertical-align: middle;
}

.rev-btn-2 {
   vertical-align: middle;
}
.top-view{
   padding-top:100px;
}
#selectbox{
padding-left:10px
}
#addr{
width:200px;
height:40px;
}
.text-center{
padding-left:82px;
}
.camp-indu{
padding-top:2px;
}
body{
font-family: BMHANNAAIR;
}

.camp-addr{
padding-top:2px;
}


</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="top-view">
   <div id="selectbox">
   <select name="addr" id="addr" onchange="select()">
      <option value="null">지 역</option>
      <%
         for (int i = 0; i < geo.size(); i++) {
      %>
      <option value=<%=geo.get(i)%>> <%=geo.get(i)%></option>
      <%} %>
   </select>
   </div>
   <hr>
   <div id="list-all">
      <div id="select-list">
         <div id="list-view" >

<!--             <div class="item" onclick="setCenter(this);"> -->
<!--                <div class="box1"> -->
<!--                   <img -->
<%--                      src="/campimage/image<%=j %>.jpg" width="100" height="100"> --%>
<!--                </div> -->
<!--                <div class="box2"> -->
<!--                   <a href="#"><input type="hidden" class="campName" -->
<%--                      value="<%=camp.get(i).getCAMP_NAME()%>" /><%=camp.get(i).getCAMP_NAME()%></a> --%>
<%--                   <p class="camp-indu" style="font-size: 10px;"><%=camp.get(i).getINDUTY()%></p> --%>
<%--                   <p class="camp-addr" style="font-size: 8px;"><%=camp.get(i).getADDR()%></p> --%>
<%--                   <input type="hidden" value=<%=camp.get(i).getLONGITUDE()%> --%>
<!--                      class="long" /> <input type="hidden" -->
<%--                      value=<%=camp.get(i).getLATITUDE()%> class="lati" /> <input --%>
<%--                      type="hidden" value="<%=camp.get(i).getADDR()%>" class="addR" /> --%>

<!--                </div> -->
<!--                <div class="box3"> -->
<%--                   <button type="button" class="rev-btn" name="rev-btn" id="rev-btn" onclick="page_move(<%=camp.get(i).getCAMP_ID() %>,<%=j%>);">예약하기</button> --%>
<!--                </div> -->
<!--             </div> -->
<!--              <hr> -->
<%--             <% --%>

<%--             %> --%>
         <jsp:include page="/WEB-INF/views/camp/list.jsp" />
      
         </div>
         <!-- list view end -->
<!--          <div id="new-list"></div> -->
         <!-- new-list end -->
              
      </div>
      
      <!-- select-list end -->
      <div id="map"></div>
      <!-- map end -->
      
      <!-- map script 여기에 넣어줘야 맵이 뜸 script 위로 올리면 div 보다 먼저 실행되서 맵 로딩이 안됨 -->
      <script type="text/javascript"
         src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2839883285d5293951571fa58223465e"></script>

      <script>
         var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
         var options = { //지도를 생성할 때 필요한 기본 옵션
            center : new kakao.maps.LatLng(37.499138, 127.032930), //지도의 중심좌표.
            level : 3
         //지도의 레벨(확대, 축소 정도)
         };
         var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
         // 마커가 표시될 위치입니다 
         var markerPosition = new kakao.maps.LatLng(37.499138, 127.032930);

         // 마커를 생성합니다
         var marker = new kakao.maps.Marker({
            position : markerPosition
         });

         // 마커가 지도 위에 표시되도록 설정합니다
         marker.setMap(map);

         function setCenter(t) {
            
            var moveLatLon = new kakao.maps.LatLng(
                  $(t).find(".lati").val(), $(t).find(".long").val());

            // 지도 중심을 이동 시킵니다
            map.setCenter(moveLatLon);
            var markerPosition = new kakao.maps.LatLng($(t).find(".lati")
                  .val(), $(t).find(".long").val());

            // 마커를 생성합니다
            var marker = new kakao.maps.Marker({
               position : markerPosition
            });

            // 마커가 지도 위에 표시되도록 설정합니다
            marker.setMap(map);
            var addr = $(t).find(".addR").val()
            var name = $(t).find(".campName").val()
            console.log($(t).find(".campName").val())
            console.log($(t).find(".addR").val())

            var content = '<div class="wrap">'
                  + '    <div class="info">'
                  + '        <div class="title">'
                  + name
                  + '        </div>' + '        <div class="body">'
                  + '            <div class="desc">'
                  + '                <div class="ellipsis">' + addr
                  + '</div>' + '            </div>' + '        </div>'
                  + '    </div>' + '</div>';

            // 마커 위에 커스텀오버레이를 표시합니다
            // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
            var overlay = new kakao.maps.CustomOverlay({
               content : content,
               map : map,
               position : marker.getPosition()
            });
            
            kakao.maps.event.addListener(marker, 'click', function() {
               overlay.setMap(map);
            });
            

         }

      </script>
   </div>
   <!-- list all end -->
   </div><!-- top view end -->
   <br><br><br><br><br><br><br><br><br><br><br><br><hr>
   <div class="select-all-list">
      
      <div class="select-selectbox">
      &nbsp
      <select name="select-addr" id="select-addr" class="select-i">
      <option value="0">지 역</option>
      <%
         for (int i = 0; i < geo.size(); i++) {
      %>
      <option value=<%=geo.get(i)%>><%=geo.get(i)%></option>
      <%
         }
      %>
      </select>
      &nbsp
      <select name="select-ind" id="select-ind" class="select-i">
         <option value="0">종류</option>
         <option value="일반야영장">일반야영장</option>
         <option value="카라반">카라반</option>
         <option value="글램핑">글램핑</option>
         <option value="자동차야영장">자동차야영장</option>
         
      </select>
      &nbsp
      <input type="text" name="select-text" id="select-text" class="select-i"/>
      <button  name="btn" id="btn" onclick="list_select();">검색</button>
      </div> <!--select-selectbox end -->
      <br><br><br><br><br>

      <div class="select-all-list-view" >
            <%
               for (int i = 0; i < camp.size(); i++) {
                  int j=(int)(Math.random()*20)+1;
            %>
            <div class="select-item" id="select-item">
               <div class="select-box1">
                  <img
                     src="/campimage/image<%=j %>.jpg"  width="250" height="130">
                     <input type="hidden" value=<%=j %> id="image">
               </div>
               <div class="select-box2">
                  <div class="title"><span class="title-1"><%=camp.get(i).getCAMP_NAME()%></span>&nbsp&nbsp&nbsp<span class="title-2"><<%=camp.get(i).getINDUTY() %>></span></div>
                  <br>
                  <div class="ADDRRR" style="font-size: 12px;"><%=camp.get(i).getADDR()%></div>
                  <br>
                  <div class="tel" style="font-size: 12px;"><span >대표 전화:</span><%=camp.get(i).getTEL() %></div>
               </div>
               <div class="select-box3">
                  <div class="select-box-sub">
                     <div class="facil" style="font-size: 11px;"><span class=facil-1">편의 시설: <%=camp.get(i).getFACIL() %></span></div>
                     <div class="intro"><p class="intro-1"><%=camp.get(i).getINTRO() %></p> </div>
                  </div>
               </div>
               <div class="select-box4">
                  <button type="button" class="select-rev-btn" onclick="page_move(<%=camp.get(i).getCAMP_ID() %>,<%=j %>);" >예약하기</button>
               </div>
            </div>
            <br><br><hr><br>
            <%
               }
            %>
            
      </div> <!-- select-all-list-view end -->
      <div class="select-item-new" id="select-item-new">
      </div> <!-- new end -->
   </div> <!-- test1 end -->
</body>
</html>