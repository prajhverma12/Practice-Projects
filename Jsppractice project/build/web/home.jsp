<%-- 
    Document   : home
    Created on : Dec 10, 2016, 11:23:12 AM
    Author     : Prajwal
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <tr>
                <td>Login</td>
                <td>password</td>
                <td>type</td>
                
        <%
            Class.forName("com.mysql.jdbc.Driver");
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wtp16kiit","root","password");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("selsct * from login");
            while(rs.next())
            {
        %>
            <tr>
                <td><% out.println(rs.getString(1)); %></td>
                <td><% out.println(rs.getString(2)); %></td>
                <td><% out.println(rs.getString(3)); %></td>
            </tr>
            <%
                }
            %>
        </table>
                
    </body>
</html>
