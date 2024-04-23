package be.kdg.programming3.mangaStore.repository.javaCollection;

import be.kdg.programming3.mangaStore.domain.Manga;
import be.kdg.programming3.mangaStore.repository.MangaRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
@Profile("collection")
public class ListMangaRepository implements MangaRepository {
//    private Logger logger = LoggerFactory.getLogger(this.getClass());

    static List<Manga> mangas = new ArrayList<>();

    @Override
    public List<Manga> readMangas() {
//        logger.info("Reading mangas from database");
        return mangas;
    }

    @Override
    public Manga findById(int id) {
        return null;
    }

    @Override
    public List<Manga> findByMangaka(int mangakaId) {
        return null;
    }

    @Override
    public List<Manga> findByPublisher(int publisherId) {
        return null;
    }

    @Override
    public Manga createManga(Manga manga) {
        manga.setId(mangas.stream().mapToInt(Manga::getId).max().orElse(0) + 1);
        mangas.add(manga);
        return manga;
    }

    @Override
    public void updateManga(Manga manga) {

    }

    @Override
    public void deleteManga(int id) {

    }
}
