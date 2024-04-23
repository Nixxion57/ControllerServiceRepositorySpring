package be.kdg.programming3.mangaStore.service;

import be.kdg.programming3.mangaStore.domain.Mangaka;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MangakaService {
    List<Mangaka> getMangakas();
    List<Mangaka> getMangakaByNameAndGender(String name, char gender);
    Mangaka getMangakaById(int id);
    Mangaka addMangaka(Mangaka mangaka);
    void deleteMangaka(int id);


    void writeMangakasToJson(List<Mangaka> mangakas);
}
