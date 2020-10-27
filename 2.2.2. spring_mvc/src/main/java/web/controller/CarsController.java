package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Cars {
    @GetMapping(value = "cars")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello! It's cars!");
        messages.add("Text for cars");
        messages.add("Cars");
        model.addAttribute("messages", messages);
        return "index";
    }
}
