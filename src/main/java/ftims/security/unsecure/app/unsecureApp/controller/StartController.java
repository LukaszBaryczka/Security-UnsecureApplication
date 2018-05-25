package ftims.security.unsecure.app.unsecureApp.controller;

import ftims.security.unsecure.app.unsecureApp.model.AuthForm;
import ftims.security.unsecure.app.unsecureApp.model.Comment;
import ftims.security.unsecure.app.unsecureApp.model.CommentForm;
import ftims.security.unsecure.app.unsecureApp.service.CommentService;
import ftims.security.unsecure.app.unsecureApp.service.SessionComponent;
import ftims.security.unsecure.app.unsecureApp.service.UserService;
import org.apache.commons.lang3.BooleanUtils;
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

    @Autowired
    CommentService commentService;

    @GetMapping("/start")
    public String hello(Model model) {
        if(sessionComponent != null && BooleanUtils.isTrue(sessionComponent.getLogged())) {
            model.addAttribute("name", sessionComponent.getUser().getUsername());
            model.addAttribute("commentForm", new CommentForm());
            model.addAttribute("comments", commentService.findAllComments());
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

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public String setNewComment(@Valid @ModelAttribute(name = "commentForm") CommentForm commentForm, BindingResult bindingResult, Model model){

        Comment comment = new Comment();
        comment.setContent(commentForm.getContent());
        comment.setUsername(sessionComponent.getUser().getUsername());
        commentService.setNewComment(comment);
        return "redirect:/start";
    }

    @GetMapping("/")
    public String redirect(Model model) {
        return "redirect:/login";
    }
}
