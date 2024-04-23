package be.kdg.programming3.mangaStore.presentation;

import be.kdg.programming3.mangaStore.presentation.viewModel.MangaViewModel;
import be.kdg.programming3.mangaStore.presentation.viewModel.PublisherViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import be.kdg.programming3.mangaStore.domain.Genres;
import be.kdg.programming3.mangaStore.domain.Manga;
import be.kdg.programming3.mangaStore.service.MangaService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
//import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.lang.String;

//@RestController
@Controller
@Profile("web")
@RequestMapping("/mangas")
public class MangaController {
    private Logger logger = LoggerFactory.getLogger(MangakaController.class);
    private MangaService mangaService;

    @Autowired
    public MangaController(MangaService mangaService) {
        this.mangaService = mangaService;
    }

    @GetMapping
    public String showMangasView(Model model, HttpSession httpSession){
        logger.info("controller is running showMangasView!");
        List<Manga> mangas = mangaService.getMangas();
        model.addAttribute("mangas", mangas);

        String url = "/mangas";
        String timeStamp = LocalDateTime.now().toString();
        httpSession.setAttribute(timeStamp,url);
        return "allMangas";
    }

    @GetMapping("manga/{id}")
    public String showOneManga(@PathVariable int id, Model model, HttpSession httpSession) {
        logger.info("Loading manga by id...");
        model.addAttribute("manga", mangaService.getMangaById(id));

        String url = "/manga";
        String timeStamp = LocalDateTime.now().toString();
        httpSession.setAttribute(timeStamp, url);
        return "manga";
    }

    @PostMapping("manga/{id}")
    public String processOneManga(@PathVariable int id, @Valid @ModelAttribute("mangaViewModel")
                                  MangaViewModel mangaViewModel, Model model) {
        model.addAttribute("id", id);
        return "redirect:/manga/{id}";
    }
}
