package be.kdg.programming3.mangaStore.presentation;

import be.kdg.programming3.mangaStore.presentation.viewModel.PublisherViewModel;
import be.kdg.programming3.mangaStore.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/removePublisher")
@Profile("web")
public class RemovePublisherController {
    private PublisherService publisherService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public RemovePublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public String showRemovePublisherForm(Model model) {
        model.addAttribute("publisherViewModel", new PublisherViewModel());
        return "removePublisher";
    }

    @PostMapping
    public String removePublisher(int id, HttpSession httpSession){
        logger.debug("Deleting publisher...");
        publisherService.deletePublisher(id);
        httpSession.removeAttribute("publisher");
        return "redirect:/publishers";
    }
}
