package be.kdg.programming3.mangaStore.presentation;

import be.kdg.programming3.mangaStore.domain.Mangaka;
import be.kdg.programming3.mangaStore.exceptions.DestinationNotAvailable;
import be.kdg.programming3.mangaStore.presentation.viewModel.MangakaViewModel;
import be.kdg.programming3.mangaStore.service.MangakaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InvalidClassException;
import java.time.LocalDateTime;

@Controller
@Profile("web")
@RequestMapping("/addMangaka")
public class AddMangakaController {
    private MangakaService mangakaService;
    private Logger logger = LoggerFactory.getLogger(MangakaController.class);

    @Autowired
    public AddMangakaController(MangakaService mangakaService) {
        this.mangakaService = mangakaService;
    }

    @GetMapping
    public String showAddMangakaForm(Model model, HttpSession httpSession) {
        model.addAttribute("mangakaViewModel", new MangakaViewModel());

        String url = "/addMangaka";
        String timeStamp = LocalDateTime.now().toString();
        httpSession.setAttribute(timeStamp, url);
        return "addMangaka";
    }

    @PostMapping
    public String processAddMangaka(@Valid @ModelAttribute("mangakaViewModel") MangakaViewModel viewModel, HttpSession httpSession, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> {
                logger.error(error.toString());
            });
            return "addMangaka";
        }

        try {
            logger.debug("Processing " + viewModel);
            Mangaka mangaka = new Mangaka(viewModel.getName(), viewModel.getGender(), viewModel.getYears());
            mangakaService.addMangaka(mangaka);
            httpSession.setAttribute("mangaka", mangaka);
        } catch (DestinationNotAvailable notAvailable) {
            logger.error("There is a problem with the access to the page:" + notAvailable.getMessage());
            return "error";
        }

        return "redirect:/mangakas";

    }

    @ExceptionHandler(InvalidClassException.class)
    public String handleInvalidInput(Exception e, Model model) {
        logger.error(e.getMessage());
        model.addAttribute("error", "Invalid input!");
        return "error";
    }
}
