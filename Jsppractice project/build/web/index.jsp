<%-- 
    Document   : index
    Created on : Dec 10, 2016, 11:11:16 AM
    Author     : Prajwal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>Welcome</h1><br><br>
        <form action="loginValidatejsp.jsp" method="post">
            Username : <input type="text" name="uname"><br><br>
            PAssword : <input type="password" name="pass"><br><br>
            <input type="submit" name="submit" value="Login"><br><br>
        </form>
    </body>
</html>
