package be.kdg.programming3.mangaStore.repository.JDBC;

import be.kdg.programming3.mangaStore.domain.Mangaka;
import be.kdg.programming3.mangaStore.exceptions.DatabaseException;
import be.kdg.programming3.mangaStore.repository.MangakaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("test")
public class JDBCMangakaRepository implements MangakaRepository {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${spring.datasource.url}")
    private String dbURL;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;


    @Override
    public List<Mangaka> readMangakas() {
        List<Mangaka> mangakas = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbURL, user, password)) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM Mangakas")) {
                    while (resultSet.next()) {
                        String name = resultSet.getString("name");
                        char gender = resultSet.getString("gender").charAt(0);
                        int years = resultSet.getInt("years");
                        Mangaka mangaka = new Mangaka(name, gender, years);
                        mangakas.add(mangaka);
                    }
                }
            }
            return mangakas;
        } catch (SQLException e) {
            logger.error("Something went wrong during the retrieval of the mangakas:", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mangaka findById(int id) {
        return null;
    }

    @Override
    public Mangaka createMangaka(Mangaka mangaka) {
        Mangaka createdMangaka = new Mangaka(mangaka.getName(), mangaka.getGender(), mangaka.getYears());
        try (Connection connection = DriverManager.getConnection(dbURL, user, password)) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Mangakas(name, gender, years) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, mangaka.getName());
                statement.setString(2, String.valueOf(mangaka.getGender()));
                statement.setInt(3, mangaka.getYears());
                int result = statement.executeUpdate();
                if (result != 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            createdMangaka.setId(createdMangaka.getId());
                        } else {
                            throw new SQLException("Creating the mangaka failed...");
                        }
                    }
                }

            }

        } catch (SQLException e) {
            logger.error("Problem occurred while creating mangaka...", e);
            throw new DatabaseException("Problem occurred while creating the mangaka...", e);
        }
        return createdMangaka;
    }

    @Override
    public void updateMangaka(Mangaka mangaka) {

    }

    @Override
    public void deleteMangaka(int id) {

    }
}
