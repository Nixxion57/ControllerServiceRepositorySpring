package be.kdg.programming3.mangaStore.util;

import be.kdg.programming3.mangaStore.util.LocalDateTimeSerializer;
import be.kdg.programming3.mangaStore.domain.Manga;
import be.kdg.programming3.mangaStore.domain.Mangaka;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Component
public class JsonWriter {

    private static final String MANGAS_JSON = "mangas.json";
    private static final String MANGAKAS_JSON = "mangakas.json";

    private Gson gson;

    public JsonWriter() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateTimeSerializer());
        gson = builder.create();
    }

    public void writeMangas(List<Manga> mangas) {
        String json = gson.toJson(mangas);
        try (FileWriter writer = new FileWriter(MANGAS_JSON)) {
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save mangas to JSON", e);
        }
    }

    public void writeMangakas(List<Mangaka> mangakas) {
        String json = gson.toJson(mangakas);
        try (FileWriter writer = new FileWriter(MANGAKAS_JSON)) {
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save mangakas to JSON", e);
        }
    }
}
