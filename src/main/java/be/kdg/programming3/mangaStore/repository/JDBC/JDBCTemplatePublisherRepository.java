package be.kdg.programming3.mangaStore.repository.JDBC;

import be.kdg.programming3.mangaStore.domain.Publisher;
import be.kdg.programming3.mangaStore.repository.PublisherRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("jdbc")
public class JDBCTemplatePublisherRepository implements PublisherRepository {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert inserter;

    public JDBCTemplatePublisherRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.inserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Publisher")
                .usingGeneratedKeyColumns("id");

    }

    public static Publisher mapRow(ResultSet rs, int rowId) throws SQLException {
        return new Publisher(rs.getInt("id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getDate("startDate").toLocalDate());
    }

    @Override
    public List<Publisher> readPublishers() {
        return jdbcTemplate.query("select * from Publisher", JDBCTemplatePublisherRepository::mapRow);
    }

    @Override
    public Publisher findById(int id) {
        return jdbcTemplate.queryForObject("select * from Publisher where id = ?", JDBCTemplatePublisherRepository::mapRow, id);
    }

    @Override
    public Publisher createPublisher(Publisher publisher) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", publisher.getName());
        parameters.put("address", publisher.getAddress());
        parameters.put("startDate", publisher.getStartDate());
        publisher.setId(inserter.executeAndReturnKey(parameters).intValue());
        return publisher;
    }

    @Override
    public void updatePublisher(Publisher publisher) {
        jdbcTemplate.update("update Publisher set name=?, address = ?, startDate= ? where id=? ",
                publisher.getName(), publisher.getAddress(), publisher.getStartDate(), publisher.getId());
    }

    @Override
    public void deletePublisher(int id) {
        jdbcTemplate.update("delete from Ownership where manga_id in (select manga_id from Manga where publisher_id=?)", id);
        jdbcTemplate.update("delete from Manga where publisher_id = ?", id);
        jdbcTemplate.update("delete from Publisher where id= ?", id);

    }
}
