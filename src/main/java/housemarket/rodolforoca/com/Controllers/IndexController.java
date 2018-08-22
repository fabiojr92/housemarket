package housemarket.rodolforoca.com.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @RequestMapping("/index")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="Wooorld!!!") String name) {
        model.addAttribute("name", name);
        return "index";
    }
}
