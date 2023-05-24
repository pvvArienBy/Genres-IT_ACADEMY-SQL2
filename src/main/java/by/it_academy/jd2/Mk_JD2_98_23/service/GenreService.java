package by.it_academy.jd2.Mk_JD2_98_23.service;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.GenreCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.GenreDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IGenreDao;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IGenreService;

import java.util.List;

public class GenreService implements IGenreService {

    private final IGenreDao genreDao;

    public GenreService(IGenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public List<GenreDTO> get() {
        return genreDao.get();
    }

    @Override
    public GenreDTO get(int id) {
        return genreDao.get(id);
    }

    @Override
    public GenreDTO save(GenreCreateDTO item) {
        GenreDTO dto = new GenreDTO();
        dto.setName(item.getName());

        return genreDao.save(dto);

    }
}
