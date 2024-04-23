package be.kdg.programming3.mangaStore.presentation.converter;

import be.kdg.programming3.mangaStore.domain.Genres;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.core.convert.converter.Converter;


public class StringToGenre implements Converter<String, Genres> {
    @Override
    public Genres convert(String value) {
        if(value.toUpperCase().startsWith("COMEDY")) return Genres.COMEDY;
        if(value.toUpperCase().startsWith("ACTION")) return Genres.ACTION;
        if(value.toUpperCase().startsWith("SHONEN")) return Genres.SHONEN;
        if(value.toUpperCase().startsWith("HORROR")) return Genres.HORROR;
        if(value.toUpperCase().startsWith("DRAMA")) return Genres.DRAMA;
        if(value.toUpperCase().startsWith("ROMANCE")) return Genres.ROMANCE;
        if(value.toUpperCase().startsWith("FANTASY")) return Genres.FANTASY;
        return Genres.UNKNOWN;
    }
}
