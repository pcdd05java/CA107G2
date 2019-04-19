<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.renting_house_information.model.*"%>

<%
Renting_House_Information_VO rhiVO = (Renting_House_Information_VO) request.getAttribute("rhiVO");
%>
<%= rhiVO == null %> 
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���θ�T�s�W - addRhi.jsp</title>

<style>
  #table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  .form {
	width: 500px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
	.prev {
		width:300px;
		height:200px;
	}
	.tprev{
	width:100%; 
	}
	
</style>

</head>
<body bgcolor='white'>

<table class="form" id="table-1">
	<tr><td>
		 <h3>���θ�T�s�W - addRhi.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="rhi.do" name="form1"  enctype="multipart/form-data">


<table class="form">
	<tr>
		<td>���θ�T���e�G</td>
		<td><input type="text" name="rhi_content" size="45" 
			 value="<%= (rhiVO==null)? "�п�J���e" : rhiVO.getRhi_content()%>" /></td>
	</tr>
	<tr>
		<td>���θ�T���A�G</td>
		<td><select size="1" name="rhi_status">
			<option value="0" selected>�U�[</option>
			<option value="1">�W�[</option>
		</select></td>
	</tr>
	<tr>
		<td>���θ�T�Z�n����G</td>
		<td><input name="rhi_date" id="f_date1" type="text" value="${java.sql.Date.valueOf(System.currentTimeMillis().trim())}" disabled="true"></td>
	</tr>
	<tr>
		<td>���ιϤ��W��1�G</td>
		<td><input type="file" name="rhi_p1" size="45" accept="image/*"/></td>
	</tr>
		<tr>
		<td>���ιϤ��W��2�G</td>
		<td><input type="file" name="rhi_p2" size="45" accept="image/*"/></td>
	</tr>
		<tr>
		<td>���ιϤ��W��3�G</td>
		<td><input type="file" name="rhi_p3" size="45" accept="image/*"/></td>
	</tr>
		<tr>
		<td>���ιϤ��W��4�G</td>
		<td><input type="file" name="rhi_p4" size="45" accept="image/*"/></td>
	</tr>
		<tr>
		<td>���ιϤ��W��5�G</td>
		<td><input type="file" name="rhi_p5" size="45" accept="image/*" /></td>
	</tr>
	</table>
	
	<table class="tprev">
	<tr><td>
	<div id="div1">
		</div></td>
	<td><div id="div2">
		</div></td>
	<td><div id="div3">
		</div></td>
	<td><div id="div4">
		</div></td>
	<td><div id="div5">
		</div>
	</td></tr>
	</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>

</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<% 
  java.sql.Date rhi_date = null;
  try {
	  rhi_date = rhiVO.getRhi_date();
   } catch (Exception e) {
	   rhi_date = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=rhi_date%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
        
        
   
        // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

        //      1.�H�U���Y�@�Ѥ��e������L�k���
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.�H�U���Y�@�Ѥ��᪺����L�k���
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
<script type="text/javascript">

	 document.getElementsByName("rhi_p1")[0].addEventListener("change", show1, false);
	 function show1(){
	 var div1 = document.getElementById("div1");
	 var upfile1 = document.getElementsByName("rhi_p1")[0].files[0];
	 var reader = new FileReader();
	 reader.addEventListener("load", function(){ div1.innerHTML = '<img class="prev" src="'+ reader.result + '">';}, false)
	 if(upfile1){
	 reader.readAsDataURL(upfile1);}
	 }


	 document.getElementsByName("rhi_p2")[0].addEventListener("change", show2, false);
	 function show2(){
	 var div2 = document.getElementById("div2");
	 var upfile2 = document.getElementsByName("rhi_p2")[0].files[0];
	 var reader = new FileReader();
	 reader.addEventListener("load", function(){ div2.innerHTML = '<img class="prev" src="'+ reader.result + '">';}, false)
	 if(upfile2){
	 reader.readAsDataURL(upfile2);}
	 }

	 document.getElementsByName("rhi_p3")[0].addEventListener("change", show3, false);
	 function show3(){
	 var div3 = document.getElementById("div3");
	 var upfile3 = document.getElementsByName("rhi_p3")[0].files[0];
	 var reader = new FileReader();
	 reader.addEventListener("load", function(){ div3.innerHTML = '<img class="prev" src="'+ reader.result + '">';}, false)
	 if(upfile3){
	 reader.readAsDataURL(upfile3);}
	 }

	 document.getElementsByName("rhi_p4")[0].addEventListener("change", show4, false);
	 function show4(){
	 var div4 = document.getElementById("div4");
	 var upfile4 = document.getElementsByName("rhi_p4")[0].files[0];
	 var reader = new FileReader();
	 reader.addEventListener("load", function(){ div4.innerHTML = '<img class="prev" src="'+ reader.result + '">';}, false)
	 if(upfile4){
	 reader.readAsDataURL(upfile4);}
	 }

	 	 document.getElementsByName("rhi_p5")[0].addEventListener("change", show5, false);
	 function show5(){
	 var div5 = document.getElementById("div5");
	 var upfile5 = document.getElementsByName("rhi_p5")[0].files[0];
	 var reader = new FileReader();
	 reader.addEventListener("load", function(){ div5.innerHTML = '<img class="prev" src="'+ reader.result + '">';}, false)
	 if(upfile5){
	 reader.readAsDataURL(upfile5);}
	 }

</script>
</html>