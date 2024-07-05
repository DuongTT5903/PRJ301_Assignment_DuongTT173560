<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
</head>
<body>
    <form action="login" method="POST">
        <label for="role">Select Role:</label>
        <select name="roleid" id="role">
            <c:forEach items="${requestScope.roles}" var="role">
                <option value="${role.roleid}">${role.rolename}</option>
            </c:forEach>
        </select><br><br>
        
        Username: <input type="text" name="username"><br>
        Password: <input type="password" name="password"><br>
        <input type="submit" value="Login">
    </form>
</body>
</html>
