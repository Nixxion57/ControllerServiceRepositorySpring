package be.kdg.programming3.mangaStore.presentation;

import be.kdg.programming3.mangaStore.domain.Publisher;
import be.kdg.programming3.mangaStore.exceptions.DestinationNotAvailable;
import be.kdg.programming3.mangaStore.presentation.viewModel.PublisherViewModel;
import be.kdg.programming3.mangaStore.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/addPublisher")
@Profile("web")
public class AddPublisherController {
    private PublisherService publisherService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public AddPublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public String showAddPublisherForm(Model model, HttpSession httpSession) {
        model.addAttribute("publisherViewModel", new PublisherViewModel());

        String url = "/addPublisher";
        String timeStamp = LocalDateTime.now().toString();
        httpSession.setAttribute(timeStamp, url);
        return "addPublisher";
    }

    @PostMapping
    public String processAddPublisher(HttpSession httpSession, @Valid @ModelAttribute("publisherViewModel")  PublisherViewModel publisherViewModel, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> {
                logger.error(error.toString());
            });
            return "addPublisher";
        }


        try {
            logger.debug("Creating a new publisher...");
            Publisher publisher = new Publisher(publisherViewModel.getName(),publisherViewModel.getAddress(),publisherViewModel.getStartDate());
            publisherService.addPublisher(publisher);
            httpSession.setAttribute("publisher", publisher);
        } catch (DestinationNotAvailable e) {
            logger.error("Error when trying to access the page:" + e.getMessage());
            return "error";
        }

        return "redirect:/publishers";
    }

    @ExceptionHandler(InvalidPropertyException.class)
    public String handleInvalidInput(Exception e, Model model){
        logger.error(e.getMessage());
        model.addAttribute("error", "Invalid input...");
        return "error";
    }

}
