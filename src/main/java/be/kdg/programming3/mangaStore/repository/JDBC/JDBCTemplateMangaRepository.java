package be.kdg.programming3.mangaStore.repository.JDBC;

import be.kdg.programming3.mangaStore.domain.Genres;
import be.kdg.programming3.mangaStore.domain.Manga;
import be.kdg.programming3.mangaStore.repository.MangaRepository;
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
public class JDBCTemplateMangaRepository implements MangaRepository {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert inserter;

    public JDBCTemplateMangaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.inserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Manga")
                .usingGeneratedKeyColumns("id");
    }

    //Helper method: maps the columns of the DB to the attributes of the Student
    private Manga mapRow(ResultSet rs, int rowid) throws SQLException {
        return new Manga(rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getInt("chapters"),
                rs.getDate("written").toLocalDate(),
                Genres.valueOf(rs.getString("genre")),
                rs.getInt("pricePerCopy"));
    }


    @Override
    public List<Manga> readMangas() {
        return jdbcTemplate.query("select * from Manga", this::mapRow);
    }

    @Override
    public Manga findById(int id) {
        return jdbcTemplate.queryForObject("select * from Manga where id=?", this::mapRow, id);
    }

    @Override
    public List<Manga> findByMangaka(int mangakaId) {
        return jdbcTemplate.query("select * from Manga where id in (select manga_id from Ownership where mangaka_id = ?)", this::mapRow, mangakaId);
    }

    @Override
    public List<Manga> findByPublisher(int publisherId) {
        return jdbcTemplate.query("select * from Manga where publisher_id=?", this::mapRow, publisherId);
    }

    @Override
    public Manga createManga(Manga manga) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", manga.getTitle());
        parameters.put("author", manga.getAuthor());
        parameters.put("chapters", manga.getChapters());
        parameters.put("written", manga.getWritten());
        parameters.put("genre", manga.getGenre());
        parameters.put("pricePerCopy", manga.getPricePerCopy());
        manga.setId(inserter.executeAndReturnKey(parameters).intValue());
        return manga;
    }

    @Override
    public void updateManga(Manga manga) {
        jdbcTemplate.update("update Manga set title=?,author=?,chapters=?,written=?,genre=?,pricePerCopy=? where id=?",
                manga.getTitle(), manga.getAuthor(), manga.getChapters(), manga.getWritten(), manga.getGenre(), manga.getPricePerCopy(), manga.getId());
    }

    @Override
    public void deleteManga(int id) {
        jdbcTemplate.update("delete from Ownership where manga_id = ?", id);
        jdbcTemplate.update("delete from Manga where id=?", id);
    }

}
