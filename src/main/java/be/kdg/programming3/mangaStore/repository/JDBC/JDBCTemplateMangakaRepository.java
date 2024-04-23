package be.kdg.programming3.mangaStore.repository.JDBC;

import be.kdg.programming3.mangaStore.domain.Mangaka;
import be.kdg.programming3.mangaStore.repository.MangakaRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("jdbc")
public class JDBCTemplateMangakaRepository implements MangakaRepository {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert inserter;

    public JDBCTemplateMangakaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.inserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Mangaka")
                .usingGeneratedKeyColumns("id");
    }

    //Helper method: maps the columns of the DB to the attributes of the Student
    private Mangaka mapRow(ResultSet rs, int rowid) throws SQLException {
        return new Mangaka(rs.getInt("id"),
                rs.getString("name"),
                rs.getString("gender").charAt(0),
                rs.getInt("years"));
    }

    @Override
    public List<Mangaka> readMangakas() {
        return jdbcTemplate.query("SELECT * FROM Mangaka", this::mapRow);
    }

    @Override
    public Mangaka findById(int id) {
        return jdbcTemplate.queryForObject("select * from Mangaka where id=?", this::mapRow, id);
    }

    @Override
    public Mangaka createMangaka(Mangaka mangaka) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", mangaka.getName());
        parameters.put("gender", mangaka.getGender());
        parameters.put("years", mangaka.getYears());
        mangaka.setId(inserter.executeAndReturnKey(parameters).intValue());
        return mangaka;
    }

    @Override
    public void updateMangaka(Mangaka mangaka) {
        jdbcTemplate.update("update Mangaka set name=?, gender=?,years= ? where id=? ",
                mangaka.getName(),mangaka.getGender(),mangaka.getYears(),mangaka.getId());
    }

    @Override
    public void deleteMangaka(int id) {
        jdbcTemplate.update("delete from Ownership where mangaka_id=?",id);
        jdbcTemplate.update("delete from Mangaka where id=?", id);
    }

}
