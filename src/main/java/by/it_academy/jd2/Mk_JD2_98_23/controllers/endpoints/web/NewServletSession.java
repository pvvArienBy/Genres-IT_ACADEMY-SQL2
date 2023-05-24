package by.it_academy.jd2.Mk_JD2_98_23.controllers.endpoints.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/newServletSession")
public class NewServletSession extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        HttpSession session = request.getSession();

        if (firstName != null && lastName != null) {
            session.setAttribute("firstName", firstName);
            session.setAttribute("lastName", lastName);
        }

        firstName = (String) session.getAttribute("firstName");
        lastName = (String) session.getAttribute("lastName");

        if (firstName == null || lastName == null) {
            response.getWriter().println("Ошибка - нет информации ни в параметрах ни в сессии");
            return;
        }
        response.getWriter().println("Hello " + firstName + " " + lastName);
    }
}