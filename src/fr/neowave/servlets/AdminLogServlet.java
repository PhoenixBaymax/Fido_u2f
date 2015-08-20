package fr.neowave.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/log")
public class AdminLogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!request.getSession().getAttribute("username").equals("admin")){
            response.sendRedirect("/404");
        }
        else{
            if(!request.getSession().getAttribute("hasKey").equals("true")) {
                response.sendRedirect("/adminU2fRegister?from=".concat(request.getRequestURL().toString()));
            } else{
                this.getServletContext().getRequestDispatcher("/WEB-INF/admin/log.jsp").forward(request,response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!request.getSession().getAttribute("username").equals("admin")){
            response.sendRedirect("/404");
        } else{
            if(!request.getSession().getAttribute("hasKey").equals("true")) {
                response.sendRedirect("/adminU2fRegister");
            } else{
                this.getServletContext().getRequestDispatcher("/WEB-INF/admin/log.jsp").forward(request,response);
            }
        }
    }
}
