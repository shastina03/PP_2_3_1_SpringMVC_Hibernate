package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.entity.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    @Transactional
    public String showAllUsers(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "users";
    }

    @GetMapping("/addNewUser")
    @Transactional
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/createNewUser")
    @Transactional
    public String createNewUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/editUser")
    @Transactional
    public String showEditUserForm(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "updateUser";
    }

    @PostMapping("/updateUser")
    @Transactional
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/deleteUser")
    @Transactional
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
