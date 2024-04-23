package be.kdg.programming3.mangaStore.presentation;

import be.kdg.programming3.mangaStore.domain.Mangaka;
import be.kdg.programming3.mangaStore.presentation.viewModel.MangakaViewModel;
import be.kdg.programming3.mangaStore.presentation.viewModel.PublisherViewModel;
import be.kdg.programming3.mangaStore.service.MangakaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@Profile("web")
@RequestMapping("/mangakas")
public class MangakaController {
    private Logger logger = LoggerFactory.getLogger(MangakaController.class);
    private MangakaService mangakaService;

    @Autowired
    public MangakaController(MangakaService mangakaService) {
        logger.debug("Creating MangakaController");
        this.mangakaService = mangakaService;
    }

    @GetMapping
    public String showMangakasView(Model model, HttpSession httpSession) {
        model.addAttribute("mangakas", mangakaService.getMangakas());

        String url = "/mangakas";
        String timeStamp = LocalDateTime.now().toString();
        httpSession.setAttribute(timeStamp,url);
        return "allMangakas";
    }

    @GetMapping("mangaka/{id}")
    public String showOneMangaka(@PathVariable int id, Model model, HttpSession httpSession) {
        logger.info("Loading mangaka by id...");
        model.addAttribute("mangaka", mangakaService.getMangakaById(id));

        String url = "/mangaka";
        String timeStamp = LocalDateTime.now().toString();
        httpSession.setAttribute(timeStamp, url);
        return "mangaka";
    }

    @PostMapping("mangaka/{id}")
    public String processOnePublisher(@PathVariable int id, @Valid @ModelAttribute("mangakaViewModel")
                                      MangakaViewModel mangakaViewModel, Model model) {
        model.addAttribute("id", id);
        return "redirect:/mangaka/{id}";
    }

}
