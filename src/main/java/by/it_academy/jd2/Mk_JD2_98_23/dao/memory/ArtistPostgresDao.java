package by.it_academy.jd2.Mk_JD2_98_23.dao.memory;

import by.it_academy.jd2.Mk_JD2_98_23.controllers.coonections.DataConnections;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.ArtistDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IArtistDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ArtistPostgresDao implements IArtistDao {

    private Connection connection;

    public ArtistPostgresDao() {
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


//    public void close() throws SQLException {
//        if (connection != null) {
//            connection.close();
//        }
//    }

    @Override
    public List<ArtistDTO> get() {
        List<ArtistDTO> artists = new ArrayList<>();
        String sql = "SELECT id, name FROM app.artists";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                artists.add(new ArtistDTO(id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return artists;
    }

    @Override
    public ArtistDTO get(int id) {
        String sql = "SELECT * FROM app.artists WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    return new ArtistDTO(id, name);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArtistDTO save(ArtistDTO item) {
        String sql = "UPDATE app.artists SET name = ? WHERE id = ?";
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