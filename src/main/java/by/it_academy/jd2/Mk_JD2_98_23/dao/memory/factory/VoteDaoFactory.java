package by.it_academy.jd2.Mk_JD2_98_23.dao.memory.factory;

import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IVoteDao;
import by.it_academy.jd2.Mk_JD2_98_23.dao.memory.VoteMemoryDao;

public class VoteDaoFactory {
    private static volatile IVoteDao instance;

    private VoteDaoFactory() {
    }

    public static IVoteDao getInstance() {
        if (instance == null)  {
            synchronized (VoteDaoFactory.class) {
                if (instance == null) {
                    instance = new VoteMemoryDao();
                }
            }

        }
        return instance;
    }
}
