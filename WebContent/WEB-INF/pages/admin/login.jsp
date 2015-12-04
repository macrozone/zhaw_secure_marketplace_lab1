<!DOCTYPE html>
<html>
<head>
    <title>Marketplace</title>
</head>
<body>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Admin Login Form</h1>

<p>Please enter your username and password to continue.</p>

<form action="<c:url value='/admin/login' />" method="post">
  <table cellspacing="5" border="0">
    <tr>
      <td align="right">Username</td>
      <td><input type="text" name="username"></td>
    </tr>
    <tr>
      <td align="right">Password</td>
      <td><input type="password" name="password"></td>
    </tr>
    <tr>
      <td><input type="submit" value="Login"></td>
    </tr>
  </table>
</form>

</body>
</html>
