package Project.Controller;

import Project.Model.Url;
import Project.Service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    private UrlService urlService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String main(ModelMap modelMap, HttpSession httpSession) {

        modelMap.addAttribute("Url", new Url());

        return "Main/index";
    }

    @RequestMapping( method = RequestMethod.POST , value = "/addUrl")
    public String addUrl(@ModelAttribute("Url") @Valid Url message, BindingResult result, ModelMap modelMap, HttpSession httpSession) {

        Url url = (Url) httpSession.getAttribute("Url");

        message.validate(message, result);
        if (result.hasErrors()) {
            return "Main/index";
        }

        urlService.add(url);

        return "redirect:/";
    }
}
