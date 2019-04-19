<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.renting_house_information.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	Renting_House_Information_Service rhiSvc = new Renting_House_Information_Service();
    List<Renting_House_Information_VO> list = rhiSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ����θ�T - listAllRhi.jsp</title>

<style>
  table#table-1 {
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
  
  img{
  	width:300px;
  	height:200px;
  }
</style>

<style>
  table {
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����θ�T - listAllRhi.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>���θ�T�s��</th>
		<th>���θ�T���e</th>
		<th>���θ�T���A</th>
		<th>���θ�T�Z�n���</th>
		<th>�Ϥ�1</th>
		<th>�Ϥ�2</th>
		<th>�Ϥ�3</th>
		<th>�Ϥ�4</th>
		<th>�Ϥ�5</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="rhiVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${rhiVO.rhi_no}</td>
			<td>${rhiVO.rhi_content}</td>
			<td>${(rhiVO.rhi_status == 1)? "�W�[" : "�U�[" } </td>
			<td>${rhiVO.rhi_date}</td>
			<td class="pic"><img src="<%=request.getContextPath()%>/RentingHouseInformation/DBGifReader1?rhi_no=${rhiVO.rhi_no}"></td>
			<td class="pic"><img src="<%=request.getContextPath()%>/RentingHouseInformation/DBGifReader2?rhi_no=${rhiVO.rhi_no}"></td> 
			<td class="pic"><img src="<%=request.getContextPath()%>/RentingHouseInformation/DBGifReader3?rhi_no=${rhiVO.rhi_no}"></td>
			<td class="pic"><img src="<%=request.getContextPath()%>/RentingHouseInformation/DBGifReader4?rhi_no=${rhiVO.rhi_no}"></td>
			<td class="pic"><img src="<%=request.getContextPath()%>/RentingHouseInformation/DBGifReader5?rhi_no=${rhiVO.rhi_no}"></td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/RentingHouseInformation/rhi.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="rhi_no"  value="${rhiVO.rhi_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/RentingHouseInformation/rhi.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="rhi_no"  value="${rhiVO.rhi_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>