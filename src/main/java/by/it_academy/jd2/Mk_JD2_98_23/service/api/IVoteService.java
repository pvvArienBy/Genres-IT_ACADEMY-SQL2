package by.it_academy.jd2.Mk_JD2_98_23.service.api;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.VoteCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.VoteDTO;

import java.time.LocalDateTime;
import java.util.Map;

public interface IVoteService extends ICRUDService<VoteDTO, VoteCreateDTO>{

     void setService(IArtistService artistService, IGenreService genreService);
     Map<Integer, Integer> sortArtist();
     Map<Integer, Integer> sortGenres();
     Map<LocalDateTime, String> sortAbout();

}
