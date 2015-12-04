<!DOCTYPE html>
<html>
<head>
  <title>Marketplace</title>
</head>
<body>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Welcome to the Marketplace</h1>

<p><font color="red"><c:out value="${message}" /></font></p>

<p>To search for products, enter any search string below and click the Search button</p>

<form action="<c:url value='/searchProducts' />" method="get">
  <table cellspacing="5" border="0">
    <tr>
      <td><input type="text" name="searchString"></td>
      <td><input type="submit" value="Search"></td>
    </tr>
  </table>
</form>

<br />

<table cellpadding="5">
  <tr valign="top">
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
    <td>
      <form action="<c:url value='/admin/login' />" method="get">
        <input type="submit" value="Admin area">
      </form>
    </td>
  </tr>
</table>

</body>
</html>