package by.it_academy.jd2.Mk_JD2_98_23.controllers.endpoints.web;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.GenreCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.GenreDTO;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IGenreService;
import by.it_academy.jd2.Mk_JD2_98_23.service.factory.GenreServiceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/genre")
public class GenreServlet extends HttpServlet {

    private final IGenreService genreService;
    private ObjectMapper objectMapper;


    public GenreServlet() {
        this.genreService = GenreServiceFactory.getInstance();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");


        PrintWriter writer = resp.getWriter();

        List<GenreDTO> genreDTOS = this.genreService.get();

        writer.write(objectMapper.writeValueAsString(genreDTOS));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");


        // String name = req.getParameter("name");

        GenreCreateDTO dto = this.objectMapper.readValue(req.getInputStream(), GenreCreateDTO.class);

        this.genreService.save(dto);

    }

}
