<%-- 
    Document   : home
    Created on : Dec 13, 2016, 6:17:09 AM
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
        <Center>
        <h1>
            Welcome <% out.println(session.getAttribute("uname")); %>
        </h1><br><br>
        <% 
            Cookie c[] = request.getCookies();
            for(Cookie c1:c)
            {
                out.println(c1.getName()+""+c1.getValue());
            }
        %>
        <br><br>
        <a href="Logout">Logout</a>
        </center>
    </body>
</html>
