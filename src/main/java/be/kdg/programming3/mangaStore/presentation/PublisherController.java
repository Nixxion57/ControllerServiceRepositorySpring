package be.kdg.programming3.mangaStore.presentation;

import be.kdg.programming3.mangaStore.domain.Publisher;
import be.kdg.programming3.mangaStore.presentation.viewModel.PublisherViewModel;
import be.kdg.programming3.mangaStore.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/publishers")
@Profile("web")
public class PublisherController {
    private PublisherService publisherService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public String showPublishers(Model model, HttpSession httpSession){
        logger.info("controller is running PublishersView!");
        List<Publisher> publishers = publisherService.getPublishers();
        model.addAttribute("publishers", publishers);

        String url = "/publishers";
        String timeStamp = LocalDateTime.now().toString();
        httpSession.setAttribute(timeStamp,url);
        return "allPublishers";
    }

    @GetMapping("publisher/{id}")
    public String showOnePublisher(@PathVariable int id, Model model, HttpSession httpSession) {
        logger.info("Loading publisher by id...");
        model.addAttribute("publisher", publisherService.getPublisherById(id));

        String url = "/publisher";
        String timeStamp = LocalDateTime.now().toString();
        httpSession.setAttribute(timeStamp, url);
        return "publisher";
    }

    @PostMapping("publisher/{id}")
    public String processOnePublisher(@PathVariable int id, @Valid @ModelAttribute("publisherViewModel")
                                      PublisherViewModel publisherViewModel, Model model) {
        model.addAttribute("id", id);
        return "redirect:/publisher/{id}";
    }

}
