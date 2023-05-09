package by.it_academy.jd2.Mk_JD2_98_23.service;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.ArtistCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.ArtistDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IArtistDao;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IArtistService;

import java.util.List;

public class ArtistService implements IArtistService {

    private final IArtistDao artistDao;


    public ArtistService(IArtistDao artistDao) {
        this.artistDao = artistDao;
    }

    @Override
    public List<ArtistDTO> get() {
        return artistDao.get();
    }

    @Override
    public ArtistDTO get(int id) {
        return artistDao.get(id);
    }

    @Override
    public ArtistDTO save(ArtistCreateDTO item) {

        int maxCurrentId = this.get()
                .stream()
                .mapToInt(ArtistDTO::getId)
                .max()
                .orElseThrow();

        ArtistDTO dto = new ArtistDTO();
        dto.setId(maxCurrentId + 1);
        dto.setName(item.getName());

        return artistDao.save(dto);
    }
}
