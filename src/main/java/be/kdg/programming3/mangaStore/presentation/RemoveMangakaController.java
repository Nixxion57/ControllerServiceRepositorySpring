package be.kdg.programming3.mangaStore.presentation;

import be.kdg.programming3.mangaStore.presentation.viewModel.MangakaViewModel;
import be.kdg.programming3.mangaStore.service.MangakaService;
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
@RequestMapping("/removeMangaka")
@Profile("web")
public class RemoveMangakaController {
    private MangakaService mangakaService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public RemoveMangakaController(MangakaService mangakaService) {
        this.mangakaService = mangakaService;
    }

    @GetMapping
    public String showRemoveMangakaForm(Model model) {
        model.addAttribute("mangakaViewModel", new MangakaViewModel());
        return "removeMangaka";
    }

    @PostMapping
    public String removeMangaka(int id, HttpSession httpSession){
        logger.debug("Deleting mangaka...");
        mangakaService.deleteMangaka(id);
        httpSession.removeAttribute("mangaka");
        return "redirect:/mangakas";
    }
}
