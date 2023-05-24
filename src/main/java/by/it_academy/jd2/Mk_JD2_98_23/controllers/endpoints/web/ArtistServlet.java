package by.it_academy.jd2.Mk_JD2_98_23.controllers.endpoints.web;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.ArtistCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.ArtistDTO;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IArtistService;
import by.it_academy.jd2.Mk_JD2_98_23.service.factory.ArtistServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(urlPatterns = "/artist")
public class ArtistServlet extends HttpServlet {
    private final IArtistService artistService;


    public ArtistServlet() {
        this.artistService = ArtistServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");


        PrintWriter writer = resp.getWriter();
        List<ArtistDTO> artistDTOS = this.artistService.get();


        artistDTOS.forEach(g -> {
            writer.write(g.getId() + " - " + g.getName() + "</br>");
        });
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");


        String name = req.getParameter("name");

        ArtistCreateDTO dto =  new ArtistCreateDTO(name);

        this.artistService.save(dto);

    }
}
