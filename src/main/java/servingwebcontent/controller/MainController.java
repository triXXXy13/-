package servingwebcontent.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import servingwebcontent.domain.Message;
import servingwebcontent.domain.User;
import servingwebcontent.repos.MessageRep;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRep messageRep;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages = messageRep.findAll();
        if (filter != null && !filter.isEmpty()) {
            messages = messageRep.findByTag(filter);
        } else {
            messages = messageRep.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag, user);

        messageRep.save(message);

        Iterable<Message> messages = messageRep.findAll();

        model.put("messages", messages);

        return "main";
    }


}
