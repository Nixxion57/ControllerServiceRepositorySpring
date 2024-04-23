package be.kdg.programming3.mangaStore.presentation;

import be.kdg.programming3.mangaStore.presentation.viewModel.MangaViewModel;
import be.kdg.programming3.mangaStore.service.MangaService;
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
@RequestMapping("/removeManga")
@Profile("web")
public class RemoveMangaController {
    private MangaService mangaService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public RemoveMangaController(MangaService mangaService) {
        this.mangaService = mangaService;
    }

    @GetMapping
    public String showRemoveMangaForm(Model model) {
        model.addAttribute("mangaViewModel", new MangaViewModel());
        return "removeManga";
    }

    @PostMapping
    public String removeManga(int id, HttpSession httpSession){
        logger.debug("Deleting manga...");
        mangaService.deleteManga(id);
        httpSession.removeAttribute("manga");
        return "redirect:/mangas";
    }
}
