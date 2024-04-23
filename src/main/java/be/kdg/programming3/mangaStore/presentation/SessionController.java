package be.kdg.programming3.mangaStore.presentation;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@Profile("web")
@RequestMapping("/session")
public class SessionController {
    public Set<String> attributes(HttpSession session) {
        TreeSet<String> attributes = new TreeSet<>();
        Enumeration<String> enumeration = session.getAttributeNames();

        while (enumeration.hasMoreElements()) {
            attributes.add(enumeration.nextElement());
        }
        return attributes;
    }

    @GetMapping
    String showHistory(Model model, HttpSession session) {
        Set<String> sessions = attributes(session);
        List<String> urls = new ArrayList<>();
        Iterator<String> itr = sessions.iterator();

        model.addAttribute("sessions", sessions);

        while (itr.hasNext()) {
            String attName = itr.next();
            Object attValue = session.getAttribute(attName);
            if (attValue instanceof String){
                urls.add((String) attValue);
            }
            else {
                urls.add("Non-string attribute: " + attName);
            }
//            urls.add((String) session.getAttribute(itr.next()));
        }

        model.addAttribute("url", urls);
        return "/session";
    }
}
