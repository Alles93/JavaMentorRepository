package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.CarService;
import web.service.CarServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarsController {

    CarService carService = new CarServiceImpl();

    @GetMapping(value = "/cars")
    public String printCars(@RequestParam(value = "count", required = false) Integer x, Model model) {
        if (x == null) {
            model.addAttribute("cars", carService.listCars(5));
        } else {
            model.addAttribute("cars", carService.listCars(x));
        }
        List<String> messages = new ArrayList<>();
        messages.add("Hello! It's cars!");
        messages.add("Text for cars");
        messages.add("Cars");
        model.addAttribute("messages", messages);
        return "cars";
    }
}
