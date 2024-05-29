package com.mycompany.jeel.user;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("ListUsers", listUsers);
        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes rs) {
        service.save(user);
        rs.addFlashAttribute("message", "User saved successfully");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            return "user_form";
        } catch (Exception e) {
            ra.addFlashAttribute("message", "The user could not be found");
            return "redirect:/users";
        }
    }
        @GetMapping("/users/delete/{id}")
        public String deleteUser (@PathVariable("id") Integer id, RedirectAttributes ra){
            try {
                service.delete(id);
                ra.addFlashAttribute("message", "User deleted successfully");
            } catch (Exception e) {
                ra.addFlashAttribute("message", "The user could not be found");
            } catch (UserNotFoundException e) {
                throw new RuntimeException(e);
            }
            return "redirect:/users";
        }
    }
