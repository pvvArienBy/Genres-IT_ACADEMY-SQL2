package by.it_academy.jd2.Mk_JD2_98_23.dao.db;


import by.it_academy.jd2.Mk_JD2_98_23.core.dto.GenreDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.AccessDataException;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IGenreDao;
import by.it_academy.jd2.Mk_JD2_98_23.dao.db.ds.DatabaseConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GenreJDBCDao implements IGenreDao {

    @Override
    public List<GenreDTO> get() {
        List<GenreDTO> data = new ArrayList<>();

        try (Connection conn = DatabaseConnectionFactory.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT id, name FROM app.genres ORDER BY id ASC");) {

            while (rs.next()) {
                GenreDTO dto = new GenreDTO();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                data.add(dto);
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return data;
    }

    @Override
    public GenreDTO get(int id) {
        GenreDTO dto = null;
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT id, name FROM app.genres WHERE id = " + id + " ORDER BY id ASC");) {

            if (rs.next()) {
                dto = new GenreDTO();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return dto;
    }

    @Override
    public GenreDTO save(GenreDTO item) {
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("INSERT INTO app.genres(name) VALUES ('" + item.getName() + "') RETURNING id;");) {

            while (rs.next()) {
                item.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return item;
    }
}
