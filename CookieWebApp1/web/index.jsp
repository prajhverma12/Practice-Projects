<%-- 
    Document   : index
    Created on : Dec 11, 2016, 11:34:40 PM
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
            Cookie cookie = null;                                    /* making a new cookie and requestiing to get cookies from the client and searching from rcpl name  cookie*/   
            Cookie c[] = request.getCookies();
            for(Cookie c1:c)                                         /* if any cookie is found then storing that in cookie*/  
            {
                if(c1.getName().equals("rcpl"))                      /*if the coockie was found then it has to be validated so ot is sent to cookie validate through requestDispatcher*/  
                {
                    cookie = c1;
                    break ;
                }
            }
            if(cookie!=null)
            {
                request.setAttribute("mycookie", cookie);
                request.getRequestDispatcher("cookieValidate").forward(request, response);
            }
            else if(session.getAttribute("uname")!= null)
            {
                response.sendRedirect("home.jsp");
            }
        %>
        <center>
        <h1>Welcome</h1><br><br>
        <form action="LoginValidate">
            Username : <input type="text" name="username"/><br><br>
            Password : <input type="password" name="pass" /><br><br>
            <input type="checkbox" name="rm">Remember me<br><br>
            <input type="submit" name="submit" value="Login"/><br>
    
        </form>
        </center>
    </body>
</html>
