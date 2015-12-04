<!DOCTYPE html>
<html>
<head>
    <title>Marketplace</title>
</head>
<body>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h1>Manage Products</h1>

<p><font color="red"><c:out value="${message}" /></font></p>

<c:choose>
  <c:when test="${fn:length(products) == 0}">
    <p>No products available</p>
  </c:when>
  <c:otherwise>
    <table cellpadding="5" border=1>
      <tr valign="bottom">
        <td align="left"><b>Code</b></td>
        <td align="left"><b>Description</b></td>
    	<td align="left"><b>Price</b></td>
    	<td align="left"></td>
  	  </tr>
  
  	  <c:forEach var="item" items="${products}">
  		<tr valign="top">
    	  <td>${item.code}</td>
    	  <td>${item.description}</td>
    	  <td>${item.priceCurrencyFormat}</td>
          <td><a href="<c:url value='/admin/deleteproduct?productCode=${item.code}' />">Delete</a></td>
        </tr>
      </c:forEach>
    </table>
  </c:otherwise>
</c:choose>

<br />

<table cellpadding="5">
  <tr valign="top">
    <td>
      <form action="<c:url value='/admin/addproduct' />" method="get">
        <input type="submit" value="Add product">
      </form>
    </td>
    <td>
      <form action="<c:url value='/index.jsp' />" method="get">
        <input type="submit" value="Return to search page">
      </form>
    </td>
    <td>
      <form action="<c:url value='/admin/logout' />" method="get">
        <input type="submit" value="Logout and return to search page">
      </form>
    </td>
  </tr>
</table>

</body>
</html>