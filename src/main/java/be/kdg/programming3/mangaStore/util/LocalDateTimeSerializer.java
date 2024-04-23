package be.kdg.programming3.mangaStore.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSerializer implements JsonSerializer<LocalDate> {
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("d-MMM-yyyy");
    @Override
    public JsonElement serialize(LocalDate localDate,
                                 Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(FORMATTER.format(localDate));
    }
}


