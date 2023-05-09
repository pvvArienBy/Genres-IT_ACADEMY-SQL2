package by.it_academy.jd2.Mk_JD2_98_23.dao.memory;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.VoteDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IVoteDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VoteMemoryDao implements IVoteDao {

    private final Map<Integer, VoteDTO> votes = new ConcurrentHashMap<>();


    @Override
    public List<VoteDTO> get() {
        return new ArrayList<>(this.votes.values());
    }

    @Override
    public VoteDTO get(int id) {
        return this.votes.get(id);
    }

    @Override
    public VoteDTO save(VoteDTO item) {
        this.votes.put(item.getId(),item);
        return item;
    }
}
