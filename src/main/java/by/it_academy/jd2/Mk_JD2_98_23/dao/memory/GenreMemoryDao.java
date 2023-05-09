package by.it_academy.jd2.Mk_JD2_98_23.dao.memory;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.GenreDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IGenreDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GenreMemoryDao implements IGenreDao {

    private final Map<Integer, GenreDTO> genres = new ConcurrentHashMap<>();

    public GenreMemoryDao() {

        {
            GenreDTO dto = new GenreDTO(1, "Trance");
            genres.put(dto.getId(), dto);
        }
        {
            GenreDTO dto = new GenreDTO(2, "Reggae");
            genres.put(dto.getId(), dto);
        }
        {
            GenreDTO dto = new GenreDTO(3, "DeepHouse");
            genres.put(dto.getId(), dto);
        }
        {
            GenreDTO dto = new GenreDTO(4, "House");
            genres.put(dto.getId(), dto);
        }
        {
            GenreDTO dto = new GenreDTO(5, "Country");
            genres.put(dto.getId(), dto);
        }
        {
            GenreDTO dto = new GenreDTO(6, "Break-beat");
            genres.put(dto.getId(), dto);
        }
        {
            GenreDTO dto = new GenreDTO(7, "Heavy-Metal");
            genres.put(dto.getId(), dto);
        }
        {
            GenreDTO dto = new GenreDTO(8, "Punk");
            genres.put(dto.getId(), dto);
        }
        {
            GenreDTO dto = new GenreDTO(9, "Rock");
            genres.put(dto.getId(), dto);
        }
        {
            GenreDTO dto = new GenreDTO(10, "Jazz");
            genres.put(dto.getId(), dto);
        }
    }


    @Override
    public List<GenreDTO> get() {
        return new ArrayList<>(this.genres.values());
    }

    @Override
    public GenreDTO get(int id) {
        return this.genres.get(id);
    }

    @Override
    public  GenreDTO save(GenreDTO item) {
        this.genres.put(item.getId(),item);
        return item;
    }
}
