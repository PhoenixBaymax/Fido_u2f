package fr.neowave.servlets;

import fr.neowave.forms.U2fAuthenticationForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/u2fAuthenticate")
public class U2FAuthenticationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("username") == null || request.getSession().getAttribute("username").equals("admin")) {
            response.sendRedirect(request.getContextPath().concat("/404"));
        }else{
            this.getServletContext().getRequestDispatcher("/WEB-INF/user/u2fAuthentication.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        U2fAuthenticationForm u2fAuthenticationForm = new U2fAuthenticationForm();
        u2fAuthenticationForm.doU2fAuthentication(request);
        if(u2fAuthenticationForm.getErrors().isEmpty()){

            response.sendRedirect(request.getContextPath().concat("/"));
        }
        else{
            request.setAttribute("errors", u2fAuthenticationForm.getErrors());
            this.getServletContext().getRequestDispatcher("/WEB-INF/user/passwordAuthentication.jsp").forward(request, response);
        }


    }
}