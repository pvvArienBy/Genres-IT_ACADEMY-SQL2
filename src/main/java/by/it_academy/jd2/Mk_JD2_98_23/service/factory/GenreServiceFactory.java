package by.it_academy.jd2.Mk_JD2_98_23.service.factory;

import by.it_academy.jd2.Mk_JD2_98_23.dao.memory.factory.GenreDaoFactory;
import by.it_academy.jd2.Mk_JD2_98_23.service.GenreService;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IGenreService;

public class GenreServiceFactory {
    private static volatile IGenreService instance;

    private GenreServiceFactory() {
    }

    public static IGenreService getInstance() {
        if (instance == null) {
            synchronized (GenreServiceFactory.class) {
                if (instance == null) {
                    instance = new GenreService(GenreDaoFactory.getInstance());
                }
            }

        }
        return instance;
    }
}
