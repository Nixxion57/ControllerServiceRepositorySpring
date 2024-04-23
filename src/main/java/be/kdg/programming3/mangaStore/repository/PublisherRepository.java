package be.kdg.programming3.mangaStore.repository;

import be.kdg.programming3.mangaStore.domain.Publisher;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile({"collection", "jpa", "jdbc"})
public interface PublisherRepository {
    List<Publisher> readPublishers();
    Publisher findById(int id);
    Publisher createPublisher(Publisher publisher);
    void updatePublisher(Publisher publisher);
    void deletePublisher(int id);
}
