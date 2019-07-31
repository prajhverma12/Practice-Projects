<%-- 
    Document   : index
    Created on : Dec 13, 2016, 5:24:09 AM
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
            Cookie cookie = null ;
            Cookie c[] = request.getCookies();
            for(Cookie c1:c)
            {
                if(c1.getName().equals("rcpl"))
                {
                    cookie = c1 ;
                    break;
                }
            }
            if(cookie!=null)
            {
                request.setAttribute("mycookie", cookie);
                request.getRequestDispatcher("CookieValidate").forward(request, response);
            }
            else if(session.getAttribute("uname")!= null)
            {
                response.sendRedirect("home.jsp");
            }
        %>
        <center>
        <h1>Welcome</h1>
        <br>
        <br>
        <form action="LoginServlet">
            <input type="text" name="user" placeholder="Enter your Login ID Here."/><br><br>
            <input type="password" name="pass" placeholder="Enter your Login ID Here."/><br><br>
            <input type="checkbox" name="rm"/>Remember me <br><br>
            <input type="submit" name="submit" value="Login"/><br><br>
        </form>
        </center>
    </body>
</html>
