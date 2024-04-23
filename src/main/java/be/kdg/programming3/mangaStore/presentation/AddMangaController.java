package be.kdg.programming3.mangaStore.presentation;

import be.kdg.programming3.mangaStore.domain.Manga;
import be.kdg.programming3.mangaStore.domain.Mangaka;
import be.kdg.programming3.mangaStore.exceptions.DestinationNotAvailable;
import be.kdg.programming3.mangaStore.presentation.viewModel.MangaViewModel;
import be.kdg.programming3.mangaStore.service.MangaService;
import be.kdg.programming3.mangaStore.service.MangakaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@Profile("web")
@RequestMapping("/addManga")
public class AddMangaController {
    private MangaService mangaService;
    private Logger logger = LoggerFactory.getLogger(MangaController.class);

    @Autowired
    public AddMangaController(MangaService mangaService) {
        this.mangaService = mangaService;
    }

    @GetMapping
    public String showAddMangaForm(Model model, HttpSession httpSession) {
        model.addAttribute("mangaViewModel", new MangaViewModel());

        String url = "/addManga";
        String timeStamp = LocalDateTime.now().toString();
        httpSession.setAttribute(timeStamp, url);
        return "addManga";
    }

    @PostMapping
    public String processAddManga(@Valid @ModelAttribute("mangaViewModel") MangaViewModel viewModel, HttpSession httpSession, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> {
                logger.error(error.toString());
            });
            return "addManga";
        }

        try {
            logger.debug("Processing " + viewModel);
            Manga manga = new Manga(viewModel.getTitle(), viewModel.getAuthor(), viewModel.getChapters(), viewModel.getWritten(), viewModel.getGenre(), viewModel.getPricePerCopy());
            mangaService.addManga(manga);
            httpSession.setAttribute("manga", manga);
        } catch (DestinationNotAvailable notAvailable) {
            logger.error("There is a problem with the access to the page:" + notAvailable.getMessage());
            return "error";
        }
        return "redirect:/mangas";
    }

    @ExceptionHandler(InvalidPropertyException.class)
    public String handleInvalidInput(Exception e, Model model) {
        logger.error(e.getMessage());
        model.addAttribute("error", "Invalid input!");
        return "error";
    }
}
