package web.Controllers;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.Service.UserService;


import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String welcome(Model model){
        List<String> message = new ArrayList<>();
        message.add("Hello!");
        message.add("If you want to see all users then just click :");
        model.addAttribute("message",message);
     return "index";
    }


    @GetMapping("/users")
    public String findAll(Model model){
        model.addAttribute("users", userService.allUser());
        return "user";
    }
    @GetMapping("/users/new")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "addUser";
    }

    @PostMapping("/users")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "editUser";
    }

    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable int id, @ModelAttribute("user") User user, Model model) {
        User createdUser = userService.getUser(id);
        createdUser.setId(id);
        createdUser.setName(user.getName());
        createdUser.setLastName(user.getLastName());
        createdUser.setAge(user.getAge());
        userService.updateUser(createdUser);

        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String deleteUser(@PathVariable int id){
        userService.delete(id);
        return "redirect:/users";
    }

}
