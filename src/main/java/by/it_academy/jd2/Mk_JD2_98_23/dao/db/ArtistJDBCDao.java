package by.it_academy.jd2.Mk_JD2_98_23.dao.db;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.ArtistDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.AccessDataException;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IArtistDao;
import by.it_academy.jd2.Mk_JD2_98_23.dao.db.ds.DatabaseConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ArtistJDBCDao implements IArtistDao {

    private Connection connection;

    @Override
    public List<ArtistDTO> get() {
        List<ArtistDTO> data = new ArrayList<>();

        try (Connection conn = DatabaseConnectionFactory.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT id, name FROM app.artists ORDER BY id ASC");) {

            while (rs.next()) {
                ArtistDTO dto = new ArtistDTO();
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
    public ArtistDTO get(int id) {
        ArtistDTO dto = null;
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT id, name FROM app.artists WHERE id = " + id + " ORDER BY id ASC");) {

            if (rs.next()) {
                dto = new ArtistDTO();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return dto;
    }

    @Override
    public ArtistDTO save(ArtistDTO item) {
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("INSERT INTO app.artists(name) VALUES ('" + item.getName() + "') RETURNING id;");) {

            while (rs.next()) {
                item.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return item;
    }
}