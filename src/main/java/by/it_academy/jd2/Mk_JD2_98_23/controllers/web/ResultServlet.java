package by.it_academy.jd2.Mk_JD2_98_23.controllers.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@WebServlet("/display_results")
public class ResultServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        VotingData votingData = ((VoteServlet) getServletContext().getAttribute("votingServlet")).getVotingData();
//
//        resp.setContentType("text/html");
//        PrintWriter out = resp.getWriter();
//
//        out.println("<html>");
//        out.println("<head><title>Results</title></head>");
//        out.println("<body>");
//
//        // Display performers sorted by votes
//        out.println("<h2>Top Performers</h2>");
//        out.println("<ul>");
//        for (Map.Entry<String, Integer> entry : votingData.getSortedArtistResults()) {
//            out.println("<li>" + entry.getKey() + ": " + entry.getValue() + " votes</li>");
//        }
//        out.println("</ul>");
//
//        // Display favorite genres sorted by votes
//        out.println("<h2>Favorite Genres</h2>");
//        out.println("<ul>");
//        for (Map.Entry<String, Integer> entry : votingData.getSortedGenresResults()) {
//            out.println("<li>" + entry.getKey() + ": "  + entry.getValue() + " votes</li>");
//        }
//        out.println("</ul>");
//
//        // Display favorite usrText sorted by date
//        out.println("<h2>Voting Comments</h2>");
//        out.println("<ul>");
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
//        for (CommentsDTO user : votingData.getAllComments()) {
//            String formattedDate = user.getDateTime().format(dateTimeFormatter);
//            out.println("<li>" + user.getText() + " "  + "<p style=\"color: blue; font-size: 9px;\">" + formattedDate + "</p>" + "  </li>");
//            out.println(" __________________________________ ");
//            out.println(" ");
//        }
//        out.println("</ul>");
//
//        out.println("</body>");
//        out.println("/<html>");

    }
}