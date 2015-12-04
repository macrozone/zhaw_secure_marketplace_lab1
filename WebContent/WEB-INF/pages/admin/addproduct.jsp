<!DOCTYPE html>
<html>
<head>
    <title>Marketplace</title>
</head>
<body>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Add Product</h1>

<p><font color="red"><c:out value="${message}" /></font></p>

<p>Please insert the following information to add a product:</p>

<form action="<c:url value='/admin/storeproduct' />" method="post">
  <table cellspacing="5" border="0">
    <tr>
      <td align="right">Product code:</td>
      <td><input type="text" name="productCode"></td>
    </tr>
    <tr>
      <td align="right">Description:</td>
      <td><input type="text" name="description"></td>
    </tr>
    <tr>
      <td align="right">Price:</td>
      <td><input type="text" name="price"></td>
    </tr>
    <tr>
      <td></td>
      <td><br><input type="submit" value="Store product"></td>
    </tr>
  </table>
</form>

<table cellpadding="5">
  <tr valign="top">
    <td>
      <form action="<c:url value='/admin/manageproducts' />" method="get">
        <input type="submit" value="Return to manage products page">
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