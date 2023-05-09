package by.it_academy.jd2.Mk_JD2_98_23.core.dto;

public class ArtistDTO {

    private  int id;
    private String name;



    public ArtistDTO() {
    }

    public ArtistDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
