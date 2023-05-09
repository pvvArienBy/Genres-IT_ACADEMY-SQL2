package by.it_academy.jd2.Mk_JD2_98_23.service;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.GenreDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.VoteCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.VoteDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IVoteDao;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IArtistService;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IGenreService;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IVoteService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class VoteService implements IVoteService {

    private IVoteDao voteDao;
    private IArtistService artistService;
    private IGenreService genreService;


    public VoteService(IVoteDao voteDao) {
        this.voteDao = voteDao;
    }

    public VoteService(IArtistService artistService, IGenreService genreService) {
        this.artistService = artistService;
        this.genreService = genreService;
    }

    public void setService(IArtistService artistService, IGenreService genreService) {
        this.artistService = artistService;
        this.genreService = genreService;
    }


    @Override
    public List<VoteDTO> get() {
        return voteDao.get();
    }

    @Override
    public VoteDTO get(int id) {
        return voteDao.get(id);
    }

    @Override
    public VoteDTO save(VoteCreateDTO createDTO) {
        validate(createDTO);

        int maxCurrentId = 0;
        if (this.get().size() == 0) {
            maxCurrentId = 1;
        } else {
            maxCurrentId = this.get()
                    .stream()
                    .mapToInt(VoteDTO::getId)
                    .max()
                    .orElseThrow();
        }


        VoteDTO dto = new VoteDTO();
        dto.setId(maxCurrentId + 1);
        dto.setArtist(createDTO.getArtist());
        dto.setGenres(createDTO.getGenres());
        dto.setAbout(createDTO.getAbout());
        dto.setDateTime(LocalDateTime.now());

        return voteDao.save(dto);
    }

    private void validate(VoteCreateDTO createDTO) {
        Integer[] genres = createDTO.getGenres();
        if (genres.length < 3 || genres.length > 5) {
            throw new IllegalArgumentException("Выберите от 3-х до 5-и жанров");
        }

        for (Integer genre : genres) {
            GenreDTO genreDTO = this.genreService.get(genre);
            if (genreDTO == null) {
                throw new IllegalArgumentException("Жанра не существует");
            }
        }
    }

    public Map<Integer, Integer> sortArtist() {
        List<VoteDTO> list = get();
        Map<Integer, Integer> artistCountMap = new ConcurrentHashMap<>();

        // Подсчитываем количество каждого artist
        for (VoteDTO voteDTO : list) {
            Integer artist = voteDTO.getArtist();
            artistCountMap.put(artist, artistCountMap.getOrDefault(artist, 0) + 1);
        }

        // Создаем список пар ключ-значение и сортируем его
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(artistCountMap.entrySet());
        Collections.sort(entryList, (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Создаем новую упорядоченную мапу на основе entryList
        Map<Integer, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }


    public Map<Integer, Integer> sortGenres() {
        List<VoteDTO> list = get();
        Map<Integer, Integer> artistCountMap = new ConcurrentHashMap<>();

        // Подсчитываем количество каждого artist
        for (VoteDTO voteDTO : list) {
            Integer [] artistArray = voteDTO.getGenres();
            for (Integer artist : artistArray) {
                artistCountMap.put(artist, artistCountMap.getOrDefault(artist, 0) + 1);
            }

        }

        // Создаем список пар ключ-значение и сортируем его
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(artistCountMap.entrySet());
        Collections.sort(entryList, (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Создаем новую упорядоченную мапу на основе entryList
        Map<Integer, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public Map<LocalDateTime, String> sortAbout() {
        List<VoteDTO> list = get();
        Map<LocalDateTime, String> sortedTreeMap = new TreeMap<>();

        for (VoteDTO voteDTO : list) {
            sortedTreeMap.put(voteDTO.getDateTime(), voteDTO.getAbout());
        }

        return sortedTreeMap;
    }
}
