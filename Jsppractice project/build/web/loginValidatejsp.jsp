<%-- 
    Document   : loginValidatejsp
    Created on : Dec 10, 2016, 11:18:20 AM
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
        <%
            String uname = request.getParameter("uname");
            String pass = request.getParameter("pass");
            if(uname.equals(pass))
            {
                response.sendRedirect("home.jsp");
            }
            else
            {
                response.sendRedirect("index.jsp");
            }
        %>
    </body>
</html>
