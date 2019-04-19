<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>CA107G2 RentingHouseInformation: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>CA107G2 RentingHouseInformation: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for CA107G2 RentingHouseInformation: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllRhi.jsp'>List</a> all RentingHouseInformation.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="rhi.do">
        <b>輸入租屋資訊編號 (如RH000001):</b>
        <input type="text" name="rhi_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="rhiSvc" scope="page" class="com.renting_house_information.model.Renting_House_Information_Service" />
   
  <li>
     <FORM METHOD="post" ACTION="rhi.do" >
       <b>選擇租屋資訊編號:</b>
       <select size="1" name="rhi_no">
         <c:forEach var="rhiVO" items="${rhiSvc.all}" > 
          <option value="${rhiVO.rhi_no}">${rhiVO.rhi_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
</ul>


<h3>租屋資訊管理</h3>

<ul>
  <li><a href='addRhi.jsp'>Add</a> a new RentingHouseInformation.</li>
</ul>

</body>
</html>