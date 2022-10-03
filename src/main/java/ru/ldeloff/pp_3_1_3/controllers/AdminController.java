package ru.ldeloff.pp_3_1_3.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ldeloff.pp_3_1_3.models.User;
import ru.ldeloff.pp_3_1_3.service.RoleService;
import ru.ldeloff.pp_3_1_3.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "")
    public String AdminPage(Principal principal, Model model) {
        model.addAttribute("person", userService.getByEmail(principal.getName()));
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("newUser", new User());
        model.addAttribute("userUp", new User());
        return "panelAdmin";
    }

    @PostMapping(value = "/add")
    public String add(@RequestParam(name = "firstName") String firstName,
                      @RequestParam(name = "lastName") String lastName,
                      @RequestParam(name = "age") int age,
                      @RequestParam(name = "email") String email,
                      @RequestParam(name = "password") String password,
                      @RequestParam(name = "roleName") String roleName) {
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setPassword(password);
        user.addRole(roleService.getByName(roleName));
        userService.add(user);
        return "redirect:/admin/";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") long id) {
        userService.delete(id);
        return "redirect:/admin/";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(@RequestParam(name = "id") long id,
                             @RequestParam(name = "firstName") String firstName,
                             @RequestParam(name = "lastName") String lastName,
                             @RequestParam(name = "age") int age,
                             @RequestParam(name = "email") String email,
                             @RequestParam(name = "password") String password,
                             @RequestParam(name = "roleName") String roleName) {
        User user = userService.getById(id);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setPassword(password);
        user.addRole(roleService.getByName(roleName));
        userService.edit(user);
        return "redirect:/admin/";
    }
}
