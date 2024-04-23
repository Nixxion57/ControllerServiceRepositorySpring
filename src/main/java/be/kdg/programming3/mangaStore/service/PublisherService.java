package be.kdg.programming3.mangaStore.service;

import be.kdg.programming3.mangaStore.domain.Publisher;

import java.util.List;

public interface PublisherService {
    List<Publisher> getPublishers();
    Publisher getPublisherById(int id);
    void deletePublisher(int id);
    Publisher addPublisher(Publisher publisher);
}
