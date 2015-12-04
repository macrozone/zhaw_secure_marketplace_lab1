<!DOCTYPE html>
<html>
<head>
    <title>Marketplace</title>
</head>
<body>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h1>Products list</h1>

<p>You searched for: <c:out value="${param.searchString}" /></p>

<c:choose>
  <c:when test="${fn:length(products) == 0}">
    <p>No products match your search</p>
  </c:when>
  <c:otherwise>
    <table cellpadding="5" border=1>
      <tr valign="bottom">
        <td align="left"><b>Description</b></td>
    	<td align="left"><b>Price</b></td>
    	<td align="left"></td>
  	  </tr>
  
  	  <c:forEach var="item" items="${products}">
  		<tr valign="top">
    	  <td><c:out value="${item.description}" /></td>
    	  <td><c:out value="${item.priceCurrencyFormat}" /></td>
          <td><c:url var="url" value='/cart?productCode=${item.code}' />
              <a href="<c:out value="${url}" />">Add to Cart</a></td>
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
      <form action="<c:url value='/cart' />" method="get">
        <input type="submit" value="Show cart">
      </form>
    </td>
    <td>
      <form action="<c:url value='/checkout' />" method="get">
        <input type="submit" value="Checkout">
      </form>
    </td>
  </tr>
</table>

</body>
</html>