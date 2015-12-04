<!DOCTYPE html>
<html>
<head>
    <title>Marketplace</title>
</head>
<body>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Your cart</h1>

<p><font color="red"><c:out value="${message}" /></font></p>

<c:choose>
  <c:when test="${(cart == null) || (cart.count == 0)}">
    <p>Your cart is empty</p>
  </c:when>
  <c:otherwise>
    <table border="1" cellpadding="5">
      <tr>
        <th>Description</th>
        <th>Price</th>
      </tr>

      <c:forEach var="item" items="${cart.items}">
        <tr valign="top">
          <td><c:out value="${item.product.description}" /></td>
          <td><c:out value="${item.product.priceCurrencyFormat}" /></td>
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
      <form action="<c:url value='/checkout' />" method="get">
        <input type="submit" value="Checkout">
      </form>
    </td>
  </tr>
</table>

</body>
</html>