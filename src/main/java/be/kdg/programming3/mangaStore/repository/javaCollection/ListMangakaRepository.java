package be.kdg.programming3.mangaStore.repository.javaCollection;


import be.kdg.programming3.mangaStore.domain.Mangaka;
import be.kdg.programming3.mangaStore.presentation.MangakaController;
import be.kdg.programming3.mangaStore.repository.MangakaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("collection")
public class ListMangakaRepository implements MangakaRepository {
    private static List<Mangaka> mangakas = new ArrayList<>();
//    private Logger logger = LoggerFactory.getLogger(MangakaController.class);

    @Override
    public List<Mangaka> readMangakas() {
//        logger.info("Reading mangakas from database");
        return mangakas;
    }

    @Override
    public Mangaka findById(int id) {
        return null;
    }

    @Override
    public Mangaka createMangaka(Mangaka mangaka) {
//        logger.info("Creating the mangaka {}", mangaka);
        mangaka.setId(mangakas.stream().mapToInt(Mangaka::getId).max().orElse(0) + 1);
        mangakas.add(mangaka);
        return mangaka;
    }

    @Override
    public void updateMangaka(Mangaka mangaka) {

    }

    @Override
    public void deleteMangaka(int id) {

    }
}
