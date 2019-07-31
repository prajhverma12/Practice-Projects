/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Prajwal
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
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
            else
            {
                String user_id = request.getParameter("user");
                String pass = request.getParameter("pass");
                String rm = request.getParameter("rm");
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wtp16kiit","root","password");
                PreparedStatement pst = con.prepareStatement("select * from login where login_id=? and password=?");
                pst.setString(1, user_id);
                pst.setString(2, pass);
                ResultSet rs = pst.executeQuery();
                if(rs.next())
                {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("uname", user_id);
                    //response.sendRedirect("home.jsp");
                    if(rm != null && rm.equals("on"))
                    {
                        System.out.println("heloooooooooooooooooooooooooo");
                        String value_login = user_id+":"+pass;
                        Cookie mycookie = new Cookie("rcpl", value_login);
                        mycookie.setMaxAge(60*60*24*30);
                        response.addCookie(mycookie);
                        
                    }
                    response.sendRedirect("home.jsp");
                }
                else
                {
                    request.getRequestDispatcher("index.jsp").include(request, response);
                    out.println("Username or password incorrect");
                }
            }
            out.println("</body>");
            out.println("</html>");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
