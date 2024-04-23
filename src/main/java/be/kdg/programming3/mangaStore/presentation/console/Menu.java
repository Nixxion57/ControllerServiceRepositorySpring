package be.kdg.programming3.mangaStore.presentation.console;

import be.kdg.programming3.mangaStore.domain.Manga;
import be.kdg.programming3.mangaStore.domain.Publisher;
import be.kdg.programming3.mangaStore.service.MangaService;
import be.kdg.programming3.mangaStore.service.MangakaService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class Menu {
    Scanner scanner = new Scanner(System.in);
    private MangaService mangaService;
    private MangakaService mangakaService;

    public Menu(MangakaService mangakaService, MangaService mangaService) {
        this.mangakaService = mangakaService;
        this.mangaService = mangaService;
    }

    public void show() {
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("==========================");
            System.out.println("0) Quit");
            System.out.println("1) Show all mangas");
            System.out.println("2) Show mangas by publisher");
            System.out.println("3) Show all mangakas");
            System.out.println("4) Show mangas from certain author and/or certain publisher");
            System.out.print(" Choice (0-4):");
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    System.exit(0);
                    break;

                case 1:
                    System.out.println("All mangas:");
                    System.out.println("-----------------");
                    mangaService.getMangas().forEach(System.out::println);
                    System.out.println("Writing to Json...");
                    mangaService.writeMangasToJson(mangaService.getMangas());
                    break;

                case 2:
                    showMangasByPublisher();
                    break;

                case 3:
                    System.out.println("All mangakas:");
                    System.out.println("-----------------");
                    mangakaService.getMangakas().forEach(System.out::println);
                    System.out.println("Writing to Json...");
                    mangakaService.writeMangakasToJson(mangakaService.getMangakas());
                    break;
                case 4:
                    showMangasByAuthorOrAndPublisher();
                    break;

                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    private void showMangasByPublisher() {
        System.out.println("Choose a publisher:");
        List<Publisher> publishersList = mangaService.getPublishers();
        AtomicInteger i = new AtomicInteger(0);
        publishersList.forEach(publisher -> {
            System.out.println(i.getAndIncrement() + publisher.toString());
        });
        int choice = scanner.nextInt();
        if (choice < 0 || choice > publishersList.size()) {
            System.out.println("Invalid choice");
        } else {
            System.out.println("Mangas by: " + publishersList.get(choice-1));
            List<Manga> selection = mangaService.getMangasByPublisher(publishersList.get(choice-1));
            selection.forEach(System.out::println);
            System.out.println("Writing to Json...");
            mangaService.writeMangasToJson(selection);
        }
    }

    private void showMangasByAuthorOrAndPublisher() {
        System.out.println("Choose an Author");

        System.out.println("Choose a publisher");

    }

}
