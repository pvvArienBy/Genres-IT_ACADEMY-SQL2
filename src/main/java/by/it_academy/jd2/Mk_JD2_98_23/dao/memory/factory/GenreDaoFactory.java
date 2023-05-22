package by.it_academy.jd2.Mk_JD2_98_23.dao.memory.factory;

import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IGenreDao;
import by.it_academy.jd2.Mk_JD2_98_23.dao.db.GenreJDBCDao;

public class GenreDaoFactory {
    private static volatile  IGenreDao instance;

    private GenreDaoFactory() {
    }

    public static IGenreDao getInstance() {
        if (instance == null)  {
            synchronized (GenreDaoFactory.class) {
                if (instance == null) {
                    instance = new GenreJDBCDao();
                }
            }

        }
        return instance;
    }
}
