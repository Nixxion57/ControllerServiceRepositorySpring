package be.kdg.programming3.mangaStore.repository.springDataJpa;

import be.kdg.programming3.mangaStore.domain.Manga;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Profile("springdata")
public interface SpringDataJpaMangaRepo extends JpaRepository<Manga, Integer> {
    @Query("select manga from Manga manga where manga.publisher.id = :publisher_id")
    List<Manga> findByPublisher(int publisher_id);
}
