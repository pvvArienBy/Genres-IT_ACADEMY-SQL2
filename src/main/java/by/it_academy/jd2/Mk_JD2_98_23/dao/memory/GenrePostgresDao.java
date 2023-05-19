package by.it_academy.jd2.Mk_JD2_98_23.dao.memory;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.GenreDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IGenreDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GenrePostgresDao implements IGenreDao {
    private Connection connection;

    public GenrePostgresDao() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:postgresql://localhost:5432/voting";
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "root");
        try {
            connection = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<GenreDTO> get() {
        List<GenreDTO> genres = new ArrayList<>();
        String sql = "SELECT id, name FROM app.genres";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                genres.add(new GenreDTO(id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return genres;
    }

    @Override
    public GenreDTO get(int id) {
        String sql = "SELECT * FROM app.genres WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    return new GenreDTO(id, name);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public GenreDTO save(GenreDTO item) {
        String sql = "UPDATE app.genres SET name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.getName());
            statement.setInt(2, item.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return item;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
