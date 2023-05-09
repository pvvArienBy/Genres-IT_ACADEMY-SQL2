package by.it_academy.jd2.Mk_JD2_98_23.service.factory;

import by.it_academy.jd2.Mk_JD2_98_23.dao.memory.factory.ArtistDaoFactory;
import by.it_academy.jd2.Mk_JD2_98_23.service.ArtistService;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IArtistService;

public class ArtistServiceFactory {
    private static volatile  IArtistService instance;

    private ArtistServiceFactory() {
    }

    public static IArtistService getInstance() {
        if (instance == null)  {
            synchronized (ArtistServiceFactory.class) {
                if (instance == null) {
                    instance =new ArtistService(ArtistDaoFactory.getInstance());
                }
            }

        }
        return instance;
    }
}
