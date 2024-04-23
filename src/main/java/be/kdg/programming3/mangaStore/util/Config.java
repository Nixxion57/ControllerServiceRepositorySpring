package be.kdg.programming3.mangaStore.util;

import be.kdg.programming3.mangaStore.domain.Manga;
import be.kdg.programming3.mangaStore.domain.Mangaka;
import be.kdg.programming3.mangaStore.repository.MangaRepository;
import be.kdg.programming3.mangaStore.repository.MangakaRepository;
import be.kdg.programming3.mangaStore.repository.javaCollection.ListMangaRepository;
import be.kdg.programming3.mangaStore.repository.javaCollection.ListMangakaRepository;
import be.kdg.programming3.mangaStore.service.MangaService;
import be.kdg.programming3.mangaStore.service.MangaServiceImpl;
import be.kdg.programming3.mangaStore.service.MangakaService;
import be.kdg.programming3.mangaStore.service.MangakaServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class Config {

    @Bean
    public MangakaRepository mangakaRepository() {
        return new ListMangakaRepository();
    }

    @Bean
    public MangakaService mangakaService(MangakaRepository mangakaRepository) {
        return new MangakaServiceImpl(mangakaRepository);
    }
    @Bean
    public MangaRepository mangaRepository() {
        return new ListMangaRepository();
    }

    @Bean
    public MangaService mangaService(MangaRepository mangaRepository) {
        return new MangaServiceImpl(mangaRepository);
    }


}
