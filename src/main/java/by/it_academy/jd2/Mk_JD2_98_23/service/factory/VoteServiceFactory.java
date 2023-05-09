package by.it_academy.jd2.Mk_JD2_98_23.service.factory;

import by.it_academy.jd2.Mk_JD2_98_23.dao.memory.factory.VoteDaoFactory;
import by.it_academy.jd2.Mk_JD2_98_23.service.VoteService;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IVoteService;

public class VoteServiceFactory {

    private static volatile IVoteService instance;

    private VoteServiceFactory() {
    }

    public static IVoteService getInstance() {
        if (instance == null) {
            synchronized (VoteServiceFactory.class) {
                if (instance == null) {
                    instance = new VoteService(VoteDaoFactory.getInstance());
                }
            }

        }
        return instance;
    }
}
