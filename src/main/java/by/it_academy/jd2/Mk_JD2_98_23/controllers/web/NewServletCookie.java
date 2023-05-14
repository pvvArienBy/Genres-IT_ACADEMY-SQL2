package by.it_academy.jd2.Mk_JD2_98_23.controllers.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


@WebServlet(urlPatterns = "/newServlet")
public class NewServletCookie extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");


        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        if (firstName != null && lastName != null) {
            Cookie firstNameCookie = new Cookie("firstName", firstName);
            Cookie lastNameCookie = new Cookie("lastName", lastName);
            firstNameCookie.setMaxAge(Math.toIntExact(TimeUnit.DAYS.toSeconds(1)));
            lastNameCookie.setMaxAge(Math.toIntExact(TimeUnit.DAYS.toSeconds(1)));
            resp.addCookie(firstNameCookie);
            resp.addCookie(lastNameCookie);
        }

        if (firstName == null || lastName == null) {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("firstName")) {
                        firstName = cookie.getValue();
                    } else if (cookie.getName().equals("lastName")) {
                        lastName = cookie.getValue();
                    }
                }
            }
        }

        if (firstName == null || lastName == null) {
            resp.getWriter().println("Ошибка - нет информации ни в  параметрах ни в куках");
            return;
        }
        resp.getWriter().println("Hello " + firstName + " " + lastName);
    }
}
