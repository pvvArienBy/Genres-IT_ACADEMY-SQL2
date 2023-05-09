package by.it_academy.jd2.Mk_JD2_98_23.dao.memory;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.ArtistDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IArtistDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ArtistMemoryDao implements IArtistDao {

    private final Map<Integer, ArtistDTO> artists = new ConcurrentHashMap<>();


    public ArtistMemoryDao() {

        {
            ArtistDTO  dto = new ArtistDTO(1, "M. Jackson");
            artists.put(dto.getId(), dto);
        }
        {
            ArtistDTO  dto = new ArtistDTO(2, "John Lennon");
            artists.put(dto.getId(), dto);
        }
        {
            ArtistDTO  dto = new ArtistDTO(3, "ЯвДуше");
            artists.put(dto.getId(), dto);
        }
        {
            ArtistDTO  dto = new ArtistDTO(4, "R.Miles");
            artists.put(dto.getId(), dto);
        }
    }

    @Override
    public List<ArtistDTO> get() {
        return new ArrayList<>(this.artists.values());
    }

    @Override
    public ArtistDTO get(int id) {
        return this.artists.get(id);
    }

    @Override
    public  ArtistDTO save(ArtistDTO item) {
        this.artists.put(item.getId(),item);
        return item;
    }
}
