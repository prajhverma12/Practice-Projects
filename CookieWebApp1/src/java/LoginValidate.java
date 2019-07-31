/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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
public class LoginValidate extends HttpServlet {

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
            
            Cookie cookie = null;
            Cookie c[] = request.getCookies();                                   /* making a new cookie and requestiing to get cookies from the client and searching from rcpl name  cookie*/
            for(Cookie c1:c)
            {                                                                    /* if any cookie is found then storing that in cookie*/        
                if(c1.getName().equals("rcpl"))
                {                                                                /*if the coockie was found then it has to be validated so ot is sent to cookie validate through requestDispatcher*/
                    cookie=c1;
                    break;
                }
            }
            
            if(cookie!=null)
            {
                request.setAttribute("mycookie", cookie);
                request.getRequestDispatcher("cookieValidate").forward(request, response);
            }
            else
            {                                                                    /*if no cookie is existing before then first validate the login id and password and then make acookie if checkbox is checked otherwise*/
                String user_id = request.getParameter("username");
                String pass = request.getParameter("pass");
                String rm = request.getParameter("rm");                          /*if the checkbox is not clickednas login and password are validated then simply redirect them to home.jsp if the user id and password are incorrect then redirect to index.jsp */
                if(user_id.equals(pass))
                {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("uname", user_id);
                    if(rm.equals("on") && rm != null)
                    {
                        String value = user_id+":"+pass;                         /* if user wants to make cookie then we make a new cookie fr that client and  assign a value to it and set its age and send it to client via response*/
                        Cookie mycookie = new Cookie("rcpl", value);             /*The user id and password and stored in value by concatanating them with a : and at the time of extracting and validating the cookie we split them through : and validate*/    
                        mycookie.setMaxAge(60*60*24*30);
                        response.addCookie(mycookie);
                        response.sendRedirect("home.jsp");
                    }
                    else
                    {
                        response.sendRedirect("home.jsp");
                    }
                }
                else
                {
                    request.getRequestDispatcher("index.jsp").include(request, response);
                    out.println("Username and Password incorrect");
                }
            }
            
            
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
