package be.kdg.programming3.mangaStore.repository;

import be.kdg.programming3.mangaStore.domain.Manga;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Profile({"collection", "jpa", "jdbc"})
public interface MangaRepository {
    List<Manga> readMangas();
    Manga findById(int id);
    List<Manga> findByMangaka(int mangakaId);
    List<Manga> findByPublisher(int publisherId);
    Manga createManga(Manga manga);
    void updateManga(Manga manga);
    void deleteManga(int id);
}
