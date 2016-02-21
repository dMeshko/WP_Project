<%--
  Created by IntelliJ IDEA.
  User: Darko
  Date: 2/19/2016
  Time: 11:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h2>Hello?</h2>
<!--String name, String surname, Date birthDate, String email, String username, String password, String imageURL, Boolean isAdmin-->
  <form action="./" method = "POST" enctype="multipart/form-data">
    <input name = "name" value = "" placeholder="name" /><br />
    <input name = "surname" value = "" placeholder="name" /><br />
    <input name = "birthDate" value = "" placeholder="name" /><br />
    <input name = "email" value = "" placeholder="name" /><br />
    <input name = "username" value = "" placeholder="name" /><br />
    <input name = "password" value = "" placeholder="name" /><br />
    <input name = "imageURL" value = "" placeholder="name" /><br />
    <input type = "file" name = "file" />
    <input type="submit">
  </form>
</body>
</html>
