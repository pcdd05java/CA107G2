<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.renting_house_information.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
Renting_House_Information_VO rhiVO = (Renting_House_Information_VO) request.getAttribute("rhiVO"); 
%>

<html>
<head>
<title>���θ�T - listOneRhi.jsp</title>

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
    img{
  	width:300px;
  	height:200px;
  }
</style>

</head>
<body bgcolor='white'>

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���θ�T- ListOneRhi.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

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
	</tr>
	<tr>
		<td><%=rhiVO.getRhi_no()%></td>
		<td><%=rhiVO.getRhi_content()%></td>
		<td>${(rhiVO.rhi_status == 1)? "�W�[" : "�U�[" } </td>
		<td><%=rhiVO.getRhi_date()%></td>
		<td class="pic"><img src="<%=request.getContextPath()%>/RentingHouseInformation/DBGifReader1?rhi_no=${rhiVO.rhi_no}"></td>
			<td class="pic"><img src="<%=request.getContextPath()%>/RentingHouseInformation/DBGifReader2?rhi_no=${rhiVO.rhi_no}"></td> 
			<td class="pic"><img src="<%=request.getContextPath()%>/RentingHouseInformation/DBGifReader3?rhi_no=${rhiVO.rhi_no}"></td>
			<td class="pic"><img src="<%=request.getContextPath()%>/RentingHouseInformation/DBGifReader4?rhi_no=${rhiVO.rhi_no}"></td>
			<td class="pic"><img src="<%=request.getContextPath()%>/RentingHouseInformation/DBGifReader5?rhi_no=${rhiVO.rhi_no}"></td>
	</tr>
</table>

</body>
</html>