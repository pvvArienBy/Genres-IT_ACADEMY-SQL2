package by.it_academy.jd2.Mk_JD2_98_23.core.dto;

public class VoteCreateDTO {
    private final Integer artist;
    private final Integer[] genres;
    private final String about;


    public VoteCreateDTO(Integer artist, Integer[] genres, String about) {
        this.artist = artist;
        this.genres = genres;
        this.about = about;
    }


    public Integer getArtist() {
        return artist;
    }


    public Integer[] getGenres() {
        return genres;
    }



    public String getAbout() {
        return about;
    }
}
