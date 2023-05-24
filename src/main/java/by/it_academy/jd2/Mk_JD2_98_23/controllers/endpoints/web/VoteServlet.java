package by.it_academy.jd2.Mk_JD2_98_23.controllers.endpoints.web;


import by.it_academy.jd2.Mk_JD2_98_23.core.dto.ArtistDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.GenreDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.VoteCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IArtistService;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IGenreService;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IVoteService;
import by.it_academy.jd2.Mk_JD2_98_23.service.factory.ArtistServiceFactory;
import by.it_academy.jd2.Mk_JD2_98_23.service.factory.GenreServiceFactory;
import by.it_academy.jd2.Mk_JD2_98_23.service.factory.VoteServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


@WebServlet(urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {

    private static final String ARTIST_PARAM_NAME = "artist";
    private static final String GENRE_PARAM_NAME = "genre";
    private static final String ABOUT_PARAM_NAME = "about";

    private final IArtistService artistService;
    private final IGenreService genreService;
    private final IVoteService voteService;

    public VoteServlet() {
        this.artistService = ArtistServiceFactory.getInstance();
        this.genreService = GenreServiceFactory.getInstance();
        this.voteService = (VoteServiceFactory.getInstance());
        this.voteService.setService(this.artistService, this.genreService);
    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<GenreDTO> genres = genreService.get();
        List<ArtistDTO> artists = artistService.get();



        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        writer.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Voting Form</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Voting Form</h1>\n" +
                "<form action=\"/Mk-JD2-98-23-1.2.2-Testnewmvn/vote\" method=\"POST\">\n" +
                "    <label for=\"artist\">Best Artist:</label>\n" +
                "    <select name=\"artist\" id=\"artist\" required>\n");

        for (ArtistDTO artist : artists) {
            writer.write("        <option value=\"" + artist.getId() + "\">" + artist.getName() + " </option>\n");

        }

        writer.write("    </select>\n" +
                "    <br><br>\n" +
                "\n" +
                "    <label>Your Favorite Genres (Select 3-5 options):</label>\n" +
                "    <br>\n");

        for (GenreDTO genre : genres) {
            writer.write("    <input type=\"checkbox\" name=\"genre\" id=\"genre\" value=\"" + genre.getId() + "\">\n" +
                    "    <label for=\"genre\">" + genre.getName() + "</label>\n" +
                    "    <br>\n");
        }
        writer.write("\n" +
                "    <label for=\"text\">Short Text About You:</label>\n" +
                "    <br>\n" +
                "    <textarea name=\"about\" id=\"about\" rows=\"4\" cols=\"50\" required></textarea>\n" +
                "    <br><br>\n" +
                "\n" +
                "    <input type=\"submit\" value=\"Submit Vote\">\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        /**
         * Settings Encoding
         **/
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");


        Map<String, String[]> parameterMap = req.getParameterMap();
        PrintWriter writer = resp.getWriter();


        String artistsRaw = req.getParameter(ARTIST_PARAM_NAME);
        Integer artist = Integer.parseInt(artistsRaw);

//        if (artistsRaw.length > 1) {
//             throw new IllegalArgumentException("Слишком много артистов");
//        }

//        Integer artist = null;
//        if (artistsRaw.length == 1) {
//            artist = Integer.parseInt(artistsRaw[0]);
//        }

        String[] genresRaw = parameterMap.get(GENRE_PARAM_NAME);

        Integer[] genres = new Integer[genresRaw.length];

        for (int i = 0; i < genresRaw.length; i++) {
            genres[i] = Integer.parseInt(genresRaw[i]);
        }


        if (genres == null || genres.length < 3 || genres.length > 5) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Please select 3-5 favorite genres.");
            return;
        }


        String[] aboutRaw = parameterMap.get(ABOUT_PARAM_NAME);

        if (aboutRaw.length > 1) {
            // throw new IllegalArgumentException("Слишком много о себе");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Слишком много о себе");
        }

        String about = null;
        if (aboutRaw.length == 1) {
            about = aboutRaw[0];
        }


        VoteCreateDTO voteCreateDTO = new VoteCreateDTO(artist, genres, about);
        voteService.save(voteCreateDTO);


        resp.setContentType("text/html");
        writer.write("<html>");
        writer.write("<head><title>Results</title></head>");
        writer.write("<body>");

        // Display performers sorted by votes
        writer.write("<h2>Top Performers</h2>");
        writer.write("<ul>");
        voteService.sortArtist().forEach((k, v) -> {
            writer.write("<li>");
            writer.write(artistService.get(k).getName() + ": " + v.toString());
            writer.write(" votes</li>");
        });

        writer.write("</ul>");

        // Display favorite genres sorted by votes
        writer.write("<h2>Favorite Genres</h2>");
        writer.write("<ul>");

        voteService.sortGenres().forEach((k, v) -> {
            writer.write("<li>");
            writer.write(genreService.get(k).getName() + ": " + v.toString());
            writer.write(" votes</li>");
        });
        writer.write("</ul>");
        writer.write("<h2>Voting Comments</h2>");
        writer.write("<ul>");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
        voteService.sortAbout().forEach((k, v) -> {
            writer.write("<li>" + v + " "  + "<p style=\"color: blue; font-size: 9px;\">" + k.format(dateTimeFormatter) + "</p>" + "  </li>");
            writer.write(" __________________________________ ");
            writer.write(" ");
        });

        writer.write("</ul>");
        writer.write("</body>");
    }
}
