package be.kdg.programming3.mangaStore.repository;

import be.kdg.programming3.mangaStore.domain.Mangaka;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


import java.util.List;
@Profile({"collection", "jpa", "jdbc"})

public interface MangakaRepository {
    List<Mangaka> readMangakas();
    Mangaka findById(int id);
    Mangaka createMangaka(Mangaka mangaka);
    void updateMangaka(Mangaka mangaka);
    void deleteMangaka(int id);
}
