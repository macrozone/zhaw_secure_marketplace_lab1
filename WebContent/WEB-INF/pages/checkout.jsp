<!DOCTYPE html>
<html>
<head>
    <title>Marketplace</title>
</head>
<body>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Checkout</h1>

<p><font color="red"><c:out value="${message}" /></font></p>

<p>Please insert the following information to complete your purchase:</p>

<form action="<c:url value='/purchase' />" method="post">
  <table cellspacing="5" border="0">
    <tr>
      <td align="right">First name:</td>
      <td><input type="text" name="firstName"></td>
    </tr>
    <tr>
      <td align="right">Last name:</td>
      <td><input type="text" name="lastName"></td>
    </tr>
    <tr>
      <td align="right">Credit card number:</td>
      <td><input type="text" name="ccNumber"></td>
    </tr>
    <tr>
      <td></td>
      <td><br><input type="submit" value="Purchase"></td>
    </tr>
  </table>
</form>

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
  </tr>
</table>

</body>
</html>