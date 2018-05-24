package ftims.security.unsecure.app.unsecureApp.controller;

import ftims.security.unsecure.app.unsecureApp.model.AuthForm;
import ftims.security.unsecure.app.unsecureApp.service.SessionComponent;
import ftims.security.unsecure.app.unsecureApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;

/**
 * Created by bryka on 24.05.18.
 */
@Controller
public class StartController {

    @Autowired
    UserService userService;

    @Autowired
    SessionComponent sessionComponent;

    @GetMapping("/start")
    public String hello(Model model) {
        if(sessionComponent != null && sessionComponent.getLogged()) {
            model.addAttribute("name", sessionComponent.getUser().getUsername());

            return "start";
        }
        return "redirect:/login";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String postLogin(@Valid @ModelAttribute(name = "loginForm") AuthForm authForm, BindingResult bindingResult, Model model) {
        Boolean authorized = userService.authUser(authForm.getUsername(), authForm.getPassword());
        if(authorized) {
            return "redirect:/start";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(Model model) {
        model.addAttribute("loginForm", new AuthForm());
        return "login";
    }

    @GetMapping("/")
    public String redirect(Model model) {
        return "redirect:/login";
    }
}
