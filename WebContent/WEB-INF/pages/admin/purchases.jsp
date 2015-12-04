<!DOCTYPE html>
<html>
<head>
    <title>Marketplace</title>
</head>
<body>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h1>Purchases</h1>

<p><font color="red"><c:out value="${message}" /></font></p>

<c:choose>
  <c:when test="${fn:length(purchases) == 0}">
    <p>No purchases available</p>
  </c:when>
  <c:otherwise>
    <table cellpadding="5" border=1>
      <tr valign="bottom">
        <td align="left"><b>First Name</b></td>
    	<td align="left"><b>Last Name</b></td>
    	<td align="left"><b>CC Number</b></td>
    	<td align="left"><b>Total Price</b></td>
    	<c:if test="${completeAllowed}">
    	  <td align="left"></td>
    	</c:if>
   	  </tr>
  
  	  <c:forEach var="item" items="${purchases}">
  		<tr valign="top">
    	  <td><c:out value="${item.firstName}" /></td>
    	  <td><c:out value="${item.lastName}" /></td>
   	      <td><c:out value="${item.ccNumber}" /></td>
   	      <td><c:out value="${item.totalPriceCurrencyFormat}" /></td>
   	      <c:if test="${completeAllowed}">
   	        <td><c:url var="url" value='/admin/completepurchase?purchaseId=${item.id}&csrftoken=${csrftoken}' />
            <a href="<c:out value="${url}" />">Complete Purchase</a></td>
          </c:if>
        </tr>
      </c:forEach>
    </table>
  </c:otherwise>
</c:choose>

<br />

<table cellpadding="5">
  <tr valign="top">
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