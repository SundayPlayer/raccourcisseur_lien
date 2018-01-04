package Project.Controller;

import Project.Model.Url;
import Project.Model.User;
import Project.Service.UrlService;
import Project.Service.UserService;
import Project.Utils.Encode;
import Project.Utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    private UrlService urlService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String main(ModelMap modelMap, HttpSession httpSession) {

        modelMap.addAttribute("Url", new Url());
        modelMap.addAttribute( "User", new User());

        return "Main/index";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addUrl")
    public String addUrl(@ModelAttribute("Url") @Valid Url url, BindingResult result, ModelMap modelMap, HttpSession httpSession) {

        url.validate(url, result);
        if (result.hasErrors()) {
            return "Main/index";
        }

        RandomString randGen = new RandomString();

        String tinyUrl = randGen.nextString();

        url.setUrlCourte(tinyUrl);

        if (httpSession.getAttribute("userId") != null)
        {
            url.setUser(userService.getById((Long) httpSession.getAttribute("userId")));
        }

        urlService.add(url);

        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addUser")
    public String addUser(@ModelAttribute("User") @Valid User user, BindingResult result, ModelMap modelMap, HttpSession httpSession) {

        user.setPassword(Encode.encodeSHA512(user.getPassword(),null));
        userService.add(user);

        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/connect")
    public String connect(@ModelAttribute("User") @Valid User user, BindingResult result, ModelMap modelMap, HttpSession httpSession) {

        if (httpSession.getAttribute("userId") != null)
            return "redirect:/";

        User savedUser = userService.getByMail(user.getEmail());

        String toTestHashPass = Encode.encodeSHA512(user.getPassword(),null);
        String hashPass = savedUser.getPassword();

        if (toTestHashPass.equals(hashPass))
            httpSession.setAttribute("userId", savedUser.getId());

        return "redirect:/";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {}

    @RequestMapping(method = RequestMethod.GET, value = "/{tinyCode:^[a-zA-Z0-9_]{8}$}")
    public String redirectUrl (@PathVariable String tinyCode) {

        Url url = null;
        try {
            url = urlService.getByTinyUrl(tinyCode);
        } catch (NoResultException e) {
            throw new ResourceNotFoundException();
        }

        return "redirect:" + url.getUrl();
    }
}
